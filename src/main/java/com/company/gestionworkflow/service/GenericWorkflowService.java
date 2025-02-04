package com.company.gestionworkflow.service;

import com.company.gestionworkflow.entity.Event;
import com.company.gestionworkflow.entity.State;
import com.company.gestionworkflow.entity.Transition;
import com.company.gestionworkflow.entity.Workflow;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GenericWorkflowService<T extends Workflow> implements WorkflowService<T> {

    @Autowired
    private StateMachine<String, String> stateMachine;

    @Autowired
    private TransitionService transitionService;

    @Autowired
    StateService stateService;

    @Autowired
    private DataManager dataManager;

    @Override
    public List<Transition> getTransitionsForCurrentState(T entity,String coodeWorkflow) {
        // Récupère l'état actuel de l'entité
        State currentState = entity.getStatus();
        // Retourne les transitions possibles pour cet état
        return transitionService.getTransitionfromStateAndWorkflow(currentState,coodeWorkflow);
    }

    @Override
    public void executeTransition(T entity, Transition transition) throws Exception {
        Event event = transition.getEvent();
        List<String> roles = Arrays.asList("ROLE_USER");
        stateMachine.getExtendedState().getVariables().put("userRoles", roles);
        stateMachine.getExtendedState().getVariables().put("codeWorkflow", entity.getCodeWorkflow());
        stateMachine.stop();
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(accessor -> accessor.resetStateMachine(
                        new DefaultStateMachineContext<>(entity.getStatus().getName(), null, null, null)));
        stateMachine.start();
        // Envoie l'événement à la machine d'état
        boolean accepted = stateMachine.sendEvent(event.getName());

        // Met à jour l'état de l'entité en fonction de l'état actuel de la machine

        if (accepted) {

            State newState = stateService.getStateByName(stateMachine.getState().getId()); // Récupérer le nouvel état
            entity.setStatus(newState);

            // Sauvegarder la commande avec son nouvel état
            saveEntity(entity);
        } else {
            throw new Exception("Échec du traitement de l'événement pour : " + entity.getId());
        }

        // Sauvegarde l'entité après avoir effectué la transition

    }

    @Override
    public void saveEntity(T entity) {

        dataManager.save(entity);
    }
}


package com.company.gestionworkflow.view;

import com.company.gestionworkflow.entity.Event;
import com.company.gestionworkflow.entity.State;
import com.company.gestionworkflow.entity.Transition;
import com.company.gestionworkflow.entity.Workflow;
import com.company.gestionworkflow.service.TransitionService;
import com.company.gestionworkflow.service.WorkflowService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@ViewController(id = "WorkflowDetails")
public abstract  class WorkflowDetails<T extends Workflow> extends StandardDetailView<T> {
    @Autowired
    private UiComponents uiComponents;
// Layout pour ajouter les boutons

    @Autowired
    private TransitionService transitionService;  // Repository pour récupérer les transitions

    @Autowired
    private WorkflowService<T> workflowService;

    @ViewComponent
    private HorizontalLayout detailActions;


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        //super.onBeforeShow(event);

        // Récupère l'état actuel de l'entité
        State currentState = getEditedEntity().getStatus();
        String codeWorkflow = getEditedEntity().getCodeWorkflow();


             // Récupère les transitions possibles pour cet état
             List<Transition> transitions = getTransitionsForCurrentState(currentState,codeWorkflow);

             // Ajoute dynamiquement un bouton pour chaque transition
             ///   detailActions.removeAll();
         if (transitions != null) {
             for (Transition transition : transitions) {
                 Button transitionButton = createTransitionButton(transition);
                 detailActions.add(transitionButton);
             }
         }

    }

    private List<Transition> getTransitionsForCurrentState(State currentState,String codeWorkflow) {
        // Récupère les transitions possibles pour l'état courant
        return transitionService.getTransitionfromStateAndWorkflow(currentState,codeWorkflow);
    }

    private Button createTransitionButton(Transition transition) {
        Button button = uiComponents.create(Button.class);
        button.setText(transition.getEvent().getName());  // Affiche le nom de l'événement comme le texte du bouton
        button.addClickListener(clickEvent -> {
            try {
                handleTransition(transition);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    private void handleTransition(Transition transition) throws Exception {
        Event event = transition.getEvent();
        T entity = getEditedEntity();

        // Utilise le service générique pour exécuter la transition
        workflowService.executeTransition(entity, transition);

        closeWithSave();
    }
}

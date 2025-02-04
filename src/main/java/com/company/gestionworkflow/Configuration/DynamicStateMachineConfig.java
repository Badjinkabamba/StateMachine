package com.company.gestionworkflow.Configuration;


import com.company.gestionworkflow.entity.State;
import com.company.gestionworkflow.entity.Transition;
import com.company.gestionworkflow.service.StateService;
import com.company.gestionworkflow.service.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableStateMachineFactory
public class DynamicStateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Autowired
    private StateService stateService;

    @Autowired
    private TransitionService transitionService;

    @Autowired
    private RoleGuard roleGuard;

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
        List<State> dbStates = stateService.getStates(); // Récupérer tous les états de la base de données (en fonction du codeWorkflow si nécessaire)
        Set<String> stateNames = dbStates.stream()
                .map(State::getName) // Récupérer les noms des états
                .collect(Collectors.toSet());
        states.withStates()
                .initial("BROUILLON") // État initial (si nécessaire)
                .states(stateNames);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        List<Transition> dbTransitions =transitionService.getTransitions(); // Récupérer toutes les transitions
        if (dbTransitions == null || dbTransitions.isEmpty()) {
            throw new IllegalStateException("Aucune transition trouvée en base !");
        }
        for (Transition transition : dbTransitions) {
            transitions
                    .withExternal()
                    .source(transition.getSourceState().getName()) // État source
                    .target(transition.getTargetState().getName()) // État cible
                    .event(transition.getEvent().getName())// Événement déclencheur
                    .guard(roleGuard);
        }

    }

    @Bean
    public StateMachine<String, String> stateMachine(StateMachineFactory<String, String> factory) {
        return factory.getStateMachine();  // Utiliser la factory pour obtenir la machine d'état
    }


}
package com.company.gestionworkflow.service;

import com.company.gestionworkflow.entity.State;
import com.company.gestionworkflow.entity.Transition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TransitionService {
    List<Transition> getTransitions();
    Optional<String> getRequiredRole(String sourceStateName, String targetStateName, String eventName, String codeWorkflow);

    List<Transition> getTransitionfromStateAndWorkflow(State state, String codeWorkflow);
}

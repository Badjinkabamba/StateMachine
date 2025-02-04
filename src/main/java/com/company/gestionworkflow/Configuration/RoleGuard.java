package com.company.gestionworkflow.Configuration;

import com.company.gestionworkflow.service.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RoleGuard implements Guard<String, String> {

    @Autowired
   private TransitionService transitionService;

    @Override
    public boolean evaluate(StateContext<String, String> context) {
        String source = context.getSource().getId();
        String target = context.getTarget().getId();
        String event = context.getEvent();
        String codeWorkflow = (String) context.getExtendedState().getVariables().get("codeWorkflow");

        Optional<String> requiredRoles = transitionService.getRequiredRole( source,  target, event, codeWorkflow);
        if (requiredRoles.isPresent()) {
            List<String> requiredRoleList = Arrays.asList(requiredRoles.get().split(","));
            List<String> userRoles = (List<String>) context.getExtendedState().getVariables().get("userRoles");
            return userRoles.stream().anyMatch(requiredRoleList::contains);
        }

        return false;
    }
}

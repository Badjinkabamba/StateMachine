package com.company.gestionworkflow.service;

import com.company.gestionworkflow.entity.Transition;
import com.company.gestionworkflow.entity.Workflow;

import java.util.List;

public interface WorkflowService<T extends Workflow> {

    // Retourne les transitions possibles pour l'état actuel de l'entité
    List<Transition> getTransitionsForCurrentState(T entity,String codeWorkflow);

    // Exécute la transition spécifiée pour l'entité
    void executeTransition(T entity, Transition transition) throws Exception;

    // Sauvegarde l'entité après la mise à jour de l'état
    void saveEntity(T entity);
}

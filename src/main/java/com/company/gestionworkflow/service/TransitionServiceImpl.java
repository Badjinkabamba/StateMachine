package com.company.gestionworkflow.service;

import com.company.gestionworkflow.entity.State;
import com.company.gestionworkflow.entity.Transition;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.security.SystemAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TransitionServiceImpl implements TransitionService {

    private static final Logger log = LoggerFactory.getLogger(TransitionServiceImpl.class);
    @Autowired
    private SystemAuthenticator systemAuthenticator;

    @Autowired
    private FetchPlans fetchPlans;

    private final DataManager dataManager;

    public TransitionServiceImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public List<Transition> getTransitions() {
        List<Transition> transitions = new ArrayList<>();
        try {

            transitions = systemAuthenticator.withSystem(() ->
                dataManager.load(Transition.class)
                        .query("select ts from Transition ts")
                        .fetchPlan(getFetchPlan())
                        .list()
            );

        } catch (Exception e) {
            log.error("Error", e);
            return null;
        }

        return transitions;
    }

    @Override
    public Optional<String> getRequiredRole(String sourceStateName, String targetStateName, String eventName, String codeWorkflow) {
        return  dataManager.loadValue(
                        "select t.required_role from Transition t " +
                                "where t.sourceState.name = :sourceStateName " +
                                "and t.targetState.name = :targetStateName " +
                                "and t.event.name = :eventName " +
                                "and t.codeWorkflow = :codeWorkflow", String.class)
                .parameter("sourceStateName", sourceStateName)
                .parameter("targetStateName", targetStateName)
                .parameter("eventName", eventName)
                .parameter("codeWorkflow", codeWorkflow)
                .optional();
    }

    @Override
    public List<Transition> getTransitionfromStateAndWorkflow(State state, String codeWorkflow) {
        List<Transition> transitions = new ArrayList<>();
        try {

            transitions = systemAuthenticator.withSystem(() ->
                    dataManager.load(Transition.class)
                            .query("select ts from Transition ts where ts.codeWorkflow=:codeWorkflow and ts.sourceState.id=:state")
                            .parameter("codeWorkflow", codeWorkflow)
                            .parameter("state", state.getId())
                            .fetchPlan(getFetchPlan())
                            .list()
            );

        } catch (Exception e) {
            log.error("Error", e);
            return null;
        }

        return transitions;
    }
    private FetchPlan getFetchPlan() {

        return  fetchPlans.builder(Transition.class)
                .addFetchPlan(FetchPlan.BASE)
                .add("sourceState")
                .add("targetState")
                .add("event")
                .build();
    }
}

package com.company.gestionworkflow.service;

import com.company.gestionworkflow.entity.State;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    private static final Logger log = LoggerFactory.getLogger(StateServiceImpl.class);
    @Autowired
    private SystemAuthenticator systemAuthenticator;
    private final DataManager dataManager;

    public StateServiceImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public List<State> getStates() {
        List<State> States = new ArrayList<>();
        try {
            States = systemAuthenticator.withSystem(() ->
                    dataManager.load(State.class)
                            .query("select ts from State ts")
                            .list()
            );

        } catch (Exception e) {
            log.error("Error", e);
            return null;
        }

        return States;
    }

    @Override
    public State getStateByName(String name) {
        return dataManager.load(State.class)
                .query("select s from State s where s.name=:name ")
                .parameter("name", name)
                .one();

    }
}

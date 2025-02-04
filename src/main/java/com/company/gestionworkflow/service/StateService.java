package com.company.gestionworkflow.service;

import java.util.List;
import com.company.gestionworkflow.entity.State;
import org.springframework.stereotype.Component;


public interface StateService {


    List<State> getStates();

    State getStateByName(String name);
}

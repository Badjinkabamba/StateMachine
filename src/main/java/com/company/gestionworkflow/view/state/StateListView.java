package com.company.gestionworkflow.view.state;

import com.company.gestionworkflow.entity.State;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "states", layout = MainView.class)
@ViewController(id = "State.list")
@ViewDescriptor(path = "state-list-view.xml")
@LookupComponent("statesDataGrid")
@DialogMode(width = "64em")
public class StateListView extends StandardListView<State> {
}
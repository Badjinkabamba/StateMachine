package com.company.gestionworkflow.view.state;

import com.company.gestionworkflow.entity.State;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "states/:id", layout = MainView.class)
@ViewController(id = "State.detail")
@ViewDescriptor(path = "state-detail-view.xml")
@EditedEntityContainer("stateDc")
public class StateDetailView extends StandardDetailView<State> {
}
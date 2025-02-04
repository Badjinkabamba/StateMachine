package com.company.gestionworkflow.view.transition;

import com.company.gestionworkflow.entity.Transition;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "transitions", layout = MainView.class)
@ViewController(id = "Transition.list")
@ViewDescriptor(path = "transition-list-view.xml")
@LookupComponent("transitionsDataGrid")
@DialogMode(width = "64em")
public class TransitionListView extends StandardListView<Transition> {
}
package com.company.gestionworkflow.view.transition;

import com.company.gestionworkflow.entity.Transition;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "transitions/:id", layout = MainView.class)
@ViewController(id = "Transition.detail")
@ViewDescriptor(path = "transition-detail-view.xml")
@EditedEntityContainer("transitionDc")
public class TransitionDetailView extends StandardDetailView<Transition> {
}
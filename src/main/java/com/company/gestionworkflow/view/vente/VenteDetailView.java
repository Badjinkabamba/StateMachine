package com.company.gestionworkflow.view.vente;

import com.company.gestionworkflow.entity.Orders;
import com.company.gestionworkflow.entity.Vente;
import com.company.gestionworkflow.view.WorkflowDetails;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "ventes/:id", layout = MainView.class)
@ViewController(id = "Vente.detail")
@ViewDescriptor(path = "vente-detail-view.xml")
@EditedEntityContainer("venteDc")
public class VenteDetailView extends WorkflowDetails<Vente> {
}
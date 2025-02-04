package com.company.gestionworkflow.view.vente;

import com.company.gestionworkflow.entity.Vente;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "ventes", layout = MainView.class)
@ViewController(id = "Vente.list")
@ViewDescriptor(path = "vente-list-view.xml")
@LookupComponent("ventesDataGrid")
@DialogMode(width = "64em")
public class VenteListView extends StandardListView<Vente> {
}
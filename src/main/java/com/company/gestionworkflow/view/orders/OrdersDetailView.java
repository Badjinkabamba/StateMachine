package com.company.gestionworkflow.view.orders;

import com.company.gestionworkflow.entity.Orders;
import com.company.gestionworkflow.view.WorkflowDetails;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "orderses/:id", layout = MainView.class)
@ViewController(id = "Orders.detail")
@ViewDescriptor(path = "orders-detail-view.xml")
@EditedEntityContainer("ordersDc")
public class OrdersDetailView extends WorkflowDetails<Orders>/*StandardDetailView<Orders>*/ {
}
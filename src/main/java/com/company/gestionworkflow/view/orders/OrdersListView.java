package com.company.gestionworkflow.view.orders;

import com.company.gestionworkflow.entity.Orders;
import com.company.gestionworkflow.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "orderses", layout = MainView.class)
@ViewController(id = "Orders.list")
@ViewDescriptor(path = "orders-list-view.xml")
@LookupComponent("ordersesDataGrid")
@DialogMode(width = "64em")
public class OrdersListView extends StandardListView<Orders> {
}
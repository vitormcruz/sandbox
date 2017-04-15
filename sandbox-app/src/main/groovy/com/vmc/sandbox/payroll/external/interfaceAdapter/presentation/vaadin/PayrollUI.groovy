package com.vmc.sandbox.payroll.external.interfaceAdapter.presentation.vaadin

import com.vaadin.annotations.Push
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@Title("Sandbox")
@Theme("valo")
@Push
public class PayrollUI extends UI {

    protected void init(VaadinRequest request) {
        this.setPollInterval(200)
        setContent(new PayrollPage())
    }
}

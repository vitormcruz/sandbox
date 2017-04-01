package com.vmc.sandbox.heavyValidation.external.interfaceAdapter.presentation.vaadin

import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@Title("HeavyValidation")
@Theme("valo")
//@Push
public class HeavyValidationUI extends UI {

    protected void init(VaadinRequest request) {
        this.setPollInterval(200)
        setContent(new HeavyValidationPage())
    }
}

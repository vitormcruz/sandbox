package com.vmc.sandbox.payroll.external.presentation.vaadin

import com.vaadin.ui.AbsoluteLayout
import com.vaadin.ui.Button
import com.vaadin.ui.Notification

class PayrollPage extends AbsoluteLayout {

    PayrollPage() {
        def button = new Button("Save")
        button.addClickListener({Notification.show("Alert", "To be implemented",
                                 Notification.Type.ERROR_MESSAGE)})
        this.addComponent(button)
    }
}

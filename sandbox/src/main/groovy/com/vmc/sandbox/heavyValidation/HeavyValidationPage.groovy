package com.vmc.sandbox.heavyValidation
import com.vaadin.data.Property
import com.vaadin.data.util.ObjectProperty
import com.vaadin.ui.Button
import com.vaadin.ui.CssLayout
import com.vaadin.ui.ProgressBar
import com.vmc.sandbox.allapps.external.config.SandboxApplication

/**
 */
class HeavyValidationPage extends CssLayout{
    private ProgressBar progressBar
    private Property<Float> progressBarDataSource
    private Button button
    private AsyncHeavyValidation asyncHeavyValidation = SandboxApplication.asyncHeavyValidation

    HeavyValidationPage() {
        button = new Button("Validate")
        this.button.setDisableOnClick(true)

        progressBarDataSource = new ObjectProperty<Float>(0 as Float)
        this.addComponent(this.button)

        this.button.addClickListener({

            progressBar = new ProgressBar(this.progressBarDataSource)
            progressBar.setEnabled(true)
            addComponent(this.progressBar)


            asyncHeavyValidation.doValidation([ notifyProgress: { progress ->

                //            new HeavyValidationClass().validate()
                UI.access({
                    progressBarDataSource.setValue(progress as Float)
                })

                if (progress == 1){
                    sleep(800)
                    UI.access({
                        this.removeComponent(progressBar)
                        button.setEnabled(true)
                    })
                }

            }] as AsyncHeavyValidation.NotifyProgress)


            progressBarDataSource.setValue(0 as Float)
            button.setEnabled(true)

        })

    }


}

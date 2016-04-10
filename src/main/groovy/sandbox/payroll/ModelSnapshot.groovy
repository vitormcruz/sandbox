package sandbox.payroll

interface ModelSnapshot {

    /**
     * Save all the changes made in objects of the model
     */
    void save();

    void add(modelObject)
}

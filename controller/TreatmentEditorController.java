package com.northamptonmedicalclinic.app.controller;

import com.northamptonmedicalclinic.app.models.Treatment;
import com.northamptonmedicalclinic.app.models.TreatmentEditorModel;
import com.northamptonmedicalclinic.app.models.TreatmentSelectionModel;
import com.northamptonmedicalclinic.app.views.SelectionView;
import com.northamptonmedicalclinic.app.views.TreatmentEditorView;

public class TreatmentEditorController {
	private TreatmentEditorModel model;
	private TreatmentEditorView view;

	public TreatmentEditorController(TreatmentEditorModel model) {
		this.model = model;
	}
	
	/**
	 * Set a reference to TreatmentEditorView to dispatch update event.
	 */
	public void setView(TreatmentEditorView view) {
		this.view = view;
	}
	
	/**
	 * Return to patient selection view.
	 */
	public void onCancel() {
		returnToSelection();
	}
	
	/**
	 * Validate and save the treatment for the currently selected patient.
	 */
	public void onSave(Treatment treatment) {
		if(treatment.getTreatment() == null || treatment.getTreatment().length() == 0) {
			model.setErrorMessage("Treatment cannot be empty");
			view.update();
		} else {
			model.setErrorMessage(null);
			model.save(treatment);
			model.saveToDisk();
			returnToSelection();
		}
	}
	
	/**
	 * Return to patient selection view.
	 */
	private void returnToSelection() {
		final TreatmentSelectionModel tModel = 
				new TreatmentSelectionModel("Appointments", "Add Treatment", "Select Appointment");
		tModel.setUser(model.getUser());
		final NewTreatmentSelectionController controller = new NewTreatmentSelectionController(tModel);
		final SelectionView sView = new SelectionView(tModel, controller);
		controller.setView(sView);
		sView.setVisible(true);
		view.dispose();
	}
	

}

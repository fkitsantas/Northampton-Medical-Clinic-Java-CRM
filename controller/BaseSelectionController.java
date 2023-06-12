package com.northamptonmedicalclinic.app.controller;

import com.northamptonmedicalclinic.app.views.SelectionView;

/**
 * Base class for selection controllers. It is used by the SelectionView
 * to pass on user events.
 */
public abstract class BaseSelectionController {

	/**
	 * Store the currently selected item's index in our model.
	 */
	abstract public void onSelected(int index);
	
	/**
	 * Handle the back/cancel button event.
	 */
	abstract public void onCancel();
	
	/**
	 * Handle the action button once the user selects an entry from the Combobox.
	 */
	abstract public void onActionPressed();
	
	/**
	 * Set the SelectionView reference.
	 */
	abstract public void setView(SelectionView view);
}

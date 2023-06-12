package com.northamptonmedicalclinic.app.models;

import java.util.ArrayList;


/**
 * This is a generic class used by the SelectionView.
 * It can can contain <T>
 */
public class SelectionModel<T> {
	
	/**
	 * Reference to the currently logged in user.
	 */
	private User user;
	
	
	private String title;				//Title to be shown by the SelectionView
	private String actionButtonLabel;	//Text to be displayed by the SelectionView for the selection confirmation button next to the combo box.
	private String selectionLablel;		//Label to be displayed describing the choices. 
	private String status; 				//Status text for any validation/error message to be displayed by the view.
	
	//This will hold the display values in the Combo Box.
	private ArrayList<String> viewList;
	
	/**
	 * This list contains the details for each item contained in the "viewList".
	 */
	ArrayList<T> list;
	
	/**
	 * Stores the index of the currently selected item.
	 */
	private int selectionIndex;
	
	
	public SelectionModel(String title, String actionButtonLabel, String selectionLabel, User user){
		this.user = user;
		this.title = title;
		this.actionButtonLabel = actionButtonLabel;
		this.selectionLablel = selectionLabel;
	}
	
	
	/**
	 * Get the currently logged in user.
	 */
	public User getUser() {
		return user;
	}

	
	/**
	 * Delete the currently selected item. This method does not save the list to disk.
	 * Saving to disk is handled separately by the controller.
	 */
	public void deleteItem() {
		status = "Deleted: " + viewList.get(selectionIndex);
		list.remove(selectionIndex);
		viewList.remove(selectionIndex);
	}

	/**
	 * Get the details for the currently selected item. 
	 */
	public T getSelectedItem() {
		return list.get(selectionIndex);
	}


	/**
	 * @return the list
	 */
	public ArrayList<T> getList() {
		return list;
	}


	/**
	 * @return the selectionIndex
	 */
	public int getSelectionIndex() {
		return selectionIndex;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @return the actionButtonLabel
	 */
	public String getActionButtonLabel() {
		return actionButtonLabel;
	}



	/**
	 * @return the selectionLablel
	 */
	public String getSelectionLablel() {
		return selectionLablel;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @return the viewList
	 */
	public ArrayList<String> getViewList() {
		return viewList;
	}




	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<T> list) {
		this.list = list;
	}




	/**
	 * Saves the index of the currently selected item.
	 */
	public void setSelectionIndex(int selectionIndex) {
		this.selectionIndex = selectionIndex;
	}






	/**
	 * Sets the status message which in turn is displayed by the view.
	 */
	public void setStatus(String status) {
		this.status = status;
	}




	/**
	 * Sets the choices that will be displayed by the view's combo box.
	 */
	public void setViewList(ArrayList<String> viewList) {
		this.viewList = viewList;
	}
	
	
	
}

package org.geogebra.web.shared.components;

import java.util.ArrayList;

import org.geogebra.keyboard.base.Button;

/**
 * object holding the settings of the dialog
 */
public class DialogData {

	private String titleTransKey;
	private ArrayList<String> buttonTransKeys;

	/**
	 * dialog settings constructor
	 * @param titleTransKey - ggb trans key for the dialog title
	 * @param buttonTransKeys - list of button text trans keys
	 */
	public DialogData(String titleTransKey, ArrayList<String> buttonTransKeys) {
		setTitleTransKey(titleTransKey);
		setButtonTransKeys(buttonTransKeys);
	}

	/**
	 * @return trans key of the dialog title
	 */
	public String getTitleTransKey() {
		return titleTransKey;
	}

	/**
	 * @param titleTransKey - ggb trans key of the dialog title
	 */
	public void setTitleTransKey(String titleTransKey) {
		this.titleTransKey = titleTransKey;
	}

	/**
	 * @return list of button trans keys from
	 */
	public ArrayList<String> getButtonTransKeys() {
		return buttonTransKeys;
	}

	/**
	 * @param buttonTransKeys - ggb trans keys of the dialog buttons
	 */
	public void setButtonTransKeys(ArrayList<String> buttonTransKeys) {
		this.buttonTransKeys = buttonTransKeys;
	}
}

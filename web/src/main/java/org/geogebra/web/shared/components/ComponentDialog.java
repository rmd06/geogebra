package org.geogebra.web.shared.components;

import java.util.ArrayList;

import org.geogebra.common.gui.SetLabels;
import org.geogebra.web.html5.gui.GPopupPanel;
import org.geogebra.web.html5.gui.view.button.StandardButton;
import org.geogebra.web.html5.main.AppW;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

/**
 * Base dialog material design component
 */
public class ComponentDialog extends GPopupPanel implements SetLabels {
	private Label title;
	private FlowPanel dialogContent;

	public ComponentDialog(AppW app, DialogData dialogData, boolean autoHide,
						   boolean hasScrim) {
		super(autoHide, true, app.getPanel(), app);
		setGlassEnabled(hasScrim);
		this.setStyleName("dialogComponent");
		buildDialog(dialogData);
	}

	private void  buildDialog(DialogData dialogData) {
		FlowPanel dialogMainPanel = new FlowPanel();
		dialogMainPanel.addStyleName("dialogMainPanel");

		addTitleOfDialog(dialogMainPanel, dialogData.getTitleTransKey());
		createEmptyDialogContent(dialogMainPanel);
		addButtonsOfDialog(dialogMainPanel, dialogData.getButtonTransKeys());

		this.add(dialogMainPanel);
	}

	private void addTitleOfDialog(FlowPanel dialogMainPanel, String titleTransKey) {
		title = new Label(getApplication().getLocalization().getMenu(titleTransKey));
		title.setStyleName("dialogTitle");
		dialogMainPanel.add(title);
	}

	private void createEmptyDialogContent(FlowPanel dialogMainPanel) {
		dialogContent = new FlowPanel();
		dialogContent.addStyleName("dialogContent");
		dialogMainPanel.add(dialogContent);
	}

	private void addButtonsOfDialog(FlowPanel dialogMainPanel, ArrayList<String> buttonTransKeys) {
		FlowPanel dialogButtonPanel = new FlowPanel();
		dialogButtonPanel.setStyleName("dialogBtnPanel");
		int i = 0;
		for (String btnTransKey : buttonTransKeys) {
			i++;
			StandardButton button = new StandardButton(btnTransKey, getApplication());
			button.setStyleName(i==1 ? "materialTextButton" : "materialContainedButton");

			dialogButtonPanel.add(button);
		}
		dialogMainPanel.add(dialogButtonPanel);
	}

	public void firstBtnAction() {
		super.hide();
	}

	public void secondBtnAction() {
		super.hide();
	}

	public void thirdBtnAction() {
		super.hide();
	}

	public void addDialogContent(IsWidget content) {
		dialogContent.add(content);
	}

	@Override
	public void show() {
		super.show();
		super.center();
	}

	@Override
	public void setLabels() {

	}
}

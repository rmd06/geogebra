package org.geogebra.web.shared.components;

import java.util.ArrayList;

import org.geogebra.common.gui.SetLabels;
import org.geogebra.common.main.Localization;
import org.geogebra.common.util.debug.Log;
import org.geogebra.web.html5.gui.FastClickHandler;
import org.geogebra.web.html5.gui.GPopupPanel;
import org.geogebra.web.html5.gui.view.button.StandardButton;
import org.geogebra.web.html5.main.AppW;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base dialog material design component
 */
public class ComponentDialog extends GPopupPanel implements SetLabels {
	private Localization localization;
	private Label title;
	private FlowPanel dialogContent;

	public ComponentDialog(AppW app, DialogData dialogData, boolean autoHide,
						   boolean hasScrim) {
		super(false, true, app.getPanel(), app);
		localization = app.getLocalization();
		//setGlassEnabled(hasScrim);
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
		title = new Label(localization.getMenu(titleTransKey));
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
		for (String btnTransKey : buttonTransKeys) {
			StandardButton button = new StandardButton(btnTransKey, getApplication());
			button.addFastClickHandler(new FastClickHandler() {
				@Override
				public void onClick(Widget source) {

				}
			});
			dialogButtonPanel.add(button);
		}
		dialogMainPanel.add(dialogButtonPanel);
	}

	private void addBtnAction() {

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
	public void hide() {
		Log.debug("DO NOTHING");
	}

	@Override
	public void setLabels() {

	}
}

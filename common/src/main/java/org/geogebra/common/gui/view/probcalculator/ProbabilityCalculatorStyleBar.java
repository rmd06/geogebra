package org.geogebra.common.gui.view.probcalculator;

import org.geogebra.common.gui.menubar.OptionsMenu;
import org.geogebra.common.main.App;

/**
 * @author gabor
 * 
 *         Superclass for probability calculator stylebar
 *
 */
public abstract class ProbabilityCalculatorStyleBar {

	private App app;

	/** probabililty calculator */
	private ProbabilityCalculatorView probCalc;
	private OptionsMenu optionsMenu;

	protected ProbabilityCalculatorStyleBar(App app,
			ProbabilityCalculatorView probCalc) {
		this.probCalc = probCalc;
		this.app = app;
		this.setOptionsMenu(new OptionsMenu(app.getLocalization()));
	}
	protected App getApp() {
		return app;
	}

	/**
	 * @return probability calculator
	 */
	public ProbabilityCalculatorView getProbCalc() {
		return probCalc;
	}

	protected OptionsMenu getOptionsMenu() {
		return optionsMenu;
	}

	protected void setOptionsMenu(OptionsMenu optionsMenu) {
		this.optionsMenu = optionsMenu;
	}

}

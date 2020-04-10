package org.geogebra.web.full.euclidian;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.himamis.retex.editor.share.event.MathFieldListener;
import com.himamis.retex.editor.share.model.MathSequence;
import org.geogebra.common.euclidian.inline.InlineFormulaController;
import org.geogebra.common.util.debug.Log;
import org.geogebra.web.full.gui.components.MathFieldEditor;
import org.geogebra.web.html5.main.AppW;

public class InlineFormulaControllerW implements InlineFormulaController {

	private static class FormulaMathFieldListener implements MathFieldListener {

		@Override
		public void onEnter() {
			// do nothing
		}

		@Override
		public void onKeyTyped() {
			// do nothing
		}

		@Override
		public void onCursorMove() {
			// do nothing
		}

		@Override
		public void onUpKeyPressed() {
			// do nothing
		}

		@Override
		public void onDownKeyPressed() {
			// do nothing
		}

		@Override
		public String serialize(MathSequence selectionText) {
			return null;
		}

		@Override
		public void onInsertString() {
			// do nothing
		}

		@Override
		public boolean onEscape() {
			return false;
		}

		@Override
		public void onTab(boolean shiftDown) {
			// do nothing
		}
	}

	private MathFieldEditor mathFieldEditor;

	private Style style;

	public InlineFormulaControllerW(AppW app,  AbsolutePanel parent) {
		this.mathFieldEditor = new MathFieldEditor(app, new FormulaMathFieldListener());
		this.style = mathFieldEditor.getStyle();

		mathFieldEditor.attach(parent);
		style.setPosition(Style.Position.ABSOLUTE);
		style.setZIndex(51);
	}

	@Override
	public void setLocation(int x, int y) {
		style.setLeft(x, Style.Unit.PX);
		style.setTop(y, Style.Unit.PX);
	}

	@Override
	public void setWidth(int width) {
		style.setWidth(width, Style.Unit.PX);
	}

	@Override
	public void setHeight(int height) {
		style.setHeight(height, Style.Unit.PX);
	}

	@Override
	public void setAngle(double angle) {
		style.setProperty("transform", "rotate(" + angle + "rad)");
	}

	@Override
	public void toForeground(int x, int y) {
		Log.debug("foregrounding");
		mathFieldEditor.requestFocus();
	}

	@Override
	public void updateContent(String content) {
		mathFieldEditor.setText(content);
	}
}

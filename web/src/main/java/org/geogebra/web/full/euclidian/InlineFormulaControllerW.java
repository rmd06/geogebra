package org.geogebra.web.full.euclidian;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.himamis.retex.editor.share.event.MathFieldListener;
import com.himamis.retex.editor.share.model.MathSequence;
import org.geogebra.common.euclidian.inline.InlineFormulaController;
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

	public InlineFormulaControllerW(AppW app,  AbsolutePanel parent) {
		this.mathFieldEditor = new MathFieldEditor(app, new FormulaMathFieldListener());
		mathFieldEditor.attach(parent);
	}
}

package org.geogebra.web.full.euclidian;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.himamis.retex.editor.share.event.MathFieldListener;
import com.himamis.retex.editor.share.model.MathSequence;
import com.himamis.retex.renderer.web.graphics.ColorW;
import org.geogebra.common.awt.GAffineTransform;
import org.geogebra.common.awt.GColor;
import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.euclidian.draw.DrawFormula;
import org.geogebra.common.euclidian.inline.InlineFormulaController;
import org.geogebra.common.kernel.geos.GeoFormula;
import org.geogebra.web.full.gui.components.MathFieldEditor;
import org.geogebra.web.html5.awt.GGraphics2DW;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.main.DrawEquationW;

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

	private GeoFormula formula;
	private MathFieldEditor mathFieldEditor;

	private Style style;

	public InlineFormulaControllerW(GeoFormula formula, AppW app, AbsolutePanel parent) {
		this.formula = formula;
		this.mathFieldEditor = new MathFieldEditor(app, new FormulaMathFieldListener());

		mathFieldEditor.attach(parent);
		mathFieldEditor.addStyleName("mowWidget");

		this.style = mathFieldEditor.getStyle();
		style.setPosition(Style.Position.ABSOLUTE);
		String origin = "-" + DrawFormula.PADDING + "px ";
		style.setProperty("transformOrigin", origin + origin);
		style.setProperty("boxSizing", "border-box");
		style.setMargin(8, Style.Unit.PX);
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
		mathFieldEditor.setVisible(true);
		mathFieldEditor.requestFocus();
		mathFieldEditor.getMathField().getInternal().onPointerUp(x, y);
	}

	@Override
	public void toBackground() {
		if (!formula.getContent().equals(mathFieldEditor.getText())) {
			formula.setContent(mathFieldEditor.getText());
			formula.updateRepaint();
			formula.getKernel().storeUndoInfo();
		}

		mathFieldEditor.setVisible(false);
	}

	@Override
	public void draw(GGraphics2D g2, GAffineTransform transform) {
		if (!mathFieldEditor.isVisible()) {
			g2.saveTransform();
			g2.transform(transform);

			mathFieldEditor.getMathField().paint(((GGraphics2DW) g2).getContext(), DrawFormula.PADDING,
					DrawFormula.PADDING);
			g2.restoreTransform();
		}
	}

	@Override
	public void updateContent(String content) {
		mathFieldEditor.setText(content);
	}

	@Override
	public void setColor(GColor objectColor) {
		ColorW color = DrawEquationW.convertColorW(objectColor);
		mathFieldEditor.getMathField().setForegroundColor(color);
	}
}

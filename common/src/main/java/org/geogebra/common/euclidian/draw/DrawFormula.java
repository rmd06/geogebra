package org.geogebra.common.euclidian.draw;

import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.awt.GPoint2D;
import org.geogebra.common.awt.GRectangle;
import org.geogebra.common.euclidian.Drawable;
import org.geogebra.common.euclidian.EuclidianBoundingBoxHandler;
import org.geogebra.common.euclidian.EuclidianView;
import org.geogebra.common.euclidian.RotatableBoundingBox;
import org.geogebra.common.euclidian.inline.InlineFormulaController;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoFormula;

import java.util.ArrayList;
import java.util.List;

public class DrawFormula extends Drawable implements DrawMedia {

	public static final int PADDING = 8;

	private final TransformableRectangle rectangle;

	private final GeoFormula formula;
	private final InlineFormulaController formulaController;

	/**
	 * @param ev view
	 * @param formula formula
	 */
	public DrawFormula(EuclidianView ev, GeoFormula formula) {
		super(ev, formula);
		this.rectangle = new TransformableRectangle(view, formula);
		this.formula = formula;
		this.formulaController = ev.getApplication().createInlineFormulaController(ev, formula);
		update();
	}

	@Override
	public void update() {
		updateStrokes(geo);
		labelDesc = geo.toValueString(StringTemplate.defaultTemplate);
		rectangle.updateSelfAndBoundingBox();

		GPoint2D point = formula.getLocation();
		if (formulaController != null && point != null) {
			double angle = formula.getAngle();
			double width = formula.getWidth();
			double height = formula.getHeight();

			formulaController.setLocation(view.toScreenCoordX(point.getX()),
					view.toScreenCoordY(point.getY()));
			formulaController.setHeight((int) (height - 2 * PADDING));
			formulaController.setWidth((int) (width - 2 * PADDING));
			formulaController.setAngle(angle);
			formulaController.updateContent(formula.getContent());
		}
	}

	@Override
	public void draw(GGraphics2D g2) {
		if (formulaController != null) {
			formulaController.draw(g2, rectangle.getDirectTransform());
		}
	}

	@Override
	public GRectangle getBounds() {
		return rectangle.getBounds();
	}

	@Override
	public boolean hit(int x, int y, int hitThreshold) {
		return rectangle.hit(x, y);
	}

	@Override
	public boolean isInside(GRectangle rect) {
		return rect.contains(getBounds());
	}

	@Override
	public GeoElement getGeoElement() {
		return geo;
	}

	@Override
	public RotatableBoundingBox getBoundingBox() {
		return rectangle.getBoundingBox();
	}

	@Override
	public void updateByBoundingBoxResize(GPoint2D point, EuclidianBoundingBoxHandler handler) {
		rectangle.updateByBoundingBoxResize(point, handler);
	}

	@Override
	public void updateContent() {
		if (formulaController != null) {
			formulaController.updateContent(formula.getContent());
		}
	}

	@Override
	public void toForeground(int x, int y) {
		if (formulaController != null) {
			GPoint2D p = rectangle.getInversePoint(x - PADDING, y - PADDING);
			formulaController.toForeground((int) p.getX(), (int) p.getY());
		}
	}

	@Override
	public void toBackground() {
		if (formulaController != null) {
			formulaController.toBackground();
		}
	}

	@Override
	public void fromPoints(ArrayList<GPoint2D> points) {
		rectangle.fromPoints(points);
	}

	@Override
	protected List<GPoint2D> toPoints() {
		return rectangle.toPoints();
	}
}

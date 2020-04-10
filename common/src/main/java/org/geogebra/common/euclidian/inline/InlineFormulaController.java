package org.geogebra.common.euclidian.inline;

import org.geogebra.common.awt.GAffineTransform;
import org.geogebra.common.awt.GColor;
import org.geogebra.common.awt.GGraphics2D;

public interface InlineFormulaController {

	void setLocation(int x, int y);

	void setWidth(int width);

	void setHeight(int height);

	void setAngle(double angle);

	void toForeground(int x, int y);

	void toBackground();

	void draw(GGraphics2D g2, GAffineTransform transform);

	void updateContent(String content);

	void setColor(GColor objectColor);
}

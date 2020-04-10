package org.geogebra.common.euclidian.inline;

public interface InlineFormulaController {

	void setLocation(int x, int y);

	void setWidth(int width);

	void setHeight(int height);

	void setAngle(double angle);

	void toForeground(int x, int y);

	void updateContent(String content);
}

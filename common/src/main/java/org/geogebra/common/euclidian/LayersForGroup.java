package org.geogebra.common.euclidian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.groups.Group;

public class LayersForGroup {
	private final List<GeoElement> drawingOrder;

	public LayersForGroup(List<GeoElement> drawingOrder) {
		this.drawingOrder = drawingOrder;
	}

	/**
	 * Moves geo to the top of drawables
	 * within its group.
	 *
	 * @param geo to move front.
	 */
	void moveToFront(GeoElement geo) {
		moveTo(geo, lastIndexOf(geo.getParentGroup()));
	}

	private void moveTo(GeoElement geo, int index) {
		int srcIdx = indexOf(geo);
		if (srcIdx != index) {
			drawingOrder.remove(geo);
			drawingOrder.add(index, geo);
		}
	}

	private int indexOf(GeoElement geo) {
		return drawingOrder.indexOf(geo);
	}

	/**
	 * Moves geo to the bottom of drawables
	 * within its group.
	 *
	 * @param geo to move back.
	 */
	void moveToBack(GeoElement geo) {
		moveTo(geo, firstIndexOf(geo.getParentGroup()));
	}

	private int firstIndexOf(Group group) {
		return drawingOrder.indexOf(group.getGroupedGeos().get(0));
	}

	/**
	 * Moves geo one step forward in the drawables
	 * within its group.
	 *
	 * @param geo to move forward.
	 */
	void moveForward(GeoElement geo) {
		int index = indexOf(geo);
		if (index < lastIndexOf(geo.getParentGroup())) {
			Collections.swap(drawingOrder, index, index + 1);
		}
	}

	private int lastIndexOf(Group group) {
		ArrayList<GeoElement> geos = group.getGroupedGeos();
		GeoElement last = geos.get(geos.size() - 1);
		return drawingOrder.indexOf(last);
	}

	/**
	 * Moves geo one step backward in the drawables
	 * within its group.
	 *
	 * @param geo to move backward.
	 */
	void moveBackward(GeoElement geo) {
		int index = indexOf(geo);
		if (index > 0) {
			Collections.swap(drawingOrder, index, index - 1);
		}
	}
}

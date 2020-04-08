package org.geogebra.common.euclidian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.geogebra.common.factories.AwtFactoryCommon;
import org.geogebra.common.jre.headless.AppCommon;
import org.geogebra.common.jre.headless.LocalizationCommon;
import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoPolygon;
import org.geogebra.common.kernel.geos.groups.Group;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupLayersTest {

	private LayerManager layerManager = new LayerManager();
	private Map<String, GeoElement> geoMap = new HashMap<>();
	private Construction construction;
	private Group group;

	@Before
	public void setup() {
		AwtFactoryCommon factoryCommon = new AwtFactoryCommon();
		AppCommon app = new AppCommon(new LocalizationCommon(2), factoryCommon);
		construction = app.getKernel().getConstruction();
		for (int i = 0; i < 10; i++) {
			GeoElement geo = createGeo(i + 1);
			geoMap.put(geo.getLabelSimple(), geo);
			layerManager.addGeo(geo);
		}

		ArrayList<GeoElement> allGeos = new ArrayList<>(geoMap.values());

		group = new Group(new ArrayList<>(allGeos.subList(3, 7)));
		construction.addGroupToGroupList(group);
	}

	private GeoElement geoByLabel(String label) {
		return geoMap.get(label);
	}

	@Test
	public void testMoveToFront() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("5"));
		layerManager.moveToFront(selection);
		assertOrderingInGroup(4, 6, 7, 5);
	}

	@Test
	public void testMoveFirstToFront() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("7"));
		layerManager.moveToFront(selection);
		assertOrderingInGroup(4, 5, 6, 7);
	}

	@Test
	public void testMoveToBack() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("6"));
		layerManager.moveToBack(selection);
		assertOrderingInGroup(6, 4, 5, 7);
	}

	@Test
	public void testMoveLastToBack() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("6"));
		layerManager.moveToBack(selection);
		assertOrderingInGroup(6, 4, 5, 7);
	}


	@Test
	public void testMoveForward() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("4"));
		layerManager.moveForward(selection);
		assertOrderingInGroup(5, 4, 6, 7);
	}

	@Test
	public void testMoveForwardLastInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("7"));
		layerManager.moveForward(selection);
		assertOrderingInGroup(4, 5, 6, 7);
	}

	@Test
	public void testMoveBackwardInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("6"));
		layerManager.moveBackward(selection);
		assertOrderingInGroup(4, 6, 5, 7);
	}

	@Test
	public void testMoveBackwardLastInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geoByLabel("4"));
		layerManager.moveBackward(selection);
		assertOrderingInGroup(4, 5, 6, 7);
	}


	private void assertOrderingInGroup(Integer... orders) {
		ArrayList<GeoElement> geos = group.getGroupedGeos();
		Collections.sort(geos,
				new Comparator<GeoElement>() {
					@Override
					public int compare(GeoElement geo1, GeoElement geo2) {
						Integer ordering1 = geo1.getOrdering();
						return ordering1.compareTo(geo2.getOrdering());
					}
				});
		ArrayList<Integer> actual = new ArrayList<>();
		for (GeoElement geo : geos) {
			actual.add(Integer.parseInt(geo.getLabelSimple()));
		}

		Assert.assertArrayEquals(orders, actual.toArray());
	}

	private GeoElement createGeo(int order) {
		 GeoElement geo = new GeoPolygon(construction);
		 geo.setLabel(Integer.toString(order));
		 geo.setOrdering(order);
		 return geo;
	}
}

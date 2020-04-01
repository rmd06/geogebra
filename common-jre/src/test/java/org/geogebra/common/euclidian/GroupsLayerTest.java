package org.geogebra.common.euclidian;

import java.util.ArrayList;
import java.util.List;

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

public class GroupsLayerTest {

	private LayerManager layerManager = new LayerManager();
	private List<GeoElement> allGeos = new ArrayList<>();
	private Construction construction;
	private Group group;

	@Before
	public void setup() {
		AwtFactoryCommon factoryCommon = new AwtFactoryCommon();
		AppCommon app = new AppCommon(new LocalizationCommon(2), factoryCommon);
		construction = app.getKernel().getConstruction();
		for (int i = 0; i < 10; i++) {
			GeoElement geo = createGeo(i + 1);
			allGeos.add(geo);
			layerManager.addGeo(geo);
		}

		group = new Group(new ArrayList<>(allGeos.subList(3, 7)));
		construction.addGroupToGroupList(group);
	}

	@Test
	public void testMoveToFrontInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(group.getGroupedGeos().get(1));
		layerManager.moveToFront(selection);
		assertOrderingInGroup(4, 6, 7, 5);
	}

	@Test
	public void testMoveToBackInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(group.getGroupedGeos().get(2));
		layerManager.moveToBack(selection);
		assertOrderingInGroup(6, 3, 4, 5);
	}

	private void assertOrderingInGroup(Integer... orders) {
		ArrayList<GeoElement> geos = group.getGroupedGeos();
		ArrayList<Integer> actual = new ArrayList<>();
		for (GeoElement geo : geos) {
			actual.add(Integer.parseInt(geo.getLabelSimple()));
		}
		Assert.assertArrayEquals(orders, actual.toArray());

		}

	private GeoElement createGeo(int order) {
		 GeoElement geo = new GeoPolygon(construction);
		 geo.setLabel(order +"");
		 geo.setOrdering(order);
		 return geo;
	}
}

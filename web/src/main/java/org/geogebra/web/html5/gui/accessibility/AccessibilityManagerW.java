package org.geogebra.web.html5.gui.accessibility;

import java.util.Comparator;
import java.util.TreeSet;

import org.geogebra.common.gui.AccessibilityManagerInterface;
import org.geogebra.common.gui.MayHaveFocus;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.main.App;
import org.geogebra.common.main.SelectionManager;

/**
 * Web implementation of AccessibilityManager.
 *
 */
public class AccessibilityManagerW implements AccessibilityManagerInterface {
	private final GeoTabber geoTabber;
	private App app;
	private SelectionManager selection;
	private MayHaveFocus anchor;
	private SideBarAccessibilityAdapter menuContainer;
	private TreeSet<MayHaveFocus> components = new TreeSet<>(new Comparator<MayHaveFocus>() {
		@Override
		public int compare(MayHaveFocus o1, MayHaveFocus o2) {
			int viewDiff = o1.getViewId() - o2.getViewId();
			if (viewDiff != 0 && o1.getViewId() != -1 && o2.getViewId() != -1) {
				return viewDiff;
			}
			return o1.getAccessibilityGroup().ordinal() - o2.getAccessibilityGroup().ordinal();
		}
	});

	/**
	 * Constructor.
	 *
	 * @param app
	 *            The application.
	 */
	public AccessibilityManagerW(App app) {
		this.app = app;
		selection = app.getSelectionManager();
		this.geoTabber =  new GeoTabber(app);
		components.add(geoTabber);
		components.add(new PlayButtonTabber(app.getActiveEuclidianView()));
		components.add(new ResetButtonTabber(app.getActiveEuclidianView()));
	}

	@Override
	public void focusNext() {
		for (MayHaveFocus entry: components) {
			if (entry.hasFocus()) {
				if (!entry.focusNext()) {
					focusFirstVisible(findNext(entry));
				}
				return;
			}
		}

		focusFirstVisible(components.first());
	}

	private void focusFirstVisible(MayHaveFocus entry) {
		MayHaveFocus nextEntry = entry;
		while (nextEntry != null) {
			if (nextEntry.focusIfVisible(false)) {
				return;
			}
			nextEntry = findNext(nextEntry);
		}
	}

	private void focusLastVisible(MayHaveFocus entry) {
		MayHaveFocus nextEntry = entry;
		while (nextEntry != null) {
			if (nextEntry.focusIfVisible(true)) {
				return;
			}
			nextEntry = findPrevious(nextEntry);
		}
	}

	private MayHaveFocus findNext(MayHaveFocus entry) {
		MayHaveFocus nextEntry = components.higher(entry);
		if (nextEntry == null) {
			return components.first();
		}
		return nextEntry;
	}

	private MayHaveFocus findPrevious(MayHaveFocus entry) {
		MayHaveFocus nextEntry = components.lower(entry);
		if (nextEntry == null) {
			return components.last();
		}
		return nextEntry;
	}

	@Override
	public void focusPrevious() {
		for (MayHaveFocus entry: components) {
			if (entry.hasFocus()) {
				if (!entry.focusPrevious()) {
					focusLastVisible(findPrevious(entry));
				}
				return;
			}
		}

		focusLastVisible(components.last());
	}

	@Override
	public void register(MayHaveFocus focusable) {
		components.add(focusable);
	}

	@Override
	public void setTabOverGeos() {
		geoTabber.setFocused(true);
	}

	@Override
	public void focusFirstElement() {
		components.first().focusIfVisible(false);
	}

	@Override
	public boolean focusInput(boolean force) {
		if (menuContainer != null) {
			return menuContainer.focusInput(force);
		}
		return false;
	}

	@Override
	public void focusGeo(GeoElement geo) {
		if (geo != null) {
			selection.addSelectedGeoForEV(geo);
			if (!geo.isGeoInputBox()) {
				app.getActiveEuclidianView().requestFocus();
			}
		} else {
			if (menuContainer != null) {
				menuContainer.focusMenu();
			}
		}
	}

	@Override
	public void setAnchor(MayHaveFocus anchor) {
		this.anchor = anchor;
	}

	@Override
	public MayHaveFocus getAnchor() {
		return anchor;
	}

	@Override
	public void focusAnchor() {
		if (anchor == null) {
			return;
		}
		anchor.focusIfVisible(false);
		cancelAnchor();
	}

	@Override
	public void focusAnchorOrMenu() {
		if (anchor == null) {
			focusFirstElement();
		} else {
			focusAnchor();
		}
	}

	@Override
	public void cancelAnchor() {
		anchor = null;
	}

	/**
	 * @param toolbarPanel side bar adapter
	 */
	public void setMenuContainer(SideBarAccessibilityAdapter toolbarPanel) {
		this.menuContainer = toolbarPanel;
	}
}

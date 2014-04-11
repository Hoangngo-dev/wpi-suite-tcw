package edu.wpi.cs.wpisuitetcw.modules.planningpoker.view;



import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.ClosableTabComponent;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.overviews.CreateSessionPanel;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.overviews.OverviewPanel;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.overviews.SessionInProgressPanel;

public class MainView extends JTabbedPane {

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 4184001083813964646L;
	private OverviewPanel overviewPanel;

	/**
	 * Create the panel.
	 */

	public MainView() {		
		// Put all tabs in a scroll layout
		setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
		
		overviewPanel = new OverviewPanel();
		this.addTab("Overview", overviewPanel);
	}
	
	/**
	 * Overridden insertTab function to add the closable tab element.
	 * 
	 * @param title	Title of the tab
	 * @param icon	Icon for the tab
	 * @param component	The tab
	 * @param tip	Showing mouse tip when hovering over tab
	 * @param index	Location of the tab
	 */
	@Override
	public void insertTab(String title, Icon icon, Component component,
			String tip, int index) {
		super.insertTab(title, icon, component, tip, index);
		if (!(component instanceof OverviewPanel)) {
			setTabComponentAt(index, new ClosableTabComponent(this));
		}
	}
}

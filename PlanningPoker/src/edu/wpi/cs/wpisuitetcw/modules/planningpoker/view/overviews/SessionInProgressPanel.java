package edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.overviews;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetcw.modules.planningpoker.controllers.vote.AddVoteController;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.controllers.vote.GetRequirementsVotesController;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.entitymanagers.ViewSessionTableManager;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.PlanningPokerRequirement;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.PlanningPokerSession;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.PlanningPokerVote;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.ViewEventManager;

import javax.swing.JTabbedPane;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SessionInProgressPanel extends JSplitPane {

	private final PlanningPokerSession session;
	private JTextField vote;
	private JLabel name;
	private JLabel description;
	private JLabel deadline;
	private PlanningPokerRequirement[] reqsList;
	private JButton btnSubmit;
	private String selectedReqName;
	private JTable reqsViewTable;
	private ViewSessionTableManager reqsViewTableManager = new ViewSessionTableManager();
	public JList voteList;

	/**
	 * Create the panel.
	 */
	public SessionInProgressPanel(PlanningPokerSession session) {
		this.session = session;
		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

		// Set up Session Info Panel
		JPanel LeftPanel = new JPanel();
		LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));

		// Padding
		Component verticalStrut = Box.createVerticalStrut(10);
		LeftPanel.add(verticalStrut);

		// "Session Info" label
		JLabel lblSessionInfo = new JLabel("Session Info:");
		lblSessionInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LeftPanel.add(lblSessionInfo);

		// Padding
		Component verticalStrut2 = Box.createVerticalStrut(20);
		LeftPanel.add(verticalStrut2);

		// "Name" label
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LeftPanel.add(lblName);

		// Call setter for session name (TBR)
		setSessionName("Planning Poker");
		LeftPanel.add(name);

		// Padding
		Component verticalStrut3 = Box.createVerticalStrut(20);
		LeftPanel.add(verticalStrut3);

		// "Description" label
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LeftPanel.add(lblDescription);

		// Call setter for session description (TBR)
		setSessionDescription("A sweet session where you do stuff and all is well.");
		LeftPanel.add(description);

		// Padding
		Component verticalStrut4 = Box.createVerticalStrut(20);
		LeftPanel.add(verticalStrut4);

		// "Deadline" label
		JLabel lblDate = new JLabel("Deadline:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LeftPanel.add(lblDate);

		// Call setter for session deadline (TBR)
		setSessionDeadline("12/13/14", "12:00 PM");
		LeftPanel.add(deadline);

		// Set up Reqs Panel
		JPanel requirementsPanel = new JPanel();
		requirementsPanel.setLayout(new BoxLayout(requirementsPanel,
				BoxLayout.X_AXIS));

		// Split out panes
		JSplitPane splitTopBottom = new JSplitPane();
		splitTopBottom.setResizeWeight(0.8);
		splitTopBottom.setOrientation(JSplitPane.VERTICAL_SPLIT);

		// Set up tabs at bottom
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		// Set up "Stats Tab"
		JPanel statsTab = new JPanel();
		tabbedPane.addTab("Statistics", null, statsTab, null);
		statsTab.setLayout(new GridLayout(1, 0, 0, 0));

		// Holder label (TBM)
		JLabel lblCurrentEstimate = new JLabel("Current Estimate:");
		statsTab.add(lblCurrentEstimate);

		// Holder label (TBM)
		JLabel lblNumberOfVotes = new JLabel("Number of Votes:");
		statsTab.add(lblNumberOfVotes);

		JList voteList = new JList();
		statsTab.add(voteList);

		// Set up "Vote Tab"
		JPanel voteTab = new JPanel();
		tabbedPane.addTab("Voting", null, voteTab, null);

		// "Estimate" label
		JLabel lblEstimate = new JLabel("Estimate:");
		voteTab.add(lblEstimate);

		// Text box for vote
		vote = new JTextField(10);
		voteTab.add(vote);

		// Submit button
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new AddVoteController(this, this.session));
		voteTab.add(btnSubmit);

		// Split into Reqs list and Reqs info
		JSplitPane splitLeftRight = new JSplitPane();
		splitLeftRight.setResizeWeight(0.8);
		splitLeftRight.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

		JPanel reqsView = new JPanel();

		// Extract the requirements from the table provided by
		// ViewSessionTableManager and converts them to list
		ArrayList<String> testReqs = new ArrayList<String>();
		ViewSessionTableManager a = new ViewSessionTableManager();
		ViewSessionTableModel v = a.get(this.session.getID());
		Vector vector = v.getDataVector();
		for (int i = 0; i < vector.size(); ++i) {
			testReqs.add((String) (((Vector) vector.elementAt(i)).elementAt(1)));
		}
		String[] reqArr = new String[testReqs.size()];
		for (int i = 0; i < testReqs.size(); ++i) {
			reqArr[i] = testReqs.get(i);
		}

		reqsViewTable = new JTable();
		
				
		reqsViewTable.setFillsViewportHeight(true);
		// TODO: Make sure you add the table model here after construction!
		//reqsViewTable.getColumnModel().getColumn(1).setResizable(false);
		this.getReqsViewTable();
		reqsView.setLayout(new BorderLayout(0, 0));
		reqsView.add(reqsViewTable);

		JPanel reqsDetail = new JPanel();
		reqsDetail.setLayout(new BorderLayout(0, 0));
		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
			String[] values = new String[] { "ID:", "", "", "Name:",
					selectedReqName, "", "Description:", "" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		reqsDetail.add(list, BorderLayout.CENTER);

		JLabel lblRequirementDetail = new JLabel("Requirement Detail:");
		lblRequirementDetail.setHorizontalAlignment(SwingConstants.CENTER);
		reqsDetail.add(lblRequirementDetail, BorderLayout.NORTH);

		// Set all components
		splitLeftRight.setLeftComponent(reqsView);
		
		JLabel lblNewLabel = new JLabel("Requirements (ID, Name, Priority)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reqsView.add(lblNewLabel, BorderLayout.NORTH);
		splitLeftRight.setRightComponent(reqsDetail);

		splitTopBottom.setTopComponent(splitLeftRight);
		splitTopBottom.setBottomComponent(tabbedPane);

		setLeftComponent(LeftPanel);
		setRightComponent(splitTopBottom);
	}

	/**
	 * 
	 * @return Session Model for this Panel
	 */
	public PlanningPokerSession getSession() {
		return session;
	}

	/**
	 * 
	 * @param session
	 */
	public void setSession(PlanningPokerSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param sessionName
	 */
	public void setSessionName(String sessionName) {
		name = new JLabel(sessionName, JLabel.LEFT);
	}

	public void setSessionDescription(String sessionDescription) {
		description = new JLabel("<html>" + sessionDescription + "</html>",
				JLabel.LEFT);
	}

	/**
	 * 
	 * @param sessionDeadlineDate
	 *            Deadline Date (mm/dd/yyyy) of Session as a String
	 * @param sessionDeadlineTime
	 *            Deadline Time (hh:mm AM) of Session as a String
	 */
	public void setSessionDeadline(String sessionDeadlineDate,
			String sessionDeadlineTime) {
		deadline = new JLabel("<html>" + sessionDeadlineDate + " at "
				+ sessionDeadlineTime + "</html>", JLabel.LEFT);
	}

	/**
	 * 
	 * @return List of Reqs for this session
	 */
	public PlanningPokerRequirement[] getReqsList() {
		return reqsList;
	}

	/**
	 * 
	 * @param reqsList
	 */
	public void setReqsList(PlanningPokerRequirement[] reqsList) {
		this.reqsList = reqsList;
	}

	/**
	 * 
	 * @return Requirement Name selected in the list
	 */
	public String getSelectedRequirement() {
		return selectedReqName;
	}

	/**
	 * 
	 * @return vote parsed as an integer
	 */
	public int getVote() {
		return Integer.getInteger(vote.toString());
	}
	/**
	 * sets the reqsViewTable with the appropriate information
	 */
	public void getReqsViewTable(){
		reqsViewTable.setModel(this.reqsViewTableManager.get(this.session.getID()));
	}
}
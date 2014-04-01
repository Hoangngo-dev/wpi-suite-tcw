package edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.overviews;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;

import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.characteristics.SessionLiveType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ScrollablePanel;

/**
 * 
 * @author Rob, Ben, Jenny
 * 
 */

public class CreateSessionPanel extends JSplitPane {
	final int DEFAULT_DATA_SIZE = 30; // default data size for database entry

	// The right panel holds info about selected requirements
	private final ScrollablePanel rightPanel;
	// The left leftPanel contains reqList, name, and Deadline.
	private final ScrollablePanel leftPanel;

	// Constructor for our Create Session Panel
	public CreateSessionPanel() {
		// initialize left and right panel
		rightPanel = new ScrollablePanel();
		leftPanel = new ScrollablePanel();

		// create labels for each data field
		JLabel labelName = new JLabel("Name *");
		JLabel labelDeadline = new JLabel("Deadline *");
		JLabel labelDropdownType = new JLabel("Type *");

		// create date picker
		JXDatePicker pickerDeadlineDate = new JXDatePicker();
		pickerDeadlineDate.setDate(Calendar.getInstance().getTime());
		pickerDeadlineDate.setFormats(new SimpleDateFormat("MM/dd/yyyy"));
		
		// create time selector
		JSpinner pickerDeadlineTime = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(pickerDeadlineTime, "HH:mm:ss");
		pickerDeadlineTime.setEditor(timeEditor);
		pickerDeadlineTime.setValue(new Date()); // will only show the current time

		// create textfield
		// TODO check with java team to see what the limit size for each data is
		JTextField fieldName = new JTextField(DEFAULT_DATA_SIZE);
		// JTextField fieldDeadline = new JTextField(DEFAULT_DATA_SIZE);

		// create dropdown menu
		JComboBox<SessionLiveType> dropdownType = new JComboBox<SessionLiveType>(
				SessionLiveType.values());
		dropdownType.setEditable(false);
		dropdownType.setBackground(Color.WHITE);

		// create buttons and listeners
		JButton btnSaveSession = new JButton("Save");

		// setup right panel
		// MigLayout is a convenient way of creating responsive layout with
		// Swing
		rightPanel.setLayout(new MigLayout("", "", "shrink"));
		rightPanel.setAlignmentX(LEFT_ALIGNMENT);

		// labels and textfields
		rightPanel.add(labelName, "wrap");
		rightPanel.add(fieldName, "width 150px, left, wrap");

		rightPanel.add(labelDeadline, "wrap");
		rightPanel.add(pickerDeadlineDate, "width 100px");
		
		rightPanel.add(pickerDeadlineTime, "width 100px, wrap");
		// leftPanel.add(fieldDeadline, "growx, pushx, shrinkx, span, wrap");

		// dropdowns
		rightPanel.add(labelDropdownType, "wrap");
		rightPanel.add(dropdownType, "width 150px, left, wrap");
		
		// buttons
		rightPanel.add(btnSaveSession, "width 150px, left, wrap");

		// Adding UI to the rightPane
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setDividerLocation(180);
	}

}

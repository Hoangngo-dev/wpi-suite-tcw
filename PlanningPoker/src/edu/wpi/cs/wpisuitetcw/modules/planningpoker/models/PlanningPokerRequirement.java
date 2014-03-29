package edu.wpi.cs.wpisuitetcw.modules.planningpoker.models;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * @author Nicholas Kalamvokis and Matt Suarez
 * @date 3/24/2014
 */

public class PlanningPokerRequirement extends Requirement {
	private int sessionID;

	/**
	 * Constructs the PlanningPokerObject
	 */
	public PlanningPokerRequirement() {
		setSessionID(-1);
		System.out.println("Session ID:" + this.sessionID);
	}

	public PlanningPokerRequirement(int id) {
		setSessionID(id);
		System.out.println("Session ID:" + id);
	}

	/**
	 * Retrieves the ID of the current Planning Poker Session
	 * 
	 * @return Session ID
	 */
	public int getSessionID() {
		return sessionID;
	}

	/**
	 * Sets the ID of the current Planning Poker Session
	 * 
	 */
	public void setSessionID(int id) {
		this.sessionID = id;
	}

	/**
	 * Returns an instance of PlanningPokerRequirement constructed using the
	 * given Requirement encoded as a JSON string.
	 * 
	 * @param json
	 *            JSON-encoded Requirement to deserialize
	 * 
	 * @return the Requirement contained in the given JSON
	 */
	public static PlanningPokerRequirement fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, PlanningPokerRequirement.class);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission getPermission(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPermission(Permission p, User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project getProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProject(Project p) {
		// TODO Auto-generated method stub
		
	}

}
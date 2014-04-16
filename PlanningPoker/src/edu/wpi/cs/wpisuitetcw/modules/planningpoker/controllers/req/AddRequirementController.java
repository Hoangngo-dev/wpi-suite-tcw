/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Combat Wombat
 ******************************************************************************/

package edu.wpi.cs.wpisuitetcw.modules.planningpoker.controllers.req;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetcw.modules.planningpoker.controllers.put.GenericPUTRequestObserver;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.PlanningPokerRequirement;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.PlanningPokerSession;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.PlanningPokerVote;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.overviews.SessionInProgressPanel;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.overviews.viewSessionComp.ViewSessionReqPanel;
import edu.wpi.cs.wpisuitetcw.modules.planningpoker.view.tablemanager.RequirementTableManager;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the "Create" button by using
 * all entered information to construct a new session and storing in the
 * database
 * 
 */
public class AddRequirementController implements ActionListener {


	private ViewSessionReqPanel view;

	
	public AddRequirementController(ViewSessionReqPanel view) {
		this.view = view;
		System.out.println("Constructed the action listener");
	}

	
	public void addReq(PlanningPokerSession s){
		
		System.out.println("Constructing update");
		PlanningPokerRequirement r = new PlanningPokerRequirement();
		r.setName(this.view.getNewReqName());
		this.view.clearNewReqName();
		r.setDescription(this.view.getNewReqDesc());
		this.view.clearNewReqDesc();
		s.addRequirement(r);
		
		System.out.println("Sending update");
		//Get Session 1
		//Update the session remotely
		s.update();
		System.out.println("Update Sent");
		
		
		
		
		
		new RequirementTableManager().fetch(s.getID());
		
		this.view.allReqTable.repaint();
		
		
		
	}
	
	public void buildNewSession0(){
		System.out.println("Constructing new session");
		PlanningPokerRequirement r = new PlanningPokerRequirement();

		r.setName("TEST");
		r.setDescription("TEST");
		PlanningPokerSession s = new PlanningPokerSession();
		s.setID(0);
		s.addRequirement(r);
		
		System.out.println("Sending new session 0");
		//Get Session 1
		//Update the session remotely
		s.write();
		
		System.out.println("Update Sent");
	}
	
	/*
	 * This method is called when the user clicks the vote button
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		System.out.println("Requesting null session");
		
		final Request request = Network.getInstance().makeRequest("planningpoker/session/1", HttpMethod.GET);
		request.addObserver(new AddRequirementRequestObserver(this));
		request.send();
		
	
		System.out.println("Request Sent");
	
	}
}
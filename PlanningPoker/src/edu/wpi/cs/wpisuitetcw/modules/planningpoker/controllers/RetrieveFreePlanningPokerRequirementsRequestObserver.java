/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package edu.wpi.cs.wpisuitetcw.modules.planningpoker.controllers;

import edu.wpi.cs.wpisuitetcw.modules.planningpoker.models.PlanningPokerRequirement;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * An observer for a request to retrieve planning poker requirements that have
 * not been assigned to a planning poker session.
 */
public class RetrieveFreePlanningPokerRequirementsRequestObserver implements
		RequestObserver {

	/** The controller managing the request */
	protected RetrieveFreePlanningPokerRequirementsController controller;

	/**
	 * Constructs the observer
	 * 
	 * @param controller
	 *            The controller to update upon receiving the server response
	 */
	public RetrieveFreePlanningPokerRequirementsRequestObserver(
			RetrieveFreePlanningPokerRequirementsController controller) {
		this.controller = controller;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// cast observable to request
		Request request = (Request) iReq;

		// get the response from the request
		ResponseModel response = request.getResponse();

		if (response.getStatusCode() == 200) {
			PlanningPokerRequirement[] requirements = PlanningPokerRequirement
					.fromJsonArray(response.getBody());
			if (requirements == null) {
				requirements = new PlanningPokerRequirement[0];
			}
			controller.receivedData(requirements);
		} else {
			controller.errorReceivingData("Received "
					+ iReq.getResponse().getStatusCode()
					+ " error from server: "
					+ iReq.getResponse().getStatusMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void responseError(IRequest iReq) {
		// an error occurred
		controller.errorReceivingData("Received "
				+ iReq.getResponse().getStatusCode() + " error from server: "
				+ iReq.getResponse().getStatusMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		// an error occurred
		controller.errorReceivingData("Unable to complete request: "
				+ exception.getMessage());
	}
}

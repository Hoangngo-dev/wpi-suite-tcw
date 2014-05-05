/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Combat Wombat
 ******************************************************************************/

package edu.wpi.cs.wpisuitetcw.modules.planningpoker.longpoll;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * Wrapper class for holding an object and a type
 */
public class ModelWithType {
	private AbstractModel object;
	private Class<?> type;
	
	public ModelWithType(Class<?> type, AbstractModel object) {
		this.type = type;
		this.object = object;
	}
	
	public AbstractModel getObject() {
		return object;
	}
	
	public Class getType() {
		return type;
	}
}
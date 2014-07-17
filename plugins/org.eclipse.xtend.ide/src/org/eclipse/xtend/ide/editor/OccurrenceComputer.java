/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.ide.editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.core.xtend.XtendParameter;
import org.eclipse.xtext.ui.editor.occurrences.DefaultOccurrenceComputer;
import org.eclipse.xtext.xbase.XVariableDeclaration;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class OccurrenceComputer extends DefaultOccurrenceComputer {

	@Override
	protected boolean canBeReferencedLocally(EObject object) {
		if (super.canBeReferencedLocally(object))
			return true;
		if (object instanceof XVariableDeclaration || object instanceof XtendParameter)
			return true;
		return false;
	}
	
}

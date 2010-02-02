/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.templates;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.swt.graphics.Image;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class XtextTemplateProposal extends TemplateProposal {

	public XtextTemplateProposal(Template template, TemplateContext context, IRegion region, Image image) {
		super(template, context, region, image);
	}
	
	public XtextTemplateProposal(Template template, TemplateContext context, IRegion region, Image image, int relevance) {
		super(template, context, region, image, relevance);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof XtextTemplateProposal))
			return false;
		XtextTemplateProposal other = (XtextTemplateProposal) obj;
		
		return getTemplate().equals(other.getTemplate());
	}
	
	@Override
	public int hashCode() {
		return getTemplate().hashCode();
	}
}

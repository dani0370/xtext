package org.eclipse.xtext.ui.editor.templates;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;

/**
 * Provides a common base class for xtext <code>TemplateVariableResolver</code>.
 * 
 * @author Michael Clay - Initial contribution and API
 */
public abstract class AbstractTemplateVariableResolver extends
		TemplateVariableResolver {

	public AbstractTemplateVariableResolver() {
		super();
	}

	public AbstractTemplateVariableResolver(String type, String description) {
		super(type, description);
	}

	@Override
	public void resolve(TemplateVariable variable, TemplateContext templateContext) {
		XtextTemplateContext castedContext = (XtextTemplateContext) templateContext;
		List<String> names = resolveValues(variable, castedContext);
		String[] bindings = names.toArray(new String[names.size()]);
		if (bindings.length != 0)
			variable.setValues(bindings);
		if (bindings.length > 1)
			variable.setUnambiguous(false);
		else
			variable.setUnambiguous(isUnambiguous(castedContext));
		variable.setResolved(true);
	}

	protected EClassifier getEClassifierForGrammar(String eClassName,
			Grammar grammar) {
		List<AbstractMetamodelDeclaration> allMetamodelDeclarations = GrammarUtil
				.allMetamodelDeclarations(grammar);
		for (AbstractMetamodelDeclaration decl : allMetamodelDeclarations) {
			EClassifier eClassifier = decl.getEPackage().getEClassifier(
					eClassName);
			if (eClassifier != null) {
				return eClassifier;
			}
		}
		return null;
	}

	protected Grammar getGrammar(XtextTemplateContext xtextTemplateContext) {
		EObject grammarElement = xtextTemplateContext.getContentAssistContext()
				.getRootNode().getGrammarElement();
		return (Grammar) EcoreUtil.getRootContainer(grammarElement);
	}

	public abstract List<String> resolveValues(TemplateVariable variable,
			XtextTemplateContext xtextTemplateContext);
}
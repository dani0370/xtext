/**
 * <copyright>
 * </copyright>
 *
 * $Id: AModelValidator.java,v 1.1 2010/02/04 09:24:54 sefftinge Exp $
 */
package org.eclipse.xtext.grammarinheritance.ametamodel.validation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.grammarinheritance.ametamodel.AType;

/**
 * A sample validator interface for {@link org.eclipse.xtext.grammarinheritance.ametamodel.AModel}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface AModelValidator {
	boolean validate();

	boolean validateElements(EList<AType> value);
}

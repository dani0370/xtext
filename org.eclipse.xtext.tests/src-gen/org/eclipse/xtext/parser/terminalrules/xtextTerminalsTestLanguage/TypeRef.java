/**
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.TypeRef#getMetamodel <em>Metamodel</em>}</li>
 *   <li>{@link org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.TypeRef#getClassifier <em>Classifier</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.XtextTerminalsTestLanguagePackage#getTypeRef()
 * @model
 * @generated
 */
public interface TypeRef extends EObject
{
  /**
   * Returns the value of the '<em><b>Metamodel</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Metamodel</em>' reference.
   * @see #setMetamodel(AbstractMetamodelDeclaration)
   * @see org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.XtextTerminalsTestLanguagePackage#getTypeRef_Metamodel()
   * @model
   * @generated
   */
  AbstractMetamodelDeclaration getMetamodel();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.TypeRef#getMetamodel <em>Metamodel</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Metamodel</em>' reference.
   * @see #getMetamodel()
   * @generated
   */
  void setMetamodel(AbstractMetamodelDeclaration value);

  /**
   * Returns the value of the '<em><b>Classifier</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classifier</em>' reference.
   * @see #setClassifier(EClassifier)
   * @see org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.XtextTerminalsTestLanguagePackage#getTypeRef_Classifier()
   * @model
   * @generated
   */
  EClassifier getClassifier();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.TypeRef#getClassifier <em>Classifier</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Classifier</em>' reference.
   * @see #getClassifier()
   * @generated
   */
  void setClassifier(EClassifier value);

} // TypeRef

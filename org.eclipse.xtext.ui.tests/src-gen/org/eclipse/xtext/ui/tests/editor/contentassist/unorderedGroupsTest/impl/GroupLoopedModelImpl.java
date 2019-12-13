/**
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.GroupLoopedModel;
import org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.UnorderedGroupsTestPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group Looped Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.impl.GroupLoopedModelImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.impl.GroupLoopedModelImpl#getStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.impl.GroupLoopedModelImpl#getSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.impl.GroupLoopedModelImpl#getAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.impl.GroupLoopedModelImpl#getFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.xtext.ui.tests.editor.contentassist.unorderedGroupsTest.impl.GroupLoopedModelImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupLoopedModelImpl extends MinimalEObjectImpl.Container implements GroupLoopedModel
{
  /**
   * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
  protected EList<String> visibility;

  /**
   * The cached value of the '{@link #getStatic() <em>Static</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatic()
   * @generated
   * @ordered
   */
  protected EList<String> static_;

  /**
   * The cached value of the '{@link #getSynchronized() <em>Synchronized</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSynchronized()
   * @generated
   * @ordered
   */
  protected EList<String> synchronized_;

  /**
   * The cached value of the '{@link #getAbstract() <em>Abstract</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAbstract()
   * @generated
   * @ordered
   */
  protected EList<String> abstract_;

  /**
   * The cached value of the '{@link #getFinal() <em>Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinal()
   * @generated
   * @ordered
   */
  protected EList<String> final_;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupLoopedModelImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return UnorderedGroupsTestPackage.Literals.GROUP_LOOPED_MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getVisibility()
  {
    if (visibility == null)
    {
      visibility = new EDataTypeEList<String>(String.class, this, UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__VISIBILITY);
    }
    return visibility;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getStatic()
  {
    if (static_ == null)
    {
      static_ = new EDataTypeEList<String>(String.class, this, UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__STATIC);
    }
    return static_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getSynchronized()
  {
    if (synchronized_ == null)
    {
      synchronized_ = new EDataTypeEList<String>(String.class, this, UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__SYNCHRONIZED);
    }
    return synchronized_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getAbstract()
  {
    if (abstract_ == null)
    {
      abstract_ = new EDataTypeEList<String>(String.class, this, UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__ABSTRACT);
    }
    return abstract_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getFinal()
  {
    if (final_ == null)
    {
      final_ = new EDataTypeEList<String>(String.class, this, UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__FINAL);
    }
    return final_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__VISIBILITY:
        return getVisibility();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__STATIC:
        return getStatic();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__SYNCHRONIZED:
        return getSynchronized();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__ABSTRACT:
        return getAbstract();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__FINAL:
        return getFinal();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__NAME:
        return getName();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__VISIBILITY:
        getVisibility().clear();
        getVisibility().addAll((Collection<? extends String>)newValue);
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__STATIC:
        getStatic().clear();
        getStatic().addAll((Collection<? extends String>)newValue);
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__SYNCHRONIZED:
        getSynchronized().clear();
        getSynchronized().addAll((Collection<? extends String>)newValue);
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__ABSTRACT:
        getAbstract().clear();
        getAbstract().addAll((Collection<? extends String>)newValue);
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__FINAL:
        getFinal().clear();
        getFinal().addAll((Collection<? extends String>)newValue);
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__NAME:
        setName((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__VISIBILITY:
        getVisibility().clear();
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__STATIC:
        getStatic().clear();
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__SYNCHRONIZED:
        getSynchronized().clear();
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__ABSTRACT:
        getAbstract().clear();
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__FINAL:
        getFinal().clear();
        return;
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__NAME:
        setName(NAME_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__VISIBILITY:
        return visibility != null && !visibility.isEmpty();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__STATIC:
        return static_ != null && !static_.isEmpty();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__SYNCHRONIZED:
        return synchronized_ != null && !synchronized_.isEmpty();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__ABSTRACT:
        return abstract_ != null && !abstract_.isEmpty();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__FINAL:
        return final_ != null && !final_.isEmpty();
      case UnorderedGroupsTestPackage.GROUP_LOOPED_MODEL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (visibility: ");
    result.append(visibility);
    result.append(", static: ");
    result.append(static_);
    result.append(", synchronized: ");
    result.append(synchronized_);
    result.append(", abstract: ");
    result.append(abstract_);
    result.append(", final: ");
    result.append(final_);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //GroupLoopedModelImpl

/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.testlanguages.backtracking.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.AliasedRequiredCapability;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.AndExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.AssignmentExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.AtExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.BeeLangTestLanguagePackage;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.BinaryOpExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.CachedExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.CallFeature;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.CallFunction;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.CallNamedFunction;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.ChainedExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.ClosureParameter;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.ClosureTypeRef;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.CreateExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.DefValue;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.FeatureExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.Function;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.GuardExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.Model;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.OrExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.ParameterDeclaration;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.ParameterList;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.ProvidedCapability;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.RequiredCapability;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.SimpleTypeRef;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.UnaryOpExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.UnaryPostOpExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.UnaryPreOpExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.Unit;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.ValueLiteral;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.VariableExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.WithContextExpression;
import org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.WithExpression;
import org.eclipse.xtext.testlanguages.backtracking.services.BeeLangTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class BeeLangTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private BeeLangTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == BeeLangTestLanguagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case BeeLangTestLanguagePackage.ALIASED_REQUIRED_CAPABILITY:
				sequence_AliasedRequiredCapability(context, (AliasedRequiredCapability) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.AND_EXPRESSION:
				sequence_AndExpression(context, (AndExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.ASSIGNMENT_EXPRESSION:
				if (rule == grammarAccess.getTopLevelExpressionRule()
						|| rule == grammarAccess.getExpressionRule()
						|| rule == grammarAccess.getAssignmentExpressionRule()
						|| action == grammarAccess.getAssignmentExpressionAccess().getAssignmentExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getCachedExpressionRule()
						|| rule == grammarAccess.getOrExpressionRule()
						|| action == grammarAccess.getOrExpressionAccess().getOrExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAndExpressionRule()
						|| action == grammarAccess.getAndExpressionAccess().getAndExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getRelationalExpressionRule()
						|| action == grammarAccess.getRelationalExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAdditiveExpressionRule()
						|| action == grammarAccess.getAdditiveExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getMultiplicativeExpressionRule()
						|| action == grammarAccess.getMultiplicativeExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getSetExpressionRule()
						|| action == grammarAccess.getSetExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getUnaryOrInfixExpressionRule()
						|| rule == grammarAccess.getPostopExpressionRule()
						|| action == grammarAccess.getPostopExpressionAccess().getUnaryPostOpExpressionExprAction_1_0()
						|| rule == grammarAccess.getInfixExpressionRule()
						|| action == grammarAccess.getInfixExpressionAccess().getCallFeatureFuncExprAction_1_0_0()
						|| action == grammarAccess.getInfixExpressionAccess().getAtExpressionObjExprAction_1_1_0()
						|| action == grammarAccess.getInfixExpressionAccess().getFeatureExpressionObjExprAction_1_2_0()
						|| rule == grammarAccess.getCallExpressionRule()
						|| action == grammarAccess.getCallExpressionAccess().getCallFunctionFuncExprAction_1_0()
						|| rule == grammarAccess.getPrimaryExpressionRule()
						|| rule == grammarAccess.getOneOrManyExpressionsRule()
						|| rule == grammarAccess.getParanthesizedExpressionRule()) {
					sequence_AssignmentExpression(context, (AssignmentExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getInitializationExpressionRule()) {
					sequence_InitializationExpression(context, (AssignmentExpression) semanticObject); 
					return; 
				}
				else break;
			case BeeLangTestLanguagePackage.AT_EXPRESSION:
				sequence_InfixExpression(context, (AtExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.BINARY_OP_EXPRESSION:
				sequence_AdditiveExpression_MultiplicativeExpression_RelationalExpression_SetExpression(context, (BinaryOpExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.CACHED_EXPRESSION:
				sequence_CachedExpression(context, (CachedExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.CALL_FEATURE:
				sequence_InfixExpression(context, (CallFeature) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.CALL_FUNCTION:
				sequence_CallExpression(context, (CallFunction) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.CALL_NAMED_FUNCTION:
				sequence_OperationCall(context, (CallNamedFunction) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.CHAINED_EXPRESSION:
				if (rule == grammarAccess.getBlockExpressionWithoutBracketsRule()) {
					sequence_BlockExpressionWithoutBrackets(context, (ChainedExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getOneOrManyExpressionsRule()) {
					sequence_BlockExpression_BlockExpressionWithoutBrackets(context, (ChainedExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTopLevelExpressionRule()
						|| rule == grammarAccess.getExpressionRule()
						|| rule == grammarAccess.getAssignmentExpressionRule()
						|| action == grammarAccess.getAssignmentExpressionAccess().getAssignmentExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getCachedExpressionRule()
						|| rule == grammarAccess.getOrExpressionRule()
						|| action == grammarAccess.getOrExpressionAccess().getOrExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAndExpressionRule()
						|| action == grammarAccess.getAndExpressionAccess().getAndExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getRelationalExpressionRule()
						|| action == grammarAccess.getRelationalExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAdditiveExpressionRule()
						|| action == grammarAccess.getAdditiveExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getMultiplicativeExpressionRule()
						|| action == grammarAccess.getMultiplicativeExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getSetExpressionRule()
						|| action == grammarAccess.getSetExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getUnaryOrInfixExpressionRule()
						|| rule == grammarAccess.getPostopExpressionRule()
						|| action == grammarAccess.getPostopExpressionAccess().getUnaryPostOpExpressionExprAction_1_0()
						|| rule == grammarAccess.getInfixExpressionRule()
						|| action == grammarAccess.getInfixExpressionAccess().getCallFeatureFuncExprAction_1_0_0()
						|| action == grammarAccess.getInfixExpressionAccess().getAtExpressionObjExprAction_1_1_0()
						|| action == grammarAccess.getInfixExpressionAccess().getFeatureExpressionObjExprAction_1_2_0()
						|| rule == grammarAccess.getCallExpressionRule()
						|| action == grammarAccess.getCallExpressionAccess().getCallFunctionFuncExprAction_1_0()
						|| rule == grammarAccess.getPrimaryExpressionRule()
						|| rule == grammarAccess.getBlockExpressionRule()
						|| rule == grammarAccess.getParanthesizedExpressionRule()) {
					sequence_BlockExpression(context, (ChainedExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getInitializationBlockExpressionRule()) {
					sequence_InitializationBlockExpression(context, (ChainedExpression) semanticObject); 
					return; 
				}
				else break;
			case BeeLangTestLanguagePackage.CLOSURE_PARAMETER:
				sequence_ClosureParameter(context, (ClosureParameter) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.CLOSURE_TYPE_REF:
				sequence_ClosureTypeRef(context, (ClosureTypeRef) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.CREATE_EXPRESSION:
				sequence_ConstructorCallExpression(context, (CreateExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.DEF_VALUE:
				if (rule == grammarAccess.getValDeclarationRule()) {
					sequence_ValDeclaration(context, (DefValue) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTopLevelExpressionRule()) {
					sequence_ValDeclaration_VarDeclaration(context, (DefValue) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getVarDeclarationRule()) {
					sequence_VarDeclaration(context, (DefValue) semanticObject); 
					return; 
				}
				else break;
			case BeeLangTestLanguagePackage.FEATURE_EXPRESSION:
				if (rule == grammarAccess.getFeatureOfThisRule()) {
					sequence_FeatureOfThis(context, (FeatureExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTopLevelExpressionRule()
						|| rule == grammarAccess.getExpressionRule()
						|| rule == grammarAccess.getAssignmentExpressionRule()
						|| action == grammarAccess.getAssignmentExpressionAccess().getAssignmentExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getCachedExpressionRule()
						|| rule == grammarAccess.getOrExpressionRule()
						|| action == grammarAccess.getOrExpressionAccess().getOrExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAndExpressionRule()
						|| action == grammarAccess.getAndExpressionAccess().getAndExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getRelationalExpressionRule()
						|| action == grammarAccess.getRelationalExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAdditiveExpressionRule()
						|| action == grammarAccess.getAdditiveExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getMultiplicativeExpressionRule()
						|| action == grammarAccess.getMultiplicativeExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getSetExpressionRule()
						|| action == grammarAccess.getSetExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getUnaryOrInfixExpressionRule()
						|| rule == grammarAccess.getPostopExpressionRule()
						|| action == grammarAccess.getPostopExpressionAccess().getUnaryPostOpExpressionExprAction_1_0()
						|| rule == grammarAccess.getInfixExpressionRule()
						|| action == grammarAccess.getInfixExpressionAccess().getCallFeatureFuncExprAction_1_0_0()
						|| action == grammarAccess.getInfixExpressionAccess().getAtExpressionObjExprAction_1_1_0()
						|| action == grammarAccess.getInfixExpressionAccess().getFeatureExpressionObjExprAction_1_2_0()
						|| rule == grammarAccess.getCallExpressionRule()
						|| action == grammarAccess.getCallExpressionAccess().getCallFunctionFuncExprAction_1_0()
						|| rule == grammarAccess.getPrimaryExpressionRule()
						|| rule == grammarAccess.getOneOrManyExpressionsRule()
						|| rule == grammarAccess.getParanthesizedExpressionRule()) {
					sequence_InfixExpression(context, (FeatureExpression) semanticObject); 
					return; 
				}
				else break;
			case BeeLangTestLanguagePackage.FUNCTION:
				if (rule == grammarAccess.getTopLevelExpressionRule()
						|| rule == grammarAccess.getExpressionRule()
						|| rule == grammarAccess.getAssignmentExpressionRule()
						|| action == grammarAccess.getAssignmentExpressionAccess().getAssignmentExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getCachedExpressionRule()
						|| rule == grammarAccess.getOrExpressionRule()
						|| action == grammarAccess.getOrExpressionAccess().getOrExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAndExpressionRule()
						|| action == grammarAccess.getAndExpressionAccess().getAndExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getRelationalExpressionRule()
						|| action == grammarAccess.getRelationalExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAdditiveExpressionRule()
						|| action == grammarAccess.getAdditiveExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getMultiplicativeExpressionRule()
						|| action == grammarAccess.getMultiplicativeExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getSetExpressionRule()
						|| action == grammarAccess.getSetExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getUnaryOrInfixExpressionRule()
						|| rule == grammarAccess.getPostopExpressionRule()
						|| action == grammarAccess.getPostopExpressionAccess().getUnaryPostOpExpressionExprAction_1_0()
						|| rule == grammarAccess.getInfixExpressionRule()
						|| action == grammarAccess.getInfixExpressionAccess().getCallFeatureFuncExprAction_1_0_0()
						|| action == grammarAccess.getInfixExpressionAccess().getAtExpressionObjExprAction_1_1_0()
						|| action == grammarAccess.getInfixExpressionAccess().getFeatureExpressionObjExprAction_1_2_0()
						|| rule == grammarAccess.getCallExpressionRule()
						|| action == grammarAccess.getCallExpressionAccess().getCallFunctionFuncExprAction_1_0()
						|| rule == grammarAccess.getPrimaryExpressionRule()
						|| rule == grammarAccess.getLiteralRule()
						|| rule == grammarAccess.getLiteralFunctionRule()
						|| rule == grammarAccess.getClosureExpressionRule()
						|| rule == grammarAccess.getOneOrManyExpressionsRule()
						|| rule == grammarAccess.getParanthesizedExpressionRule()) {
					sequence_ClosureExpression(context, (Function) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFunctionRule()) {
					sequence_Function(context, (Function) semanticObject); 
					return; 
				}
				else break;
			case BeeLangTestLanguagePackage.GUARD_EXPRESSION:
				sequence_GuardExpression(context, (GuardExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.MODEL:
				sequence_Model(context, (Model) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.OR_EXPRESSION:
				sequence_OrExpression(context, (OrExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.PARAMETER:
				sequence_Parameter(context, (org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.Parameter) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.PARAMETER_DECLARATION:
				sequence_ParameterDeclaration(context, (ParameterDeclaration) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.PARAMETER_LIST:
				sequence_ParameterList(context, (ParameterList) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.PROVIDED_CAPABILITY:
				sequence_ProvidedCapability(context, (ProvidedCapability) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.REQUIRED_CAPABILITY:
				sequence_RequiredCapability(context, (RequiredCapability) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.SIMPLE_TYPE_REF:
				sequence_SimpleTypeRef(context, (SimpleTypeRef) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.UNARY_OP_EXPRESSION:
				sequence_UnaryExpression(context, (UnaryOpExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.UNARY_POST_OP_EXPRESSION:
				sequence_PostopExpression(context, (UnaryPostOpExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.UNARY_PRE_OP_EXPRESSION:
				sequence_PreopExpression(context, (UnaryPreOpExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.UNIT:
				sequence_Unit(context, (Unit) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.VALUE_LITERAL:
				sequence_ValueLiteral(context, (ValueLiteral) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.VARIABLE_EXPRESSION:
				if (rule == grammarAccess.getTopLevelExpressionRule()
						|| rule == grammarAccess.getExpressionRule()
						|| rule == grammarAccess.getAssignmentExpressionRule()
						|| action == grammarAccess.getAssignmentExpressionAccess().getAssignmentExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getCachedExpressionRule()
						|| rule == grammarAccess.getOrExpressionRule()
						|| action == grammarAccess.getOrExpressionAccess().getOrExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAndExpressionRule()
						|| action == grammarAccess.getAndExpressionAccess().getAndExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getRelationalExpressionRule()
						|| action == grammarAccess.getRelationalExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getAdditiveExpressionRule()
						|| action == grammarAccess.getAdditiveExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getMultiplicativeExpressionRule()
						|| action == grammarAccess.getMultiplicativeExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getSetExpressionRule()
						|| action == grammarAccess.getSetExpressionAccess().getBinaryOpExpressionLeftExprAction_1_0()
						|| rule == grammarAccess.getUnaryOrInfixExpressionRule()
						|| rule == grammarAccess.getPostopExpressionRule()
						|| action == grammarAccess.getPostopExpressionAccess().getUnaryPostOpExpressionExprAction_1_0()
						|| rule == grammarAccess.getInfixExpressionRule()
						|| action == grammarAccess.getInfixExpressionAccess().getCallFeatureFuncExprAction_1_0_0()
						|| action == grammarAccess.getInfixExpressionAccess().getAtExpressionObjExprAction_1_1_0()
						|| action == grammarAccess.getInfixExpressionAccess().getFeatureExpressionObjExprAction_1_2_0()
						|| rule == grammarAccess.getCallExpressionRule()
						|| action == grammarAccess.getCallExpressionAccess().getCallFunctionFuncExprAction_1_0()
						|| rule == grammarAccess.getPrimaryExpressionRule()
						|| rule == grammarAccess.getOneOrManyExpressionsRule()
						|| rule == grammarAccess.getParanthesizedExpressionRule()) {
					sequence_KeywordVariables_Value(context, (VariableExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getKeywordVariablesRule()) {
					sequence_KeywordVariables(context, (VariableExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getValueRule()) {
					sequence_Value(context, (VariableExpression) semanticObject); 
					return; 
				}
				else break;
			case BeeLangTestLanguagePackage.WITH_CONTEXT_EXPRESSION:
				sequence_WithContextExpression(context, (WithContextExpression) semanticObject); 
				return; 
			case BeeLangTestLanguagePackage.WITH_EXPRESSION:
				sequence_WithExpression(context, (WithExpression) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns BinaryOpExpression
	 *     Expression returns BinaryOpExpression
	 *     AssignmentExpression returns BinaryOpExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns BinaryOpExpression
	 *     CachedExpression returns BinaryOpExpression
	 *     OrExpression returns BinaryOpExpression
	 *     OrExpression.OrExpression_1_0 returns BinaryOpExpression
	 *     AndExpression returns BinaryOpExpression
	 *     AndExpression.AndExpression_1_0 returns BinaryOpExpression
	 *     RelationalExpression returns BinaryOpExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns BinaryOpExpression
	 *     AdditiveExpression returns BinaryOpExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns BinaryOpExpression
	 *     MultiplicativeExpression returns BinaryOpExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns BinaryOpExpression
	 *     SetExpression returns BinaryOpExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns BinaryOpExpression
	 *     UnaryOrInfixExpression returns BinaryOpExpression
	 *     PostopExpression returns BinaryOpExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns BinaryOpExpression
	 *     InfixExpression returns BinaryOpExpression
	 *     InfixExpression.CallFeature_1_0_0 returns BinaryOpExpression
	 *     InfixExpression.AtExpression_1_1_0 returns BinaryOpExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns BinaryOpExpression
	 *     CallExpression returns BinaryOpExpression
	 *     CallExpression.CallFunction_1_0 returns BinaryOpExpression
	 *     PrimaryExpression returns BinaryOpExpression
	 *     OneOrManyExpressions returns BinaryOpExpression
	 *     ParanthesizedExpression returns BinaryOpExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftExpr=RelationalExpression_BinaryOpExpression_1_0 functionName=RelationalOperator rightExpr=AdditiveExpression) | 
	 *         (leftExpr=AdditiveExpression_BinaryOpExpression_1_0 (functionName='+' | functionName='-') rightExpr=MultiplicativeExpression) | 
	 *         (leftExpr=MultiplicativeExpression_BinaryOpExpression_1_0 (functionName='*' | functionName='/' | functionName='%') rightExpr=SetExpression) | 
	 *         (leftExpr=SetExpression_BinaryOpExpression_1_0 functionName='..' rightExpr=UnaryOrInfixExpression)
	 *     )
	 * </pre>
	 */
	protected void sequence_AdditiveExpression_MultiplicativeExpression_RelationalExpression_SetExpression(ISerializationContext context, BinaryOpExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AliasedRequiredCapability returns AliasedRequiredCapability
	 *
	 * Constraint:
	 *     (nameSpace=ID? name=ID alias=ID? (condExpr=Expression | greedy?='greedy' | min=INT | max=INT | versionRange=ID)*)
	 * </pre>
	 */
	protected void sequence_AliasedRequiredCapability(ISerializationContext context, AliasedRequiredCapability semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns AndExpression
	 *     Expression returns AndExpression
	 *     AssignmentExpression returns AndExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns AndExpression
	 *     CachedExpression returns AndExpression
	 *     OrExpression returns AndExpression
	 *     OrExpression.OrExpression_1_0 returns AndExpression
	 *     AndExpression returns AndExpression
	 *     AndExpression.AndExpression_1_0 returns AndExpression
	 *     RelationalExpression returns AndExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns AndExpression
	 *     AdditiveExpression returns AndExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns AndExpression
	 *     MultiplicativeExpression returns AndExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns AndExpression
	 *     SetExpression returns AndExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns AndExpression
	 *     UnaryOrInfixExpression returns AndExpression
	 *     PostopExpression returns AndExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns AndExpression
	 *     InfixExpression returns AndExpression
	 *     InfixExpression.CallFeature_1_0_0 returns AndExpression
	 *     InfixExpression.AtExpression_1_1_0 returns AndExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns AndExpression
	 *     CallExpression returns AndExpression
	 *     CallExpression.CallFunction_1_0 returns AndExpression
	 *     PrimaryExpression returns AndExpression
	 *     OneOrManyExpressions returns AndExpression
	 *     ParanthesizedExpression returns AndExpression
	 *
	 * Constraint:
	 *     (leftExpr=AndExpression_AndExpression_1_0 rightExpr=RelationalExpression)
	 * </pre>
	 */
	protected void sequence_AndExpression(ISerializationContext context, AndExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.AND_EXPRESSION__LEFT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.AND_EXPRESSION__LEFT_EXPR));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.AND_EXPRESSION__RIGHT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.AND_EXPRESSION__RIGHT_EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAndExpressionAccess().getAndExpressionLeftExprAction_1_0(), semanticObject.getLeftExpr());
		feeder.accept(grammarAccess.getAndExpressionAccess().getRightExprRelationalExpressionParserRuleCall_1_2_0(), semanticObject.getRightExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns AssignmentExpression
	 *     Expression returns AssignmentExpression
	 *     AssignmentExpression returns AssignmentExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns AssignmentExpression
	 *     CachedExpression returns AssignmentExpression
	 *     OrExpression returns AssignmentExpression
	 *     OrExpression.OrExpression_1_0 returns AssignmentExpression
	 *     AndExpression returns AssignmentExpression
	 *     AndExpression.AndExpression_1_0 returns AssignmentExpression
	 *     RelationalExpression returns AssignmentExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns AssignmentExpression
	 *     AdditiveExpression returns AssignmentExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns AssignmentExpression
	 *     MultiplicativeExpression returns AssignmentExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns AssignmentExpression
	 *     SetExpression returns AssignmentExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns AssignmentExpression
	 *     UnaryOrInfixExpression returns AssignmentExpression
	 *     PostopExpression returns AssignmentExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns AssignmentExpression
	 *     InfixExpression returns AssignmentExpression
	 *     InfixExpression.CallFeature_1_0_0 returns AssignmentExpression
	 *     InfixExpression.AtExpression_1_1_0 returns AssignmentExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns AssignmentExpression
	 *     CallExpression returns AssignmentExpression
	 *     CallExpression.CallFunction_1_0 returns AssignmentExpression
	 *     PrimaryExpression returns AssignmentExpression
	 *     OneOrManyExpressions returns AssignmentExpression
	 *     ParanthesizedExpression returns AssignmentExpression
	 *
	 * Constraint:
	 *     (leftExpr=AssignmentExpression_AssignmentExpression_1_0 functionName=AssignmentOperator rightExpr=AssignmentExpression)
	 * </pre>
	 */
	protected void sequence_AssignmentExpression(ISerializationContext context, AssignmentExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__LEFT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__LEFT_EXPR));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__FUNCTION_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__FUNCTION_NAME));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__RIGHT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__RIGHT_EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAssignmentExpressionAccess().getAssignmentExpressionLeftExprAction_1_0(), semanticObject.getLeftExpr());
		feeder.accept(grammarAccess.getAssignmentExpressionAccess().getFunctionNameAssignmentOperatorParserRuleCall_1_1_0(), semanticObject.getFunctionName());
		feeder.accept(grammarAccess.getAssignmentExpressionAccess().getRightExprAssignmentExpressionParserRuleCall_1_2_0(), semanticObject.getRightExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     BlockExpressionWithoutBrackets returns ChainedExpression
	 *
	 * Constraint:
	 *     expressions+=TopLevelExpression+
	 * </pre>
	 */
	protected void sequence_BlockExpressionWithoutBrackets(ISerializationContext context, ChainedExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     OneOrManyExpressions returns ChainedExpression
	 *
	 * Constraint:
	 *     (expressions+=TopLevelExpression+ | expressions+=TopLevelExpression+)?
	 * </pre>
	 */
	protected void sequence_BlockExpression_BlockExpressionWithoutBrackets(ISerializationContext context, ChainedExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns ChainedExpression
	 *     Expression returns ChainedExpression
	 *     AssignmentExpression returns ChainedExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns ChainedExpression
	 *     CachedExpression returns ChainedExpression
	 *     OrExpression returns ChainedExpression
	 *     OrExpression.OrExpression_1_0 returns ChainedExpression
	 *     AndExpression returns ChainedExpression
	 *     AndExpression.AndExpression_1_0 returns ChainedExpression
	 *     RelationalExpression returns ChainedExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns ChainedExpression
	 *     AdditiveExpression returns ChainedExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns ChainedExpression
	 *     MultiplicativeExpression returns ChainedExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns ChainedExpression
	 *     SetExpression returns ChainedExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns ChainedExpression
	 *     UnaryOrInfixExpression returns ChainedExpression
	 *     PostopExpression returns ChainedExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns ChainedExpression
	 *     InfixExpression returns ChainedExpression
	 *     InfixExpression.CallFeature_1_0_0 returns ChainedExpression
	 *     InfixExpression.AtExpression_1_1_0 returns ChainedExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns ChainedExpression
	 *     CallExpression returns ChainedExpression
	 *     CallExpression.CallFunction_1_0 returns ChainedExpression
	 *     PrimaryExpression returns ChainedExpression
	 *     BlockExpression returns ChainedExpression
	 *     ParanthesizedExpression returns ChainedExpression
	 *
	 * Constraint:
	 *     expressions+=TopLevelExpression*
	 * </pre>
	 */
	protected void sequence_BlockExpression(ISerializationContext context, ChainedExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns CachedExpression
	 *     Expression returns CachedExpression
	 *     AssignmentExpression returns CachedExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns CachedExpression
	 *     CachedExpression returns CachedExpression
	 *     OrExpression returns CachedExpression
	 *     OrExpression.OrExpression_1_0 returns CachedExpression
	 *     AndExpression returns CachedExpression
	 *     AndExpression.AndExpression_1_0 returns CachedExpression
	 *     RelationalExpression returns CachedExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns CachedExpression
	 *     AdditiveExpression returns CachedExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns CachedExpression
	 *     MultiplicativeExpression returns CachedExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns CachedExpression
	 *     SetExpression returns CachedExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns CachedExpression
	 *     UnaryOrInfixExpression returns CachedExpression
	 *     PostopExpression returns CachedExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns CachedExpression
	 *     InfixExpression returns CachedExpression
	 *     InfixExpression.CallFeature_1_0_0 returns CachedExpression
	 *     InfixExpression.AtExpression_1_1_0 returns CachedExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns CachedExpression
	 *     CallExpression returns CachedExpression
	 *     CallExpression.CallFunction_1_0 returns CachedExpression
	 *     PrimaryExpression returns CachedExpression
	 *     OneOrManyExpressions returns CachedExpression
	 *     ParanthesizedExpression returns CachedExpression
	 *
	 * Constraint:
	 *     expr=OrExpression
	 * </pre>
	 */
	protected void sequence_CachedExpression(ISerializationContext context, CachedExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.CACHED_EXPRESSION__EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.CACHED_EXPRESSION__EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getCachedExpressionAccess().getExprOrExpressionParserRuleCall_0_2_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns CallFunction
	 *     Expression returns CallFunction
	 *     AssignmentExpression returns CallFunction
	 *     AssignmentExpression.AssignmentExpression_1_0 returns CallFunction
	 *     CachedExpression returns CallFunction
	 *     OrExpression returns CallFunction
	 *     OrExpression.OrExpression_1_0 returns CallFunction
	 *     AndExpression returns CallFunction
	 *     AndExpression.AndExpression_1_0 returns CallFunction
	 *     RelationalExpression returns CallFunction
	 *     RelationalExpression.BinaryOpExpression_1_0 returns CallFunction
	 *     AdditiveExpression returns CallFunction
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns CallFunction
	 *     MultiplicativeExpression returns CallFunction
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns CallFunction
	 *     SetExpression returns CallFunction
	 *     SetExpression.BinaryOpExpression_1_0 returns CallFunction
	 *     UnaryOrInfixExpression returns CallFunction
	 *     PostopExpression returns CallFunction
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns CallFunction
	 *     InfixExpression returns CallFunction
	 *     InfixExpression.CallFeature_1_0_0 returns CallFunction
	 *     InfixExpression.AtExpression_1_1_0 returns CallFunction
	 *     InfixExpression.FeatureExpression_1_2_0 returns CallFunction
	 *     CallExpression returns CallFunction
	 *     CallExpression.CallFunction_1_0 returns CallFunction
	 *     PrimaryExpression returns CallFunction
	 *     OneOrManyExpressions returns CallFunction
	 *     ParanthesizedExpression returns CallFunction
	 *
	 * Constraint:
	 *     (funcExpr=CallExpression_CallFunction_1_0 parameterList=ParameterList?)
	 * </pre>
	 */
	protected void sequence_CallExpression(ISerializationContext context, CallFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns Function
	 *     Expression returns Function
	 *     AssignmentExpression returns Function
	 *     AssignmentExpression.AssignmentExpression_1_0 returns Function
	 *     CachedExpression returns Function
	 *     OrExpression returns Function
	 *     OrExpression.OrExpression_1_0 returns Function
	 *     AndExpression returns Function
	 *     AndExpression.AndExpression_1_0 returns Function
	 *     RelationalExpression returns Function
	 *     RelationalExpression.BinaryOpExpression_1_0 returns Function
	 *     AdditiveExpression returns Function
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns Function
	 *     MultiplicativeExpression returns Function
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns Function
	 *     SetExpression returns Function
	 *     SetExpression.BinaryOpExpression_1_0 returns Function
	 *     UnaryOrInfixExpression returns Function
	 *     PostopExpression returns Function
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns Function
	 *     InfixExpression returns Function
	 *     InfixExpression.CallFeature_1_0_0 returns Function
	 *     InfixExpression.AtExpression_1_1_0 returns Function
	 *     InfixExpression.FeatureExpression_1_2_0 returns Function
	 *     CallExpression returns Function
	 *     CallExpression.CallFunction_1_0 returns Function
	 *     PrimaryExpression returns Function
	 *     Literal returns Function
	 *     LiteralFunction returns Function
	 *     ClosureExpression returns Function
	 *     OneOrManyExpressions returns Function
	 *     ParanthesizedExpression returns Function
	 *
	 * Constraint:
	 *     (
	 *         returnType=TypeRef? 
	 *         (
	 *             (parameters+=ParameterDeclaration parameters+=ParameterDeclaration* (varArgs?='...' parameters+=ParameterDeclaration)?) | 
	 *             (varArgs?='...' parameters+=ParameterDeclaration)
	 *         )? 
	 *         funcExpr=OneOrManyExpressions
	 *     )
	 * </pre>
	 */
	protected void sequence_ClosureExpression(ISerializationContext context, Function semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     FirstParameter returns ClosureParameter
	 *     ClosureParameter returns ClosureParameter
	 *
	 * Constraint:
	 *     expr=ClosureExpression
	 * </pre>
	 */
	protected void sequence_ClosureParameter(ISerializationContext context, ClosureParameter semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.PARAMETER__EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.PARAMETER__EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getClosureParameterAccess().getExprClosureExpressionParserRuleCall_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TypeRef returns ClosureTypeRef
	 *     ClosureTypeRef returns ClosureTypeRef
	 *
	 * Constraint:
	 *     (((parameterTypes+=ID parameterTypes+=ID* (varArgs?='...' parameterTypes+=ID)?) | (varArgs?='...' parameterTypes+=ID))? returnType=ID)
	 * </pre>
	 */
	protected void sequence_ClosureTypeRef(ISerializationContext context, ClosureTypeRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns CreateExpression
	 *     Expression returns CreateExpression
	 *     AssignmentExpression returns CreateExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns CreateExpression
	 *     CachedExpression returns CreateExpression
	 *     OrExpression returns CreateExpression
	 *     OrExpression.OrExpression_1_0 returns CreateExpression
	 *     AndExpression returns CreateExpression
	 *     AndExpression.AndExpression_1_0 returns CreateExpression
	 *     RelationalExpression returns CreateExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns CreateExpression
	 *     AdditiveExpression returns CreateExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns CreateExpression
	 *     MultiplicativeExpression returns CreateExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns CreateExpression
	 *     SetExpression returns CreateExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns CreateExpression
	 *     UnaryOrInfixExpression returns CreateExpression
	 *     PostopExpression returns CreateExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns CreateExpression
	 *     InfixExpression returns CreateExpression
	 *     InfixExpression.CallFeature_1_0_0 returns CreateExpression
	 *     InfixExpression.AtExpression_1_1_0 returns CreateExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns CreateExpression
	 *     CallExpression returns CreateExpression
	 *     CallExpression.CallFunction_1_0 returns CreateExpression
	 *     PrimaryExpression returns CreateExpression
	 *     ConstructorCallExpression returns CreateExpression
	 *     OneOrManyExpressions returns CreateExpression
	 *     ParanthesizedExpression returns CreateExpression
	 *
	 * Constraint:
	 *     (typeExpr=ID parameterList=ParameterList? alias=ID? contextBlock=InitializationBlockExpression?)
	 * </pre>
	 */
	protected void sequence_ConstructorCallExpression(ISerializationContext context, CreateExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     FeatureOfThis returns FeatureExpression
	 *
	 * Constraint:
	 *     featureName=ID
	 * </pre>
	 */
	protected void sequence_FeatureOfThis(ISerializationContext context, FeatureExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.FEATURE_EXPRESSION__FEATURE_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.FEATURE_EXPRESSION__FEATURE_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFeatureOfThisAccess().getFeatureNameIDTerminalRuleCall_1_0(), semanticObject.getFeatureName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Function returns Function
	 *
	 * Constraint:
	 *     (
	 *         documentation=DOCUMENTATION? 
	 *         visibility=ID? 
	 *         final?='final'? 
	 *         returnType=TypeRef? 
	 *         name=ID 
	 *         (
	 *             (parameters+=ParameterDeclaration parameters+=ParameterDeclaration* (varArgs?='...' parameters+=ParameterDeclaration)?) | 
	 *             (varArgs?='...' parameters+=ParameterDeclaration)
	 *         )? 
	 *         guard=GuardExpression? 
	 *         (funcExpr=Expression | funcExpr=BlockExpression)
	 *     )
	 * </pre>
	 */
	protected void sequence_Function(ISerializationContext context, Function semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     GuardExpression returns GuardExpression
	 *
	 * Constraint:
	 *     (guardExpr=Expression | guardExpr=BlockExpression)
	 * </pre>
	 */
	protected void sequence_GuardExpression(ISerializationContext context, GuardExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns AtExpression
	 *     Expression returns AtExpression
	 *     AssignmentExpression returns AtExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns AtExpression
	 *     CachedExpression returns AtExpression
	 *     OrExpression returns AtExpression
	 *     OrExpression.OrExpression_1_0 returns AtExpression
	 *     AndExpression returns AtExpression
	 *     AndExpression.AndExpression_1_0 returns AtExpression
	 *     RelationalExpression returns AtExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns AtExpression
	 *     AdditiveExpression returns AtExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns AtExpression
	 *     MultiplicativeExpression returns AtExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns AtExpression
	 *     SetExpression returns AtExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns AtExpression
	 *     UnaryOrInfixExpression returns AtExpression
	 *     PostopExpression returns AtExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns AtExpression
	 *     InfixExpression returns AtExpression
	 *     InfixExpression.CallFeature_1_0_0 returns AtExpression
	 *     InfixExpression.AtExpression_1_1_0 returns AtExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns AtExpression
	 *     CallExpression returns AtExpression
	 *     CallExpression.CallFunction_1_0 returns AtExpression
	 *     PrimaryExpression returns AtExpression
	 *     OneOrManyExpressions returns AtExpression
	 *     ParanthesizedExpression returns AtExpression
	 *
	 * Constraint:
	 *     (objExpr=InfixExpression_AtExpression_1_1_0 indexExpr=Expression)
	 * </pre>
	 */
	protected void sequence_InfixExpression(ISerializationContext context, AtExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.AT_EXPRESSION__OBJ_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.AT_EXPRESSION__OBJ_EXPR));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.AT_EXPRESSION__INDEX_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.AT_EXPRESSION__INDEX_EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInfixExpressionAccess().getAtExpressionObjExprAction_1_1_0(), semanticObject.getObjExpr());
		feeder.accept(grammarAccess.getInfixExpressionAccess().getIndexExprExpressionParserRuleCall_1_1_2_0(), semanticObject.getIndexExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns CallFeature
	 *     Expression returns CallFeature
	 *     AssignmentExpression returns CallFeature
	 *     AssignmentExpression.AssignmentExpression_1_0 returns CallFeature
	 *     CachedExpression returns CallFeature
	 *     OrExpression returns CallFeature
	 *     OrExpression.OrExpression_1_0 returns CallFeature
	 *     AndExpression returns CallFeature
	 *     AndExpression.AndExpression_1_0 returns CallFeature
	 *     RelationalExpression returns CallFeature
	 *     RelationalExpression.BinaryOpExpression_1_0 returns CallFeature
	 *     AdditiveExpression returns CallFeature
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns CallFeature
	 *     MultiplicativeExpression returns CallFeature
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns CallFeature
	 *     SetExpression returns CallFeature
	 *     SetExpression.BinaryOpExpression_1_0 returns CallFeature
	 *     UnaryOrInfixExpression returns CallFeature
	 *     PostopExpression returns CallFeature
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns CallFeature
	 *     InfixExpression returns CallFeature
	 *     InfixExpression.CallFeature_1_0_0 returns CallFeature
	 *     InfixExpression.AtExpression_1_1_0 returns CallFeature
	 *     InfixExpression.FeatureExpression_1_2_0 returns CallFeature
	 *     CallExpression returns CallFeature
	 *     CallExpression.CallFunction_1_0 returns CallFeature
	 *     PrimaryExpression returns CallFeature
	 *     OneOrManyExpressions returns CallFeature
	 *     ParanthesizedExpression returns CallFeature
	 *
	 * Constraint:
	 *     (funcExpr=InfixExpression_CallFeature_1_0_0 name=ID parameterList=ParameterList?)
	 * </pre>
	 */
	protected void sequence_InfixExpression(ISerializationContext context, CallFeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns FeatureExpression
	 *     Expression returns FeatureExpression
	 *     AssignmentExpression returns FeatureExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns FeatureExpression
	 *     CachedExpression returns FeatureExpression
	 *     OrExpression returns FeatureExpression
	 *     OrExpression.OrExpression_1_0 returns FeatureExpression
	 *     AndExpression returns FeatureExpression
	 *     AndExpression.AndExpression_1_0 returns FeatureExpression
	 *     RelationalExpression returns FeatureExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns FeatureExpression
	 *     AdditiveExpression returns FeatureExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns FeatureExpression
	 *     MultiplicativeExpression returns FeatureExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns FeatureExpression
	 *     SetExpression returns FeatureExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns FeatureExpression
	 *     UnaryOrInfixExpression returns FeatureExpression
	 *     PostopExpression returns FeatureExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns FeatureExpression
	 *     InfixExpression returns FeatureExpression
	 *     InfixExpression.CallFeature_1_0_0 returns FeatureExpression
	 *     InfixExpression.AtExpression_1_1_0 returns FeatureExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns FeatureExpression
	 *     CallExpression returns FeatureExpression
	 *     CallExpression.CallFunction_1_0 returns FeatureExpression
	 *     PrimaryExpression returns FeatureExpression
	 *     OneOrManyExpressions returns FeatureExpression
	 *     ParanthesizedExpression returns FeatureExpression
	 *
	 * Constraint:
	 *     (objExpr=InfixExpression_FeatureExpression_1_2_0 featureName=ID)
	 * </pre>
	 */
	protected void sequence_InfixExpression(ISerializationContext context, FeatureExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.FEATURE_EXPRESSION__OBJ_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.FEATURE_EXPRESSION__OBJ_EXPR));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.FEATURE_EXPRESSION__FEATURE_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.FEATURE_EXPRESSION__FEATURE_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInfixExpressionAccess().getFeatureExpressionObjExprAction_1_2_0(), semanticObject.getObjExpr());
		feeder.accept(grammarAccess.getInfixExpressionAccess().getFeatureNameIDTerminalRuleCall_1_2_2_0(), semanticObject.getFeatureName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     InitializationBlockExpression returns ChainedExpression
	 *
	 * Constraint:
	 *     expressions+=InitializationExpression
	 * </pre>
	 */
	protected void sequence_InitializationBlockExpression(ISerializationContext context, ChainedExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     InitializationExpression returns AssignmentExpression
	 *
	 * Constraint:
	 *     (leftExpr=FeatureOfThis functionName=':' rightExpr=Expression)
	 * </pre>
	 */
	protected void sequence_InitializationExpression(ISerializationContext context, AssignmentExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__LEFT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__LEFT_EXPR));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__FUNCTION_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__FUNCTION_NAME));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__RIGHT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.ASSIGNMENT_EXPRESSION__RIGHT_EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInitializationExpressionAccess().getLeftExprFeatureOfThisParserRuleCall_1_0(), semanticObject.getLeftExpr());
		feeder.accept(grammarAccess.getInitializationExpressionAccess().getFunctionNameColonKeyword_2_0(), semanticObject.getFunctionName());
		feeder.accept(grammarAccess.getInitializationExpressionAccess().getRightExprExpressionParserRuleCall_3_0(), semanticObject.getRightExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns VariableExpression
	 *     Expression returns VariableExpression
	 *     AssignmentExpression returns VariableExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns VariableExpression
	 *     CachedExpression returns VariableExpression
	 *     OrExpression returns VariableExpression
	 *     OrExpression.OrExpression_1_0 returns VariableExpression
	 *     AndExpression returns VariableExpression
	 *     AndExpression.AndExpression_1_0 returns VariableExpression
	 *     RelationalExpression returns VariableExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns VariableExpression
	 *     AdditiveExpression returns VariableExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns VariableExpression
	 *     MultiplicativeExpression returns VariableExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns VariableExpression
	 *     SetExpression returns VariableExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns VariableExpression
	 *     UnaryOrInfixExpression returns VariableExpression
	 *     PostopExpression returns VariableExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns VariableExpression
	 *     InfixExpression returns VariableExpression
	 *     InfixExpression.CallFeature_1_0_0 returns VariableExpression
	 *     InfixExpression.AtExpression_1_1_0 returns VariableExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns VariableExpression
	 *     CallExpression returns VariableExpression
	 *     CallExpression.CallFunction_1_0 returns VariableExpression
	 *     PrimaryExpression returns VariableExpression
	 *     OneOrManyExpressions returns VariableExpression
	 *     ParanthesizedExpression returns VariableExpression
	 *
	 * Constraint:
	 *     (
	 *         name=ID | 
	 *         name='input' | 
	 *         name='output' | 
	 *         name='source' | 
	 *         name='properties' | 
	 *         name='builder' | 
	 *         name='unit' | 
	 *         name='this'
	 *     )
	 * </pre>
	 */
	protected void sequence_KeywordVariables_Value(ISerializationContext context, VariableExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     KeywordVariables returns VariableExpression
	 *
	 * Constraint:
	 *     (
	 *         name='input' | 
	 *         name='output' | 
	 *         name='source' | 
	 *         name='properties' | 
	 *         name='builder' | 
	 *         name='unit' | 
	 *         name='this'
	 *     )
	 * </pre>
	 */
	protected void sequence_KeywordVariables(ISerializationContext context, VariableExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns Model
	 *
	 * Constraint:
	 *     (units+=Unit+ | functions+=Function+)
	 * </pre>
	 */
	protected void sequence_Model(ISerializationContext context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns CallNamedFunction
	 *     Expression returns CallNamedFunction
	 *     AssignmentExpression returns CallNamedFunction
	 *     AssignmentExpression.AssignmentExpression_1_0 returns CallNamedFunction
	 *     CachedExpression returns CallNamedFunction
	 *     OrExpression returns CallNamedFunction
	 *     OrExpression.OrExpression_1_0 returns CallNamedFunction
	 *     AndExpression returns CallNamedFunction
	 *     AndExpression.AndExpression_1_0 returns CallNamedFunction
	 *     RelationalExpression returns CallNamedFunction
	 *     RelationalExpression.BinaryOpExpression_1_0 returns CallNamedFunction
	 *     AdditiveExpression returns CallNamedFunction
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns CallNamedFunction
	 *     MultiplicativeExpression returns CallNamedFunction
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns CallNamedFunction
	 *     SetExpression returns CallNamedFunction
	 *     SetExpression.BinaryOpExpression_1_0 returns CallNamedFunction
	 *     UnaryOrInfixExpression returns CallNamedFunction
	 *     PostopExpression returns CallNamedFunction
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns CallNamedFunction
	 *     InfixExpression returns CallNamedFunction
	 *     InfixExpression.CallFeature_1_0_0 returns CallNamedFunction
	 *     InfixExpression.AtExpression_1_1_0 returns CallNamedFunction
	 *     InfixExpression.FeatureExpression_1_2_0 returns CallNamedFunction
	 *     CallExpression returns CallNamedFunction
	 *     CallExpression.CallFunction_1_0 returns CallNamedFunction
	 *     PrimaryExpression returns CallNamedFunction
	 *     FeatureCall returns CallNamedFunction
	 *     OperationCall returns CallNamedFunction
	 *     OneOrManyExpressions returns CallNamedFunction
	 *     ParanthesizedExpression returns CallNamedFunction
	 *
	 * Constraint:
	 *     (name=ID parameterList=ParameterList?)
	 * </pre>
	 */
	protected void sequence_OperationCall(ISerializationContext context, CallNamedFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns OrExpression
	 *     Expression returns OrExpression
	 *     AssignmentExpression returns OrExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns OrExpression
	 *     CachedExpression returns OrExpression
	 *     OrExpression returns OrExpression
	 *     OrExpression.OrExpression_1_0 returns OrExpression
	 *     AndExpression returns OrExpression
	 *     AndExpression.AndExpression_1_0 returns OrExpression
	 *     RelationalExpression returns OrExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns OrExpression
	 *     AdditiveExpression returns OrExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns OrExpression
	 *     MultiplicativeExpression returns OrExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns OrExpression
	 *     SetExpression returns OrExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns OrExpression
	 *     UnaryOrInfixExpression returns OrExpression
	 *     PostopExpression returns OrExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns OrExpression
	 *     InfixExpression returns OrExpression
	 *     InfixExpression.CallFeature_1_0_0 returns OrExpression
	 *     InfixExpression.AtExpression_1_1_0 returns OrExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns OrExpression
	 *     CallExpression returns OrExpression
	 *     CallExpression.CallFunction_1_0 returns OrExpression
	 *     PrimaryExpression returns OrExpression
	 *     OneOrManyExpressions returns OrExpression
	 *     ParanthesizedExpression returns OrExpression
	 *
	 * Constraint:
	 *     (leftExpr=OrExpression_OrExpression_1_0 rightExpr=AndExpression)
	 * </pre>
	 */
	protected void sequence_OrExpression(ISerializationContext context, OrExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.OR_EXPRESSION__LEFT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.OR_EXPRESSION__LEFT_EXPR));
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.OR_EXPRESSION__RIGHT_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.OR_EXPRESSION__RIGHT_EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getOrExpressionAccess().getOrExpressionLeftExprAction_1_0(), semanticObject.getLeftExpr());
		feeder.accept(grammarAccess.getOrExpressionAccess().getRightExprAndExpressionParserRuleCall_1_2_0(), semanticObject.getRightExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ParameterDeclaration returns ParameterDeclaration
	 *
	 * Constraint:
	 *     (type=TypeRef? name=ID)
	 * </pre>
	 */
	protected void sequence_ParameterDeclaration(ISerializationContext context, ParameterDeclaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ParameterList returns ParameterList
	 *
	 * Constraint:
	 *     (parameters+=FirstParameter parameters+=FirstParameter*)
	 * </pre>
	 */
	protected void sequence_ParameterList(ISerializationContext context, ParameterList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     FirstParameter returns Parameter
	 *     Parameter returns Parameter
	 *
	 * Constraint:
	 *     expr=Expression
	 * </pre>
	 */
	protected void sequence_Parameter(ISerializationContext context, org.eclipse.xtext.testlanguages.backtracking.beeLangTestLanguage.Parameter semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.PARAMETER__EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.PARAMETER__EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getParameterAccess().getExprExpressionParserRuleCall_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns UnaryPostOpExpression
	 *     Expression returns UnaryPostOpExpression
	 *     AssignmentExpression returns UnaryPostOpExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns UnaryPostOpExpression
	 *     CachedExpression returns UnaryPostOpExpression
	 *     OrExpression returns UnaryPostOpExpression
	 *     OrExpression.OrExpression_1_0 returns UnaryPostOpExpression
	 *     AndExpression returns UnaryPostOpExpression
	 *     AndExpression.AndExpression_1_0 returns UnaryPostOpExpression
	 *     RelationalExpression returns UnaryPostOpExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns UnaryPostOpExpression
	 *     AdditiveExpression returns UnaryPostOpExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns UnaryPostOpExpression
	 *     MultiplicativeExpression returns UnaryPostOpExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns UnaryPostOpExpression
	 *     SetExpression returns UnaryPostOpExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns UnaryPostOpExpression
	 *     UnaryOrInfixExpression returns UnaryPostOpExpression
	 *     PostopExpression returns UnaryPostOpExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns UnaryPostOpExpression
	 *     InfixExpression returns UnaryPostOpExpression
	 *     InfixExpression.CallFeature_1_0_0 returns UnaryPostOpExpression
	 *     InfixExpression.AtExpression_1_1_0 returns UnaryPostOpExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns UnaryPostOpExpression
	 *     CallExpression returns UnaryPostOpExpression
	 *     CallExpression.CallFunction_1_0 returns UnaryPostOpExpression
	 *     PrimaryExpression returns UnaryPostOpExpression
	 *     OneOrManyExpressions returns UnaryPostOpExpression
	 *     ParanthesizedExpression returns UnaryPostOpExpression
	 *
	 * Constraint:
	 *     (expr=PostopExpression_UnaryPostOpExpression_1_0 (functionName='--' | functionName='++'))
	 * </pre>
	 */
	protected void sequence_PostopExpression(ISerializationContext context, UnaryPostOpExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns UnaryPreOpExpression
	 *     Expression returns UnaryPreOpExpression
	 *     AssignmentExpression returns UnaryPreOpExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns UnaryPreOpExpression
	 *     CachedExpression returns UnaryPreOpExpression
	 *     OrExpression returns UnaryPreOpExpression
	 *     OrExpression.OrExpression_1_0 returns UnaryPreOpExpression
	 *     AndExpression returns UnaryPreOpExpression
	 *     AndExpression.AndExpression_1_0 returns UnaryPreOpExpression
	 *     RelationalExpression returns UnaryPreOpExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns UnaryPreOpExpression
	 *     AdditiveExpression returns UnaryPreOpExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns UnaryPreOpExpression
	 *     MultiplicativeExpression returns UnaryPreOpExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns UnaryPreOpExpression
	 *     SetExpression returns UnaryPreOpExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns UnaryPreOpExpression
	 *     UnaryOrInfixExpression returns UnaryPreOpExpression
	 *     PreopExpression returns UnaryPreOpExpression
	 *     PostopExpression returns UnaryPreOpExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns UnaryPreOpExpression
	 *     InfixExpression returns UnaryPreOpExpression
	 *     InfixExpression.CallFeature_1_0_0 returns UnaryPreOpExpression
	 *     InfixExpression.AtExpression_1_1_0 returns UnaryPreOpExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns UnaryPreOpExpression
	 *     CallExpression returns UnaryPreOpExpression
	 *     CallExpression.CallFunction_1_0 returns UnaryPreOpExpression
	 *     PrimaryExpression returns UnaryPreOpExpression
	 *     OneOrManyExpressions returns UnaryPreOpExpression
	 *     ParanthesizedExpression returns UnaryPreOpExpression
	 *
	 * Constraint:
	 *     ((functionName='++' | functionName='--') expr=InfixExpression)
	 * </pre>
	 */
	protected void sequence_PreopExpression(ISerializationContext context, UnaryPreOpExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ProvidedCapability returns ProvidedCapability
	 *
	 * Constraint:
	 *     (nameSpace=ID? (condExpr=Expression | name=ID | version=ID)*)
	 * </pre>
	 */
	protected void sequence_ProvidedCapability(ISerializationContext context, ProvidedCapability semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     RequiredCapability returns RequiredCapability
	 *
	 * Constraint:
	 *     (nameSpace=ID? name=ID (condExpr=Expression | greedy?='greedy' | min=INT | max=INT | versionRange=ID)*)
	 * </pre>
	 */
	protected void sequence_RequiredCapability(ISerializationContext context, RequiredCapability semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TypeRef returns SimpleTypeRef
	 *     SimpleTypeRef returns SimpleTypeRef
	 *
	 * Constraint:
	 *     (rawType=ID (actualArgumentsList+=ID actualArgumentsList+=ID*)?)
	 * </pre>
	 */
	protected void sequence_SimpleTypeRef(ISerializationContext context, SimpleTypeRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns UnaryOpExpression
	 *     Expression returns UnaryOpExpression
	 *     AssignmentExpression returns UnaryOpExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns UnaryOpExpression
	 *     CachedExpression returns UnaryOpExpression
	 *     OrExpression returns UnaryOpExpression
	 *     OrExpression.OrExpression_1_0 returns UnaryOpExpression
	 *     AndExpression returns UnaryOpExpression
	 *     AndExpression.AndExpression_1_0 returns UnaryOpExpression
	 *     RelationalExpression returns UnaryOpExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns UnaryOpExpression
	 *     AdditiveExpression returns UnaryOpExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns UnaryOpExpression
	 *     MultiplicativeExpression returns UnaryOpExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns UnaryOpExpression
	 *     SetExpression returns UnaryOpExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns UnaryOpExpression
	 *     UnaryOrInfixExpression returns UnaryOpExpression
	 *     UnaryExpression returns UnaryOpExpression
	 *     PostopExpression returns UnaryOpExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns UnaryOpExpression
	 *     InfixExpression returns UnaryOpExpression
	 *     InfixExpression.CallFeature_1_0_0 returns UnaryOpExpression
	 *     InfixExpression.AtExpression_1_1_0 returns UnaryOpExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns UnaryOpExpression
	 *     CallExpression returns UnaryOpExpression
	 *     CallExpression.CallFunction_1_0 returns UnaryOpExpression
	 *     PrimaryExpression returns UnaryOpExpression
	 *     OneOrManyExpressions returns UnaryOpExpression
	 *     ParanthesizedExpression returns UnaryOpExpression
	 *
	 * Constraint:
	 *     ((functionName='!' | functionName='-') expr=InfixExpression)
	 * </pre>
	 */
	protected void sequence_UnaryExpression(ISerializationContext context, UnaryOpExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Unit returns Unit
	 *
	 * Constraint:
	 *     (
	 *         documentation=DOCUMENTATION? 
	 *         name=ID? 
	 *         version=ID? 
	 *         (implements+=SimpleTypeRef implements+=SimpleTypeRef*)? 
	 *         (
	 *             sourceLocation=Path | 
	 *             outputLocation=Path | 
	 *             providedCapabilities+=ProvidedCapability | 
	 *             requiredCapabilities+=AliasedRequiredCapability | 
	 *             metaRequiredCapabilities+=RequiredCapability | 
	 *             functions+=Function
	 *         )*
	 *     )
	 * </pre>
	 */
	protected void sequence_Unit(ISerializationContext context, Unit semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ValDeclaration returns DefValue
	 *
	 * Constraint:
	 *     (final?='final'? immutable?='val' type=TypeRef? name=ID valueExpr=Expression)
	 * </pre>
	 */
	protected void sequence_ValDeclaration(ISerializationContext context, DefValue semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns DefValue
	 *
	 * Constraint:
	 *     ((final?='final'? type=TypeRef? name=ID valueExpr=Expression?) | (final?='final'? immutable?='val' type=TypeRef? name=ID valueExpr=Expression))
	 * </pre>
	 */
	protected void sequence_ValDeclaration_VarDeclaration(ISerializationContext context, DefValue semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns ValueLiteral
	 *     Expression returns ValueLiteral
	 *     AssignmentExpression returns ValueLiteral
	 *     AssignmentExpression.AssignmentExpression_1_0 returns ValueLiteral
	 *     CachedExpression returns ValueLiteral
	 *     OrExpression returns ValueLiteral
	 *     OrExpression.OrExpression_1_0 returns ValueLiteral
	 *     AndExpression returns ValueLiteral
	 *     AndExpression.AndExpression_1_0 returns ValueLiteral
	 *     RelationalExpression returns ValueLiteral
	 *     RelationalExpression.BinaryOpExpression_1_0 returns ValueLiteral
	 *     AdditiveExpression returns ValueLiteral
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns ValueLiteral
	 *     MultiplicativeExpression returns ValueLiteral
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns ValueLiteral
	 *     SetExpression returns ValueLiteral
	 *     SetExpression.BinaryOpExpression_1_0 returns ValueLiteral
	 *     UnaryOrInfixExpression returns ValueLiteral
	 *     PostopExpression returns ValueLiteral
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns ValueLiteral
	 *     InfixExpression returns ValueLiteral
	 *     InfixExpression.CallFeature_1_0_0 returns ValueLiteral
	 *     InfixExpression.AtExpression_1_1_0 returns ValueLiteral
	 *     InfixExpression.FeatureExpression_1_2_0 returns ValueLiteral
	 *     CallExpression returns ValueLiteral
	 *     CallExpression.CallFunction_1_0 returns ValueLiteral
	 *     PrimaryExpression returns ValueLiteral
	 *     Literal returns ValueLiteral
	 *     OneOrManyExpressions returns ValueLiteral
	 *     ValueLiteral returns ValueLiteral
	 *     ParanthesizedExpression returns ValueLiteral
	 *
	 * Constraint:
	 *     value=STRING
	 * </pre>
	 */
	protected void sequence_ValueLiteral(ISerializationContext context, ValueLiteral semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.VALUE_LITERAL__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.VALUE_LITERAL__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getValueLiteralAccess().getValueSTRINGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Value returns VariableExpression
	 *
	 * Constraint:
	 *     name=ID
	 * </pre>
	 */
	protected void sequence_Value(ISerializationContext context, VariableExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BeeLangTestLanguagePackage.Literals.VARIABLE_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BeeLangTestLanguagePackage.Literals.VARIABLE_EXPRESSION__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getValueAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     VarDeclaration returns DefValue
	 *
	 * Constraint:
	 *     (final?='final'? type=TypeRef? name=ID valueExpr=Expression?)
	 * </pre>
	 */
	protected void sequence_VarDeclaration(ISerializationContext context, DefValue semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns WithContextExpression
	 *     Expression returns WithContextExpression
	 *     AssignmentExpression returns WithContextExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns WithContextExpression
	 *     CachedExpression returns WithContextExpression
	 *     OrExpression returns WithContextExpression
	 *     OrExpression.OrExpression_1_0 returns WithContextExpression
	 *     AndExpression returns WithContextExpression
	 *     AndExpression.AndExpression_1_0 returns WithContextExpression
	 *     RelationalExpression returns WithContextExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns WithContextExpression
	 *     AdditiveExpression returns WithContextExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns WithContextExpression
	 *     MultiplicativeExpression returns WithContextExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns WithContextExpression
	 *     SetExpression returns WithContextExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns WithContextExpression
	 *     UnaryOrInfixExpression returns WithContextExpression
	 *     PostopExpression returns WithContextExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns WithContextExpression
	 *     InfixExpression returns WithContextExpression
	 *     InfixExpression.CallFeature_1_0_0 returns WithContextExpression
	 *     InfixExpression.AtExpression_1_1_0 returns WithContextExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns WithContextExpression
	 *     CallExpression returns WithContextExpression
	 *     CallExpression.CallFunction_1_0 returns WithContextExpression
	 *     PrimaryExpression returns WithContextExpression
	 *     WithContextExpression returns WithContextExpression
	 *     OneOrManyExpressions returns WithContextExpression
	 *     ParanthesizedExpression returns WithContextExpression
	 *
	 * Constraint:
	 *     (expr=Expression alias=ID? contextBlock=BlockExpression)
	 * </pre>
	 */
	protected void sequence_WithContextExpression(ISerializationContext context, WithContextExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TopLevelExpression returns WithExpression
	 *     Expression returns WithExpression
	 *     AssignmentExpression returns WithExpression
	 *     AssignmentExpression.AssignmentExpression_1_0 returns WithExpression
	 *     CachedExpression returns WithExpression
	 *     OrExpression returns WithExpression
	 *     OrExpression.OrExpression_1_0 returns WithExpression
	 *     AndExpression returns WithExpression
	 *     AndExpression.AndExpression_1_0 returns WithExpression
	 *     RelationalExpression returns WithExpression
	 *     RelationalExpression.BinaryOpExpression_1_0 returns WithExpression
	 *     AdditiveExpression returns WithExpression
	 *     AdditiveExpression.BinaryOpExpression_1_0 returns WithExpression
	 *     MultiplicativeExpression returns WithExpression
	 *     MultiplicativeExpression.BinaryOpExpression_1_0 returns WithExpression
	 *     SetExpression returns WithExpression
	 *     SetExpression.BinaryOpExpression_1_0 returns WithExpression
	 *     UnaryOrInfixExpression returns WithExpression
	 *     PostopExpression returns WithExpression
	 *     PostopExpression.UnaryPostOpExpression_1_0 returns WithExpression
	 *     InfixExpression returns WithExpression
	 *     InfixExpression.CallFeature_1_0_0 returns WithExpression
	 *     InfixExpression.AtExpression_1_1_0 returns WithExpression
	 *     InfixExpression.FeatureExpression_1_2_0 returns WithExpression
	 *     CallExpression returns WithExpression
	 *     CallExpression.CallFunction_1_0 returns WithExpression
	 *     PrimaryExpression returns WithExpression
	 *     WithExpression returns WithExpression
	 *     OneOrManyExpressions returns WithExpression
	 *     ParanthesizedExpression returns WithExpression
	 *
	 * Constraint:
	 *     ((referencedAdvice+=ID referencedAdvice+=ID*)? (funcExpr=Expression | funcExpr=BlockExpressionWithoutBrackets))
	 * </pre>
	 */
	protected void sequence_WithExpression(ISerializationContext context, WithExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}

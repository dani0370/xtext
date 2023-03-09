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
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.eclipse.xtext.testlanguages.backtracking.services.SimpleBeeLangTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class SimpleBeeLangTestLanguageSyntacticSequencer extends AbstractSyntacticSequencer {

	protected SimpleBeeLangTestLanguageGrammarAccess grammarAccess;
	protected AbstractElementAlias match_ClosureExpression_VerticalLineKeyword_2_0_0_q;
	protected AbstractElementAlias match_ConstructorCallExpression___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q;
	protected AbstractElementAlias match_Function___LeftParenthesisKeyword_6_0_RightParenthesisKeyword_6_2__q;
	protected AbstractElementAlias match_ParanthesizedExpression_LeftParenthesisKeyword_0_a;
	protected AbstractElementAlias match_ParanthesizedExpression_LeftParenthesisKeyword_0_p;
	protected AbstractElementAlias match_ProvidedCapability___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_2__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (SimpleBeeLangTestLanguageGrammarAccess) access;
		match_ClosureExpression_VerticalLineKeyword_2_0_0_q = new TokenAlias(false, true, grammarAccess.getClosureExpressionAccess().getVerticalLineKeyword_2_0_0());
		match_ConstructorCallExpression___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getConstructorCallExpressionAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getConstructorCallExpressionAccess().getRightParenthesisKeyword_3_2()));
		match_Function___LeftParenthesisKeyword_6_0_RightParenthesisKeyword_6_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_6_0()), new TokenAlias(false, false, grammarAccess.getFunctionAccess().getRightParenthesisKeyword_6_2()));
		match_ParanthesizedExpression_LeftParenthesisKeyword_0_a = new TokenAlias(true, true, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
		match_ParanthesizedExpression_LeftParenthesisKeyword_0_p = new TokenAlias(true, false, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
		match_ProvidedCapability___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getProvidedCapabilityAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getProvidedCapabilityAccess().getRightCurlyBracketKeyword_2_2()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_ClosureExpression_VerticalLineKeyword_2_0_0_q.equals(syntax))
				emit_ClosureExpression_VerticalLineKeyword_2_0_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ConstructorCallExpression___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q.equals(syntax))
				emit_ConstructorCallExpression___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Function___LeftParenthesisKeyword_6_0_RightParenthesisKeyword_6_2__q.equals(syntax))
				emit_Function___LeftParenthesisKeyword_6_0_RightParenthesisKeyword_6_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ParanthesizedExpression_LeftParenthesisKeyword_0_a.equals(syntax))
				emit_ParanthesizedExpression_LeftParenthesisKeyword_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ParanthesizedExpression_LeftParenthesisKeyword_0_p.equals(syntax))
				emit_ParanthesizedExpression_LeftParenthesisKeyword_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProvidedCapability___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_2__q.equals(syntax))
				emit_ProvidedCapability___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     '|'?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) '('* '{' (ambiguity) parameters+=ParameterDeclaration
	 *     (rule start) '('+ '{' (ambiguity) parameters+=ParameterDeclaration
	 *     (rule start) '{' (ambiguity) parameters+=ParameterDeclaration
	 *     (rule start) (ambiguity) parameters+=ParameterDeclaration
	 *     returnType=TypeRef '&gt;' (ambiguity) parameters+=ParameterDeclaration
	 
	 * </pre>
	 */
	protected void emit_ClosureExpression_VerticalLineKeyword_2_0_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     typeExpr=ID (ambiguity) ')' (rule end)
	 *     typeExpr=ID (ambiguity) 'as' alias=ID
	 *     typeExpr=ID (ambiguity) (rule end)
	 *     typeExpr=ID (ambiguity) contextBlock=InitializationBlockExpression
	 
	 * </pre>
	 */
	protected void emit_ConstructorCallExpression___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=ID (ambiguity) ':' funcExpr=Expression
	 *     name=ID (ambiguity) 'when' guard=GuardExpression
	 *     name=ID (ambiguity) funcExpr=BlockExpression
	 
	 * </pre>
	 */
	protected void emit_Function___LeftParenthesisKeyword_6_0_RightParenthesisKeyword_6_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     '('*
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) 'cached' expr=OrExpression
	 *     (rule start) (ambiguity) 'new' typeExpr=ID
	 *     (rule start) (ambiguity) 'with' ':' funcExpr=Expression
	 *     (rule start) (ambiguity) 'with' 'context' expr=Expression
	 *     (rule start) (ambiguity) 'with' '{' funcExpr=BlockExpressionWithoutBrackets
	 *     (rule start) (ambiguity) 'with' referencedAdvice+=ID
	 *     (rule start) (ambiguity) '{' '&lt;' returnType=TypeRef
	 *     (rule start) (ambiguity) '{' '|' funcExpr=OneOrManyExpressions
	 *     (rule start) (ambiguity) '{' '|'? parameters+=ParameterDeclaration
	 *     (rule start) (ambiguity) '{' '}' (rule start)
	 *     (rule start) (ambiguity) '{' expressions+=TopLevelExpression
	 *     (rule start) (ambiguity) '{' varArgs?='...'
	 *     (rule start) (ambiguity) functionName='!'
	 *     (rule start) (ambiguity) functionName='++'
	 *     (rule start) (ambiguity) functionName='-'
	 *     (rule start) (ambiguity) functionName='--'
	 *     (rule start) (ambiguity) name='builder'
	 *     (rule start) (ambiguity) name='input'
	 *     (rule start) (ambiguity) name='output'
	 *     (rule start) (ambiguity) name='properties'
	 *     (rule start) (ambiguity) name='source'
	 *     (rule start) (ambiguity) name='this'
	 *     (rule start) (ambiguity) name='unit'
	 *     (rule start) (ambiguity) name=ID
	 *     (rule start) (ambiguity) value=STRING
	 *     (rule start) (ambiguity) {AndExpression.leftExpr=}
	 *     (rule start) (ambiguity) {AssignmentExpression.leftExpr=}
	 *     (rule start) (ambiguity) {AtExpression.objExpr=}
	 *     (rule start) (ambiguity) {BinaryOpExpression.leftExpr=}
	 *     (rule start) (ambiguity) {CallFeature.funcExpr=}
	 *     (rule start) (ambiguity) {CallFunction.funcExpr=}
	 *     (rule start) (ambiguity) {FeatureExpression.objExpr=}
	 *     (rule start) (ambiguity) {OrExpression.leftExpr=}
	 *     (rule start) (ambiguity) {UnaryPostOpExpression.expr=}
	 
	 * </pre>
	 */
	protected void emit_ParanthesizedExpression_LeftParenthesisKeyword_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     '('+
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) 'cached' expr=OrExpression
	 *     (rule start) (ambiguity) 'new' typeExpr=ID
	 *     (rule start) (ambiguity) 'with' ':' funcExpr=Expression
	 *     (rule start) (ambiguity) 'with' 'context' expr=Expression
	 *     (rule start) (ambiguity) 'with' '{' funcExpr=BlockExpressionWithoutBrackets
	 *     (rule start) (ambiguity) 'with' referencedAdvice+=ID
	 *     (rule start) (ambiguity) '{' '&lt;' returnType=TypeRef
	 *     (rule start) (ambiguity) '{' '|' funcExpr=OneOrManyExpressions
	 *     (rule start) (ambiguity) '{' '|'? parameters+=ParameterDeclaration
	 *     (rule start) (ambiguity) '{' '}' ')' (rule start)
	 *     (rule start) (ambiguity) '{' expressions+=TopLevelExpression
	 *     (rule start) (ambiguity) '{' varArgs?='...'
	 *     (rule start) (ambiguity) functionName='!'
	 *     (rule start) (ambiguity) functionName='++'
	 *     (rule start) (ambiguity) functionName='-'
	 *     (rule start) (ambiguity) functionName='--'
	 *     (rule start) (ambiguity) name='builder'
	 *     (rule start) (ambiguity) name='input'
	 *     (rule start) (ambiguity) name='output'
	 *     (rule start) (ambiguity) name='properties'
	 *     (rule start) (ambiguity) name='source'
	 *     (rule start) (ambiguity) name='this'
	 *     (rule start) (ambiguity) name='unit'
	 *     (rule start) (ambiguity) name=ID
	 *     (rule start) (ambiguity) value=STRING
	 *     (rule start) (ambiguity) {AndExpression.leftExpr=}
	 *     (rule start) (ambiguity) {AssignmentExpression.leftExpr=}
	 *     (rule start) (ambiguity) {AtExpression.objExpr=}
	 *     (rule start) (ambiguity) {BinaryOpExpression.leftExpr=}
	 *     (rule start) (ambiguity) {CallFeature.funcExpr=}
	 *     (rule start) (ambiguity) {CallFunction.funcExpr=}
	 *     (rule start) (ambiguity) {FeatureExpression.objExpr=}
	 *     (rule start) (ambiguity) {OrExpression.leftExpr=}
	 *     (rule start) (ambiguity) {UnaryPostOpExpression.expr=}
	 
	 * </pre>
	 */
	protected void emit_ParanthesizedExpression_LeftParenthesisKeyword_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     ('{' '}')?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'unit' (ambiguity) (rule start)
	 *     nameSpace=ID (ambiguity) (rule end)
	 
	 * </pre>
	 */
	protected void emit_ProvidedCapability___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}

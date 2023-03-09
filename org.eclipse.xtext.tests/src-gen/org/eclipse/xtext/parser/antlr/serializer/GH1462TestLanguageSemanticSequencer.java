/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parser.antlr.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parser.antlr.gh1462Test.Gh1462TestPackage;
import org.eclipse.xtext.parser.antlr.gh1462Test.Root;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule1;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule10;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule11;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule12;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule2;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule3;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule4;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule5;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule6;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule7;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule8;
import org.eclipse.xtext.parser.antlr.gh1462Test.Rule9;
import org.eclipse.xtext.parser.antlr.gh1462Test.Wrapper;
import org.eclipse.xtext.parser.antlr.services.GH1462TestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class GH1462TestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private GH1462TestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == Gh1462TestPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case Gh1462TestPackage.ROOT:
				sequence_Root(context, (Root) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE1:
				sequence_Rule1(context, (Rule1) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE10:
				sequence_Rule10(context, (Rule10) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE11:
				sequence_Rule11(context, (Rule11) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE12:
				sequence_Rule12(context, (Rule12) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE2:
				sequence_Rule2(context, (Rule2) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE3:
				sequence_Rule3(context, (Rule3) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE4:
				sequence_Rule4(context, (Rule4) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE5:
				sequence_Rule5(context, (Rule5) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE6:
				sequence_Rule6(context, (Rule6) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE7:
				sequence_Rule7(context, (Rule7) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE8:
				sequence_Rule8(context, (Rule8) semanticObject); 
				return; 
			case Gh1462TestPackage.RULE9:
				sequence_Rule9(context, (Rule9) semanticObject); 
				return; 
			case Gh1462TestPackage.WRAPPER:
				sequence_Wrapper(context, (Wrapper) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Root returns Root
	 *
	 * Constraint:
	 *     (
	 *         element=Rule1 | 
	 *         element=Rule2 | 
	 *         element=Rule3 | 
	 *         element=Rule4 | 
	 *         element=Rule5 | 
	 *         element=Rule6 | 
	 *         element=Rule7 | 
	 *         element=Rule8 | 
	 *         element=Rule9 | 
	 *         element=Rule10 | 
	 *         element=Rule11 | 
	 *         element=Rule12
	 *     )
	 * </pre>
	 */
	protected void sequence_Root(ISerializationContext context, Root semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule10 returns Rule10
	 *
	 * Constraint:
	 *     (left=INT right?=MaybeEmpty unit='s')
	 * </pre>
	 */
	protected void sequence_Rule10(ISerializationContext context, Rule10 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE10__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE10__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE10__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE10__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE10__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE10__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule10Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule10Access().getRightMaybeEmptyParserRuleCall_1_0(), semanticObject.isRight());
		feeder.accept(grammarAccess.getRule10Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule11 returns Rule11
	 *
	 * Constraint:
	 *     (left=INT right?=Integer unit='s')
	 * </pre>
	 */
	protected void sequence_Rule11(ISerializationContext context, Rule11 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE11__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE11__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE11__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE11__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE11__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE11__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule11Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule11Access().getRightIntegerParserRuleCall_1_0(), semanticObject.isRight());
		feeder.accept(grammarAccess.getRule11Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule12 returns Rule12
	 *
	 * Constraint:
	 *     (left=INT right?=Wrapper unit='s')
	 * </pre>
	 */
	protected void sequence_Rule12(ISerializationContext context, Rule12 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE12__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE12__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE12__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE12__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE12__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE12__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule12Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule12Access().getRightWrapperParserRuleCall_1_0(), semanticObject.isRight());
		feeder.accept(grammarAccess.getRule12Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule1 returns Rule1
	 *
	 * Constraint:
	 *     (left=INT right=INT unit='s')
	 * </pre>
	 */
	protected void sequence_Rule1(ISerializationContext context, Rule1 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE1__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE1__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE1__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE1__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE1__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE1__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule1Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule1Access().getRightINTTerminalRuleCall_1_0(), semanticObject.getRight());
		feeder.accept(grammarAccess.getRule1Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule2 returns Rule2
	 *
	 * Constraint:
	 *     (value=INT unit='s')
	 * </pre>
	 */
	protected void sequence_Rule2(ISerializationContext context, Rule2 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE2__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE2__VALUE));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE2__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE2__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule2Access().getValueINTTerminalRuleCall_1_0(), semanticObject.getValue());
		feeder.accept(grammarAccess.getRule2Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule3 returns Rule3
	 *
	 * Constraint:
	 *     (left=ID right=STRING unit='s')
	 * </pre>
	 */
	protected void sequence_Rule3(ISerializationContext context, Rule3 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE3__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE3__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE3__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE3__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE3__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE3__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule3Access().getLeftIDTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule3Access().getRightSTRINGTerminalRuleCall_1_0(), semanticObject.getRight());
		feeder.accept(grammarAccess.getRule3Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule4 returns Rule4
	 *
	 * Constraint:
	 *     (left=STRING right=ID unit='s')
	 * </pre>
	 */
	protected void sequence_Rule4(ISerializationContext context, Rule4 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE4__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE4__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE4__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE4__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE4__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE4__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule4Access().getLeftSTRINGTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule4Access().getRightIDTerminalRuleCall_1_0(), semanticObject.getRight());
		feeder.accept(grammarAccess.getRule4Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule5 returns Rule5
	 *
	 * Constraint:
	 *     (left=INT right=Integer unit='s')
	 * </pre>
	 */
	protected void sequence_Rule5(ISerializationContext context, Rule5 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE5__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE5__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE5__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE5__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE5__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE5__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule5Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule5Access().getRightIntegerParserRuleCall_1_0(), semanticObject.getRight());
		feeder.accept(grammarAccess.getRule5Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule6 returns Rule6
	 *
	 * Constraint:
	 *     (left=INT right=Wrapper unit='s')
	 * </pre>
	 */
	protected void sequence_Rule6(ISerializationContext context, Rule6 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE6__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE6__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE6__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE6__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE6__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE6__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule6Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule6Access().getRightWrapperParserRuleCall_1_0(), semanticObject.getRight());
		feeder.accept(grammarAccess.getRule6Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule7 returns Rule7
	 *
	 * Constraint:
	 *     (left=INT right?=INT unit='s')
	 * </pre>
	 */
	protected void sequence_Rule7(ISerializationContext context, Rule7 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE7__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE7__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE7__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE7__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE7__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE7__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule7Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule7Access().getRightINTTerminalRuleCall_1_0(), semanticObject.isRight());
		feeder.accept(grammarAccess.getRule7Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule8 returns Rule8
	 *
	 * Constraint:
	 *     (left=INT right?='#1' unit='s')
	 * </pre>
	 */
	protected void sequence_Rule8(ISerializationContext context, Rule8 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE8__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE8__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE8__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE8__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE8__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE8__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule8Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule8Access().getRight1Keyword_1_0(), semanticObject.isRight());
		feeder.accept(grammarAccess.getRule8Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Rule9 returns Rule9
	 *
	 * Constraint:
	 *     (left=INT right=MaybeEmpty unit='s')
	 * </pre>
	 */
	protected void sequence_Rule9(ISerializationContext context, Rule9 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE9__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE9__LEFT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE9__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE9__RIGHT));
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.RULE9__UNIT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.RULE9__UNIT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRule9Access().getLeftINTTerminalRuleCall_0_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getRule9Access().getRightMaybeEmptyParserRuleCall_1_0(), semanticObject.getRight());
		feeder.accept(grammarAccess.getRule9Access().getUnitSKeyword_2_0(), semanticObject.getUnit());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Wrapper returns Wrapper
	 *
	 * Constraint:
	 *     value=INT
	 * </pre>
	 */
	protected void sequence_Wrapper(ISerializationContext context, Wrapper semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Gh1462TestPackage.Literals.WRAPPER__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Gh1462TestPackage.Literals.WRAPPER__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getWrapperAccess().getValueINTTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
}

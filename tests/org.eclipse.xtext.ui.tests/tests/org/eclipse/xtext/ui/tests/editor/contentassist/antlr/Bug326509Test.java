/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist.antlr;

import junit.framework.TestCase;

import org.antlr.runtime.BitSet;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug326509Test extends TestCase {

	public static class TestableParser extends AbstractInternalContentAssistParser {

		public TestableParser() {
			super(null);
		}
		
		public BitSet[] getFollowing() {
			return following;
		}
		
		@Override
		public void pushFollow(BitSet bitSet) {
			super.pushFollow(bitSet);
		}

		@Override
		protected Grammar getGrammar() {
			return null;
		}
		
	}
	
	private TestableParser testMe;
	
	@Override
	protected void setUp() throws Exception {
		testMe = new TestableParser();
	}
	
	public void testBug326509() {
		for(int i = 0; i <= 200; i++) {
			testMe.pushFollow(new BitSet());
		}
		BitSet[] following = testMe.getFollowing();
		for(int i = 0; i < following.length && i <= 200; i++) {
			assertNotNull(Integer.toString(i), following[i]);
		}
	}
	
}

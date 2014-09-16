/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.util;

import org.eclipse.emf.common.util.WrappedException;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class Exceptions {

	public static <T> T throwUncheckedException(Throwable e) {
		if (e instanceof RuntimeException)
			throw (RuntimeException) e;
		if (e instanceof Error)
			throw (Error) e;
		if (e instanceof Exception)
			throw new WrappedException((Exception) e);
		throw new RuntimeException(e);
	}
	
	/**
	 * @since 2.8
	 */
	public static RuntimeException getOperationCanceledException(Throwable t) {
		if (t instanceof RuntimeException) {
			RuntimeException re = (RuntimeException) t;
			if (re.getClass().getName().equals("com.intellij.openapi.progress.ProcessCanceledException")) {
				return re;
			}
			if (re.getClass().getName().equals("org.eclipse.core.runtime.OperationCanceledException")) {
				return re;
			}
		}
		return null;
	}
	
	/**
	 * @since 2.8
	 */
	public static void throwIfOperationCanceledException(Throwable t) {
		RuntimeException opCanceledException = getOperationCanceledException(t);
		if (opCanceledException != null) {
			throw opCanceledException;
		}
	}
}

/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

define(function() {
	
	function MockEditorContext(text, caretOffset) {
		if (text)
			this._text = text;
		else
			this._text = "";
		
		if (caretOffset)
			this._caretOffset = caretOffset;
		else
			this._caretOffset = 0;
		
		this._selection = {
			start: 0,
			end: 0
		};
		this._dirty = false;
		this._serverState = {
			stateId: '0',
			text: ''
		};
		this._serverStateListeners = [];
		this._modelChangeListeners = [];
		this._clientServiceState = {};
		this._markers = [];
	};

	MockEditorContext.prototype = {
		
		getServerState : function() {
			return this._serverState;
		},
		
		updateServerState : function(currentText, currentStateId) {
			this._serverState.text = currentText;
			this._serverState.stateId = currentStateId;
			return this._serverStateListeners;
		},
		
		addServerStateListener : function(listener) {
			this._serverStateListeners.push(listener);
		},
		
		addModelChangeListener : function(listener) {
			this._modelChangeListeners.push(listener);
		},
		
		triggerModelChange : function(text, start, end) {
			this._setText(text, start, end);
			for (var i in this._modelChangeListeners) {
				var listener = this._modelChangeListeners[i];
				listener(text);
			}
		},
		
		getClientServiceState : function() {
			return this._clientServiceState;
		},
		
		clearClientServiceState : function() {
			this._clientServiceState = {};
		},
		
		getCaretOffset : function() {
			return this._caretOffset;
		},
		
		getLineStart : function(lineNumber) {
			throw "Not supported: getLineStart";
		},
		
		getSelection : function() {
			return this._selection;
		},
		
		getText : function(start, end) {
			if (start && end)
				return this._text.substring(start, end);
			else
				return this._text;
		},
		
		isDirty : function() {
			return this._dirty;
		},
		
		markClean : function(clean) {
			this._dirty = !clean;
		},
		
		clearUndoStack : function() {
		},
		
		setCaretOffset : function(offset) {
			this._caretOffset = offset;
		},
		
		setSelection : function(selection) {
			this._selection = selection;
		},
		
		setText : function(text, start, end) {
			if (start && end)
				this._text = this._text.substring(0, start) + text + this._text.substring(end);
			else
				this._text = text;
		},
		
		showMarkers : function(entries) {
			this._markers = entries;
		},
		
		getMarkers : function() {
			return this._markers;
		},
		
		translateCompletionProposals : function(entries) {
			return entries;
		}
	};
	
	return MockEditorContext;
});
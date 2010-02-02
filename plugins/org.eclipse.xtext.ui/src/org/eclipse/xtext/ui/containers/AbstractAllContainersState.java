/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.containers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.Wrapper;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public abstract class AbstractAllContainersState implements IResourceChangeListener, IAllContainersState {

	private final static Logger log = Logger.getLogger(AbstractAllContainersState.class);
	
	@Inject
	private IStorage2UriMapper mapper;
	
	private Map<URI, String> uriToHandle;
	private ListMultimap<String, String> handleToVisibleHandles;
	private SetMultimap<String, URI> handleToContent;
	private Set<String> emptyHandles;
	
	private ReentrantReadWriteLock readWriteLock;
	private ReadLock readLock;
	private WriteLock writeLock;
	
	protected AbstractAllContainersState() {
		readWriteLock = new ReentrantReadWriteLock();
		readLock = readWriteLock.readLock();
		writeLock = readWriteLock.writeLock();
		initialize();
		registerAsListener();
	}
	
	protected void registerAsListener() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, 
			  IResourceChangeEvent.PRE_DELETE 
			| IResourceChangeEvent.POST_CHANGE 
			| IResourceChangeEvent.PRE_CLOSE
			);
	}
	
	public void unregisterAsListener() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}
	
	protected void initialize() {
		try {
			writeLock.lock();
			uriToHandle = Collections.synchronizedMap(Maps.<URI, String>newHashMap());
			handleToVisibleHandles = Multimaps.newArrayListMultimap();
			handleToContent = Multimaps.newLinkedHashMultimap();
			emptyHandles = Collections.synchronizedSet(Sets.<String>newHashSet());
		} finally {
			writeLock.unlock();
		}
	}
	
	public String getContainerHandle(URI uri) {
		String result = null;
		try {
			readLock.lock();
			result = uriToHandle.get(uri);
		} finally {
			readLock.unlock();
		}
		if (result == null) {
			result = initHandle(uri);
		}
		return result;
	}
	
	protected String initHandle(URI uri) {
		String result = doInitHandle(uri);
		try {
			writeLock.lock();
			uriToHandle.put(uri, result);
			return result;
		} finally {
			writeLock.unlock();
		} 
	}
	
	protected abstract String doInitHandle(URI uri);
	
	public Collection<URI> getContainedURIs(String containerHandle) {
		Collection<URI> result = null;
		try {
			readLock.lock();
			if (emptyHandles.contains(containerHandle))
				return Collections.emptyList();
			result = handleToContent.get(containerHandle);
			if (!result.isEmpty())
				return result;
		} finally {
			readLock.unlock();
		}
		return initContainedURIs(containerHandle, result);
	}

	protected Collection<URI> initContainedURIs(String containerHandle, Collection<URI> result) {
		Collection<URI> uris = doInitContainedURIs(containerHandle);
		try {
			writeLock.lock();
			if (uris.isEmpty()) {
				emptyHandles.add(containerHandle);
				return Collections.emptyList();
			} else {
				if (result.isEmpty())
					result.addAll(uris);
			}
		} finally {
			writeLock.unlock();
		}
		return result;
	}
	
	protected abstract Collection<URI> doInitContainedURIs(String containerHandle);

	public List<String> getVisibleContainerHandles(String handle) {
		List<String> visibleHandles = null;
		try {
			readLock.lock();
			visibleHandles = handleToVisibleHandles.get(handle);
			if (!visibleHandles.isEmpty()) {
				return visibleHandles;
			}
		} finally {
			readLock.unlock();
		}
		return initVisibleContainerHandles(handle, visibleHandles);
	}
	
	protected List<String> initVisibleContainerHandles(String handle, List<String> result) {
		List<String> visibleHandles = doInitVisibleHandles(handle);
		try {
			writeLock.lock();
			if (result.isEmpty())
				result.addAll(visibleHandles);
		} finally {
			writeLock.unlock();
		}
		return result;
	}
	
	protected abstract List<String> doInitVisibleHandles(String handle);
	
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE 
				|| event.getType() == IResourceChangeEvent.PRE_DELETE) {
			initialize();
			return;
		}
		if (event.getDelta() != null) {
			IResourceDelta delta = event.getDelta();
			final Wrapper<Boolean> clear = Wrapper.wrap(Boolean.FALSE);
			try {
				delta.accept(new IResourceDeltaVisitor() {
					public boolean visit(IResourceDelta delta) throws CoreException {
						if (clear.get().booleanValue())
							return false;
						if (delta.getResource() != null && delta.getResource().isDerived())
							return false;
						if (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.REMOVED) {
							if (delta.getResource() instanceof IStorage) {
								if (getUri((IStorage) delta.getResource()) != null) {
									clear.set(Boolean.TRUE);
									return false;
								}
							}
						}
						return true;
					}
				});
				if (clear.get().booleanValue())
					initialize();
			} catch (CoreException e) {
				log.error(e.getMessage(), e);
				initialize();
			}
		}
	}
	
	protected IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}
	
	protected URI getUri(IStorage file) {
		return mapper.getUri(file);
	}
	
	protected Iterable<IStorage> getStorages(URI uri) {
		return mapper.getStorages(uri);
	}
	
	public void setMapper(IStorage2UriMapper mapper) {
		this.mapper = mapper;
	}
	
	public IStorage2UriMapper getMapper() {
		return mapper;
	}
}

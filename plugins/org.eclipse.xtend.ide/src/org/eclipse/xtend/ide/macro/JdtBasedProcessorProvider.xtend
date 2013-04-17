package org.eclipse.xtend.ide.macro

import java.net.URL
import java.net.URLClassLoader
import java.util.List
import org.apache.log4j.Logger
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.common.util.URI
import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtend.core.macro.ProcessorInstanceForJvmTypeProvider
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.resource.XtextResourceSet

class JdtBasedProcessorProvider extends ProcessorInstanceForJvmTypeProvider {
	
	static val LOG = Logger::getLogger(typeof(JdtBasedProcessorProvider))
	
	override getProcessorInstance(JvmType type) {
		try {
			val project = (type.eResource.resourceSet as XtextResourceSet).classpathURIContext as IJavaProject
			val classLoader = createClassLoader(type.identifier, project)
			val result = classLoader.loadClass(type.identifier)
			return result.newInstance
		} catch (Exception e) {
			LOG.info(e)
			return null
		}
	}
	
	def protected ClassLoader createClassLoader(String typeName, IJavaProject javaProject) {
		val type = javaProject.findType(typeName)
		if (type == null)
			return getClass.classLoader
		val projectToUse = type.javaProject
		val resolvedClasspath = projectToUse.getResolvedClasspath(true)
		val List<URL> urls = newArrayList()
		urls.addAll(getOutputFolders(projectToUse));
		for (entry : resolvedClasspath) {
			var URL url = null
			switch entry.entryKind {
				case IClasspathEntry::CPE_SOURCE: {/* do nothing */}
				case IClasspathEntry::CPE_PROJECT: {
					var IPath path = entry.getPath()
					val IResource project = projectToUse.workspaceRoot.findMember(path)
					urls.addAll(getOutputFolders(JavaCore::create(project.getProject())))
				}
				case IClasspathEntry::CPE_LIBRARY: {
					var IPath path = entry.getPath()
					// if the library is in the workspace, the entry path is relative to the workspace root
					// thus we load it as a resource and take the raw path to find the location in the file system
					val IResource library = projectToUse.workspaceRoot.findMember(path)
					url = if (library != null) {
						library.rawLocationURI.toURL
					} else {
						// otherwise we use the path itself
						path.toFile().toURI().toURL() 
					}
				}
				default: {
					var IPath path = entry.getPath();
					url = path.toFile().toURI().toURL();
				}
			}
			if (url != null) {
				urls.add(url);
			}
		}
		return new URLClassLoader(urls, getClass().getClassLoader());
	}
	
	def private getWorkspaceRoot(IJavaProject javaProject) {
		javaProject.project.workspace.root
	}

	def private List<URL> getOutputFolders(IJavaProject javaProject) {
		val List<URL> result = newArrayList;
		var IPath path = javaProject.getOutputLocation().addTrailingSeparator();
		var URL url = new URL(URI::createPlatformResourceURI(path.toString(), true).toString());
		result.add(url);
		for (IClasspathEntry entry : javaProject.getRawClasspath()) {
			switch (entry.getEntryKind()) {
				case IClasspathEntry::CPE_SOURCE: {
					path = entry.getOutputLocation();
					if (path != null) {
						url = new URL(URI::createPlatformResourceURI(path.addTrailingSeparator().toString(), true)
								.toString());
						result.add(url);
					}
				}
			}
		}
		return result;
	}
	
}
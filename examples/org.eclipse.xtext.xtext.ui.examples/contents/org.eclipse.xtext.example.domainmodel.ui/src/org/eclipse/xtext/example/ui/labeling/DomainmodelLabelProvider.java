/*
* generated by Xtext
*/
package org.eclipse.xtext.example.ui.labeling;

import org.eclipse.xtext.example.domainmodel.Attribute;
import org.eclipse.xtext.example.domainmodel.DataType;
import org.eclipse.xtext.example.domainmodel.Entity;
import org.eclipse.xtext.example.domainmodel.PackageDeclaration;
import org.eclipse.xtext.example.domainmodel.Reference;
import org.eclipse.xtext.ui.DefaultLabelProvider;

/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class DomainmodelLabelProvider extends DefaultLabelProvider {
	
	String image(Entity e) {
		return "Entity.gif";
	}

	String image(Attribute a) {
		return "Attribute.gif";
	}
	
	String image(DataType d) {
		return "DataType.gif";
	}
	
	String image(Reference r) {
		return "Reference.gif";
	}
	
	String image(PackageDeclaration p) {
		return "Package.gif";
	}
	
}

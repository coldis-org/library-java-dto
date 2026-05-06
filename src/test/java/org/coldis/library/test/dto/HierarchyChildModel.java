package org.coldis.library.test.dto;

import org.coldis.library.dto.DtoType;
import org.coldis.library.model.Nameable;

/**
 * Child fixture extending {@link HierarchyParentModel}. With both classes carrying {@link DtoType},
 * the generator should emit {@code HierarchyChildModelDto extends HierarchyParentModelDto}. Adds
 * {@link Nameable} to verify multi-interface forwarding (Identifiable inherited from parent +
 * Nameable declared here).
 */
@DtoType(
		targetPath = "src/test/java",
		namespace = "org.coldis.library.test.dto.dto",
		interfaces = { Nameable.class }
)
public class HierarchyChildModel extends HierarchyParentModel implements Nameable {

	private String name;

	private String childField;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(
			final String name) {
		this.name = name;
	}

	public String getChildField() {
		return this.childField;
	}

	public void setChildField(
			final String childField) {
		this.childField = childField;
	}

}

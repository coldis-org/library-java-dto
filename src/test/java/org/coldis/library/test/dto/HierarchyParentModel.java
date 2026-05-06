package org.coldis.library.test.dto;

import org.coldis.library.dto.DtoType;
import org.coldis.library.model.Identifiable;

/**
 * Parent fixture for the hierarchical-DTO generator test. The DTO generator should produce a
 * matching {@code HierarchyParentModelDto} that is itself extended by the DTO of any child Model
 * also annotated with {@link DtoType}. Also implements {@link Identifiable} so the generator can
 * forward that interface to the DTO via the default-interfaces intersection.
 */
@DtoType(
		targetPath = "src/test/java",
		namespace = "org.coldis.library.test.dto.dto"
)
public class HierarchyParentModel implements Identifiable {

	private Long id;

	private String parentField;

	public Long getId() {
		return this.id;
	}

	public void setId(
			final Long id) {
		this.id = id;
	}

	public String getParentField() {
		return this.parentField;
	}

	public void setParentField(
			final String parentField) {
		this.parentField = parentField;
	}

}

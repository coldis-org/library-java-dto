package org.coldis.library.test.dto.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Arrays;
import org.coldis.library.dto.DtoOrigin;

/**
 * HierarchyParentModelDto.
 */
@DtoOrigin(originalClassName = "org.coldis.library.test.dto.HierarchyParentModel")
public class HierarchyParentModelDto implements Serializable, org.coldis.library.model.Identifiable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 322219573L;
	
	/**
	 * id.
	 */
	private java.lang.Long id;

	/**
	 * parentField.
	 */
	private java.lang.String parentField;


	/**
	 * No arguments constructor.
	 */
	public HierarchyParentModelDto() {
		super();
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	
	public java.lang.Long getId() {
		return  id ;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id
	 *            The id.
	 */
	public void setId(final java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * Sets the id and returns the updated object.
	 *
	 * @param id
	 *            The id.
	 * @return The updated object.
	 */
	public HierarchyParentModelDto withId(final java.lang.Long id) {
		this.setId(id);
		return this;
	}
	/**
	 * Gets the parentField.
	 * @return The parentField.
	 */
	
	public java.lang.String getParentField() {
		return  parentField ;
	}
	
	/**
	 * Sets the parentField.
	 *
	 * @param parentField
	 *            The parentField.
	 */
	public void setParentField(final java.lang.String parentField) {
		this.parentField = parentField;
	}
	
	/**
	 * Sets the parentField and returns the updated object.
	 *
	 * @param parentField
	 *            The parentField.
	 * @return The updated object.
	 */
	public HierarchyParentModelDto withParentField(final java.lang.String parentField) {
		this.setParentField(parentField);
		return this;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(
id

,
parentField



			);
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final HierarchyParentModelDto other = (HierarchyParentModelDto) obj;
		if (! Objects.equals(id, other.id)) {
			return false;
		}
		if (! Objects.equals(parentField, other.parentField)) {
			return false;
		}
		return true;
	}
	
}
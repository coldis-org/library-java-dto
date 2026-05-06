package org.coldis.library.test.dto.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Arrays;
import org.coldis.library.dto.DtoOrigin;

/**
 * HierarchyChildModelDto.
 */
@DtoOrigin(originalClassName = "org.coldis.library.test.dto.HierarchyChildModel")
public class HierarchyChildModelDto extends org.coldis.library.test.dto.dto.HierarchyParentModelDto implements org.coldis.library.model.Nameable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1431083805L;
	
	/**
	 * name.
	 */
	private java.lang.String name;

	/**
	 * childField.
	 */
	private java.lang.String childField;


	/**
	 * No arguments constructor.
	 */
	public HierarchyChildModelDto() {
		super();
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	
	public java.lang.String getName() {
		return  name ;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name
	 *            The name.
	 */
	public void setName(final java.lang.String name) {
		this.name = name;
	}
	
	/**
	 * Sets the name and returns the updated object.
	 *
	 * @param name
	 *            The name.
	 * @return The updated object.
	 */
	public HierarchyChildModelDto withName(final java.lang.String name) {
		this.setName(name);
		return this;
	}
	/**
	 * Gets the childField.
	 * @return The childField.
	 */
	
	public java.lang.String getChildField() {
		return  childField ;
	}
	
	/**
	 * Sets the childField.
	 *
	 * @param childField
	 *            The childField.
	 */
	public void setChildField(final java.lang.String childField) {
		this.childField = childField;
	}
	
	/**
	 * Sets the childField and returns the updated object.
	 *
	 * @param childField
	 *            The childField.
	 * @return The updated object.
	 */
	public HierarchyChildModelDto withChildField(final java.lang.String childField) {
		this.setChildField(childField);
		return this;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(
name

,
childField



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
		if (!super.equals(obj)) {
			return false;
		}
		final HierarchyChildModelDto other = (HierarchyChildModelDto) obj;
		if (! Objects.equals(name, other.name)) {
			return false;
		}
		if (! Objects.equals(childField, other.childField)) {
			return false;
		}
		return true;
	}
	
}
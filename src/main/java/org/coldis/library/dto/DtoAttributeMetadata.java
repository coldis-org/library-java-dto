package org.coldis.library.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * DTO attribute metadata.
 */
public class DtoAttributeMetadata implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 6528596189706828445L;

	/**
	 * Attribute modifiers.
	 */
	private List<String> modifiers;

	/**
	 * Attribute type.
	 */
	private String type;

	/**
	 * Attribute name.
	 */
	private String name;

	/**
	 * Attribute description.
	 */
	private String description;

	/**
	 * Attribute default value.
	 */
	private String defaultValue;

	/**
	 * If attribute is required.
	 */
	private Boolean required;

	/**
	 * If attribute should be placed in constructor and have a setter.
	 */
	private Boolean readOnly;

	/**
	 * If attribute should be used when comparing the DTO.
	 */
	private Boolean usedInComparison;

	/**
	 * Complete constructor.
	 *
	 * @param modifiers        Attribute modifiers.
	 * @param type             Attribute type.
	 * @param name             Attribute name.
	 * @param description      Attribute description.
	 * @param defaultValue     Attribute default value.
	 * @param required         If attribute is required.
	 * @param readOnly         If attribute should be placed in constructor and have
	 *                             a setter.
	 * @param usedInComparison If attribute should be used when comparing the DTO.
	 */
	public DtoAttributeMetadata(final List<String> modifiers, final String type, final String name,
			final String description, final String defaultValue, final Boolean required, final Boolean readOnly,
			final Boolean usedInComparison) {
		super();
		this.modifiers = modifiers;
		this.type = type;
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
		this.required = required;
		this.readOnly = readOnly;
		this.usedInComparison = usedInComparison;
	}

	/**
	 * Gets the modifiers.
	 *
	 * @return The modifiers.
	 */
	public List<String> getModifiers() {
		// If list is not initialized.
		if (this.modifiers == null) {
			// Initializes it as an empty list.
			this.modifiers = new ArrayList<>();
		}
		// Returns the list.
		return this.modifiers;
	}

	/**
	 * Sets the modifiers.
	 *
	 * @param modifiers New modifiers.
	 */
	public void setModifiers(final List<String> modifiers) {
		this.modifiers = modifiers;
	}

	/**
	 * Gets the type.
	 *
	 * @return The type.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type New type.
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * Gets the name.
	 *
	 * @return The name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name New name.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the capitalized name.
	 *
	 * @return The capitalized name.
	 */
	public String getCapitalizedName() {
		return this.getName() == null ? null
				: this.getName().substring(0, 1).toUpperCase() + this.getName().substring(1);
	}

	/**
	 * Gets the description.
	 *
	 * @return The description.
	 */
	public String getDescription() {
		return StringUtils.isEmpty(this.description) ? this.getName() : this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description New description.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets the defaultValue.
	 *
	 * @return The defaultValue.
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * Sets the defaultValue.
	 *
	 * @param defaultValue New defaultValue.
	 */
	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Gets the required.
	 *
	 * @return The required.
	 */
	public Boolean getRequired() {
		return this.required;
	}

	/**
	 * Sets the required.
	 *
	 * @param required New required.
	 */
	public void setRequired(final Boolean required) {
		this.required = required;
	}

	/**
	 * Gets the readOnly.
	 *
	 * @return The readOnly.
	 */
	public Boolean getReadOnly() {
		return this.readOnly;
	}

	/**
	 * Sets the readOnly.
	 *
	 * @param readOnly New readOnly.
	 */
	public void setReadOnly(final Boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Gets the usedInComparison.
	 *
	 * @return The usedInComparison.
	 */
	public Boolean getUsedInComparison() {
		return this.usedInComparison;
	}

	/**
	 * Sets the usedInComparison.
	 *
	 * @param usedInComparison New usedInComparison.
	 */
	public void setUsedInComparison(final Boolean usedInComparison) {
		this.usedInComparison = usedInComparison;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.defaultValue, this.description, this.modifiers, this.name, this.readOnly, this.type,
				this.usedInComparison);
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
		if (!(obj instanceof DtoAttributeMetadata)) {
			return false;
		}
		final DtoAttributeMetadata other = (DtoAttributeMetadata) obj;
		return Objects.equals(this.defaultValue, other.defaultValue)
				&& Objects.equals(this.description, other.description)
				&& Objects.equals(this.modifiers, other.modifiers) && Objects.equals(this.name, other.name)
				&& Objects.equals(this.readOnly, other.readOnly) && Objects.equals(this.type, other.type)
				&& Objects.equals(this.usedInComparison, other.usedInComparison);
	}

}
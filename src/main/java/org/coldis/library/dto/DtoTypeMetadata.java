package org.coldis.library.dto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * DTO type metadata.
 */
public class DtoTypeMetadata implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -8977720832301173320L;

	/**
	 * Type context.
	 */
	private String context;

	/**
	 * Target path.
	 */
	private String targetPath;

	/**
	 * Template path.
	 */
	private String templatePath;

	/**
	 * The DTO file extension.
	 */
	private String fileExtension;

	/**
	 * Type namespace.
	 */
	private String namespace;

	/**
	 * Original class name.
	 */
	private String originalClassName;

	/**
	 * Type name.
	 */
	private String name;

	/**
	 * Type description.
	 */
	private String description;

	/**
	 * Type attributes metadata.
	 */
	private List<DtoAttributeMetadata> attributes;

	/**
	 * Default constructor.
	 *
	 * @param context           Type context.
	 * @param targetPath        Target path.
	 * @param templatePath      Template path.
	 * @param fileExtension     The DTO file extension.
	 * @param namespace         Type namespace.
	 * @param originalClassName Original class name.
	 * @param name              Type name.
	 * @param description       Type description.
	 * @param attributes        Type attributes metadata.
	 */
	public DtoTypeMetadata(final String originalClassName, final DtoType annotation) {
		super();
		this.originalClassName = originalClassName;
		this.context = annotation.context();
		this.targetPath = annotation.targetPath();
		this.templatePath = annotation.templatePath();
		this.fileExtension = annotation.fileExtension();
		this.namespace = annotation.namespace();
		this.name = annotation.name().isEmpty() ? List.of(originalClassName.split("\\.")).getLast() + "Dto" : annotation.name();
		this.description = annotation.description();
	}

	/**
	 * Gets the context.
	 *
	 * @return The context.
	 */
	public String getContext() {
		return this.context;
	}

	/**
	 * Sets the context.
	 *
	 * @param context New context.
	 */
	public void setContext(
			final String context) {
		this.context = context;
	}

	/**
	 * Gets the targetPath.
	 *
	 * @return The targetPath.
	 */
	public String getTargetPath() {
		return this.targetPath;
	}

	/**
	 * Sets the targetPath.
	 *
	 * @param targetPath New targetPath.
	 */
	public void setTargetPath(
			final String targetPath) {
		this.targetPath = targetPath;
	}

	/**
	 * Gets the templatePath.
	 *
	 * @return The templatePath.
	 */
	public String getTemplatePath() {
		return this.templatePath;
	}

	/**
	 * Sets the templatePath.
	 *
	 * @param templatePath New templatePath.
	 */
	public void setTemplatePath(
			final String templatePath) {
		this.templatePath = templatePath;
	}

	/**
	 * Gets the fileExtension.
	 *
	 * @return The fileExtension.
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * Sets the fileExtension.
	 *
	 * @param fileExtension New fileExtension.
	 */
	public void setFileExtension(
			final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Gets the namespace.
	 *
	 * @return The namespace.
	 */
	public String getNamespace() {
		return this.namespace;
	}

	/**
	 * Sets the namespace.
	 *
	 * @param namespace New namespace.
	 */
	public void setNamespace(
			final String namespace) {
		this.namespace = namespace;
	}

	/**
	 * Gets the file namespace.
	 *
	 * @return The file namespace.
	 */
	public String getFileNamespace() {
		return this.namespace.replace(".", File.separator);
	}

	/**
	 * Gets the originalClassName.
	 *
	 * @return The originalClassName.
	 */
	public String getOriginalClassName() {
		return this.originalClassName;
	}

	/**
	 * Sets the originalClassName.
	 *
	 * @param originalClass New originalClassName.
	 */
	public void setOriginalClassName(
			final String originalClassName) {
		this.originalClassName = originalClassName;
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
	public void setName(
			final String name) {
		this.name = name;
	}

	/**
	 * Gets the qualified name.
	 *
	 * @return The qualified name.
	 */
	public String getQualifiedName() {
		return this.getNamespace() + "." + this.getName();
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
	public void setDescription(
			final String description) {
		this.description = description;
	}

	/**
	 * Gets the attributes.
	 *
	 * @return The attributes.
	 */
	public List<DtoAttributeMetadata> getAttributes() {
		// If list is not initialized.
		if (this.attributes == null) {
			// Initializes it as an empty list.
			this.attributes = new ArrayList<>();
		}
		// Returns the list.
		return this.attributes;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes New attributes.
	 */
	public void setAttributes(
			final List<DtoAttributeMetadata> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.attributes, this.context, this.description, this.fileExtension, this.name, this.namespace, this.originalClassName,
				this.targetPath, this.templatePath);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(
			final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (this.getClass() != obj.getClass())) {
			return false;
		}
		final DtoTypeMetadata other = (DtoTypeMetadata) obj;
		return Objects.equals(this.attributes, other.attributes) && Objects.equals(this.context, other.context)
				&& Objects.equals(this.description, other.description) && Objects.equals(this.fileExtension, other.fileExtension)
				&& Objects.equals(this.name, other.name) && Objects.equals(this.namespace, other.namespace)
				&& Objects.equals(this.originalClassName, other.originalClassName) && Objects.equals(this.targetPath, other.targetPath)
				&& Objects.equals(this.templatePath, other.templatePath);
	}

}

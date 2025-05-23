package org.coldis.library.dto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.coldis.library.helper.ReflectionHelper;
import org.coldis.library.helper.TypeMirrorHelper;

import jakarta.validation.constraints.NotNull;

/**
 * DTO generator.
 */
@SupportedSourceVersion(value = SourceVersion.RELEASE_21)
@SupportedAnnotationTypes(value = { "org.coldis.library.dto.DtoType", "org.coldis.library.dto.DtoTypes" })
public class DtoGenerator extends AbstractProcessor {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(DtoGenerator.class.getName());

	/**
	 * Gets the DTO attribute metadata from an attribute getter and a context.
	 *
	 * @param  attributeGetter Attribute getter.
	 * @param  context         DTO context.
	 * @return                 The DTO attribute metadata from an attribute getter
	 *                         and a context.
	 */
	private static DtoAttribute getDtoAttributeAnno(
			final Element attributeGetter,
			final String context) {
		// Gets DTO attribute metadata annotation.
		DtoAttribute dtoAttributeAnno = attributeGetter.getAnnotation(DtoAttribute.class);
		// If the DTO attribute annotation does not match the DTO context.
		if ((dtoAttributeAnno == null) || !dtoAttributeAnno.context().equals(context)) {
			// Re-sets the DTO attribute annotation.
			dtoAttributeAnno = null;
			// Gets DTO attributes metadata annotation.
			final DtoAttributes dtoAttributesAnno = attributeGetter.getAnnotation(DtoAttributes.class);
			// If there is a DTO attributes annotation.
			if (dtoAttributesAnno != null) {
				// For each DTO attribute metadata annotation.
				for (final DtoAttribute currentDtoAttributeAnno : dtoAttributesAnno.attributes()) {
					// If the current DTO attribute annotation matches the DTO context.
					if (currentDtoAttributeAnno.context().equals(context)) {
						// Updates the DTO attribute metadata annotation.
						dtoAttributeAnno = currentDtoAttributeAnno;
					}
				}
			}
		}
		// Returns the attribute metadata.
		return dtoAttributeAnno;
	}

	/**
	 * Gets the DTO types in hierarchy recursively.
	 *
	 * @param  attributeOriginalType The type to get the DTOs type recursively.
	 * @param  context               The DTO generation context.
	 * @param  dtoTypesInHierarchy   The map with already found DTO types in
	 *                                   hierarchy.
	 * @return                       The DTO types in hierarchy recursively.
	 */
	public static Map<String, String> getDtoTypesInHierarchy(
			final TypeMirror attributeOriginalType,
			final String context,
			final Map<String, String> dtoTypesInHierarchy) {
		// If the type is a array of a declared type.
		final boolean isDeclaredArrayType = (attributeOriginalType instanceof ArrayType)
				&& (((ArrayType) attributeOriginalType).getComponentType() instanceof DeclaredType);
		// Only if it is a declared type or an array of.
		if ((attributeOriginalType instanceof DeclaredType) || isDeclaredArrayType) {
			// Gets the declared type and type element.
			final DeclaredType declaredType = isDeclaredArrayType ? ((DeclaredType) ((ArrayType) attributeOriginalType).getComponentType())
					: ((DeclaredType) attributeOriginalType);
			final TypeElement currentTypeElement = (TypeElement) declaredType.asElement();
			// Gets the DTO type metadata for a given context.
			final DtoType dtoAttributeTypeAnno = DtoGenerator.getDtoTypeAnno(currentTypeElement, context);
			// If the attribute type should also be a DTO.
			if (dtoAttributeTypeAnno != null) {
				// Gets the attribute type metadata.
				final DtoTypeMetadata dtoAttributeTypeMetadata = DtoGenerator.getDtoTypeMetadata(currentTypeElement, dtoAttributeTypeAnno, false);
				// Adds the DTO type to the map.
				dtoTypesInHierarchy.put(currentTypeElement.getQualifiedName().toString(), dtoAttributeTypeMetadata.getQualifiedName());
			}
			// For each type parameter of the current type.
			if (declaredType.getTypeArguments() != null) {
				for (final TypeMirror currentTypeArgument : declaredType.getTypeArguments()) {
					// Gets the DTO types in hierarchy recursively.
					DtoGenerator.getDtoTypesInHierarchy(currentTypeArgument, context, dtoTypesInHierarchy);
				}
			}
		}
		// Returns the updated DTO types in hierarchy.
		return dtoTypesInHierarchy;
	}

	/**
	 * Gets the DTO attribute metadata.
	 *
	 * @param  context         The DTO context.
	 * @param  attributeGetter The attribute getter.
	 * @param  defaultAttrName The default attribute name.
	 * @return                 The DTO attribute metadata.
	 */
	private static DtoAttributeMetadata getDtoAttributeMetadata(
			final String context,
			final Element attributeGetter,
			final String defaultAttrName) {
		// Gets the default attribute metadata.
		DtoAttributeMetadata dtoAttributeMetadata = null;
		// Gets the DTO attribute metadata annotation.
		final DtoAttribute dtoAttributeAnno = DtoGenerator.getDtoAttributeAnno(attributeGetter, context);
		// If the attribute should not be ignored.
		if ((dtoAttributeAnno == null) || ((dtoAttributeAnno != null) && !dtoAttributeAnno.ignore())) {
			// Gets the attribute original type.
			TypeMirror attributeOriginalType = ((ExecutableType) attributeGetter.asType()).getReturnType();
			// Hack to get correct type name for Hibernate modified classes.
			final Optional<Field> attributeOriginalTypeThisField = List.of(attributeOriginalType.getClass().getDeclaredFields()).stream()
					.filter(field -> field.getName().startsWith("this")).findFirst();
			if (attributeOriginalTypeThisField.isPresent()) {
				try {
					attributeOriginalTypeThisField.get().setAccessible(true);
					attributeOriginalType = (TypeMirror) attributeOriginalTypeThisField.get().get(attributeOriginalType);
				}
				catch (final Exception error) {
					DtoGenerator.LOGGER.warning("Error getting attributeOriginalTypeThisField: " + error.getLocalizedMessage());
				}
				finally {
					attributeOriginalTypeThisField.get().setAccessible(false);
				}
			}
			final String attributeOriginalTypeName = (attributeOriginalType.toString().substring(attributeOriginalType.toString().lastIndexOf(" ") + 1));

			// DTOs in attribute hierarchy.TypeMirror
			final Map<String, String> dtoTypesInAttrHier = DtoGenerator.getDtoTypesInHierarchy(attributeOriginalType, context, new HashMap<>());
			// Copied annotations.
			final List<String> copiedAnnotationsTypesNames = ((dtoAttributeAnno == null) ? null
					: TypeMirrorHelper.getAnnotationClassesAttribute(dtoAttributeAnno, "copiedAnnotations"));
			final List<String> actualCopiedAnnotationsTypesNames = ((copiedAnnotationsTypesNames == null)
					|| CollectionUtils.isEqualCollection(copiedAnnotationsTypesNames, List.of(void.class.getName().toString()))
							? Arrays.stream(DtoAttribute.DEFAULT_COPIED_ANNOTATIONS).map(item -> item.getName().toString()).toList()
							: copiedAnnotationsTypesNames);
			final Set<String> copiedAnnotations = attributeGetter.getAnnotationMirrors().stream()
					.filter(annotation -> actualCopiedAnnotationsTypesNames
							.contains(((TypeElement) annotation.getAnnotationType().asElement()).getQualifiedName().toString()))
					.map(annotation -> annotation.toString()).collect(Collectors.toSet());
			final String reducedCopiedAnnotations = copiedAnnotations.stream().reduce("", StringUtils::join);
			// Gets the default attribute metadata.
			dtoAttributeMetadata = new DtoAttributeMetadata(new ArrayList<>(), attributeOriginalTypeName, defaultAttrName, defaultAttrName, "", new String[] {}, false, false,
					true, reducedCopiedAnnotations);
			// If attribute is not required.
			if (!dtoAttributeMetadata.getRequired()) {
				// If there is a not null annotation.
				if (attributeGetter.getAnnotation(NotNull.class) != null) {
					// Sets the attribute as required.
					dtoAttributeMetadata.setRequired(true);
				}
			}
			// If the attribute metadata annotation is present.
			if (dtoAttributeAnno != null) {
				// Gets the attribute type value.
				final String dtoAttributeTypeName = TypeMirrorHelper.getAnnotationClassAttribute(dtoAttributeAnno, "type");
				// Updates the DTO attribute metadata from the annotation information.
				dtoAttributeMetadata.setModifiers(Arrays.asList(dtoAttributeAnno.modifiers()));
				dtoAttributeMetadata.setType(dtoAttributeAnno.typeName().isEmpty() ? dtoAttributeMetadata.getType() : dtoAttributeAnno.typeName());
				dtoAttributeMetadata.setType(dtoAttributeTypeName.equals("void") ? dtoAttributeMetadata.getType() : dtoAttributeTypeName);
				dtoAttributeMetadata.setName(dtoAttributeAnno.name().isEmpty() ? dtoAttributeMetadata.getName() : dtoAttributeAnno.name());
				dtoAttributeMetadata
						.setDescription(dtoAttributeAnno.description().isEmpty() ? dtoAttributeMetadata.getDescription() : dtoAttributeAnno.description());
				dtoAttributeMetadata.setDefaultValue(dtoAttributeAnno.defaultValue());
				dtoAttributeMetadata.setValueFromOtherAttributes(dtoAttributeAnno.valueFromOtherAttributes());
				dtoAttributeMetadata
						.setRequired(dtoAttributeAnno.required() == org.coldis.library.dto.DtoAttribute.Bool.UNDEFINED ? dtoAttributeMetadata.getRequired()
								: (dtoAttributeAnno.required() == org.coldis.library.dto.DtoAttribute.Bool.TRUE ? true : false));
				dtoAttributeMetadata.setReadOnly(dtoAttributeAnno.readOnly());
				dtoAttributeMetadata.setUsedInComparison(dtoAttributeAnno.usedInComparison());
				
			}
			// For each other DTO in the hierarchy.
			for (final Entry<String, String> dtoTypeInAttrHier : dtoTypesInAttrHier.entrySet()) {
				// Replaces the original attribute type for the correspondent DTO type.
				dtoAttributeMetadata.setType(dtoAttributeMetadata.getType().replaceAll(dtoTypeInAttrHier.getKey(), dtoTypeInAttrHier.getValue()));
			}
		}
		// Returns the attribute metadata.
		return dtoAttributeMetadata;
	}

	/**
	 * Gets the DTO type metadata from an original type and a context.
	 *
	 * @param  originalTypeElement Original type.
	 * @param  context             DTO context.
	 * @return                     The DTO type metadata from an original type and a
	 *                             context.
	 */
	private static DtoType getDtoTypeAnno(
			final TypeElement originalTypeElement,
			final String context) {
		// Gets DTO attribute return type DTO metadata annotation.
		DtoType dtoTypeAnno = originalTypeElement.getAnnotation(DtoType.class);
		// If the DTO type annotation does not match the DTO context.
		if ((dtoTypeAnno == null) || !dtoTypeAnno.context().equals(context)) {
			// Re-sets the DTO type annotation.
			dtoTypeAnno = null;
			// Gets DTO types metadata annotation.
			final DtoTypes dtoTypesAnno = originalTypeElement.getAnnotation(DtoTypes.class);
			// If there is a DTO types annotation.
			if (dtoTypesAnno != null) {
				// For each DTO type metadata annotation.
				for (final DtoType currentDtoTypeAnno : dtoTypesAnno.types()) {
					// If the current DTO type annotation matches the DTO context.
					if (currentDtoTypeAnno.context().equals(context)) {
						// Updates the DTO type metadata annotation.
						dtoTypeAnno = currentDtoTypeAnno;
					}
				}
			}
		}
		// Returns the DTO type metadata.
		return dtoTypeAnno;
	}

	/**
	 * Gets the DTO type metadata.
	 *
	 * @param  originalType              The original type.
	 * @param  dtoTypeAnno               The DTO type annotation.
	 * @param  alsoGetAttributesMetadata If attributes metadata should also be
	 *                                       retrieved.
	 * @return                           The DTO type metadata.
	 */
	private static DtoTypeMetadata getDtoTypeMetadata(
			final TypeElement originalType,
			final DtoType dtoTypeAnno,
			final Boolean alsoGetAttributesMetadata) {
		// Gets the default type metadata.
		final DtoTypeMetadata dtoTypeMetadata = new DtoTypeMetadata(originalType.getQualifiedName().toString(), dtoTypeAnno);
		// If attributes metadata should also be retrieved.
		if (alsoGetAttributesMetadata) {
			// Attributes of DTO.
			final List<String> alreadyAddedAttributes = new ArrayList<>();
			// Current type. Initially the given one, an then its super types.
			TypeElement currentClass = originalType;
			// For each type in the hierarchy.
			while ((currentClass != null) && !(currentClass instanceof NoType)) {
				// For each class enclosed element.
				for (final Element currentGetter : currentClass.getEnclosedElements()) {
					// If the element is a public getter.
					if (currentGetter.getKind().equals(ElementKind.METHOD) && currentGetter.getModifiers().contains(Modifier.PUBLIC)
							&& (!currentGetter.getModifiers().contains(Modifier.STATIC))
							&& (currentGetter.getSimpleName().toString().startsWith("get") || currentGetter.getSimpleName().toString().startsWith("is"))) {
						// Only proceed if it is a getter (no arguments).
						if (CollectionUtils.isEmpty(((ExecutableType) currentGetter.asType()).getParameterTypes())) {
							// Gets the attribute name.
							final String attributeName = ReflectionHelper.getAttributeName(currentGetter.getSimpleName().toString());
							// If the attribute has not been added yet (for override attributes).
							if (!alreadyAddedAttributes.contains(currentGetter.toString()) && (!"class".equals(attributeName))) {
								// Gets the attribute metadata.
								final DtoAttributeMetadata dtoAttributeMetadata = DtoGenerator.getDtoAttributeMetadata(dtoTypeMetadata.getContext(),
										currentGetter, attributeName);
								// If the attribute metadata is retrieved.
								if (dtoAttributeMetadata != null) {
									// Adds the DTO attribute for later conversion.
									dtoTypeMetadata.getAttributes().add(dtoAttributeMetadata);
								}
								// Adds the attribute to the already added list.
								alreadyAddedAttributes.add(currentGetter.toString());
							}
						}
					}
				}
				// The current class is the late class superclass.
				currentClass = currentClass.getSuperclass() instanceof DeclaredType ? (TypeElement) ((DeclaredType) currentClass.getSuperclass()).asElement()
						: null;
			}
		}
		// Returns the type metadata.
		return dtoTypeMetadata;
	}

	/**
	 * Generates a DTO from a original type.
	 *
	 * @param  originalType    Original type information.
	 * @param  dtoTypeMetadata DTO type metadata.
	 * @throws IOException     If the class cannot be generated.
	 */
	private void generateDto(
			final TypeElement originalType,
			final DtoTypeMetadata dtoTypeMetadata) throws IOException {
		// Gets the velocity engine.
		final VelocityEngine velocityEngine = new VelocityEngine();
		// Configures the resource loader to also look at the classpath.
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		// Initializes the velocity engine.
		velocityEngine.init();
		// Creates a new velocity context and sets its variables.
		final VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("dto", dtoTypeMetadata);
		velocityContext.put("newLine", "\r\n");
		velocityContext.put("tab", "\t");
		velocityContext.put("h", "#");
		// Gets the template for the DTO.
		final Template dtoTemplate = velocityEngine.getTemplate(dtoTypeMetadata.getTemplatePath());
		// Prepares the writer for the DTO.
		final File dtoFile = new File(dtoTypeMetadata.getTargetPath() + File.separator + dtoTypeMetadata.getFileNamespace(),
				dtoTypeMetadata.getName() + "." + dtoTypeMetadata.getFileExtension());
		FileUtils.forceMkdir(dtoFile.getParentFile());
		final Writer dtoWriter = new FileWriter(dtoFile);
		// Writes the generated class code.
		dtoTemplate.merge(velocityContext, dtoWriter);
		// Closes the class writer.
		dtoWriter.close();
	}

	/**
	 * Generates the DTO from type and metadata.
	 *
	 * @param originalType Original type generating the DTO.
	 * @param dtoMetadata  DTO metadata.
	 */
	private void generateDto(
			final TypeElement originalType,
			final DtoType dtoMetadata) {
		// Tries to generate the DTOs.
		try {
			// Gets the DTO metadata.
			final DtoTypeMetadata dtoTypeMetadata = DtoGenerator.getDtoTypeMetadata(originalType, dtoMetadata, true);
			// Generates the classes.
			DtoGenerator.LOGGER.fine("Generating DTO " + dtoTypeMetadata.getName() + ".");
			this.generateDto(originalType, dtoTypeMetadata);
			DtoGenerator.LOGGER.info("DTO " + dtoTypeMetadata.getName() + " created successfully.");
		}
		// If there is a problem generating the DTOs.
		catch (final Exception exception) {
			// Logs it.
			DtoGenerator.LOGGER.warning("DTO " + dtoMetadata.name() + " not created successfully:" + exception.getLocalizedMessage());
		}
	}

	/**
	 * @see jakarta.annotation.processing.AbstractProcessor#process(java.util.Set,
	 *      jakarta.annotation.processing.RoundEnvironment)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean process(
			final Set<? extends TypeElement> annotations,
			final RoundEnvironment roundEnv) {
		DtoGenerator.LOGGER.fine("Initializing DTOs generation...");
		// For each type generating multiple DTOs.
		for (final TypeElement originalType : (Set<TypeElement>) roundEnv.getElementsAnnotatedWith(DtoTypes.class)) {
			// Gets the DTOs metadata.
			final DtoTypes dtosMetadata = originalType.getAnnotation(DtoTypes.class);
			// For each DTO metadata.
			for (final DtoType dtoMetadata : dtosMetadata.types()) {
				// Generates the DTO.
				this.generateDto(originalType, dtoMetadata);
			}
		}
		// For each type generating a single DTO.
		for (final TypeElement originalType : (Set<TypeElement>) roundEnv.getElementsAnnotatedWith(DtoType.class)) {
			// Gets the DTO metadata.
			final DtoType dtoMetadata = originalType.getAnnotation(DtoType.class);
			// Generates the DTO.
			this.generateDto(originalType, dtoMetadata);
		}
		// Mark that the message sources annotations have been processed.
		DtoGenerator.LOGGER.fine("Finishing DtoGenerator...");
		return true;
	}

}

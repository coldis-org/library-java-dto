package org.coldis.library.dto;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.coldis.library.model.Describable;
import org.coldis.library.model.Expirable;
import org.coldis.library.model.Identifiable;
import org.coldis.library.model.Nameable;
import org.coldis.library.model.Primaryable;
import org.coldis.library.model.Timestampable;
import org.coldis.library.model.Versionable;

/**
 * DTO type metadata.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface DtoType {

	/**
	 * Default coldis interfaces to forward onto generated DTOs when the original Model implements
	 * any of them. Used as the fallback value of {@link #interfaces()} when the annotation is left
	 * at its sentinel default. Each entry is intersected with the Model's actual interface
	 * hierarchy at generation time, so unrelated entries are silently dropped.
	 *
	 * <p>{@code Typable} is intentionally excluded: it carries
	 * {@code @JsonTypeInfo(use=NAME, property="typeName")}, which auto-forwarding onto DTOs would
	 * silently enable Jackson polymorphic resolution and require subtype registration in every
	 * mapper that round-trips Model↔DTO. Callers that need it on a specific DTO can opt in via
	 * {@code interfaces=Typable.class} explicitly.
	 */
	public static final Class<?>[] DEFAULT_INTERFACES = {

			Identifiable.class, Nameable.class, Describable.class, Primaryable.class, Timestampable.class, Expirable.class, Versionable.class

	};

	/**
	 * Context is used to identify types and attributes that should be bound
	 * together.
	 */
	public String context() default "";

	/**
	 * Target path. Default is "src/main/java".
	 */
	public String targetPath() default "src/main/java";

	/**
	 * Template relative path (from resources).
	 */
	public String templatePath() default "dto/template/JavaDto.java";

	/**
	 * The DTO file extension.
	 */
	public String fileExtension() default "java";

	/**
	 * DTO type namespace.
	 */
	public String namespace();

	/**
	 * DTO type name. Default is the origin class name with the "Dto" append.
	 */
	public String name() default "";

	/**
	 * DTO type description.
	 */
	public String description() default "";

	/**
	 * Interfaces that the generated DTO should implement (in addition to {@link
	 * java.io.Serializable}, which is always present on root DTOs and inherited
	 * via the parent on hierarchical DTOs). Use the sentinel default to opt into
	 * {@link #DEFAULT_INTERFACES}; pass an explicit (possibly empty) array to
	 * override. Either way, only interfaces that the Model itself actually
	 * implements (directly or transitively) are emitted.
	 */
	public Class<?>[] interfaces() default void.class;

}

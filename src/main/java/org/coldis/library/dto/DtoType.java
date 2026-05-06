package org.coldis.library.dto;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * DTO type metadata.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface DtoType {

	/**
	 * Default coldis interfaces to forward onto generated DTOs when the original Model implements
	 * any of them. Empty by default — callers opt in per-Model via {@code interfaces=...} on the
	 * {@link DtoType} annotation. The previous broader default tripped on Models with read-only
	 * fields when an interface required setters (e.g. {@code Timestampable.setUpdatedAt}); since
	 * the generator can't validate setter availability at processing time, opt-in is safer.
	 */
	public static final Class<?>[] DEFAULT_INTERFACES = {};

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

	/**
	 * Declares an existing class as the DTO equivalent of this Model. When set
	 * to anything other than the {@code void.class} sentinel, the generator
	 * skips DTO emission for this type and downstream tooling (Fory canonical
	 * registration, child DTO {@code extends}) treats the named class as the
	 * Model's DTO. Useful when the DTO already exists in a shared library and
	 * doesn't need to be regenerated.
	 *
	 * <p>Prefer this over {@link DtoOrigin} on the DTO side — it lets the Model
	 * (which is the source of truth) carry the mapping, and works for
	 * hand-written DTOs without modifying their source.
	 */
	public Class<?> dtoClass() default void.class;

	/**
	 * Like {@link #dtoClass()} but accepts the DTO class name as a string. Use
	 * when the DTO class isn't on the Model's compile classpath (annotations
	 * can't reference unloaded classes via {@code Class<?>}). When both are
	 * set, {@link #dtoClass()} wins.
	 */
	public String dtoClassName() default "";

}

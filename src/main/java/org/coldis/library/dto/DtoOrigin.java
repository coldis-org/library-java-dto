package org.coldis.library.dto;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * DTO type origin (DTO-side pointer to the originating Model).
 *
 * @deprecated Prefer {@link DtoType#dtoClass()}/{@link DtoType#dtoClassName()}
 *             on the Model: the Model is the source of truth, and the
 *             Model-side mapping works for hand-written DTOs without
 *             modifying their source. The generator still emits this
 *             annotation on generated DTOs and the runtime helpers still
 *             read it for backward compatibility, but new mappings should
 *             use {@link DtoType#dtoClass()} instead.
 */
@Deprecated
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface DtoOrigin {

	/**
	 * Original class name.
	 */
	public String originalClassName();

}

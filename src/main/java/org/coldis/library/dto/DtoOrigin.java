package org.coldis.library.dto;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * DTO type origin.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface DtoOrigin {

	/**
	 * Original class name.
	 */
	public String originalClassName();

}

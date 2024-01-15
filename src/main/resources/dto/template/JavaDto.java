package ${dto.namespace};

import java.io.Serializable;
import java.util.Objects;

import java.util.Arrays;

/**
 * ${dto.description}.
 */
public class ${dto.name} implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = ${dto.name.hashCode()}L;
	
#{foreach}(${attribute} in ${dto.attributes})
	/**
	 * ${attribute.description}.
	 */
	private #{foreach}(${modifier} in ${attribute.modifiers})${modifier} #{end}${attribute.type} ${attribute.name}#{if}("$!attribute.defaultValue" != "") = #{if}(${attribute.type.equals("java.lang.String")})"#{end}${attribute.defaultValue}#{if}(${attribute.type.equals("java.lang.String")})"#{end}#{end};

#{end}

	/**
	 * No arguments constructor.
	 */
	public ${dto.name}() {
		super();
	}

#{foreach}( ${attribute} in ${dto.attributes} )
	/**
	 * Gets the ${attribute.description}.
	 * @return The ${attribute.description}.
	 */
	${attribute.annotations}
	public#{if}(${attribute.modifiers.contains("static")}) static#{end} ${attribute.type} get${attribute.capitalizedName}() {
		return ${attribute.name};
	}
	
#{if}(!${attribute.readOnly} && !${attribute.modifiers.contains("final")})	
	/**
	 * Sets the ${attribute.description}.
	 *
	 * @param ${attribute.name}
	 *            The ${attribute.description}.
	 */
	public#{if}(${attribute.modifiers.contains("static")}) static#{end} void set${attribute.capitalizedName}(final ${attribute.type} ${attribute.name}) {
		this.${attribute.name} = ${attribute.name};
	}
	
	/**
	 * Sets the ${attribute.description} and returns the updated object.
	 *
	 * @param ${attribute.name}
	 *            The ${attribute.description}.
	 * @return The updated object.
	 */
	public ${dto.name} with${attribute.capitalizedName}(final ${attribute.type} ${attribute.name}) {
		this.set${attribute.capitalizedName}(${attribute.name});
		return this;
	}
#{end}
#{end}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(
				#{set}($currentItemIdx = 0)#{foreach}(${attribute} in ${dto.attributes})#{if}(${attribute.usedInComparison} && !${attribute.type.endsWith("[]")})
#{if}(${currentItemIdx} > 0),
				#{end}${attribute.name}#{set}($currentItemIdx = $currentItemIdx + 1)
#{end}
#{end}

			);
#{foreach}(${attribute} in ${dto.attributes})#{if}(${attribute.usedInComparison} && ${attribute.type.endsWith("[]")})
		result = prime * result + Arrays.hashCode(${attribute.name});
#{end}#{end}
		return result;
	}
	
	/**
	 * @see java.lang.Object#[[#]]#equals(java.lang.Object)
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
		final ${dto.name} other = (${dto.name}) obj;
#{foreach}(${attribute} in ${dto.attributes})#{if}(${attribute.usedInComparison})
		if (!#{if}(${attribute.type.endsWith("[]")}) Arrays#{else} Objects#{end}.equals(${attribute.name}, other.${attribute.name})) {
			return false;
		}
#{end}#{end}
		return true;
	}
	
}
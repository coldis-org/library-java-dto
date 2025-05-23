package org.coldis.library.test.dto;

import java.util.List;

import org.coldis.library.dto.DtoAttribute;
import org.coldis.library.dto.DtoType;
import org.coldis.library.model.Identifiable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * DTO test object.
 */
@DtoType(
		targetPath = "src/test/java",
		namespace = "org.coldis.library.test.dto.dto"
)
public class DtoTestObject implements Identifiable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -6904605762253009838L;

	/**
	 * Test attribute value.
	 */
	public static final String TEST_FINAL_ATTR_VALUE = "ABC";

	/**
	 * Object identifier.
	 */
	private Long id;

	/**
	 * Test attribute.
	 */
	private DtoTestObject2 test1;

	/**
	 * Test attribute.
	 */
	private List<DtoTestObject2> test2;

	/**
	 * Test attribute.
	 */
	private String test3;

	/**
	 * Test attribute.
	 */
	private DtoTestObject2 test4;

	/**
	 * Test attribute.
	 */
	private String test5;

	/**
	 * Test attribute.
	 */
	private DtoTestObject2[] test6;

	/**
	 * Test attribute.
	 */
	private int test7;

	/**
	 * Test attribute.
	 */
	private int[] test8;

	/**
	 * Test attribute.
	 */
	private Integer test9;

	/**
	 * Test attribute.
	 */
	private Integer test10;

	/**
	 * Test attribute.
	 */
	private Integer test11;

	/**
	 * Test attribute.
	 */
	private Integer test12;

	/**
	 * @see org.coldis.library.model.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the identifier.
	 *
	 * @param id New identifier.
	 */
	public void setId(
			final Long id) {
		this.id = id;
	}

	/**
	 * Gets the test1.
	 *
	 * @return The test1.
	 */
	@JsonGetter
	final public DtoTestObject2 getTest1() {
		return this.test1;
	}

	/**
	 * Sets the test1.
	 *
	 * @param test1 New test1.
	 */
	public void setTest1(
			final DtoTestObject2 test1) {
		this.test1 = test1;
	}

	/**
	 * Gets the test2.
	 *
	 * @return The test2.
	 */
	@NotNull
	@JsonView(DtoTestObject.class)
	public List<DtoTestObject2> getTest2() {
		return this.test2;
	}

	/**
	 * Sets the test2.
	 *
	 * @param test2 New test2.
	 */
	public void setTest2(
			final List<DtoTestObject2> test2) {
		this.test2 = test2;
	}

	/**
	 * Gets the test3.
	 *
	 * @return The test3.
	 */
	public String getTest3() {
		return this.test3;
	}

	/**
	 * Sets the test3.
	 *
	 * @param test3 New test3.
	 */
	public void setTest3(
			final String test3) {
		this.test3 = test3;
	}

	/**
	 * Gets the test4.
	 *
	 * @return The test4.
	 */
	@Valid
	@DtoAttribute(
			typeName = "java.util.Map<String, Object>",
			required = org.coldis.library.dto.DtoAttribute.Bool.TRUE
	)
	public DtoTestObject2 getTest4() {
		return this.test4;
	}

	/**
	 * Sets the test4.
	 *
	 * @param test4 New test4.
	 */
	public void setTest4(
			final DtoTestObject2 test4) {
		this.test4 = test4;
	}

	/**
	 * Gets the test5.
	 *
	 * @return The test5.
	 */
	@DtoAttribute(
			defaultValue = DtoTestObject.TEST_FINAL_ATTR_VALUE,
			modifiers = { "static", "final" }
	)
	public String getTest5() {
		return this.test5;
	}

	/**
	 * Sets the test5.
	 *
	 * @param test5 New test5.
	 */
	public void setTest5(
			final String test5) {
		this.test5 = test5;
	}

	/**
	 * Gets the test6.
	 *
	 * @return The test6.
	 */
	public DtoTestObject2[] getTest6() {
		return this.test6;
	}

	/**
	 * Sets the test6.
	 *
	 * @param test6 New test6.
	 */
	public void setTest6(
			final DtoTestObject2[] test6) {
		this.test6 = test6;
	}

	/**
	 * Gets the test7.
	 *
	 * @return The test7.
	 */
	@NotNull
	public int getTest7() {
		return this.test7;
	}

	/**
	 * Sets the test7.
	 *
	 * @param test7 New test7.
	 */
	public void setTest7(
			final int test7) {
		this.test7 = test7;
	}

	/**
	 * Gets the test8.
	 *
	 * @return The test8.
	 */
	@NotNull
	@JsonGetter(value = "test88")
	@DtoAttribute(
			name = "test88",
			type = Integer[].class
	)
	public int[] getTest8() {
		return this.test8;
	}

	/**
	 * Sets the test8.
	 *
	 * @param test8 New test8.
	 */
	public void setTest8(
			final int[] test8) {
		this.test8 = test8;
	}

	/**
	 * Gets the test9.
	 *
	 * @return The test9.
	 */
	@DtoAttribute(usedInComparison = false)
	public Integer getTest9() {
		return this.test9;
	}

	/**
	 * Sets the test9.
	 *
	 * @param test9 New test9.
	 */
	public void setTest9(
			final Integer test9) {
		this.test9 = test9;
	}

	/**
	 * Gets the test10.
	 *
	 * @return The test10.
	 */
	@DtoAttribute(readOnly = true)
	public Integer getTest10() {
		return this.test10;
	}

	/**
	 * Sets the test10.
	 *
	 * @param test10 New test10.
	 */
	public void setTest10(
			final Integer test10) {
		this.test10 = test10;
	}

	/**
	 * Gets the test11.
	 * 
	 * @return The test11.
	 */
	public Integer getTest11() {
		return test11;
	}

	/**
	 * Sets the test11.
	 * 
	 * @param test11 New test11.
	 */
	public void setTest11(
			Integer test11) {
		this.test11 = test11;
	}

	/**
	 * Gets the test12.
	 * @return The test12.
	 */
	@DtoAttribute(valueFromOtherAttributes = { "test9", "test11" }, usedInComparison = false)
	public Integer getTest12() {
		return test12;
	}

	/**
	 * Sets the test12.
	 * @param test12 New test12.
	 */
	public void setTest12(
			Integer test12) {
		this.test12 = test12;
	}
	
	

}

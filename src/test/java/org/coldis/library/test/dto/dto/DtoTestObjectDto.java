package org.coldis.library.test.dto.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Arrays;
import org.coldis.library.dto.DtoOrigin;

/**
 * DtoTestObjectDto.
 */
@DtoOrigin(originalClassName = "org.coldis.library.test.dto.DtoTestObject")
public class DtoTestObjectDto implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -1877623249L;
	
	/**
	 * id.
	 */
	private java.lang.Long id;

	/**
	 * test1.
	 */
	private org.coldis.library.test.dto.dto.DtoTestObject2Dto test1;

	/**
	 * test2.
	 */
	private java.util.List<org.coldis.library.test.dto.dto.DtoTestObject2Dto> test2;

	/**
	 * test3.
	 */
	private java.lang.String test3;

	/**
	 * test4.
	 */
	private java.util.Map<String, Object> test4;

	/**
	 * test5.
	 */
	private static final java.lang.String test5 = "ABC";

	/**
	 * test6.
	 */
	private org.coldis.library.test.dto.dto.DtoTestObject2Dto[] test6;

	/**
	 * test7.
	 */
	private int test7;

	/**
	 * test8.
	 */
	private int[] test88;

	/**
	 * test9.
	 */
	private java.lang.Integer test9;

	/**
	 * test10.
	 */
	private java.lang.Integer test10;

	/**
	 * test11.
	 */
	private java.lang.Integer test11;

	/**
	 * test12.
	 */
	private java.lang.Integer test12;


	/**
	 * No arguments constructor.
	 */
	public DtoTestObjectDto() {
		super();
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	
	public java.lang.Long getId() {
		return  id ;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id
	 *            The id.
	 */
	public void setId(final java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * Sets the id and returns the updated object.
	 *
	 * @param id
	 *            The id.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withId(final java.lang.Long id) {
		this.setId(id);
		return this;
	}
	/**
	 * Gets the test1.
	 * @return The test1.
	 */
	
	public org.coldis.library.test.dto.dto.DtoTestObject2Dto getTest1() {
		return  test1 ;
	}
	
	/**
	 * Sets the test1.
	 *
	 * @param test1
	 *            The test1.
	 */
	public void setTest1(final org.coldis.library.test.dto.dto.DtoTestObject2Dto test1) {
		this.test1 = test1;
	}
	
	/**
	 * Sets the test1 and returns the updated object.
	 *
	 * @param test1
	 *            The test1.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest1(final org.coldis.library.test.dto.dto.DtoTestObject2Dto test1) {
		this.setTest1(test1);
		return this;
	}
	/**
	 * Gets the test2.
	 * @return The test2.
	 */
	@com.fasterxml.jackson.annotation.JsonView({org.coldis.library.test.dto.DtoTestObject.class})
	public java.util.List<org.coldis.library.test.dto.dto.DtoTestObject2Dto> getTest2() {
		return  test2 ;
	}
	
	/**
	 * Sets the test2.
	 *
	 * @param test2
	 *            The test2.
	 */
	public void setTest2(final java.util.List<org.coldis.library.test.dto.dto.DtoTestObject2Dto> test2) {
		this.test2 = test2;
	}
	
	/**
	 * Sets the test2 and returns the updated object.
	 *
	 * @param test2
	 *            The test2.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest2(final java.util.List<org.coldis.library.test.dto.dto.DtoTestObject2Dto> test2) {
		this.setTest2(test2);
		return this;
	}
	/**
	 * Gets the test3.
	 * @return The test3.
	 */
	
	public java.lang.String getTest3() {
		return  test3 ;
	}
	
	/**
	 * Sets the test3.
	 *
	 * @param test3
	 *            The test3.
	 */
	public void setTest3(final java.lang.String test3) {
		this.test3 = test3;
	}
	
	/**
	 * Sets the test3 and returns the updated object.
	 *
	 * @param test3
	 *            The test3.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest3(final java.lang.String test3) {
		this.setTest3(test3);
		return this;
	}
	/**
	 * Gets the test4.
	 * @return The test4.
	 */
	
	public java.util.Map<String, Object> getTest4() {
		return  test4 ;
	}
	
	/**
	 * Sets the test4.
	 *
	 * @param test4
	 *            The test4.
	 */
	public void setTest4(final java.util.Map<String, Object> test4) {
		this.test4 = test4;
	}
	
	/**
	 * Sets the test4 and returns the updated object.
	 *
	 * @param test4
	 *            The test4.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest4(final java.util.Map<String, Object> test4) {
		this.setTest4(test4);
		return this;
	}
	/**
	 * Gets the test5.
	 * @return The test5.
	 */
	
	public static java.lang.String getTest5() {
		return  test5 ;
	}
	
	/**
	 * Gets the test6.
	 * @return The test6.
	 */
	
	public org.coldis.library.test.dto.dto.DtoTestObject2Dto[] getTest6() {
		return  test6 ;
	}
	
	/**
	 * Sets the test6.
	 *
	 * @param test6
	 *            The test6.
	 */
	public void setTest6(final org.coldis.library.test.dto.dto.DtoTestObject2Dto[] test6) {
		this.test6 = test6;
	}
	
	/**
	 * Sets the test6 and returns the updated object.
	 *
	 * @param test6
	 *            The test6.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest6(final org.coldis.library.test.dto.dto.DtoTestObject2Dto[] test6) {
		this.setTest6(test6);
		return this;
	}
	/**
	 * Gets the test7.
	 * @return The test7.
	 */
	
	public int getTest7() {
		return  test7 ;
	}
	
	/**
	 * Sets the test7.
	 *
	 * @param test7
	 *            The test7.
	 */
	public void setTest7(final int test7) {
		this.test7 = test7;
	}
	
	/**
	 * Sets the test7 and returns the updated object.
	 *
	 * @param test7
	 *            The test7.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest7(final int test7) {
		this.setTest7(test7);
		return this;
	}
	/**
	 * Gets the test8.
	 * @return The test8.
	 */
	
	public int[] getTest88() {
		return  test88 ;
	}
	
	/**
	 * Sets the test8.
	 *
	 * @param test88
	 *            The test8.
	 */
	public void setTest88(final int[] test88) {
		this.test88 = test88;
	}
	
	/**
	 * Sets the test8 and returns the updated object.
	 *
	 * @param test88
	 *            The test8.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest88(final int[] test88) {
		this.setTest88(test88);
		return this;
	}
	/**
	 * Gets the test9.
	 * @return The test9.
	 */
	
	public java.lang.Integer getTest9() {
		return  test9 ;
	}
	
	/**
	 * Sets the test9.
	 *
	 * @param test9
	 *            The test9.
	 */
	public void setTest9(final java.lang.Integer test9) {
		this.test9 = test9;
	}
	
	/**
	 * Sets the test9 and returns the updated object.
	 *
	 * @param test9
	 *            The test9.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest9(final java.lang.Integer test9) {
		this.setTest9(test9);
		return this;
	}
	/**
	 * Gets the test10.
	 * @return The test10.
	 */
	
	public java.lang.Integer getTest10() {
		return  test10 ;
	}
	
	/**
	 * Gets the test11.
	 * @return The test11.
	 */
	
	public java.lang.Integer getTest11() {
		return  test11 ;
	}
	
	/**
	 * Sets the test11.
	 *
	 * @param test11
	 *            The test11.
	 */
	public void setTest11(final java.lang.Integer test11) {
		this.test11 = test11;
	}
	
	/**
	 * Sets the test11 and returns the updated object.
	 *
	 * @param test11
	 *            The test11.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest11(final java.lang.Integer test11) {
		this.setTest11(test11);
		return this;
	}
	/**
	 * Gets the test12.
	 * @return The test12.
	 */
	
	public java.lang.Integer getTest12() {
		return   test9 != null ? test9 :  test11 != null ? test11 :  null ;
	}
	
	/**
	 * Sets the test12.
	 *
	 * @param test12
	 *            The test12.
	 */
	public void setTest12(final java.lang.Integer test12) {
		this.test12 = test12;
	}
	
	/**
	 * Sets the test12 and returns the updated object.
	 *
	 * @param test12
	 *            The test12.
	 * @return The updated object.
	 */
	public DtoTestObjectDto withTest12(final java.lang.Integer test12) {
		this.setTest12(test12);
		return this;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(
id

,
test1

,
test2

,
test3

,
test4

,
test5


,
test7



,
test10

,
test11




			);
		result = prime * result + Arrays.hashCode(test6);
		result = prime * result + Arrays.hashCode(test88);
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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DtoTestObjectDto other = (DtoTestObjectDto) obj;
		if (! Objects.equals(id, other.id)) {
			return false;
		}
		if (! Objects.equals(test1, other.test1)) {
			return false;
		}
		if (! Objects.equals(test2, other.test2)) {
			return false;
		}
		if (! Objects.equals(test3, other.test3)) {
			return false;
		}
		if (! Objects.equals(test4, other.test4)) {
			return false;
		}
		if (! Objects.equals(test5, other.test5)) {
			return false;
		}
		if (! Arrays.equals(test6, other.test6)) {
			return false;
		}
		if (! Objects.equals(test7, other.test7)) {
			return false;
		}
		if (! Arrays.equals(test88, other.test88)) {
			return false;
		}
		if (! Objects.equals(test10, other.test10)) {
			return false;
		}
		if (! Objects.equals(test11, other.test11)) {
			return false;
		}
		return true;
	}
	
}
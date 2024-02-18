package org.coldis.library.test.dto;

import java.util.List;
import java.util.Map;

import org.coldis.library.dto.DtoOrigin;
import org.coldis.library.test.dto.dto.DtoTestObject2Dto;
import org.coldis.library.test.dto.dto.DtoTestObjectDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * DTO generator test.
 */
public class DtoGeneratorTest {

	/**
	 * Test data.
	 */
	private static final DtoTestObjectDto[] TEST_DATA = { new DtoTestObjectDto().withId(10L).withTest1(new DtoTestObject2Dto().withId(1L).withTest("test1"))
			.withTest2(List.of(new DtoTestObject2Dto().withId(2L).withTest("test2"), new DtoTestObject2Dto().withId(3L).withTest("test3")))
			.withTest4(Map.of("id", 4L, "test", "test4"))
			.withTest6(new DtoTestObject2Dto[] { new DtoTestObject2Dto().withId(5L).withTest("test5"), new DtoTestObject2Dto().withId(6L).withTest("test6") })
			.withTest7(7).withTest88(new int[] { 2, 3, 4 }).withTest9(9) };

	/**
	 * Object mapper.
	 */
	private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	/**
	 * Tests the DTO creation.
	 *
	 * @throws Exception If the test does not succeed.
	 */
	@Test
	public void testDtoCreation() throws Exception {
		// For each test data.
		for (final DtoTestObjectDto originalDto : DtoGeneratorTest.TEST_DATA) {
			// Converts the DTO to the original object and back.
			final DtoTestObject originalObject = this.objectMapper.convertValue(originalDto, DtoTestObject.class);
			final DtoTestObjectDto reconvertedDto = this.objectMapper.convertValue(originalObject, DtoTestObjectDto.class);
			// The DTO should remain the same.
			Assertions.assertEquals(originalDto, reconvertedDto);
			// Asserts that the attribute not used in comparison is the same and it does not
			// affect equality.
			Assertions.assertEquals(originalDto.getTest9(), reconvertedDto.getTest9());
			reconvertedDto.setTest9(8);
			Assertions.assertNotEquals(originalDto.getTest9(), reconvertedDto.getTest9());
			Assertions.assertEquals(originalDto, reconvertedDto);
			
			// Asserts the DTO has the DtoOrigin annotation.
			DtoOrigin dtoOrigin = originalDto.getClass().getAnnotation(DtoOrigin.class);
			Assertions.assertNotNull(dtoOrigin);
			Assertions.assertEquals(originalObject.getClass().getName(), dtoOrigin.originalClassName());

		}
	}

}

package com.lando.notes_hexagonal_architecture_java;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NotesHexagonalArchitectureJavaApplicationTests {
	Calculator calculator = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//Given
		int number1 = 20;
		int number2 = 30;

		//When
		int result = calculator.add(number1, number2);

		//Then
		int expected = 50;
		assertThat(result).isEqualTo(expected);
	}

	static class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}

}

package com.test.technique;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import com.test.technique.exception.InvalidOperandException;
import com.test.technique.exception.StackNotFoundException;
import com.test.technique.service.impl.CalculatorServiceImpl;


@SpringBootTest
class CalculatorServiceTests {

	@InjectMocks
	CalculatorServiceImpl calculatorService;

	
	
	@Test
	public void itShouldReturnTheListOfOperand() {
	   List<String> result = calculatorService.getOperands();
	    assertEquals(4, result.size());
	}
	
	@Test
	public void itShouldThrowExceptionWhenDeleteStackFunctionIsExecuted() {
		calculatorService.setStack(null);
		 Exception exception = assertThrows(StackNotFoundException.class, () -> {
			 calculatorService.deleteStack(1);
		    });
		 
		 String expectedMessage = "Stack is not found";
		 String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
	   
	}
	
	@Test
	public void itShouldThrowExceptionWhenGetStackByIdFunctionIsExecuted() {
		calculatorService.setStack(new HashMap<Integer, Stack<Float>>());
		calculatorService.getStack().put(1, new Stack<Float>());
		 Exception exception = assertThrows(StackNotFoundException.class, () -> {
			 calculatorService.getStackById(2);
		    });
		 
		 String expectedMessage = "Stack with Id: 2 is not found";
		 String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
	   
	}
	
	@Test
	public void itShouldThrowExceptionWhenCalculateFunctionIsExecuted() {
		calculatorService.setStack(new HashMap<Integer, Stack<Float>>());
		calculatorService.getStack().put(1, new Stack<Float>());
		 Exception exception = assertThrows(InvalidOperandException.class, () -> {
			 calculatorService.calculate(1, "addition.");
		    });
		 
		 String expectedMessage = "invalid Operand operation";
		 String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
	   
	}


}

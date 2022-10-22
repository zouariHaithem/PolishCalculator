package com.test.technique.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.test.technique.bean.SatckElement;
import com.test.technique.enums.Operand;
import com.test.technique.exception.InvalidNumberException;
import com.test.technique.exception.InvalidOperandException;
import com.test.technique.exception.StackNotFoundException;
import com.test.technique.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	private Map<Integer, Stack<Float>> stack = null;

	public Map<Integer, Stack<Float>> getStack() {
		if (stack == null) {
			stack = new HashMap<Integer, Stack<Float>>();
		}

		return stack;
	}

	public void setStack(Map<Integer, Stack<Float>> stack) {
		this.stack = stack;
	}

	@Override
	public void createStack() {
		Integer key = this.stack != null ? this.stack.size() + 1 : 1;
		this.getStack().put(key, new Stack<Float>());

	}

	@Override
	public Map<Integer, Stack<Float>> getStacks() {

		return this.stack;
	}

	@Override
	public void insertElement(Integer stackId, SatckElement stackElement) throws InvalidNumberException {

		Stack<Float> stack = this.getStack().get(stackId);
		stack.push(this.formatInput(stackElement.getStackElement()));

	}

	@Override
	public void deleteStack(Integer stackId) throws StackNotFoundException {

		if (this.stack != null) {
			Optional.ofNullable(this.stack.get(stackId))
					.orElseThrow(() -> new StackNotFoundException("Stack with Id: " + stackId + " is not found"));
			this.stack.remove(stackId);
		} else {
			throw new StackNotFoundException("Stack is not found");
		}

	}

	@Override
	public Stack<Float> getStackById(Integer stackId) throws StackNotFoundException {
		Stack<Float> result = null;
		if (this.stack != null) {
			result = Optional.ofNullable(this.stack.get(stackId))
					.orElseThrow(() -> new StackNotFoundException("Stack with Id: " + stackId + " is not found"));
		} else {
			throw new StackNotFoundException("Stack is not found");
		}
		return result;
	}

	@Override
	public void calculate(Integer stackId, String operandName) throws StackNotFoundException, InvalidOperandException {
		if (this.isOperandInput(operandName)) {
			Stack<Float> stack = this.getStackById(stackId);
			Float operand1 = stack.pop();
			Float operand2 = stack.pop();
			switch (Operand.getByOperandByName(operandName)) {
			case ADDITION:
				stack.push(operand1 + operand2);
				break;
			case SUBSTRACTION:
				stack.push(operand2 - operand1);
				break;
			case MULTIPLICATION:
				stack.push(operand2 * operand1);
				break;
			case DIVISION:
				stack.push(operand2 / operand1);
				break;
			default:
				System.out.println("Operand is not recognized");

			}

		}

	}

	private Float formatInput(String input) throws InvalidNumberException {
		try {
			return Float.parseFloat(input);
		} catch (NumberFormatException e) {
			throw new InvalidNumberException("invalid number inserted");
		}
	}

	private boolean isOperandInput(String input) throws InvalidOperandException {
		List<String> operandList = Stream.of(Operand.values()).map(Operand::getOperandValue)
				.collect(Collectors.toList());
		boolean result = operandList.contains(input);
		if (!result) {
			throw new InvalidOperandException("invalid Operand operation");
		}
		return result;

	}

	@Override
	public List<String> getOperands() {

		return Stream.of(Operand.values()).map(Operand::getOperandValue).collect(Collectors.toList());
	}
}

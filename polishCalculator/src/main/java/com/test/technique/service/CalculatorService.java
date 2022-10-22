package com.test.technique.service;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.test.technique.bean.SatckElement;
import com.test.technique.exception.InvalidNumberException;
import com.test.technique.exception.InvalidOperandException;
import com.test.technique.exception.StackNotFoundException;

public interface CalculatorService {

	List<String> getOperands();

	void createStack();

	Map<Integer, Stack<Float>> getStacks();

	void insertElement(Integer stackId, SatckElement stackElement) throws InvalidNumberException;

	void deleteStack(Integer stackId) throws StackNotFoundException;

	Stack<Float> getStackById(Integer stackId) throws StackNotFoundException;

	void calculate(Integer stackId, String operandName) throws StackNotFoundException, InvalidOperandException;
}

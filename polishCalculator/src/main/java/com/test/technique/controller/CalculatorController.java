package com.test.technique.controller;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.technique.bean.SatckElement;
import com.test.technique.exception.InvalidNumberException;
import com.test.technique.service.CalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "RPN Api", description = "Polish Calculator")
@RestController
@RequestMapping("/rpn")
public class CalculatorController {

	@Autowired
	private CalculatorService calculatorService;

	@ApiOperation(value = "List all the operand")
	@GetMapping("/op")
	private List<String> getOperands() {

		return this.calculatorService.getOperands();
	}

	@ApiOperation(value = "Create a new stack")
	@PostMapping("/stack")
	private void createStack() {

		this.calculatorService.createStack();
	}

	@ApiOperation(value = "List the availble stack")
	@GetMapping("/stack")
	private Map<Integer, Stack<Float>> getStack() {

		return this.calculatorService.getStacks();
	}

	@ApiOperation(value = "Get a stack")
	@GetMapping("/stack/{stackId}")
	private Stack<Float> getStackById(@PathVariable Integer stackId) throws Exception {

		return this.calculatorService.getStackById(stackId);
	}

	@ApiOperation(value = "Push a new value to a stack")
	@PostMapping("/stack/{stackId}")
	private void insertSatckElement(@PathVariable Integer stackId, @RequestBody SatckElement stackckElement)
			throws InvalidNumberException {

		this.calculatorService.insertElement(stackId, stackckElement);
	}

	@ApiOperation(value = "Delete a stack")
	@DeleteMapping("/stack/{stackId}")
	private void deleteStack(@PathVariable Integer stackId) throws Exception {

		this.calculatorService.deleteStack(stackId);
	}

	@ApiOperation(value = "Apply an operand to a stack")
	@PostMapping("/op/{op}/stack/{stackId}")
	private Stack<Float> calculate(@PathVariable("op") String operandName, @PathVariable Integer stackId)
			throws Exception {

		this.calculatorService.calculate(stackId, operandName);
		return this.calculatorService.getStackById(stackId);
	}

}

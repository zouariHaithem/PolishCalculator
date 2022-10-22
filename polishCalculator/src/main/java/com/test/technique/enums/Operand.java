package com.test.technique.enums;

import com.test.technique.exception.InvalidOperandException;

public enum Operand {
	
	ADDITION("addition"),
	SUBSTRACTION("soustraction"),
	DIVISION("division"),
	MULTIPLICATION("multiplication");
	
	 private Operand(String operandValue) {
		this.operandValue = operandValue ;
	}
	
	private  String operandValue ;
	
	// getter method
    public String getOperandValue()
    {
        return this.operandValue;
    }
    
    public static Operand getByOperandByName(String operandValue ) throws InvalidOperandException {
    	for(Operand operand : Operand.values()) {  
    		if(operand.getOperandValue().equals(operandValue)) {
    			return operand ;
    		}
    	}
    	throw new InvalidOperandException("invalid Operand operation");

    }
    
}

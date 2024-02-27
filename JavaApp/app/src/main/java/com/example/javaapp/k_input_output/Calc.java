package com.example.javaapp.k_input_output;

public class Calc {

    public long makeOperation(long number1, long number2, String operationType) {
        if (operationType.equals("+")) {
            return sum(number1, number2);
        }
        throw new UnsupportedOperationException("Даная операция не поддерживается");
    }

    private long sum(long number1, long number2) {
        return number1 + number2;
    }
}

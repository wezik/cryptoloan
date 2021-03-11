package com.cryptoloan.exception;

public class LoanNotFoundException extends Exception {
    public LoanNotFoundException() {
        super("Loan not found");
    }
}

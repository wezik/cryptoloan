package com.cryptoloan.exception;

public class LoanTypeNotFoundException extends Exception {
    public LoanTypeNotFoundException() {
        super("LoanType not found");
    }
}

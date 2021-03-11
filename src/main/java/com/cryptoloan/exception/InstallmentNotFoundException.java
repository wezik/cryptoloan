package com.cryptoloan.exception;

public class InstallmentNotFoundException extends Exception {
    public InstallmentNotFoundException() {
        super("Installment not found");
    }
}

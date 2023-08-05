package com.example.erp.enums;

public enum KDVType {
    EXCEPTION_KDV("EXCEPTION_KDV", 1.01),
    GENERAL_KDV("GENERAL_KDV", 1.1),
    SPECIFIC_KDV("SPECIFIC_KDV", 1.2);

    private final String kdv;
    private final double rate;

    public String getKdv() {
        return kdv;
    }

    public double getRate() {
        return rate;
    }

    KDVType(String key, double value) {
        kdv = key;
        rate = value;
    }

}

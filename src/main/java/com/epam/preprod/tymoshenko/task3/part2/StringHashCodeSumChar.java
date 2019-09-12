package com.epam.preprod.tymoshenko.task3.part2;

import java.util.Arrays;

public class StringHashCodeSumChar {
    private static final int MAX_LENGTH = 4;
    private String vendorCode;

    public StringHashCodeSumChar(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        StringHashCodeSumChar stringHashCodeSumChar = (StringHashCodeSumChar) object;
        return vendorCode.equals(stringHashCodeSumChar.vendorCode);
    }

    @Override
    public int hashCode() {
       return  vendorCode.chars().limit(MAX_LENGTH).sum();
    }


    @Override
    public String toString() {
        return "StringHashCodeSumChar{" +
                "vendorCode='" + vendorCode + '\'' +
                '}';
    }
}

package com.epam.preprod.tymoshenko.task3.part2;

public class StringHashCodeStringLength {
    private String vendorCode;

    public StringHashCodeStringLength(String vendorCode) {
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
        StringHashCodeStringLength stringHashCodeStringLength = (StringHashCodeStringLength) object;
        return vendorCode.equals(stringHashCodeStringLength.vendorCode);
    }

    @Override
    public int hashCode() {
        return vendorCode.length();
    }

    @Override
    public String toString() {
        return "StringHashCodeStringLength{" +
                "vendorCode='" + vendorCode + '\'' +
                '}';
    }
}

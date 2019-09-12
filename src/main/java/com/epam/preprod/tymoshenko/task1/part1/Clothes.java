package com.epam.preprod.tymoshenko.task1.part1;

public abstract class Clothes extends Product {
    private static final int DEFAULT_SIZE = 0;
    private  int size;

    public Clothes() {
        super();
        this.size= DEFAULT_SIZE;
    }

    public Clothes(String id, String name, double price, String country, int size) {
        super(id, name, price, country);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return   super.toString()+
                "size=" + size +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Clothes clothes = (Clothes) o;
        return ((Clothes) o).getId() == clothes.getId();
    }

}

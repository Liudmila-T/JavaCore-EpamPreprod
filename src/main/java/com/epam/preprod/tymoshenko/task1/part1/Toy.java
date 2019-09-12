package com.epam.preprod.tymoshenko.task1.part1;

public abstract class Toy extends Product {
    private static final String DEFAULT_CATEGORY = "For all";
    private String category;

    public Toy() {
        super();
        this.category = DEFAULT_CATEGORY;
    }

    public Toy(String id,String name, double price, String country, String category) {
        super(id, name, price, country);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", category='" + category+ '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Toy toy = (Toy) o;
        return ((Toy) o).getId() == toy.getId();
    }


}

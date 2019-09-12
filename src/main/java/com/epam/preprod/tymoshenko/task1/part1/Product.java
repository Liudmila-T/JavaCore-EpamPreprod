package com.epam.preprod.tymoshenko.task1.part1;

public abstract class Product {

    private String id;
    private String name;
    private double price;
    private String country;

    public Product() {
    }

    public Product(String id, String name, double price, String country) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id=id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", country='" + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }


}

package com.epam.preprod.tymoshenko.task1.part1;

public class Doll extends Toy {
    private static final String DEFAULT_HAIR_COLOR = "black";
    private String hairColor;

    public Doll() {
        super();
        this.hairColor = DEFAULT_HAIR_COLOR;
    }

    public Doll(String id, String name, double price, String country, String category, String hairColor) {
        super(id, name, price, country, category);
        this.hairColor = hairColor;
    }


    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doll doll = (Doll) o;
        return ((Doll) o).getId()==(doll.getId());
    }

    @Override
    public String toString() {
        return "Doll{" +
                 super.toString()+
                ", hairColor='" + hairColor + '\'' +
                "} " ;
    }
}

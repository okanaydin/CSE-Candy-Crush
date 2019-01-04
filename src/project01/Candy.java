package project01;

import java.util.Objects;

public class Candy {
    private String shape;
    private String color;

    public Candy(String shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public String getColor() {
        return color;
    }

    public boolean canCrush(Candy candy) {

        return shape.equals(candy.shape) || color.equals(candy.color);
    }

    public boolean equals(Candy candy) {
        return shape.equals(candy.shape) && color.equals(candy.color);
    }

}

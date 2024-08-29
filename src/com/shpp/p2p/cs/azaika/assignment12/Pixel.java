package com.shpp.p2p.cs.azaika.assignment12;

import java.util.Objects;

class Pixel {
    private int x;
    private int y;
    private int value;

    public Pixel(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public boolean isNeighbor(Pixel otherPixel) {
        return Math.abs(x - otherPixel.x) <= 1 && Math.abs(y - otherPixel.y) <= 1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  "Pixel" + this.hashCode() + "{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x && y == pixel.y && value == pixel.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, value);
    }
}

package fr.bonamy.dame_alliance.util;

import java.util.Objects;

public class Point {

    private int a;
    private int b;

    public Point(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public boolean isInRange(Point min, Point max) {
        return a >= min.getA() && a <= max.getA() && b >= min.getB() && b <= max.getB();
    }

    public Point add(Point other) {
        return new Point(this.a + other.getA(), this.b + other.getB());
    }

    public Point sub(Point other) {
        return new Point(this.a - other.getA(), this.b - other.getB());
    }

    public Point scale(int scalar) {
        return new Point(this.a * scalar, this.b * scalar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return a == point.a && b == point.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Point{a=%d, b=%d}".formatted(a, b);
    }


    public int magnitude() {
        return (int) Math.sqrt(a * a + b * b);
    }

    public Point subtract(Point from) {
        return new Point(from.getA() - this.a, from.getB() - this.b);
    }

    public Point direction(Point other) {
        int normalizedA = Integer.compare(other.getA() - this.a, 0);
        int normalizedB = Integer.compare(other.getB() - this.b, 0);
        return new Point(normalizedA, normalizedB);
    }

    public Point divide(int i) {
        if (i == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return new Point(this.a / i, this.b / i);
    }
    
    
}

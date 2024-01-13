package agh.ics.oop.model;

import agh.ics.oop.model.enums.MapDirection;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d {
    private int x;
    private int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }
    public void setX(int val){
        this.x = val;
    }
    public int getY() {
        return y;
    }
    public void setY(int val){
        this.y = val;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }
    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }
    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other){
        int x = this.x + other.x;
        int y = this.y + other.y;
        return new Vector2d(x,y);
    }
    public Vector2d subtract(Vector2d other){
        int x = this.x - other.x;
        int y = this.y - other.y;
        return new Vector2d(x,y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d (max(this.x,other.x), max(this.y, other.y));
    }
    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d (min(this.x,other.x), min(this.y, other.y));
    }
    public Vector2d opposite(){
        int x = -this.x;
        int y = -this.y;
        return new Vector2d(x,y);
    }
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        // Porównujemy pola obu obiektów
        return this.x == that.x && this.y == that.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1.toString());
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2.toString());
        System.out.println(position1.add(position2).toString());
        MapDirection direction1 = MapDirection.NORTH;
        System.out.println(direction1.next().toString());
        System.out.println(direction1.previous().toString());
        System.out.println(direction1.toUnitVector().toString());
    }
    //nie wiem co to tu robi, ale przynajmniej widać, że te metody działają
}

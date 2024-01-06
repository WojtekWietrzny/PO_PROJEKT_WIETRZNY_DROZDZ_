package agh.ics.oop.model;

public interface WorldElement {
    Vector2d getPosition();
    //zwracanie pozycji zwierzęcia bądź trawy
    MoveDirection getOrientation();
    String toString();
    //reprezentacja zwierzęcia/kępki trawy w stringu
}

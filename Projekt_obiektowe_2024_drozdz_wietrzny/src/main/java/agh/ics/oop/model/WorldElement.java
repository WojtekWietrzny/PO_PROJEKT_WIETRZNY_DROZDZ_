package agh.ics.oop.model;

import agh.ics.oop.model.enums.MapDirection;

public interface WorldElement {
    Vector2d getPosition();
    //zwracanie pozycji zwierzęcia bądź trawy
    MapDirection getOrientation();
    String toString();
    //reprezentacja zwierzęcia/kępki trawy w stringu
}

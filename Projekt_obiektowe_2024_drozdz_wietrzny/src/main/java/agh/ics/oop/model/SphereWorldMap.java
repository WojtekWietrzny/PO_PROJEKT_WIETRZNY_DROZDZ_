package agh.ics.oop.model;

import agh.ics.oop.model.*;

import java.util.List;

public class SphereWorldMap extends AbstractWorldMap {


    public SphereWorldMap(int width, int height, BehaviourType behaviourType) {
        super(width, height, behaviourType);
    }

    @Override
    public Boundary getCurrentBounds() {
        return null;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return null;
    }

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public List<Animal> getOrderedAnimals(List<Animal> animals_listed) {
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }
}

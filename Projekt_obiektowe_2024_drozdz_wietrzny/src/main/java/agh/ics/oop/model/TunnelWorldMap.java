package agh.ics.oop.model;

import java.util.List;

public class TunnelWorldMap extends AbstractWorldMap{

    public TunnelWorldMap(int width, int height, BehaviourType behaviourType, int genomeSize) {
        super(width, height, behaviourType, genomeSize);
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

package agh.ics.oop.model;

import agh.ics.oop.model.*;

import java.util.List;

public class SphereWorldMap extends AbstractWorldMap {


    public SphereWorldMap(int width, int height, BehaviourType behaviourType, int genomeSize) {
        super(width, height, behaviourType, genomeSize);
    }

    @Override
    public void advanceAnimals(){
        for (Animal animal : this.animals){
            Vector2d positionToCheck = animal.wantToMove();
            if (positionToCheck.getX() < 0){
                positionToCheck.setX(this.bounds.upperRight().getX() - 1);
            }
            if (positionToCheck.getX() >= this.bounds.upperRight().getX()){
                positionToCheck.setX(0);
            }
            if (positionToCheck.getY() < 0){
                positionToCheck.setY(0);
                animal.setOrientation(animal.getOrientation().opposite());
            }
            if (positionToCheck.getY() >= this.bounds.upperRight().getY()){
                positionToCheck.setY(this.bounds.upperRight().getY() - 1);
                animal.setOrientation(animal.getOrientation().opposite());
            }

            animal.setPosition(positionToCheck);
        }
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
        return true;
    }
}

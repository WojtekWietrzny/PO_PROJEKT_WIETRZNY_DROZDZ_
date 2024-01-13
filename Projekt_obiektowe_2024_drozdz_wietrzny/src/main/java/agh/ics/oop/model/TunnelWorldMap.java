package agh.ics.oop.model;

import agh.ics.oop.model.enums.BehaviourType;
import agh.ics.oop.model.enums.MoveDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TunnelWorldMap extends AbstractWorldMap{

    public TunnelWorldMap(int width, int height, BehaviourType behaviourType, int genomeSize) {
        super(width, height, behaviourType, genomeSize);
        generateTunnels(width/4);
    }

    public void generateTunnels(int amount){
        int maxX = this.bounds.upperRight().getX();
        int maxY = this.bounds.upperRight().getY();
        int randomColumn, randomRow;
        Vector2d tunnelStart, tunnelEnd;
        ArrayList<ArrayList<Vector2d>> allPosition = new ArrayList<>();
        ArrayList<Vector2d> tempArrayList;
        Random random = new Random();

        for (int i=0; i<maxX; i++){
            tempArrayList = new ArrayList<>();
            for (int j=0; j<maxY; j++){
                tempArrayList.add(new Vector2d(i,j));
            }
            allPosition.add(tempArrayList);
        }

        for(int i=0; i<amount; i++){
            // Generuje tunnelStart
            randomColumn = random.nextInt(allPosition.size());
            randomRow = random.nextInt(allPosition.get(randomColumn).size());
            tunnelStart = allPosition.get(randomColumn).get(randomRow);
            this.elements.get(tunnelStart).setTunelStart(true);
            allPosition.get(randomColumn).remove(randomRow);
            if (allPosition.get(randomColumn).isEmpty()){
                allPosition.remove(randomColumn);
            }

            // Generuje tunnelEnd
            randomColumn = random.nextInt(allPosition.size());
            randomRow = random.nextInt(allPosition.get(randomColumn).size());
            tunnelEnd = allPosition.get(randomColumn).get(randomRow);
            this.elements.get(tunnelStart).setTunnelDestination(tunnelEnd);
            this.elements.get(tunnelEnd).setTunelEnd(true);
            allPosition.get(randomColumn).remove(randomRow);
            if (allPosition.get(randomColumn).isEmpty()){
                allPosition.remove(randomColumn);
            }
        }
    }

    @Override
    public void advanceAnimals(){
        for (Animal animal : this.animals){
            Vector2d positionToCheck = animal.wantToMove();
            if (this.elements.get(positionToCheck).isTunelStart()){
                animal.setPosition(this.elements.get(positionToCheck).getTunnelDestination());
            }
            else if (canMoveTo(positionToCheck)){
                animal.setPosition(positionToCheck);
            }
        }
        notifyObservers();
    }

    @Override
    public Boundary getCurrentBounds() {
        return bounds;
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
        int maxX = this.bounds.upperRight().getX();
        int maxY = this.bounds.upperRight().getY();
        int givenX = position.getX();
        int givenY = position.getY();

        return (0 < givenX && givenX < maxX && 0 < givenY && givenY < maxY);
    }
}

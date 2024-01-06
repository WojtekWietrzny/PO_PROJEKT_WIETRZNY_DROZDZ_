package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualiser;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{


    private final Map<Vector2d, MapCell> elements = new HashMap<>();
    private final ArrayList<Animal> animals = new ArrayList<>();
    private int animalsQuantity = 0;
    private final int energyToReproduct = 1;
    private final Boundary bounds;

    public AbstractWorldMap(int width, int height){
        Vector2d lowerLeft = new Vector2d(0,0);
        Vector2d upperRight = new Vector2d(width, height);
        Boundary mapBounds = new Boundary(lowerLeft, upperRight);
        this.bounds = mapBounds;

    }


    private void startMap(int width, int height){
        for(int i = 0; i < width;i++){
            for(int j = 0; j < height; j++){
                Vector2d position = new Vector2d(i,j);
                MapCell cell = new MapCell();
                elements.put(position, cell);
            }
        }
    }


    public void move(WorldElement animal){
        for (int i = 0; i < animalsQuantity; i++){

        }
    }

    public void place(WorldElement animal) {
        if(canMoveTo(animal.getPosition())){
            elements.get(animal.getPosition()).addObject(animal);
            animalsQuantity += 1;
            animals.add((Animal)animal);
        }
    }
public void addGrass(Vector2d position){
        MapCell cell = elements.get(position);
        cell.growGrass();
}

public void addJungle(Vector2d position){
        MapCell cell = elements.get(position);
        cell.addJungle();
}

    public void removeDead(){


    }
    public void eat(){

    }
    public void reproduce(){

    }
    public void growPlants(){

    }

    @Override
    public abstract List<Animal> getOrderedAnimals(List<Animal> animals_listed);

    public String toString() {
        return new MapVisualiser(this).draw(lowerLeft, upperRight);
    }
}

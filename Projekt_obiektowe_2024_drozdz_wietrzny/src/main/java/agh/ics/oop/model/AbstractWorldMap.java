package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{


    private ArrayList<Boundary> jungles = new ArrayList<>();
    private Map<Vector2d,Animal> animals = new HashMap<>();
    //private Map<Vector2d,Plant> plants = new HashMap<>();
    private int animalsQuantity = 0;


    public void move(Animal animal){
        for (int i = 0; i < animalsQuantity; i++){

        }
    }

    public void place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            animalsQuantity += 1;
        }
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

}

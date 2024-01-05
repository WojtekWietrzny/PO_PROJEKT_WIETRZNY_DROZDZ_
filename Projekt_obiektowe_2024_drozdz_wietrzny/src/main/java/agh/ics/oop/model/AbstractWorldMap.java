package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualiser;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{


    private final Map<Vector2d, MapCell> elements = new HashMap<>();
    private final ArrayList<Animal> animals = new ArrayList<>();
    private int animalsQuantity = 0; // Czy nie wystarczy nam po prostu animals.size()?
    private final int energyToReproduce = 1;
    private final int grassNutritionalValue = 3;
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

    public void place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            elements.get(animal.getPosition()).addAnimal(animal);
            animalsQuantity += 1;
            animals.add(animal);
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
        ArrayList<Animal> animalsToRemove = new ArrayList<>();
        for (Animal animal : this.animals){
            if (animal.getEnergy() < 0){
                this.elements.get(animal.getPosition()).animalDied(animal);
                animalsToRemove.add(animal);
            }
        }
        for (Animal animal : animalsToRemove){
            this.animals.remove(animal);
        }
    }
    public void eat(){
        for (Animal animal : this.animals){
            if (this.elements.get(animal.getPosition()).isGrassPresent()){
                this.elements.get(animal.getPosition()).eatGrass();
                animal.addEnergy(this.grassNutritionalValue);
            }
        }

    }
    public void reproduce(){
        for (Animal animal : this.animals){
            ArrayList<Animal> animalsInCurrentCell = this.elements.get(animal.getPosition()).getAnimals();
            ArrayList<Animal> children = new ArrayList<>();
            if (animalsInCurrentCell.size() > 1){
                for (int i=0; i<animalsInCurrentCell.size(); i++){
                    Animal potentialParent1 = animalsInCurrentCell.get(i);
                    Animal potentialParent2 = animalsInCurrentCell.get((i+1)%animalsInCurrentCell.size());
                    if( potentialParent1.getEnergy() >= energyToReproduce && potentialParent2.getEnergy() >= energyToReproduce){
                        potentialParent1.reduceEnergy(energyToReproduce);
                        potentialParent2.reduceEnergy(energyToReproduce);
                        Animal child = potentialParent1.createChild(potentialParent2);
                        children.add(child);
                    }
                }
            }
            for (Animal child : children){
                place(child);
            }
        }

    }
    public void growPlants(){

    }

    @Override
    public abstract List<Animal> getOrderedAnimals(List<Animal> animals_listed);

    public String toString() {
        return new MapVisualiser(this).draw(lowerLeft, upperRight);
    }
}

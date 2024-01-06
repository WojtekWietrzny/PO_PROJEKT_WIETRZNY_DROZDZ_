package agh.ics.oop.model;

import java.util.ArrayList;

public class MapCell {
    private final ArrayList<WorldElement> objects; // Czy będziemy tu trzymać cokolwiek innego niż zwierzaki?, wsm chyba nie, można faktycznie zrobić z tego animal
    private final ArrayList<Animal> animals;
    private boolean grass = false;
    private boolean jungle = false;
    private
    private int deathCounter = 0;

    public MapCell(){
        this.objects = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public boolean isGrassPresent(){
        return grass;
    }
    public boolean isJungle(){
        return jungle;
    }
    public void addJungle(){
        jungle = true;
    }
    public void growGrass(){
        grass=true;
    }
    public void eatGrass(){
        grass=false;
    }
    void upDeathCounter(){
        deathCounter += 1;
    }
    void setDeathCounter(int newDeathCounter){
        this.deathCounter = newDeathCounter;
    }

    public int getDeathCounter() {
        return deathCounter;
    }
    public void addObject(WorldElement object){
        objects.add(object);
    }
    public void removeObject(WorldElement object){
        objects.remove(object);
    }
    public void addAnimal(Animal animal){
        this.animals.add(animal);
        this.objects.add(animal);
    }
    public void removeAnimal(Animal animal){
        this.objects.remove(animal);
        this.animals.remove(animal);
    }
    public void animalDied(Animal animal){
        removeAnimal(animal);
        upDeathCounter();
    }

    public ArrayList<WorldElement> getObjects() {
        return objects;
    }
    public ArrayList<Animal> getAnimals(){ return this.animals; }
}

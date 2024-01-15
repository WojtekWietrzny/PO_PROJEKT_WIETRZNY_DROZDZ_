package agh.ics.oop.model;

import java.util.ArrayList;

public class MapCell {
    private final ArrayList<Animal> animals;
    private boolean grass = false;
    private boolean jungle = false;
    private boolean tunelStart = false;
    private boolean tunelEnd = false;
    private Vector2d tunnelDestination;
    private int deathCounter = 0;

    public MapCell(){
        this.animals = new ArrayList<>();
    }

    public boolean isTunelStart() {
        return tunelStart;
    }

    public void setTunelStart(boolean tunelStart) {
        this.tunelStart = tunelStart;
    }

    public void setTunelEnd(boolean tunelEnd) {
        this.tunelEnd = tunelEnd;
    }

    public boolean isTunelEnd() {
        return tunelEnd;
    }
    public void setTunnelDestination(Vector2d position){
        this.tunnelDestination = position;
    }
    public Vector2d getTunnelDestination(){
        return tunnelDestination;
    }

    public Animal getFirstAnimal(){
        return this.animals.get(0);
    }

    public boolean isJungle(){
        return this.jungle;
    }
    public void setJungle(){
        this.jungle = true;
    }
    public boolean isGrassPresent(){
        return grass;
    }
    public void growGrass(){
        grass=true;
    }
    public void eatGrass(){
        grass=false;
    }
    public void upDeathCounter(){
        deathCounter += 1;
    }
    public void setDeathCounter(int newDeathCounter){
        this.deathCounter = newDeathCounter;
    }

    public int getDeathCounter() {
        return deathCounter;
    }
    public void addAnimal(Animal animal){
        this.animals.add(animal);
    }
    public void removeAnimal(Animal animal){
        this.animals.remove(animal);
    }
    public void animalDied(Animal animal){
        removeAnimal(animal);
        upDeathCounter();
    }
    public boolean isOccupied(){
        if (!animals.isEmpty()){
            return true;
        }
        return isGrassPresent();
    }
    public ArrayList<Animal> getAnimals(){ return this.animals; }
}

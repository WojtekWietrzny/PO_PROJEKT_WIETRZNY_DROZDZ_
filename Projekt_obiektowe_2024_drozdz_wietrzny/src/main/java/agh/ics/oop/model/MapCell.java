package agh.ics.oop.model;

import java.util.ArrayList;

public class MapCell {
    private final ArrayList<WorldElement> objects;
    private boolean grass = false;
    private boolean jungle = false;
    private int deathCounter = 0;

    public MapCell(){
        this.objects = new ArrayList<>();
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
    public void animalDied(WorldElement object){
        removeObject(object);
        upDeathCounter();
    }

    public ArrayList<WorldElement> getObjects() {
        return objects;
    }
}

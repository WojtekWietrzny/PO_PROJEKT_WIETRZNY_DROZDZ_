package agh.ics.oop.model;

import agh.ics.oop.model.enums.MapDirection;

public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;
    private WorldMap map;
    private Gene gene;
    private int energy;
    private int age = 0;
    private int childCount = 0;
    private int amountOfGrassEaten = 0;

    public Animal(WorldMap map){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
    }
    public Animal(WorldMap map, Vector2d initialPosition, Gene gene) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        this.gene = gene;
    }
    public String toString() {
        return switch(orientation){
            case NORTH -> "↑";
            case NORTHEAST -> "↗";
            case EAST -> "→";
            case SOUTHEAST -> "↘";
            case SOUTH -> "↓";
            case SOUTHWEST -> "↙";
            case WEST -> "←";
            case NORTHWEST -> "↖";

        };

    }

    public boolean isAt(Vector2d second_position) {
        return this.position.equals(second_position);
    }
    public Vector2d getPosition(){
        return position;
    }
    public void setPosition(Vector2d position){
        this.position = position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }
    public void setOrientation(MapDirection mapDirection){
        this.orientation = mapDirection;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
    public void addEnergy(int value) { this.energy += value; }
    public void reduceEnergy(int value){ this.energy -= value; }
    public Gene getGene() { return this.gene; }
    public int getAge(){
        return this.age;
    }
    public int getChildCount(){
        return this.childCount;
    }
    public void increaseChildCount(){
        this.childCount += 1;
    }
    public int getAmountOfGrassEaten(){
        return this.amountOfGrassEaten;
    }
    public void increaseGrassEaten(){
        this.amountOfGrassEaten += 1;
    }
    public void age(){
        this.age += 1;
    }

    public void move() {
        this.orientation = this.orientation.rotate(this.gene.getCurrent());
        Vector2d newPosition = new Vector2d(this.position.getX(), this.position.getY());
        newPosition.add(this.orientation.toUnitVector());

        if (this.map.canMoveTo(newPosition)){
            this.position = newPosition;
        }
    }

    public Vector2d wantToMove(){
        this.orientation = this.orientation.rotate(this.gene.getCurrent());
        Vector2d newPosition = new Vector2d(this.position.getX(), this.position.getY());
        newPosition = newPosition.add(this.orientation.toUnitVector());
        return newPosition;
    }

    public Animal createChild(Animal other){
        Gene childGene = this.gene.createChild(other.getGene(), this.energy, other.getEnergy());
        Vector2d childPosition = this.position;
        WorldMap childMap = this.map;
        this.increaseChildCount();
        other.increaseChildCount();
        return new Animal(childMap, childPosition, childGene);
    }
}

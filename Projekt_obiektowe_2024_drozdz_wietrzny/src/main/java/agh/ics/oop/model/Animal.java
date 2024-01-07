package agh.ics.oop.model;

import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.Random;

public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;
    private WorldMap map;
    private Gene gene;
    private int energy;

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
            case NORTH -> "N";
            case NORTHEAST -> "N-E";
            case EAST -> "E";
            case SOUTHEAST -> "S-E";
            case SOUTH -> "S";
            case SOUTHWEST -> "S-W";
            case WEST -> "W";
            case NORTHWEST -> "N-W";

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
        newPosition.add(this.orientation.toUnitVector());
        return newPosition;
    }

    public Animal createChild(Animal other){
        Gene childGene = this.gene.createChild(other.getGene(), this.energy, other.getEnergy());
        Vector2d childPosition = this.position;
        WorldMap childMap = this.map;
        return new Animal(childMap, childPosition, childGene);
    }
}

package agh.ics.oop.model;

import agh.ics.oop.model.Vector2d;

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

    public MapDirection getOrientation() {
        return orientation;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
    public void addEnergy(int value) { this.energy += value; }

    public void move() {
        this.orientation = this.orientation.rotate(this.gene.getCurrent());
        Vector2d newPosition = this.position.add(this.orientation.toUnitVector());

        if (this.map.canMoveTo(newPosition)){
            this.position = newPosition;
        }
    }
}

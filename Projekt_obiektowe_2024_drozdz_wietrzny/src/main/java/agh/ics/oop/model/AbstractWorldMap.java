package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualiser;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{


    protected final Map<Vector2d, MapCell> elements = new HashMap<>();
    protected final List<Animal> animals = new ArrayList<>();
    protected List<Vector2d> allPositions = new ArrayList<>();
    protected List<Vector2d> emptyPositionsPreferred = new ArrayList<>();
    protected List<Vector2d> emptyPositionsNotPreferred = new ArrayList<>();
    protected List<Vector2d> emptyPositionsPreferredPrototype = new ArrayList<>();
    protected List<Vector2d> emptyPositionsNotPreferredPrototype = new ArrayList<>();
    protected int animalsQuantity = 0; // Czy nie wystarczy nam po prostu animals.size()?,
    // nie bo to się przydaje potem przy statystykach, żeby trackować ile się przewinęło w ogóle przez program
    //ale faktycznie do kodu w pętli lepiej używać size
    private final int energyToReproduce = 1;
    private final int energyConsumedByReproduction = 2;
    private final int grassNutritionalValue = 3;
    private final BehaviourType behaviourType;
    private final int genomeSize;
    protected final Boundary bounds;
    private final List<MapChangeListener> observers = new ArrayList<>();

    public AbstractWorldMap(int width, int height, BehaviourType behaviourType, int genomeSize){
        Vector2d lowerLeft = new Vector2d(0,0);
        Vector2d upperRight = new Vector2d(width, height);
        Boundary mapBounds = new Boundary(lowerLeft, upperRight);
        this.bounds = mapBounds;
        this.behaviourType = behaviourType;
        this.genomeSize = genomeSize;
        float midPoint = Math.round(height/2);
        startMap(width, height);
        allPositions.sort((o1, o2) -> Float.compare(Math.abs(o1.getY() - midPoint), Math.abs(o2.getY() - midPoint)));
        emptyPositionsPreferred = allPositions.subList(0, (int) Math.round(0.2*width*height));
        emptyPositionsNotPreferred = allPositions.subList((int) Math.round(0.2*width*height), allPositions.size());
        emptyPositionsPreferredPrototype = emptyPositionsPreferred;
        emptyPositionsNotPreferredPrototype = emptyPositionsNotPreferred;
    }
    private void startMap(int width, int height){
        for(int i = 0; i < width;i++){
            for(int j = 0; j < height; j++){
                Vector2d position = new Vector2d(i,j);
                MapCell cell = new MapCell();
                elements.put(position, cell);
                allPositions.add(position);
            }
        }
    }

    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
    public  void addObserver(MapChangeListener observer){
        observers.add(observer);
    }
    public  void removeObserver(MapChangeListener observer){
        observers.remove(observer);
    }

    public void generateAnimals(int amount){
        Random random = new Random();
        int x, y;
        for(int i=0; i<amount; i++){
            x = random.nextInt(this.bounds.upperRight().getX() - this.bounds.lowerLeft().getX()) + this.bounds.lowerLeft().getX();
            y = random.nextInt(this.bounds.upperRight().getY() - this.bounds.lowerLeft().getY()) + this.bounds.lowerLeft().getY();
            Animal animal = new Animal(this, new Vector2d(x, y), Gene.generateRandomGene(this.genomeSize, this.behaviourType));
            place(animal);
        }
    }

    public void advanceAnimals(){
        for (Animal animal : this.animals){
            Vector2d positionToCheck = animal.wantToMove();
            if (canMoveTo(positionToCheck)){
                animal.setPosition(positionToCheck);
            }
        }
    }


    public boolean canMoveTo(Vector2d position){
        return position.follows(bounds.lowerLeft()) && position.precedes(bounds.upperRight());
    }

    public void place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            elements.get(animal.getPosition()).addAnimal(animal);
            animalsQuantity += 1;
            animals.add(animal);
            notifyObservers("Placed animal at " + animal.getPosition());
        }
    }
    public void addGrass(Vector2d position){
            MapCell cell = elements.get(position);
            cell.growGrass();
    }

    public void reduceAnimalEnergy(){
        for (Animal animal : this.animals){
            animal.reduceEnergy(1);
        }
    }

    public void setAnimalEnergy(int energy){
        for (Animal animal : this.animals){
            animal.setEnergy(energy);
        }
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
            Vector2d position = animal.getPosition();
            if (this.elements.get(position).isGrassPresent()){
                this.elements.get(position).eatGrass();
                animal.addEnergy(this.grassNutritionalValue);
                if(emptyPositionsPreferredPrototype.contains(position)){
                    emptyPositionsPreferred.add(position);
                }
                if(emptyPositionsNotPreferredPrototype.contains(position)){
                    emptyPositionsNotPreferred.add(position);
                }
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
                        potentialParent1.reduceEnergy(energyConsumedByReproduction);
                        potentialParent2.reduceEnergy(energyConsumedByReproduction);
                        Animal child = potentialParent1.createChild(potentialParent2);
                        child.setEnergy(energyConsumedByReproduction*2);
                        children.add(child);
                    }
                }
            }
            for (Animal child : children){
                place(child);
            }
        }

    }
    //funkcja odpowiedzialna za losowanie kolejnego pola na trawę
    public Vector2d randomNextPosition(){
        Random random = new Random();
        Vector2d position;
        int number = random.nextInt(100);
        if (number < 80){
            if(!(emptyPositionsPreferred.isEmpty())) {
                position = emptyPositionsPreferred.get(random.nextInt(emptyPositionsPreferred.size()));
                emptyPositionsPreferred.remove(position);
            }
            else{
                position = emptyPositionsNotPreferred.get(random.nextInt(emptyPositionsNotPreferred.size()));
                emptyPositionsNotPreferred.remove(position);
            }
        }
        else{
            if(!(emptyPositionsNotPreferred.isEmpty())) {
                position = emptyPositionsNotPreferred.get(random.nextInt(emptyPositionsNotPreferred.size()));
                emptyPositionsNotPreferred.remove(position);
            }
            else{
                position = emptyPositionsPreferred.get(random.nextInt(emptyPositionsPreferred.size()));
                emptyPositionsPreferred.remove(position);
            }

        }
        return position;
    }
    //sprawdzanie czy istnieją miejsca na trawę
    public boolean freePlaces(){
            return  !(emptyPositionsNotPreferred.isEmpty()) || !(emptyPositionsPreferred.isEmpty());
    }


    //wyrastanie określonej ilości trawy - cała 1 faza dnia
    public void growGrass(int grassDaily){
        for(int i =0; i < grassDaily; i++){
            if(freePlaces()){
                Vector2d position = randomNextPosition();
                addGrass(position);
            }
        }
    }

    public int getAnimalsQuantity(){
        return animalsQuantity;
    }


    public List<Vector2d> getEmptyPositionsNotPreferred() {
        return emptyPositionsNotPreferred;
    }

    public List<Vector2d> getEmptyPositionsPreferred() {
        return emptyPositionsPreferred;
    }

    @Override
    public abstract List<Animal> getOrderedAnimals(List<Animal> animals_listed);

    /*public String toString() {
        return new MapVisualiser(this).draw(lowerLeft, upperRight);
    }*/
}

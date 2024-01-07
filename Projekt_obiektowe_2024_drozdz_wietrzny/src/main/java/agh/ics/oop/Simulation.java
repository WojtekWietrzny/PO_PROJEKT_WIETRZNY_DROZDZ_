package agh.ics.oop;

import agh.ics.oop.model.*;

public class Simulation implements Runnable{
    private int currentDay;
    private SimulationParameters simulationParameters;
    private AbstractWorldMap worldMap;

    public Simulation(int width, int height, SimulationParameters allGenericParameters){
        this.currentDay = 0;
        this.simulationParameters = allGenericParameters;

        //tu będą jeszcze przekazywane ustawienia z klasy settings zapisującej te konfiguracje jakoś do pliku i czytającej z niego
        // + statystyki

        if (this.simulationParameters.mapType() == MapType.SphereMap){
            this.worldMap = new SphereWorldMap(width, height, simulationParameters.behaviourType(), simulationParameters.genomeSize());
        }else{
            this.worldMap = new TunnelWorldMap(width, height, simulationParameters.behaviourType(), simulationParameters.genomeSize());
        }

        this.worldMap.generateAnimals(simulationParameters.startingAnimalAmount());
        this.worldMap.setAnimalEnergy(simulationParameters.startingAnimalEnergy());
        this.worldMap.growGrass(simulationParameters.startingPlantAmount());

    }

    @Override
    public void run() {
        while (this.worldMap.getAnimalsQuantity() > 0) {
            this.worldMap.reduceAnimalEnergy();
            this.worldMap.removeDead();
            this.worldMap.advanceAnimals();
            this.worldMap.eat();
            this.worldMap.reproduce();
            this.worldMap.growGrass(simulationParameters.plantGrowthRate());
        }
    }
}

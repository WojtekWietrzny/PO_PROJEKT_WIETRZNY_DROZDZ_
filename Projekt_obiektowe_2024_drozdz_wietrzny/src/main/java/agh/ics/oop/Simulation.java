package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.enums.MapType;

public class Simulation implements Runnable{
    private int currentDay;
    private SetupParameters setupParameters;
    private SimulationParameters  simulationParameters;
    private AbstractWorldMap worldMap;

    //SimulationEngine powinien korzystać jakoś z klasy Settings
    public Simulation(SetupParameters allSetupParameters){
        this.currentDay = 0;
        this.setupParameters = allSetupParameters;
        this.simulationParameters = new SimulationParameters(setupParameters.getWidth(), setupParameters.getHeight(), setupParameters.getMapType(),
                setupParameters.getBehaviourType(), setupParameters.getStartingPlantAmount(), setupParameters.getPlantGrowthRate(), setupParameters.getStartingAnimalAmount(),
                setupParameters.getStartingAnimalEnergy(), setupParameters.getEnergyToReproduce(), setupParameters.getEnergyConsumedByReproduction(), setupParameters.getMinMutations(), allSetupParameters.getMaxMutations(), setupParameters.getGenomeSize());


        //tu będą jeszcze przekazywane ustawienia z klasy settings zapisującej te konfiguracje jakoś do pliku i czytającej z niego
        // + statystyki

        if (this.simulationParameters.mapType() == MapType.SphereMap){
            this.worldMap = new SphereWorldMap(simulationParameters.width(), simulationParameters.height(), simulationParameters.behaviourType(), simulationParameters.genomeSize());
        }else{
            this.worldMap = new TunnelWorldMap(simulationParameters.width(), simulationParameters.height(), simulationParameters.behaviourType(), simulationParameters.genomeSize());
        }

        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        worldMap.addObserver(consoleMapDisplay);

        this.worldMap.generateAnimals(simulationParameters.startingAnimalAmount());
        this.worldMap.setAnimalEnergy(simulationParameters.startingAnimalEnergy());
        this.worldMap.growGrass(simulationParameters.startingPlantAmount());
    }

    @Override
    public void run() {
        while (this.worldMap.getAnimalsQuantity() > 0) {
            this.worldMap.reduceAnimalEnergy();
            this.worldMap.removeDead();
            this.worldMap.ageAnimals();
            this.worldMap.advanceAnimals();
            this.worldMap.eat();
            this.worldMap.reproduce();
            this.worldMap.growGrass(simulationParameters.plantGrowthRate());
            this.currentDay += 1;
        }
    }
}

package agh.ics.oop;

import agh.ics.oop.model.enums.BehaviourType;
import agh.ics.oop.model.enums.MapType;

public class SetupParameters {
    private final String configurationName;
    private final MapType mapType;
    private final BehaviourType behaviourType;
    private final int width;
    private final int height;
    private final int startingPlantAmount;
    private final int plantGrowthRate;
    private final int startingAnimalAmount;
    private final int startingAnimalEnergy;
    private final int energyToReproduce;
    private final int energyConsumedByReproduction;
    private final int minMutations;
    private final int maxMutations;
    private final int genomeSize;

    public SetupParameters(String[] setup) throws Exception{
        this.configurationName = setup[0];
        switch(setup[1]){
            case "TunnelMap" -> this.mapType = MapType.TunnelMap;
            case "SphereMap" -> this.mapType = MapType.SphereMap;
            default -> throw new Exception("Choose Correct Map Type");
        }
        switch (setup[2]){
            case "CompletePredestination" -> this.behaviourType = BehaviourType.CompletePredestination;
            case "BitofCraziness" -> this.behaviourType = BehaviourType.BitofCraziness;
            default -> throw new Exception("Choose Correct Behaviour Type");
        }
        this.width = Integer.parseInt(setup[3]);
        this.height = Integer.parseInt(setup[4]);
        this.startingPlantAmount = Integer.parseInt(setup[5]);
        this.plantGrowthRate = Integer.parseInt(setup[6]);
        this.startingAnimalAmount = Integer.parseInt(setup[7]);
        this.startingAnimalEnergy = Integer.parseInt(setup[8]);
        this.energyToReproduce = Integer.parseInt(setup[9]);
        this.energyConsumedByReproduction = Integer.parseInt(setup[10]);
        this.minMutations = Integer.parseInt(setup[11]);
        this.maxMutations = Integer.parseInt(setup[12]);
        this.genomeSize = Integer.parseInt(setup[13]);

        if (width < 0 || width > 10000){
            throw new Exception("Choose width from 1 to 9999");
        }
        if (height < 0 || height > 10000){
            throw new Exception("Choose height from 1 to 9999");
        }
        if (startingPlantAmount < 0 || startingPlantAmount > height*width){
           throw new Exception("Choose correct starting plant ammount");
        }
        if (plantGrowthRate < 0 ){
            throw new Exception("Choose non-negative plant growth rate");
        }
        if(startingAnimalAmount <= 0 ){
            throw new Exception("Choose positive starting animal amount");
        }
        if(startingAnimalEnergy <= 0){
            throw new Exception("Choose positive starting animal energy");
        }
        if(energyToReproduce < 0){
            throw new Exception("Choose non-negative energy needed to reproduce");
        }
        if(energyConsumedByReproduction <= 0){
            throw new Exception("Choose positive energy given at reproduction");
        }
        if(minMutations < 0 || minMutations > genomeSize){
            throw new Exception("Choose correct minimal mutations");
        }
        if(maxMutations < 0 || maxMutations < minMutations ){
            throw new Exception("Choose correct maximum mutaions");
        }
        if(genomeSize <= 0){
            throw new Exception("Choose positive genome size");
        }
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public MapType getMapType() {
        return mapType;
    }
    public BehaviourType getBehaviourType() {
        return behaviourType;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartingPlantAmount() {
        return startingPlantAmount;
    }

    public int getPlantGrowthRate() {
        return plantGrowthRate;
    }

    public int getStartingAnimalAmount() {
        return startingAnimalAmount;
    }

    public int getStartingAnimalEnergy() {
        return startingAnimalEnergy;
    }

    public int getEnergyToReproduce() {
        return energyToReproduce;
    }

    public int getEnergyConsumedByReproduction() {
        return energyConsumedByReproduction;
    }

    public int getMinMutations() {
        return minMutations;
    }

    public int getMaxMutations() {
        return maxMutations;
    }

    public int getGenomeSize() {
        return genomeSize;
    }
}

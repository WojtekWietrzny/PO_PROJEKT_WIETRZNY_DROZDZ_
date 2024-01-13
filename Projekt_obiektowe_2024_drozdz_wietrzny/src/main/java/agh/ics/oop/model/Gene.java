package agh.ics.oop.model;

import agh.ics.oop.model.enums.BehaviourType;

import java.util.ArrayList;
import java.util.Random;


public class Gene {
    private ArrayList<Integer> dna = new ArrayList<Integer>();
    private final BehaviourType behaviourType;
    private int index = 0;


    public Gene(int size, BehaviourType behaviourType){
        generateRandomDna(size);
        this.behaviourType = behaviourType;
    }

    public void setDna(ArrayList<Integer> dna) {
        this.dna = dna;
    }
    public ArrayList<Integer> getDna() { return this.dna; }

    public static Gene generateRandomGene(int size, BehaviourType behaviourType){
        Gene newGene = new Gene(size, behaviourType);
        return newGene;
    }

    public Integer getCurrent(){
        Integer current = dna.get(index);
        this.advance();
        return current;
    }

    public void generateRandomDna(int size){
        Random random = new Random();
        for(int i=0; i<size; i++){
            dna.add(random.nextInt(8));
        }
    }

    private void mutate(int mutationChance){
        Random random = new Random();
        for(int i=0; i<this.dna.size(); i++){
            if (random.nextInt(100) + 1 <= mutationChance){
                this.dna.set(i, random.nextInt(8));
            }
        }
    }

    private void advance(){
        if (this.behaviourType == BehaviourType.BitofCraziness){
            Random random = new Random();
            int actual = random.nextInt(100);
            if (actual < 20){
                index = random.nextInt(dna.size()) ;
            }
            else{
                index = (index + 1) % dna.size();
            }
        }
        else{
            index = (index + 1) % dna.size();
        }
    }

    public Gene createChild(Gene other, int ownEnergy, int otherEnergy){
        Random random = new Random();
        Gene dominantGene;
        Gene recesiveGene;
        Gene childGene = new Gene(this.dna.size(), this.behaviourType);
        ArrayList<Integer> newDna = new ArrayList<>();

        int percentOwnEnergy = (ownEnergy / (ownEnergy + otherEnergy) * this.dna.size());


        if (ownEnergy > otherEnergy){
            dominantGene = this;
            recesiveGene = other;
        }else{
            dominantGene = other;
            recesiveGene = this;
        }

        int side = random.nextInt(2);
        int i;
        if (side == 0){ //Bierzemy lewą dominującego
            for(i=0; i < percentOwnEnergy; i++)
            {
                newDna.add(dominantGene.dna.get(i));
            }while (newDna.size() != recesiveGene.dna.size()){
                newDna.add(recesiveGene.dna.get(i));
                i += 1;
            }
        }else{      //Bierzemy prawą stronę
            for(i=0; i < percentOwnEnergy; i++)
            {
                newDna.add(dominantGene.dna.get(dominantGene.dna.size() - 1 - i));
            }
            while (newDna.size() != recesiveGene.dna.size()){
                newDna.add(recesiveGene.dna.get(recesiveGene.dna.size() - 1 - i));
                i += 1;
            }
        }
        childGene.setDna(newDna);
        childGene.mutate(80);
        return childGene;
    }
}

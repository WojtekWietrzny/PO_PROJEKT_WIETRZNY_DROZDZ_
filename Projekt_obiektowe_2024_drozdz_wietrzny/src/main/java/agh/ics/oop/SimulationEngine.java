package agh.ics.oop;

import agh.ics.oop.model.*;

public class SimulationEngine implements Runnable{
    private int currentDay;
    private final BehaviourType behaviourType;
    private final MapType mapType;
    private AbstractWorldMap worldMap;

    public SimulationEngine(int width, int height, MapType chosenMap, BehaviourType chosenBehaviour){
        this.currentDay = 0;
        this.mapType = chosenMap;
        this.behaviourType = chosenBehaviour;
        //tu będą jeszcze przekazywane ustawienia z klasy settings zapisującej te konfiguracje jakoś do pliku i czytającej z niego
        // + statystyki

        if (this.mapType == MapType.SphereMap){
            this.worldMap = new SphereWorldMap(width, height);
        }else{
            this.worldMap = new TunnelWorldMap(width, height);
        }

        
    }

    @Override
    public void run() {

    }
}

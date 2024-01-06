package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private ArrayList<Simulation> simulations;
    private ArrayList<Thread> threads = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(ArrayList<Simulation> simulationss){
        this.simulations = simulationss;
    }

    public void runSync(){
        for(Simulation simulation : simulations){
            simulation.run();
        }
    }

    public void runAsync(){
        for(Simulation simulation : this.simulations){
            threads.add(new Thread(simulation));
        }

        for(Thread thread: this.threads){
            thread.start();
        }
    }


    public void runAsyncInThreadPool(){
        for(Simulation simulation : simulations){
            executorService.submit(new Thread(simulation));
        }
        executorService.shutdown();
    }
}

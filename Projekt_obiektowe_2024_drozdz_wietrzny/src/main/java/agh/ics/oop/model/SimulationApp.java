package agh.ics.oop.model;

import agh.ics.oop.model.*;
import agh.ics.oop.model.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();


        GrassField worldMap = new GrassField(10);
        configureStage(primaryStage, viewRoot);

        presenter.setWorldMap(worldMap);
        worldMap.addObserver(presenter);

        primaryStage.show();

    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        Scene scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.show();
    }
    public static void startSimulation(int simulationCounter) {
        try {
            FXMLLoader loader = new FXMLLoader(SimulationApp.class.getClassLoader().getResource("simulation.fxml"));
            BorderPane simulationRoot = loader.load();

            Stage simulationStage = new Stage();
            simulationStage.setTitle("Simulation ");
            simulationStage.setScene(new Scene(simulationRoot, 400, 300));
            simulationStage.show();
            SimulationPresenter presenter = loader.getController();
            GrassField worldMap = new GrassField(10);
            presenter.setWorldMap(worldMap);
            worldMap.addObserver(presenter);


            // Increment the simulation counter for the next simulation
            simulationCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

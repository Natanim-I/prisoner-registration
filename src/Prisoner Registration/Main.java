package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrisonHome PHome = new PrisonHome();
        PHome.start(PHome.HomeStage);
        Functions.Release();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

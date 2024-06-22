package com.example.assignment1;

// Import necessary JavaFX classes
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    // The start method is the main entry point for all JavaFX applications
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file to create the user interface defined in combined_scene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/assignment1/combined_scene.fxml"));

            // Create a scene with the loaded FXML content, setting its width and height
            Scene scene = new Scene(loader.load(), 800, 600);

            // Add a CSS stylesheet to the scene for styling the UI components
            scene.getStylesheets().add(getClass().getResource("/com/example/assignment1/style.css").toExternalForm());

            // Set the title of the application window
            stage.setTitle("Sports Team Performance");

            // Set the icon of the application window
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/assignment1/ipl_icon.png")));

            // Set the scene to the stage (the application window) and display it
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // Print stack trace if there is an exception during the loading of the FXML or setting up the stage
            e.printStackTrace();
        }
    }

    // The main method which serves as the entry point for the Java application
    public static void main(String[] args) {
        // Launch the JavaFX application, which calls the start method
        launch(args);
    }
}

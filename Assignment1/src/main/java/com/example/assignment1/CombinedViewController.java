package com.example.assignment1;

// Import necessary JavaFX and SQL classes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tooltip;

import java.sql.ResultSet;

public class CombinedViewController {

    // FXML annotations to link UI components
    @FXML
    private TableView<IPLTeam> tableView;

    @FXML
    private TableColumn<IPLTeam, String> teamNameColumn;
    @FXML
    private TableColumn<IPLTeam, Integer> winsColumn;
    @FXML
    private TableColumn<IPLTeam, Integer> lossesColumn;
    @FXML
    private TableColumn<IPLTeam, Double> runRateColumn;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button toggleButton;

    @FXML
    private RadioButton winsRadioButton;
    @FXML
    private RadioButton lossesRadioButton;
    @FXML
    private RadioButton runRateRadioButton;

    @FXML
    private VBox chartContainer;

    // ToggleGroup for radio buttons
    private ToggleGroup toggleGroup;

    // Observable list to hold IPL team data
    private ObservableList<IPLTeam> teamData = FXCollections.observableArrayList();

    // Method to initialize the controller after its root element has been processed
    public void initialize() {
        // Initialize TableView columns
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        lossesColumn.setCellValueFactory(new PropertyValueFactory<>("losses"));
        runRateColumn.setCellValueFactory(new PropertyValueFactory<>("runRate"));

        // Initialize BarChart axes labels
        xAxis.setLabel("Teams");
        yAxis.setLabel("Wins");

        // Fetch data from the database
        try {
            ResultSet rs = DBUtility.fetchIPLData();
            while (rs.next()) {
                IPLTeam team = new IPLTeam(rs.getString("team_name"), rs.getInt("wins"), rs.getInt("losses"), rs.getDouble("run_rate"));
                teamData.add(team);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the fetched data to the TableView
        tableView.setItems(teamData);

        // Initially show the TableView and hide the BarChart and radio buttons
        chartContainer.setVisible(false);
        updateChart("Wins");

        // Initialize and set the ToggleGroup for radio buttons
        toggleGroup = new ToggleGroup();
        winsRadioButton.setToggleGroup(toggleGroup);
        lossesRadioButton.setToggleGroup(toggleGroup);
        runRateRadioButton.setToggleGroup(toggleGroup);

        // Add a listener to the toggle group to handle radio button selection
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                if (selectedRadioButton != null) {
                    String label = selectedRadioButton.getText();
                    updateChart(label);
                }
            }
        });

        // Add a tooltip to the toggle button
        toggleButton.setTooltip(new Tooltip("Click to toggle between Table and Chart view"));
    }

    // Method to handle the toggle button action
    @FXML
    private void handleToggleChart() {
        // Toggle visibility of TableView and BarChart container
        boolean isTableVisible = tableView.isVisible();
        tableView.setVisible(!isTableVisible);
        chartContainer.setVisible(isTableVisible);

        // Update button text based on the current view
        if (isTableVisible) {
            toggleButton.setText("Show Table");
        } else {
            toggleButton.setText("Show Chart");
        }
    }

    // Method to handle radio button action (can be left empty since the listener handles the action)
    @FXML
    private void handleRadioButtonAction() {
    }

    // Method to update the BarChart based on the selected radio button label
    private void updateChart(String label) {
        // Clear existing data in the BarChart
        barChart.getData().clear();

        // Create a new series for the BarChart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(label);

        // Add data to the series based on the selected label
        for (IPLTeam team : teamData) {
            switch (label) {
                case "Wins":
                    series.getData().add(new XYChart.Data<>(team.getTeamName(), team.getWins()));
                    yAxis.setLabel("Wins");
                    break;
                case "Losses":
                    series.getData().add(new XYChart.Data<>(team.getTeamName(), team.getLosses()));
                    yAxis.setLabel("Losses");
                    break;
                case "Run Rate":
                    series.getData().add(new XYChart.Data<>(team.getTeamName(), team.getRunRate()));
                    yAxis.setLabel("Run Rate");
                    break;
            }
        }

        // Add the series to the BarChart
        barChart.getData().add(series);
    }
}

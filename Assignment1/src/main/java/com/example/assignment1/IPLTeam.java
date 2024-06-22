package com.example.assignment1;

// Define the IPLTeam class
public class IPLTeam {
    // Private member variables to store team details
    private String teamName;
    private int wins;
    private int losses;
    private double runRate;

    // Constructor to initialize the IPLTeam object with provided values
    public IPLTeam(String teamName, int wins, int losses, double runRate) {
        this.teamName = teamName; // Set the team name
        this.wins = wins;         // Set the number of wins
        this.losses = losses;     // Set the number of losses
        this.runRate = runRate;   // Set the run rate
    }

    // Getter method for team name
    public String getTeamName() {
        return teamName;
    }

    // Getter method for number of wins
    public int getWins() {
        return wins;
    }

    // Getter method for number of losses
    public int getLosses() {
        return losses;
    }

    // Getter method for run rate
    public double getRunRate() {
        return runRate;
    }
}

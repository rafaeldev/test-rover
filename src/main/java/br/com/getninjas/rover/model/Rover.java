package br.com.getninjas.rover.model;

import br.com.getninjas.rover.enumeration.Guidance;

/**
 * @author Rafael G. Francisco
 */
public class Rover {

    private int axisX;
    private int axisY;
    private Guidance guidance;
    private String exploreCommands;

    public Rover(int axisX, int axisY, Guidance guidance) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.guidance = guidance;
    }

    public int getAxisX() {
        return axisX;
    }

    public void setAxisX(int axisX) {
        this.axisX = axisX;
    }

    public int getAxisY() {
        return axisY;
    }

    public void setAxisY(int axisY) {
        this.axisY = axisY;
    }

    public Guidance getGuidance() {
        return guidance;
    }

    public void setGuidance(Guidance guidance) {
        this.guidance = guidance;
    }

    public String getExploreCommands() {
        return exploreCommands;
    }

    public Rover setExploreCommands(String exploreCommands) {
        this.exploreCommands = exploreCommands;
        return this;
    }

}

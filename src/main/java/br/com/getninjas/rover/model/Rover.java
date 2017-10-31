package br.com.getninjas.rover.model;

import br.com.getninjas.rover.enumeration.Guidance;

/**
 * @author Dell
 */
public class Rover {

    private final Plateau plateau;
    private int axisX;
    private int axisY;
    private Guidance guidance;

    public Rover(int axisX, int axisY, Guidance guidance, Plateau plateau) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.guidance = guidance;
        this.plateau = plateau;
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

    public Plateau getPlateau() {
        return plateau;
    }

}

package br.com.getninjas.rover.model;

/**
 *
 * @author Rafael G. Francisco
 */
public class Plateau {
    
    //to axis X
    private int base;
    //to axis Y
    private int height;
    
    final private int area;

    public Plateau(int base, int height) {
        this.base = base;
        this.height = height;
        this.area = this.base * this.height;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return area;
    }
    
}

package br.com.getninjas.rover.enumeration;

/**
 *
 * @author Rafael G. Francisco
 */
public enum Guidance {
    N("North", +1, Axis.Y), 
    S("South", -1, Axis.Y), 
    E("East", +1, Axis.X), 
    W("West", -1, Axis.X);
    
    private final String translate;
    //Esse atributo indica qual a direção que será andada no eixo
    private final int axisValue;
    private final Axis axis;

    private Guidance(String translate, int axisValue, Axis axis) {
        this.translate = translate;
        this.axisValue = axisValue;
        this.axis = axis;
    }

    public String getTranslate() {
        return translate;
    }

    public int getAxisValue() {
        return axisValue;
    }

    public Axis getAxis() {
        return axis;
    }
    
}

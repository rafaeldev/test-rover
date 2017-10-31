package br.com.getninjas.rover.enumeration;

/**
 *
 * @author Dell
 */
public enum Command {
    L("Left"), R("Right"), M("Move");
    
    private final String translate;

    private Command(String translate) {
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }
    
}

/*
        Author: T Rex Rothwell
        Date: 1/23/2020
*/
public class Attribute {
    public String Specific;
    public String NAME;
    
    public Attribute(String actual, String type) {
        this.NAME = actual.replace("\"","");
        this.Specific = type.replace("\"","");
    }
    
    public boolean isActual(String s){
        return this.Specific.equals(s);
    }
    
    @Override
    public String toString() {
        return NAME + ":\t" + Specific;
    }
}

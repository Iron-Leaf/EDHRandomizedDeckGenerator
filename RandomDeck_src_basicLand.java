/*
        Author: T Rex Rothwell
        Date: 1/23/2020
*/
public class basicLand extends Card {
    
    public basicLand(String colorIdent, String name) {
        super(colorIdent, name);
        this.ManaCost="0";
    }
    
    public basicLand(String colorIdent, String name , int num) {
        super(colorIdent, name);
        this.ManaCost="0";
        this.num = num;
    }
    
    public void bump(){
        this.num++;
    }
    public void bump(int in){
        this.num+= in;
    }
}

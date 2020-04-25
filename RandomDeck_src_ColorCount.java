/*
        Author: T Rex Rothwell
        Date: 1/23/2020
*/

public class ColorCount {
    public char Counting;
    public int Total;
    
    public ColorCount(char enumer, int count) {
        this.Counting = enumer;
        this.Total = count;
    }
    
    public boolean isSame(char r){
        if (Counting == r){
            Total++;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Counting + ":\t" + Total;
    }
}

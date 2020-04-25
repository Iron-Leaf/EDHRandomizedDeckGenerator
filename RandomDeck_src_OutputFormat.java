public class OutputFormat {
    public String name;
    public int num;
    public boolean isCommander;
    
    public OutputFormat(String name, int num) {
        this.num = num;
        this.name = name;
    }
    
    public OutputFormat(String name, int num, boolean isCommander) {
        this.name = name;
        this.num = num;
        this.isCommander = isCommander;
    }
    
    public String toString(){
        String out = num + " " + name;
        if (!isCommander)
            return out;
        else
            return out+" *CMDR*";
    }
}

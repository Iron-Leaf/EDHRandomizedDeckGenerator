/*
        Author: T Rex Rothwell
        Date: 1/23/2020
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Card {
    public ArrayList<Attribute> attributes = new ArrayList<>();
    public String ColorIdent;
    public String Name;
    public String ManaCost;
    public int num = 1;
    
    public Card(String colorIdent, String name) {
        ColorIdent = colorIdent;
        Name = name;
    }
    
    public Card(String Parm) throws Exception {
        boolean succeded = false;
        while (!succeded) {
        URL oracle = new URL("https://api.scryfall.com/cards/random?q="+Parm);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
    
        
            String inputLine;
            String temp = "";
        
        while ((inputLine = in.readLine()) != null) {
            temp += inputLine;
        }
        in.close();
    
            try {
                readCard(temp);
                succeded = true;
            }catch (Exception e){
                System.out.println(e+" on Card:\t"+temp);
            }
        }
        ColorIdent = findAttribute("color_identity");
        Name = findAttribute("name");
        ManaCost = findAttribute("mana_cost");
    }
    
    private String findAttribute(String s) {
        for (int i = 0; i<attributes.size();i++){
            if (attributes.get(i).NAME.equals(s)) {
                if ((attributes.get(i).Specific.equals(" ") || attributes.get(i).Specific.isEmpty() )&& (s.equals("color_identity")||s.equals("mana_cost")))
                    return "C";
                if(s.equals("color_identity")||s.equals("mana_cost")) {
                    String temp = attributes.get(i).Specific
                            .replace(",", "")
                            .replace("{", "")
                            .replace("}", "")
                            .replace("/", "")
                            .replace("X", "");
                    if (temp.isEmpty() || temp.equals(" ") || temp.equals(null))
                        temp = "C";
                    return temp;
                }
                return attributes.get(i).Specific;
            }
        }
        return "null";
    }
    
    public void readCard(String s){
        s = s.substring(1,s.length()-1).replace('[','"').replace(']','"');
        String[] list = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < s.length()-1 || list[i].contains("reserved"); i++){
            if (list[i].contains("oldschool")) {
                break;
            }
            String[] temp = list[i].split(":");
                attributes.add(new Attribute(temp[0],temp[1]));
        }
    }
    
    public void ReadArraylist(){
        for (int i = 0; i < attributes.size();i++)
            System.out.println(attributes.get(i).toString());
    }
}

/*
        Author: T Rex Rothwell
        Date: 1/23/2020
*/

import java.util.ArrayList;

public class Deck {
    public boolean isDone = false;
    
    public String colors;
    public ArrayList<Card> cardList = new ArrayList<>();
    public ArrayList<ColorCount> colorCounts = new ArrayList<>();
    public Boolean isMono;
    
    public String base = "legal:commander+-layout:meld+";
    public String CMDR = base+("is:Commander");
    
    public int TotalSymbols = 0;
    
    
    public Deck() throws Exception{
    }
    
    public void start() throws Exception{
        cardList.add(new Card(CMDR));
        colors = cardList.get(0).ColorIdent;
    
        String NonLand = base+("-t:land+commander:")+colors;
        String Land = base+("t:land+-t:basic+commander:")+colors;
    
        isMono = true;
        addCards(Land,10);
    
        isMono = cardList.get(0).ColorIdent.length()==1;
        
        countMana(cardList.get(cardList.size()-1));
    
        addCards(NonLand,59);
        
        calcLands();
        
        this.isDone =true;
    }
    
    private void calcLands() throws InterruptedException {
        //mono
        if(isMono){
            while (cardList.size()<100){
                //30 lands
                landType(this.colors.charAt(0),99);
            }
            return;
        }else {
            int i = 0;
            int remaininglands = 99-this.cardList.size();
            while (cardList.size()<100){
                if (i>=this.colorCounts.size()) {
                    System.out.println("Reset i:\t"+i+"   "+this.cardList.size());
                    remaininglands = 100-this.cardList.size();
                    i = 0;
                }
                //30 lands
                double per = ((double) colorCounts.get(i).Total/(double) this.TotalSymbols);
                double z = (remaininglands*per)+1;
                landType(colorCounts.get(i).Counting,(int)z);
                i++;
            }
        }
        
    }
    
    private void landType(char charAt, int run) {
        //WUBRGC
        for(int i = 0; i < run; i++) {
            if (cardList.size() == 100)
                return;
            if (charAt == 'W')
            cardList.add(new basicLand("W", "Plains"));
            else if (charAt == 'U')
                cardList.add(new basicLand("U", "Island"));
            else if (charAt == 'B')
                cardList.add(new basicLand("B", "Swamp"));
            else if (charAt == 'R')
                cardList.add(new basicLand("R", "Mountain"));
            else if (charAt == 'G')
                cardList.add(new basicLand("G", "Forest"));
            else if (charAt == 'C')
                cardList.add(new basicLand("C", "Wastes"));
        }
    }
    
    public void addCards(String type,int num) throws Exception{
        num = cardList.size()+num;
        while (cardList.size()<num){
            Card temp = new Card(type);
            
            boolean isUnique = true;
            int i = 0;
            while (i < cardList.size()){
                isUnique = !temp.Name.equals(cardList.get(i).Name);
                if (!isUnique)
                    break;
                else
                    i++;
            }
            if (isUnique){
                cardList.add(temp);
                if (!isMono){
                    countMana(cardList.get(cardList.size()-1));
                }
            }
        }
    }
    
    private void countMana(Card card) throws Exception{
        String t = card.ManaCost;
        //cleanup
        for (char z = '0'; z <= '9';z++) {
            t = t.replace(z + "", "C");
        }
    
        card.ColorIdent = card.ColorIdent.replace("C","").replace("c","");
        t = card.ColorIdent.replace("C","").replace("c","");
        if (card.ColorIdent.equals("C")||card.ColorIdent.equals(" ")||card.ColorIdent.isEmpty()) {
            card.ColorIdent = "";
            return;
        }else {
            while (!t.isEmpty() || !t.equals(" ") || t.length()==0) {
                int i = 0;
                boolean wasCounted = false;
                try {
                    while (i < colorCounts.size()) {
                        if (colorCounts.get(i).isSame(t.charAt(0))) {
                            wasCounted = true;
                            break;
                        }
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!wasCounted && t.length() > 0)
                    colorCounts.add(new ColorCount(t.charAt(0), 1));
                this.TotalSymbols++;
                if (t.length() == 1)
                    return;
                t = t.substring(1);
            }
        }
    }
    
    public void printSymbols(){
        if (isMono) {
            System.out.println("This Deck is Mono Colored");
            return;
        }
        System.out.print("\nMANA SYMBOLS:\n");
        for (int i = 0; i < colorCounts.size();i++){
            System.out.print(colorCounts.get(i).Counting+": "+colorCounts.get(i).Total+"\n");
        }
        System.out.println("Totaling: "+this.TotalSymbols+" Mana Symbols");
    }
    
    public void printDeck(){
        System.out.print("\n\nDECK LIST:\n");
        for (int i = 0; i < cardList.size();i++){
            System.out.print(cardList.get(i).num+"\t"+cardList.get(i).Name+"\n");
        }
    }
}

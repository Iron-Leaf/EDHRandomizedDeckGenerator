/*
        Author: T Rex Rothwell
        Date: 1/23/2020
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main implements Runnable{
    public static ArrayList<OutputFormat> out = new ArrayList<>();
    public JFrame frame;
    
    public static void main(String[] args) throws Exception {
        Main example = new Main();
        SwingUtilities.invokeLater(example);
        Deck n;
        do {
            n = new Deck();
            n.start();
        } while (!n.isDone);
        output(n);
        write(printOut());
        example.frame.dispatchEvent(new WindowEvent(example.frame, WindowEvent.WINDOW_CLOSING));
    }
    
    public void run() {
        this.frame = new JFrame("Now Loading");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 00));
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public static void output(Deck in) {
        boolean isFirst = true;
        while (!in.cardList.isEmpty()) {
            if (isFirst) {
                out.add(new OutputFormat(in.cardList.get(0).Name, 1, true));
                isFirst = false;
            } else
                out.add(new OutputFormat(in.cardList.get(0).Name, 1));
            in.cardList.remove(0);
            int i = 0;
            while (i < in.cardList.size()) {
                if (out.get(out.size() - 1).name == in.cardList.get(i).Name) {
                    out.get(out.size() - 1).num += 1;
                    in.cardList.remove(0);
                } else
                    i++;
            }
        }
    }
    
    public static String printOut() {
        String temp = "";
        for (int i = 0; i < out.size(); i++){
            temp += out.get(i).toString()+"\n";
        }
        return temp;
    }
    
    public static void write(String s) throws IOException {
        String str = s;
        BufferedWriter writer = new BufferedWriter(new FileWriter(("RandDeck-"+ (System.currentTimeMillis()+"") +".txt"), false));
        writer.append(str);
        
        writer.close();
    }
    
}

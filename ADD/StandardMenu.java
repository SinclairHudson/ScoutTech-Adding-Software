import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.awt.event.*;

import org.jsoup.nodes.Document;
import org.jsoup.*;

import java.lang.Math.*;

import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.TextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.TransferHandler;
import javax.swing.BoxLayout;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.util.*;
public class StandardMenu extends JFrame{
    public static JTextField[] fields = new JTextField[12];
    public static String[] labels = {
            "Name",//0
            "SKU",//1
            "UPC/EAN",//2
            "Quantity",//3
            "USD Price",//4
            "CAD Price",//5
            "Cost",//6
            "Brand",//7
            "Length (in)",//8
            "Width (in)",//9
            "Height (in)",//10
            "Weight (lbs)",//11
            "Wrong",
        };
    public StandardMenu(){
        setLayout(null);
        setTitle("Add a Standard product");
        for(int i = 0; i < fields.length; i++){
            fields[i] = new JTextField();
            if(i == 0){
                fields[i].setBounds(120,i*30+30, 250, 25);
            }
            else{
                fields[i].setBounds(120,i*30+30, 150, 25);
            }
            JLabel SKULabel = new JLabel(labels[i]);
            SKULabel.setBounds(10,i*30+30, 95, 25);
            add(fields[i]);
            add(SKULabel);
        }
        String[] bigcomcat = new String[204];
        try {
            CsvReader lookup = new CsvReader("lookup.csv");	
            lookup.readHeaders();
            int i = 0;
            while (lookup.readRecord())
            {		    
                String value = lookup.get("Name");
                bigcomcat[i] = value;
                i++;
            }

            lookup.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final JComboBox<String> bigcat = new JComboBox<String>(bigcomcat);
        bigcat.setBounds(120,400,450,25);
        add(bigcat);

        String[] ebaycat = { 
                "Camping & Hiking # 16034",
                "Climbing & Caving # 30105",
                "Other Outdoor Sports # 159048",
                "Fishing # 1492",
                "Hunting # 7301",
                "Fitness, Running, Yoga > Clothing & Accessories # 158913",
                "Fitness, Running, Yoga > Shoes # 158916",      
            };
        final JComboBox<String> ecat = new JComboBox<String>(ebaycat);
        ecat.setBounds(120,430,450,25);
        add(ecat);

        JTextArea descbox = new JTextArea();
        descbox.setBounds(120, 500, 400, 420);
        JScrollPane jScrollPane1 = new JScrollPane(descbox);
        add(descbox);

        JCheckBox amazon = new JCheckBox("Amazon.ca");
        amazon.setBounds(575,490,120,25);
        add(amazon);
        JCheckBox ebay = new JCheckBox("Ebay.ca");
        ebay.setBounds(575,540,120,25);
        add(ebay);
        JCheckBox BigC = new JCheckBox("Scouttech.com");
        BigC.setBounds(575,590,120,25);
        add(BigC);
        JCheckBox newegg = new JCheckBox("Newegg.ca");
        newegg.setBounds(575,640,120,25);
        add(newegg);
        JCheckBox fruugo = new JCheckBox("Fruugo.ca");
        fruugo.setBounds(575,690,120,25);
        add(fruugo);

        JCheckBox amazonU = new JCheckBox("Amazon.com");
        amazonU.setBounds(700,490,120,25);
        add(amazonU);
        JCheckBox ebayU = new JCheckBox("Ebay.com");
        ebayU.setBounds(700,540,120,25);
        add(ebayU);
        JCheckBox over = new JCheckBox("Overstock.com");
        over.setBounds(700,640,120,25);
        add(over);
        JCheckBox shop = new JCheckBox("Shop.com");
        shop.setBounds(700,690,120,25);
        add(shop);
        JCheckBox wish = new JCheckBox("Wish.com");
        wish.setBounds(700,790,120,25);
        add(wish);

        amazon.setSelected(true);
        BigC.setSelected(true);
        newegg.setSelected(true);
        ebay.setSelected(true);
        amazonU.setSelected(true);
        ebayU.setSelected(true);
        over.setSelected(true);
        shop.setSelected(true);
        fruugo.setSelected(true);
        wish.setSelected(true);
        JButton convert = new JButton("Convert USD to CAD");
        convert.setBounds(290, 170, 200, 25);
        convert.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){ //get from XE.com
                    //http://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=CAD
                    double rate = 1.27262;
                    double Cprice = (Double.parseDouble(fields[4].getText()))*rate;
                    Cprice =(Math.round(Cprice))-0.01; //rounds to nearest dollar, and then subtracts a cent for .99
                    fields[5].setText(Double.toString(Cprice));
                }
            });
        add(convert);
        JButton save = new JButton("Save");
        save.setBounds(575, 790, 100, 30);
        save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    checkBlanks(); //error check
                    new Standard(fields[1].getText(),fields[0].getText(),fields[2].getText(),
                        Integer.parseInt(fields[3].getText()),Double.parseDouble(fields[4].getText()),
                        Double.parseDouble(fields[5].getText()),Double.parseDouble(fields[6].getText()),
                        getEbayCat(),Double.parseDouble(fields[11].getText()),Double.parseDouble(fields[10].getText()),
                        Double.parseDouble(fields[9].getText()),Double.parseDouble(fields[8].getText()),descbox.getText(),
                        false, fields[7].getText(),getCALabels(), getNewegg(),(bigcat.getSelectedItem()).toString()
                    ,getUSLabels());
                    StandardMenu.super.dispose();
                }

                public void checkBlanks(){
                    if((fields[6].getText()).equals(null)){//if there is no cost, set to 123 to add later, and carry on with the adding
                        fields[6].setText("1.23");
                    }
                    if((fields[7].getText()).equals(null)){ //if the brand is left empty, fill it with the first word of the title
                        String title = fields[0].getText();
                        int space = title.indexOf(" ");
                        fields[7].setText(title.substring(0,space));
                    }
                }

                public String getNewegg(){
                    String html = descbox.getText();
                    Document doc = Jsoup.parse(html);
                    return doc.text();
                }

                public String getUSLabels(){
                    String labels = "";//getting labels
                    if(amazonU.isSelected()){
                        labels = labels + "Amazon.com,";
                    }
                    if(over.isSelected()){
                        labels = labels + "Overstock.com,";
                    }
                    if(ebayU.isSelected()){
                        labels = labels + "Ebay.com,";
                    }
                    if(shop.isSelected()){
                        labels = labels + "Shop.com,";
                    }
                    if(wish.isSelected()){
                        labels = labels + "Wish.com,";
                    }
                    if(labels.length()!=0){
                        labels = labels.substring(0,labels.length()-1);//taking out the final comma
                    }
                    return labels;
                }

                public String getCALabels(){
                    String labels = "";//getting labels
                    if(amazon.isSelected()){
                        labels = labels + "Amazon.ca,";
                    }
                    if(newegg.isSelected()){
                        labels = labels + "Newegg.ca,";
                    }
                    if(BigC.isSelected()){
                        labels = labels + "Bigcommerce,";
                    }
                    if(ebay.isSelected()){
                        labels = labels + "Ebay.ca,";
                    }
                    if(fruugo.isSelected()){
                        labels = labels + "Fruugo.ca,";
                    }
                    if(labels.length()!=0){
                        labels = labels.substring(0,labels.length()-1);//taking out the final comma
                    }
                    return labels;
                }

                public int getEbayCat(){
                    int cat = 11111;
                    String fullstring = (ecat.getSelectedItem()).toString();
                    int pound = fullstring.indexOf("#");
                    cat = Integer.parseInt(fullstring.substring(pound + 2));
                    return cat;
                }
            });
        add(save);

        pack();

        setSize(900, 1000);

        setLocationRelativeTo(null);

        setVisible(true);

    }
}

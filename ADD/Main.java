//worked 7 periods
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.TextField;
import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.BoxLayout;
import java.awt.Image;
import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
public class Main extends JFrame{
    static final String kounta = "kounta.csv";
    static final String CA = "CAupload.csv";
    static final String US = "USupload.csv";
    public static void main(){
        new Main();
    }

    public Main(){
        setTitle("Standard or Bundle?");
        setLayout(null);
        JButton standard = new JButton("Standard");
        standard.setBounds(0, 0, 330, 88);
        JButton bundle = new JButton("Bundle");
        bundle.setBounds(0, 88, 330, 88);
        standard.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    new StandardMenu();
                }          
            });
        JButton savekounta = new JButton("Save to Kounta");
        savekounta.setBounds(400, 40, 120, 25);
        savekounta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    Main.saveCSVkounta();
                }          
            });
        add(savekounta);
        JButton saveCA = new JButton("Save to CA");
        saveCA.setBounds(400, 70, 120, 25);
        saveCA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    Main.saveCSVCA();
                }          
            });
        add(saveCA);
        JButton saveUS = new JButton("Save to US");
        saveUS.setBounds(400, 100, 120, 25);
        saveUS.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    Main.saveCSVUS();
                }          
            });
        add(saveUS);
        JButton saveALL = new JButton("Save to All");
        saveALL.setBounds(400, 130, 120, 25);
        saveALL.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    Main.saveCSVUS();
                    Main.saveCSVCA();
                    Main.saveCSVkounta();
                }          
            });
        add(saveALL);
        bundle.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    new ParentMenu();
                }          
            });

        JButton HTML = new JButton("Online HTML Editor");
        HTML.setBounds(675, 20, 150, 25);
        HTML.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    try{
                        Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://html-online.com/editor/"});
                    }
                    catch (IOException ex) 
                    {
                        System.exit(0);
                    } 
                }          
            });
        add(HTML);

        add(standard);
        add(bundle);
        pack();
        setSize(900, 900);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    Main.super.dispose();
                }
            });
    }

    public static void saveCSVkounta(){
        try {
            // use FileWriter constructor that specifies open for appending
            CsvWriter csvOutput = new CsvWriter(new FileWriter(kounta, false), ',');
            csvOutput.write("ProductID");
            csvOutput.write("ProductNo");
            csvOutput.write("ProductName");
            csvOutput.write("Barcode");
            csvOutput.write("SellPriceExTax");
            csvOutput.write("SellTaxCode");
            csvOutput.write("CostPriceExTax");
            csvOutput.write("CostTaxCode");
            csvOutput.write("IsModifier");
            csvOutput.write("PrintDocket");
            csvOutput.write("Tags");
            csvOutput.write("CategoryNames");
            csvOutput.write("SellAccount");
            csvOutput.write("BuyAccount");
            csvOutput.write("InventoryAccount");
            csvOutput.write("ImageUrl");
            csvOutput.endRecord();
            for(Standard single: Standard.StandardList){ //for every standard product
                csvOutput.write("");
                csvOutput.write(single.SKU);
                csvOutput.write(single.SKU+" "+single.title);
                csvOutput.write(single.UPC);
                csvOutput.write(Double.toString(single.CAprice));
                csvOutput.write("H");
                csvOutput.write(Double.toString(single.cost));
                csvOutput.write("H");
                csvOutput.write("0");
                csvOutput.write("1");
                csvOutput.write("");
                csvOutput.write(single.StoreCat);
                csvOutput.write("");
                csvOutput.write("");
                csvOutput.write("");
                csvOutput.write("");
                csvOutput.endRecord();
            }
            for(Parent single: Parent.ParentList){
                for(Child orphan: single.ChildList){ //for every child in every parent
                    csvOutput.write("");
                    csvOutput.write(orphan.SKU);
                    csvOutput.write(orphan.SKU+" "+orphan.title);
                    csvOutput.write(orphan.UPC);
                    csvOutput.write(Double.toString(single.CAprice));
                    csvOutput.write("H");
                    csvOutput.write(Double.toString(single.cost));
                    csvOutput.write("H");
                    csvOutput.write("0");
                    csvOutput.write("1");
                    csvOutput.write("");
                    csvOutput.write(single.StoreCat);
                    csvOutput.write("");
                    csvOutput.write("");
                    csvOutput.write("");
                    csvOutput.write("");
                    csvOutput.endRecord();
                }
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }   
        try{
            Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://my.kounta.com/product/import"});
        }
        catch (IOException ex) 
        {
            System.exit(0);
        }
    }

    public static void saveCSVCA(){
        try {
            // use FileWriter constructor that specifies open for appending
            CsvWriter csvOutput = new CsvWriter(new FileWriter(CA, false), ',');
            csvOutput.write("Auction Title");
            csvOutput.write("Inventory Number");
            csvOutput.write("SKU Type");
            csvOutput.write("Quantity");
            csvOutput.write("Warehouse Quantity");
            csvOutput.write("Starting Bid");
            csvOutput.write("Height");
            csvOutput.write("Length");
            csvOutput.write("Width");
            csvOutput.write("Weight");
            csvOutput.write("UPC");
            csvOutput.write("EAN");
            csvOutput.write("Description");
            csvOutput.write("Blocked");
            csvOutput.write("Brand");
            csvOutput.write("Condition");
            csvOutput.write("Seller Cost");
            csvOutput.write("Buy It Now Price");
            csvOutput.write("Labels");
            csvOutput.write("ChannelAdvisor Store Price");
            csvOutput.write("Attribute18Name");
            csvOutput.write("Attribute18Value");//Payment Policy
            csvOutput.write("Attribute19Name");
            csvOutput.write("Attribute19Value");//Return Policy
            csvOutput.write("Attribute20Name");
            csvOutput.write("Attribute20Value");//calculated:Canada Post...
            csvOutput.write("Attribute21Name");
            csvOutput.write("Attribute21Value");//ebay catagory
            csvOutput.write("Attribute41Name");
            csvOutput.write("Attribute41Value");//no html desc
            csvOutput.write("Attribute60Name");
            csvOutput.write("Attribute60Value");//Storecatagoryid
            csvOutput.write("Attribute61Name");
            csvOutput.write("Attribute61Value");//1
            csvOutput.write("Attribute68Name");
            csvOutput.write("Attribute68Value");//UPDATED DESC
            csvOutput.write("Relationship Name");
            csvOutput.write("Variation Parent SKU");
            csvOutput.write("Attribute47Value");//colour
            csvOutput.write("Attribute77Value");//size
            csvOutput.endRecord();
            //end of creating headers
            for(Standard single: Standard.StandardList){
                csvOutput.write(single.title);
                csvOutput.write(single.SKU);
                csvOutput.write("Basic Item");
                csvOutput.write(Integer.toString(single.quantity));
                csvOutput.write(Integer.toString(single.quantity));
                csvOutput.write(Double.toString(single.CAprice));
                csvOutput.write(Double.toString(single.height));
                csvOutput.write(Double.toString(single.length));
                csvOutput.write(Double.toString(single.width));
                csvOutput.write(Double.toString(single.weight));
                csvOutput.write(single.UPC);
                csvOutput.write(single.EAN);
                csvOutput.write(single.desc);
                if(single.block){
                    csvOutput.write("TRUE");
                }
                else{
                    csvOutput.write("TRUE");
                }
                csvOutput.write(single.brand);
                csvOutput.write("NEW");
                csvOutput.write(Double.toString(single.cost));
                csvOutput.write(Double.toString(single.CAprice));
                csvOutput.write(single.labels);
                csvOutput.write(Double.toString(single.CAprice));
                csvOutput.write("EBAY_BUSPOLICY_PAYMENT");
                csvOutput.write("Payment Policy");
                csvOutput.write("EBAY_BUSPOLICY_RETURNS");
                csvOutput.write("Return Policy");
                csvOutput.write("EBAY_BUSPOLICY_SHIPPING");
                csvOutput.write("Calculated:Canada Post Ex(Free),Canada Post X#103");
                csvOutput.write("EBAY_CATEGORY_ID");
                csvOutput.write(Integer.toString(single.EbayCat));
                csvOutput.write("NewEgg NO HTML Desc");
                csvOutput.write(single.newegg);
                csvOutput.write("StoreCategoryID");
                csvOutput.write(single.StoreCat);
                csvOutput.write("StoreCategoryID2");
                csvOutput.write("1");
                csvOutput.write("Updated Description");
                csvOutput.write(single.desc);

                csvOutput.endRecord();
            }
            for(Parent single: Parent.ParentList){
                csvOutput.write(single.title);
                csvOutput.write(single.SKU);
                csvOutput.write("PARENT");
                csvOutput.write("");//quantities
                csvOutput.write("");//quantities
                csvOutput.write(Double.toString(single.CAprice));
                csvOutput.write(Double.toString(single.height));
                csvOutput.write(Double.toString(single.length));
                csvOutput.write(Double.toString(single.width));
                csvOutput.write(Double.toString(single.weight));
                csvOutput.write("");//UPC
                csvOutput.write("");//EAN
                csvOutput.write(single.desc);
                if(single.block){
                    csvOutput.write("TRUE");
                }
                else{
                    csvOutput.write("FALSE");
                }
                csvOutput.write(single.brand);
                csvOutput.write("NEW");
                csvOutput.write(Double.toString(single.cost));
                csvOutput.write(Double.toString(single.CAprice));
                csvOutput.write(single.labels);
                csvOutput.write(Double.toString(single.CAprice));
                csvOutput.write("EBAY_BUSPOLICY_PAYMENT");
                csvOutput.write("Payment Policy");
                csvOutput.write("EBAY_BUSPOLICY_RETURNS");
                csvOutput.write("Return Policy");
                csvOutput.write("EBAY_BUSPOLICY_SHIPPING");
                csvOutput.write("Calculated:Canada Post Ex(Free),Canada Post X#103");
                csvOutput.write("EBAY_CATEGORY_ID");
                csvOutput.write(Integer.toString(single.EbayCat));
                csvOutput.write("NewEgg NO HTML Desc");
                csvOutput.write(single.newegg);
                csvOutput.write("StoreCategoryID");
                csvOutput.write(single.StoreCat);
                csvOutput.write("StoreCategoryID2");
                csvOutput.write("1");
                csvOutput.write("Updated Description");
                csvOutput.write(single.desc);
                csvOutput.write(single.var);//Relationship Name
                csvOutput.write("Parent"); 
                csvOutput.endRecord();
                for(Child kid : single.ChildList){
                    csvOutput.write(kid.title);
                    csvOutput.write(kid.SKU);
                    csvOutput.write("Child");
                    csvOutput.write(Integer.toString(kid.Quantity));//quantities
                    csvOutput.write(Integer.toString(kid.Quantity));//quantities
                    csvOutput.write(Double.toString(single.CAprice));
                    csvOutput.write(Double.toString(single.height));
                    csvOutput.write(Double.toString(single.length));
                    csvOutput.write(Double.toString(single.width));
                    csvOutput.write(Double.toString(single.weight));
                    csvOutput.write(kid.UPC);//UPC
                    csvOutput.write(kid.EAN);//EAN
                    csvOutput.write(single.desc);
                    csvOutput.write("False");
                    csvOutput.write(single.brand);
                    csvOutput.write("NEW");
                    csvOutput.write(Double.toString(single.cost));
                    csvOutput.write(Double.toString(single.CAprice));
                    csvOutput.write(single.labels);
                    csvOutput.write(Double.toString(single.CAprice));
                    csvOutput.write("EBAY_BUSPOLICY_PAYMENT");
                    csvOutput.write("Payment Policy");
                    csvOutput.write("EBAY_BUSPOLICY_RETURNS");
                    csvOutput.write("Return Policy");
                    csvOutput.write("EBAY_BUSPOLICY_SHIPPING");
                    csvOutput.write("Calculated:Canada Post Ex(Free),Canada Post X#103");
                    csvOutput.write("EBAY_CATEGORY_ID");
                    csvOutput.write(Integer.toString(single.EbayCat));
                    csvOutput.write("NewEgg NO HTML Desc");
                    csvOutput.write(single.newegg);
                    csvOutput.write("StoreCategoryID");
                    csvOutput.write(single.StoreCat);
                    csvOutput.write("StoreCategoryID2");
                    csvOutput.write("1");
                    csvOutput.write("Updated Description");
                    csvOutput.write(single.desc);
                    csvOutput.write(single.var);//Relationship Name
                    csvOutput.write(single.SKU);//parent sku
                    csvOutput.write(kid.colour);//kid.colour
                    csvOutput.write(kid.Size);
                    csvOutput.endRecord();
                }
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        try{
            Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://complete.channeladvisor.com/Inventory/ImportItems_Upload.aspx?apid=12023216"});
        }
        catch (IOException ex) 
        {
            System.exit(0);
        }
    }

    public static void saveCSVUS(){
        try {
            // use FileWriter constructor that specifies open for appending
            CsvWriter csvOutput = new CsvWriter(new FileWriter(US, false), ',');
            csvOutput.write("Auction Title");
            csvOutput.write("Inventory Number");
            csvOutput.write("SKU Type");
            csvOutput.write("Quantity");
            csvOutput.write("Warehouse Quantity");
            csvOutput.write("Starting Bid");
            csvOutput.write("Height");
            csvOutput.write("Length");
            csvOutput.write("Width");
            csvOutput.write("Weight");
            csvOutput.write("UPC");
            csvOutput.write("EAN");
            csvOutput.write("Description");
            csvOutput.write("Blocked");
            csvOutput.write("Brand");
            csvOutput.write("Condition");
            csvOutput.write("Buy It Now Price");
            csvOutput.write("Labels");
            csvOutput.write("ChannelAdvisor Store Price");
            csvOutput.write("Attribute17Name");
            csvOutput.write("Attribute17Value");//Payment Policy
            csvOutput.write("Attribute18Name");
            csvOutput.write("Attribute18Value");//Return Policy
            csvOutput.write("Attribute19Name");
            csvOutput.write("Attribute19Value");//calculated:Canada Post...
            csvOutput.write("Attribute20Name");
            csvOutput.write("Attribute20Value");//ebay catagory
            csvOutput.write("Attribute45Name");
            csvOutput.write("Attribute45Value");//Storecatagoryid
            csvOutput.write("Attribute46Name");
            csvOutput.write("Attribute46Value");//1
            csvOutput.write("Attribute50Name");
            csvOutput.write("Attribute50Value");//UPDATED DESC
            csvOutput.write("Relationship Name");
            csvOutput.write("Variation Parent SKU");
            csvOutput.write("Attribute10Value");//colour
            csvOutput.write("Attribute41Value");//size
            csvOutput.endRecord();
            //end of creating headers
            for(Standard single: Standard.StandardList){
                csvOutput.write(single.title);
                csvOutput.write(single.SKU);
                csvOutput.write("Basic Item");
                csvOutput.write(Integer.toString(single.quantity));
                csvOutput.write(Integer.toString(single.quantity));
                csvOutput.write(Double.toString(single.USprice));
                csvOutput.write(Double.toString(single.height));
                csvOutput.write(Double.toString(single.length));
                csvOutput.write(Double.toString(single.width));
                csvOutput.write(Double.toString(single.weight));
                csvOutput.write(single.UPC);
                csvOutput.write(single.EAN);
                csvOutput.write(single.desc);
                if(single.block){
                    csvOutput.write("TRUE");
                }
                else{
                    csvOutput.write("FALSE");
                }
                csvOutput.write(single.brand);
                csvOutput.write("NEW");
                csvOutput.write(Double.toString(single.USprice));
                csvOutput.write(single.USlabels);
                csvOutput.write(Double.toString(single.USprice));
                csvOutput.write("EBAY_BUSPOLICY_PAYMENT");
                csvOutput.write("Payment Policy");
                csvOutput.write("EBAY_BUSPOLICY_RETURNS");
                csvOutput.write("Return Policy");
                csvOutput.write("EBAY_BUSPOLICY_SHIPPING");
                if(single.weight < 2.2){
                    csvOutput.write("Light packet Under $100 and under 1KG");
                }
                else{
                    csvOutput.write("Free Expeditied Over $100 over 2kg");
                }
                csvOutput.write("EBAY_CATEGORY_ID");
                csvOutput.write(Integer.toString(single.EbayCat));
                csvOutput.write("StoreCategoryID");
                csvOutput.write(single.StoreCat);
                csvOutput.write("StoreCategoryID2");
                csvOutput.write("1");
                csvOutput.write("Updated Description");
                csvOutput.write(single.desc);

                csvOutput.endRecord();
            }
            for(Parent single: Parent.ParentList){
                csvOutput.write(single.title);
                csvOutput.write(single.SKU);
                csvOutput.write("PARENT");
                csvOutput.write("");
                csvOutput.write("");
                csvOutput.write(Double.toString(single.USprice));
                csvOutput.write(Double.toString(single.height));
                csvOutput.write(Double.toString(single.length));
                csvOutput.write(Double.toString(single.width));
                csvOutput.write(Double.toString(single.weight));
                csvOutput.write(single.UPC);
                csvOutput.write(single.EAN);
                csvOutput.write(single.desc);
                if(single.block){
                    csvOutput.write("TRUE");
                }
                else{
                    csvOutput.write("False");
                }
                csvOutput.write(single.brand);
                csvOutput.write("");
                csvOutput.write(Double.toString(single.USprice));
                csvOutput.write(single.USLabels);
                csvOutput.write(Double.toString(single.USprice));
                csvOutput.write("EBAY_BUSPOLICY_PAYMENT");
                csvOutput.write("Payment Policy");
                csvOutput.write("EBAY_BUSPOLICY_RETURNS");
                csvOutput.write("Return Policy");
                csvOutput.write("EBAY_BUSPOLICY_SHIPPING");
                if(single.weight < 2.2){
                    csvOutput.write("Light packet Under $100 and under 1KG");
                }
                else{
                    csvOutput.write("Free Expeditied Over $100 over 2kg");
                }
                csvOutput.write("EBAY_CATEGORY_ID");
                csvOutput.write(Integer.toString(single.EbayCat));
                csvOutput.write("StoreCategoryID");
                csvOutput.write(single.StoreCat);
                csvOutput.write("StoreCategoryID2");
                csvOutput.write("1");
                csvOutput.write("Updated Description");
                csvOutput.write(single.desc);
                if((single.var).equals("Colour")){//translation from colour to color
                    csvOutput.write("Color"); 
                }
                else if((single.var).equals("Colour_Size")){
                    csvOutput.write("Color_Size");
                }
                else{
                    csvOutput.write(single.var);//Relationship Name
                }
                csvOutput.write("Parent");  
                csvOutput.endRecord();
                for(Child kid : single.ChildList){
                    csvOutput.write(kid.title);
                    csvOutput.write(kid.SKU);
                    csvOutput.write("Child");
                    csvOutput.write(Integer.toString(kid.Quantity));//quantities
                    csvOutput.write(Integer.toString(kid.Quantity));//quantities
                    csvOutput.write(Double.toString(single.USprice));
                    csvOutput.write(Double.toString(single.height));
                    csvOutput.write(Double.toString(single.length));
                    csvOutput.write(Double.toString(single.width));
                    csvOutput.write(Double.toString(single.weight));
                    csvOutput.write(kid.UPC);//UPC
                    csvOutput.write(kid.EAN);//EAN
                    csvOutput.write(single.desc);
                    csvOutput.write("False");//blocked
                    csvOutput.write(single.brand);
                    csvOutput.write("NEW");
                    csvOutput.write(Double.toString(single.USprice));
                    csvOutput.write(single.USLabels);
                    csvOutput.write(Double.toString(single.USprice));
                    csvOutput.write("EBAY_BUSPOLICY_PAYMENT");
                    csvOutput.write("Payment Policy");
                    csvOutput.write("EBAY_BUSPOLICY_RETURNS");
                    csvOutput.write("Return Policy");
                    csvOutput.write("EBAY_BUSPOLICY_SHIPPING");
                    if(single.weight < 2.2){
                        csvOutput.write("Light packet Under $100 and under 1KG");
                    }
                    else{
                        csvOutput.write("Free Expeditied Over $100 over 2kg");
                    }
                    csvOutput.write("EBAY_CATEGORY_ID");
                    csvOutput.write(Integer.toString(single.EbayCat));
                    csvOutput.write("StoreCategoryID");
                    csvOutput.write(single.StoreCat);
                    csvOutput.write("StoreCategoryID2");
                    csvOutput.write("1");
                    csvOutput.write("Updated Description");
                    csvOutput.write(single.desc);
                    if((single.var).equals("Colour")){//translation from colour to color
                        csvOutput.write("Color"); 
                    }
                    else if((single.var).equals("Colour_Size")){
                        csvOutput.write("Color_Size");
                    }
                    else{
                        csvOutput.write(single.var);//Relationship Name
                    }
                    csvOutput.write(single.SKU);
                    csvOutput.write(kid.colour);//kid.colour
                    csvOutput.write(kid.Size);
                    csvOutput.endRecord();
                }
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        try{
            Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://complete.channeladvisor.com/Inventory/ImportItems_Upload.aspx?apid=12023160"});
        }
        catch (IOException ex) 
        {
            System.exit(0);
        }
    }
}
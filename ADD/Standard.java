import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.awt.event.*;

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
public class Standard{
    public static List<Standard> StandardList = new ArrayList<Standard>();
    public int quantity, EbayCat;
    public double weight, height, width, length, USprice, CAprice, cost;
    public String desc, SKU, title, UPC, brand, labels, newegg, StoreCat, EAN, USlabels;
    public boolean block;
    public Standard(String nSKU, String nAuctionTitle, String nUPCEAN, int nQuantity, double nUSprice, double nCAprice,
    double ncost, int nEbayCat, double nweight, double nheight, double nwidth, double nlength, String ndesc,
    boolean nblock, String nbrand, String nlabels, String nnewegg, String nStoreCat, String nUSlabels){
        SKU = nSKU;
        title = nAuctionTitle;
        if(nUPCEAN.length() == 12){
            UPC = nUPCEAN;
        }
        else{
            EAN = nUPCEAN;
        }
        quantity = nQuantity;
        CAprice = nCAprice;
        USprice = nUSprice;
        cost = ncost;
        EbayCat = nEbayCat;
        weight = nweight;
        height = nheight;
        width = nwidth;
        length = nlength;
        desc = ndesc;
        block = nblock;
        brand = nbrand;
        labels = nlabels;
        newegg = nnewegg;
        StoreCat = nStoreCat;
        USlabels = nUSlabels;
        StandardList.add(this);
    }

    public static void initfile(){
    }
    public Standard(){}
}

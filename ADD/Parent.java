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
public class Parent extends JFrame{
    public static List<Parent> ParentList = new ArrayList<Parent>();
    public List<Child> ChildList = new ArrayList<Child>();
    public int quantity, EbayCat;
    static final String vend = "vend.csv";
    static final String CA = "CAupload.csv";
    public double weight, height, width, length, USprice, CAprice, cost;
    public String desc, SKU, title, UPC, brand, labels, newegg, StoreCat, EAN, var, USLabels;
    public boolean block;
    public Parent(String nSKU, String nAuctionTitle, double nUSprice, double nCAprice,
    double ncost, int nEbayCat, double nweight, double nheight, double nwidth, double nlength, String ndesc,
    boolean nblock, String nbrand, String nlabels, String nnewegg, String nStoreCat, String nVariation,
    String nUSLabels){
        SKU = nSKU;
        title = nAuctionTitle;
        USprice = nUSprice;
        CAprice = nCAprice;
        cost = ncost;
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
        var = nVariation;
        EbayCat = nEbayCat;
        USLabels = nUSLabels;
        ParentList.add(this);
    }

    public Parent(){
    }
    public static void initfile(){
    }

}

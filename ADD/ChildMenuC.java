import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class ChildMenuC extends JFrame implements ActionListener {
    public int tick = 1;
    public ArrayList<JTextField> UPCList = new ArrayList<JTextField>();
    public ArrayList<JTextField> colList = new ArrayList<JTextField>();
    public ArrayList<JTextField> SKUList = new ArrayList<JTextField>();
    public ArrayList<JTextField> QList = new ArrayList<JTextField>();
    JPanel panel;
    Parent theparent;
    public ChildMenuC(Parent parent) {
        super("Add children for "+parent.title);
        theparent = parent;
        setLayout(null);

        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane pane = new JScrollPane(panel);
        pane.setBounds(20, 100, 900, 450);
        add(pane);

        JButton plus = new JButton("+");
        plus.setBounds(200,20,50,50);
        add(plus);

        JButton save = new JButton("Save");
        save.setBounds(800,20,80,40);
        add(save);
        save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {// if the button is clicked
                    for(int i = 0; i<UPCList.size(); i++){
                        Child birth = new Child(SKUList.get(i).getText(),ParentMenu.fields[0].getText()+", "+colList.get(i).getText(),
                            UPCList.get(i).getText(), "",
                            colList.get(i).getText(),Integer.parseInt(QList.get(i).getText()), parent);
                    }
                    ChildMenuC.super.dispose();
                }          
            });

        plus.addActionListener(this);

        setSize(1000, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        tick*=-1;
        JPanel row = new JPanel();
        if(tick == 1){
            row.setBackground(Color.lightGray);
        }
        else{
            row.setBackground(Color.white);
        }
        row.setLayout(null);
        row.setPreferredSize(new Dimension(850, 60));
        //row.setSize(new Dimension(300,600));

        JLabel UPCl = new JLabel("UPC/EAN");
        UPCl.setBounds(7,10,100,25);
        row.add(UPCl);

        JTextField UPC = new JTextField();
        UPC.setBounds(70,10,100,25);
        row.add(UPC);
        UPCList.add(UPC);

        JLabel coll = new JLabel("Colour");
        coll.setBounds(410,10,100,25);
        row.add(coll);

        JTextField col = new JTextField();
        col.setBounds(460,10,100,25);
        row.add(col);
        colList.add(col);

        JLabel quantl = new JLabel("Quantity");
        quantl.setBounds(580,10,50,25);
        row.add(quantl);

        JTextField quant = new JTextField("1");
        quant.setBounds(630,10,50,25);
        row.add(quant);
        QList.add(quant);

        JLabel SKUl = new JLabel("SKU");
        SKUl.setBounds(700,10,100,25);
        row.add(SKUl);

        JTextField SKU = new JTextField(ParentMenu.fields[1].getText()+"-");
        SKU.setBounds(740,10,100,25);
        row.add(SKU);
        SKUList.add(SKU);

        this.panel.add(row);
        this.panel.revalidate();
        validate();

    }
}
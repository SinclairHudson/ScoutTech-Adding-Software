
public class Child{
    String SKU, EAN, UPC, Size, title, colour;
    int Quantity;
    public Child(String nSKU, String nTitle, String nUPCEAN, String nSize, String ncolour, int nQuantity, Parent nParent){
        SKU = nSKU;
        if(nUPCEAN.length() == 12){
            UPC = nUPCEAN;
        }
        else{
            EAN = nUPCEAN;
        }
        Size = nSize;
        title = nTitle;
        colour = ncolour;
        Quantity = nQuantity;        
        nParent.ChildList.add(this);        
    }

    public boolean ErrorCheck(String nUPC, String nEAN, String Size, int Quantity, Parent nParent){
        if(nEAN.length()!= 13){
            System.out.println("Error, EAN is not 13 characters");
            return false;
        }
        else if(nUPC.length()!= 12){
            System.out.println("Error, UPC is not 12 characters");
            return false;
        }
        else{return true;}
    }
}

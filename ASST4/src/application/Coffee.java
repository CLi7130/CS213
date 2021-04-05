package application;

import java.text.DecimalFormat;

enum COFFEE_ADD_INS {
    CREAM, SYRUP, MILK, CARAMEL, WHIPPED_CREAM;

}

public class Coffee extends MenuItem implements Customizable{
    final static private double SHORT = 1.99;
    final static private double TALL = 2.49;
    final static private double GRANDE = 2.99;
    final static private double VENTI = 3.49;
    final static private double ADD_INS = 0.20;
    
    final static private String SHORT_STRING = "SHORT";
    final static private String TALL_STRING = "TALL";
    final static private String GRANDE_STRING = "GRANDE";
    final static private String VENTI_STRING = "VENTI";
 
    
    public int numAddIns = 0;
    public COFFEE_ADD_INS[] addins = new COFFEE_ADD_INS[5];
    
    public boolean addAddins(COFFEE_ADD_INS coffeeAddins) {
        numAddIns++;
        addins[numAddIns - 1] = coffeeAddins;
        return true;
    }
    
    public boolean removeAddins(COFFEE_ADD_INS coffeeAddins) {
        return false;
    }
    
    public int findAddins(COFFEE_ADD_INS coffeeAddins) {
        return 0;
    }
    
    public String printAddins() {
        String coffeeAddins = "";
        for(int i = 0; i < numAddIns; i++) {
            coffeeAddins += addins[i].name() + " ";
        }
        return coffeeAddins;
    }
    
    private String size;
    Coffee coffee;
    

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Coffee) {
            coffee = (Coffee) obj;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Coffee) {
            coffee = (Coffee) obj;
            return true;
        }
        return false;
    }
    
    public double itemPrice(){
        String coffeeSize = coffee.size;
        double total = 0;
        if(coffeeSize == SHORT_STRING) {
            total += SHORT;
        }
        else if(coffeeSize == TALL_STRING) {
            total += TALL;
        }
        else if(coffeeSize == GRANDE_STRING) {
            total += GRANDE;
        }
        else if(coffeeSize == VENTI_STRING) {
            total += VENTI;
        }
        
        total += ADD_INS * numAddIns;
        return total;
    }
    
    public String print() {
        DecimalFormat money = new DecimalFormat("#,###.##");
        String qualities;
        qualities = coffee.printAddins() + money.format(super.itemPrice());
        return qualities;
    }
}

package application;

import java.text.DecimalFormat;

import ruCafe.Enums.COFFEE_ADD_INS;
import ruCafe.Enums.COFFEE_SIZE;


public class Coffee extends MenuItem implements Customizable{
    final static private double SHORT = 1.99;
    final static private double TALL = 2.49;
    final static private double GRANDE = 2.99;
    final static private double VENTI = 3.49;
    final static private double [] sizeCost = {SHORT, TALL, GRANDE, VENTI};
    final static private double ADD_INS = 0.20;
    private static final int FAIL_CONDITION = -1;
    private static final int TOTAL_ADD_INS_POSSIBLE = 5;

    private COFFEE_SIZE size;
    private Coffee coffee;
    
    public int numAddIns = 0;
    public COFFEE_ADD_INS[] addins = new COFFEE_ADD_INS[TOTAL_ADD_INS_POSSIBLE];
    
    
    public boolean addAddins(COFFEE_ADD_INS coffeeAddins) {
        numAddIns++;
        addins[numAddIns - 1] = coffeeAddins;
        return true;
    }
    
    public boolean removeAddins(COFFEE_ADD_INS coffeeAddins) {
        final int index = findAddins(coffeeAddins);
        if(index >= 0) {
            addins[index] = null;
            numAddIns--;
            return true;
        }
        return false;
    }
    
    public int findAddins(COFFEE_ADD_INS coffeeAddins) {
        int index = 0;
        shiftArray();
        for(int i = 0; i < addins.length; i++) {
            if(addins[i] == null){
                continue;
            }
            if(addins[i].equals(coffeeAddins)) {
                index = i;
                return index;
            }
        }
        index = FAIL_CONDITION;
        return index;
    }
    
    public String printAddins() {
        String coffeeAddins = "";
        shiftArray();
        for(int i = 0; i < numAddIns; i++) {
            if(addins[i] == null){
                continue;
            }
            coffeeAddins += addins[i].name() + " ";
        }
        return coffeeAddins;
    }
    
    /**
    Shifts an array so that there are no null spaces/gaps.
    */
    private void shiftArray() {
        COFFEE_ADD_INS [] shiftedArray = new COFFEE_ADD_INS[addins.length];
        int count = 0;
        for(int i = 0; i < addins.length; i++) {
            if(addins[i] != null) {
                shiftedArray[count] = addins[i];
            }
            else{
                count--;
            }
            count++;
        }
        addins = shiftedArray;
    }

    /**
     * @param size the size to set
     */
    public void setSize(COFFEE_SIZE size) {
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
            coffee = null;
            return true;
        }
        return false;
    }
    
    public double itemPrice(){
        COFFEE_SIZE coffeeSize = coffee.size;
        double total = 0;
        total += sizeCost[coffeeSize.ordinal()];
        total += ADD_INS * numAddIns;
        return total;
    }
    
    public String print() {
        DecimalFormat money = new DecimalFormat("#,##0.00");
        String qualities;
        if(coffee == null) {
            return "";
        }
        qualities = "Coffee: " + coffee.printAddins() + " Quantity: " + super.getQuantity() + " Price: " + money.format(super.itemPrice());
        return qualities;
    }
}

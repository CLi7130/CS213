package ruCafe;

import java.text.DecimalFormat;

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

    /**
     * @return the cREAM
     */
    public boolean isCREAM() {
        return CREAM;
    }

    /**
     * @param cREAM the cREAM to set
     */
    public void setCREAM(boolean cREAM) {
        CREAM = cREAM;
    }

    /**
     * @return the sYRUP
     */
    public boolean isSYRUP() {
        return SYRUP;
    }

    /**
     * @param sYRUP the sYRUP to set
     */
    public void setSYRUP(boolean sYRUP) {
        SYRUP = sYRUP;
    }

    /**
     * @return the mILK
     */
    public boolean isMILK() {
        return MILK;
    }

    /**
     * @param mILK the mILK to set
     */
    public void setMILK(boolean mILK) {
        MILK = mILK;
    }

    /**
     * @return the cARAMEL
     */
    public boolean isCARAMEL() {
        return CARAMEL;
    }

    /**
     * @param cARAMEL the cARAMEL to set
     */
    public void setCARAMEL(boolean cARAMEL) {
        CARAMEL = cARAMEL;
    }

    /**
     * @return the wHIPPED_CREAM
     */
    public boolean isWHIPPED_CREAM() {
        return WHIPPED_CREAM;
    }

    /**
     * @param wHIPPED_CREAM the wHIPPED_CREAM to set
     */
    public void setWHIPPED_CREAM(boolean wHIPPED_CREAM) {
        WHIPPED_CREAM = wHIPPED_CREAM;
    }

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

    private boolean CREAM = false;
    private boolean SYRUP = false;
    private boolean MILK = false;
    private boolean CARAMEL = false;
    private boolean WHIPPED_CREAM = false;
    
    private String size;
    Coffee coffee;

    
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
        
        if(coffee.CARAMEL) {
            total += ADD_INS;
        }
        if(coffee.CREAM) {
            total += ADD_INS;
        }
        if(coffee.MILK) {
            total += ADD_INS;
        }
        if(coffee.SYRUP) {
            total += ADD_INS;
        }
        if(coffee.WHIPPED_CREAM) {
            total += ADD_INS;
        }
        return total;
    }
    
    public String print() {
        DecimalFormat money = new DecimalFormat("#,###.##");
        return money.format(itemPrice());
    }
}

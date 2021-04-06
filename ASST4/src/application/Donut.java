package application;

import application.Enums.DONUT_TYPES;

import java.text.DecimalFormat;

import application.Enums.CAKE_DONUTS;

/**
 * Class containing all information and methods necessary for a 
 * Donut object.
 * @author Craig Li, Prerak Patel
 */

public class Donut extends MenuItem implements Customizable {
    private DONUT_TYPES type;

    final static private double CAKE = 1.59;
    final static private double HOLES = 0.33;
    final static private double YEAST = 1.39;
    final static private double [] typeCost = {CAKE, HOLES, YEAST};
    private String flavor;
    
    /**
     * Constructor for a new donut.
     */
    public Donut() {
    	this.setType(DONUT_TYPES.CAKE_DONUTS);
    	this.setFlavor(CAKE_DONUTS.APPLE_CRUMB.toString());
    }
    /**
     * Gets the type of a donut
     * @return the type
     */
    public DONUT_TYPES getType() {
        return type;
    }

    /**
     * Sets the type of a donut.
     * @param type the type to set
     */
    public void setType(DONUT_TYPES type) {
        this.type = type;
    }
    /**
     * Checks to see if two donut objects are equal.
     * @param obj	the object to compare.
     * @return true if both objects are the same donut, false if not.
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Donut) {
    		Donut donut = (Donut) obj;
    			
    		if(this.flavor.equals(donut.getFlavor())
    			&& (this.type.toString().equals(donut.getType().toString()))
    			&& (this.getQuantity() == donut.getQuantity()))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * Adds a donut object to the current Order
     * @param obj	object to be added.
     * @return true if successfully added, false if not.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Donut) {
            Donut donut = (Donut) obj;
            this.itemPrice();
            return true;
        }
        return false;
    }

    /**
     * Gets the flavor of the donut.
     * @return the flavor
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Sets the flavor of a donut.
     * @param flavor the flavor to set
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    /**
     * Removes a donut object
     * @param obj	The object to be removed.
     * @return true if successfully removed, false if not.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Donut) {
            obj = null;
            this.itemPrice();
            return true;
        }
        return false;
    }
    /**
     * Calculates and sets the price of a donut.
     */
    public void itemPrice(){
    	
        DONUT_TYPES donutType = this.type;
        double total = 0;
        total += typeCost[donutType.ordinal()];
        total = total * (double) this.getQuantity();
        
        this.setPrice(total);
        
    }
    /**
     * Creates a string containing the donut's information.
     * @return qualities	The information about the donut.
     */
    public String print() {

        DecimalFormat money = new DecimalFormat("$#,##0.00");
        String qualities;
        this.itemPrice();
        qualities = super.print() + this.getFlavor().toString() 
        			+ " [ " + money.format(this.getPrice()) + " ]"; 	

        return qualities;
    }
}

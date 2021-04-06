package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Order class containing methods and information about a current order.
 * @author Craig Li, Prerak Patel
 *
 */
public class Order implements Customizable {

    private int numOrder;
    private ArrayList<MenuItem> orderList;
    private static final int FAIL_CONDITION = -1;
    final static double TAX = 1.06625;
    private double orderTotalCost = 0;
    DecimalFormat money = new DecimalFormat("$#,##0.00");


    /**
     * Gets the order number
     * @return the orderNum
     */
    public int getnumOrder() {
        return numOrder;
    }

    /**
     * Getter for an order
     * @return the orderList
     */
    public ArrayList<MenuItem> getOrderList() {
        return orderList;
    }
    /**
     * Constructor for a new order
     */
    public Order() {
        numOrder = 0;
        orderList = new ArrayList<MenuItem>();
    }
    /**
     * checks to see if an order is the correct order
     * @return true if the two orders are the same, false if not.
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Order) {
    		Order order = (Order) obj;
    		if(this.orderList.equals(order.orderList)) {
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * Adds an object to an order
     * @param obj	the object to be added to an order
     * @return 	true if object successfully added, false if not.
     */
    @Override
    public boolean add(Object obj) {
        removeNulls();
        if(obj instanceof MenuItem) {
            numOrder++;
            MenuItem tempObj = (MenuItem) obj;
            orderList.add(tempObj);
            this.orderTotalCost = this.orderTotalCost + tempObj.getPrice();
            return true;
        }
        return false;
    }
    /**
     * Removes a MenuItem from an order
     * @param obj	the object to be added to an order
     * @return	true if successful, false if not.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem) {
        	MenuItem deleteThisItem = (MenuItem) obj;
            final int index = find(deleteThisItem);
            if(index >= 0) {
            	
            	orderList.remove(index);
            	this.orderTotalCost = this.orderTotalCost - deleteThisItem.getPrice();
                numOrder--;
                return true;
            }
            return false;
        }
        return false;
    }
    /**
     * Finds the index of a menuItem in the Orders arraylist.
     * @param menuItem	the item to search for
     * @return index	the index of the item that was sought
     */
    private int find(MenuItem menuItem) {
        int index = 0;
        removeNulls();
        for(int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i) == null) {
                continue;
            }
            if(orderList.get(i).equals(menuItem)) {
                index = i;
                return index;
            }
        }
        index = FAIL_CONDITION;
        return index;
    }
    
    /**
    Removes all null values from an arraylist.
    */
    private void removeNulls() {
    	while(orderList.remove(null)){
    		//removes all null entries from arraylist
    	}
    }
    /*
     * Gets the current Order total.
     * @return	this.orderTotalCost	total cost of the order
     */
    public double getOrderTotal() {
    	double total = 0;
        for(MenuItem menuItems : orderList) {
            if(menuItems == null) {
                continue;
            }
            total += menuItems.getPrice();
            //totalTax += menuItems.itemPrice();
        }
        return total;
    }
    /**
     * Gets the amount that is paid towards tax on a certain order.
     * @return taxOnOrder	the amount of tax on an order
     */
    public double getOrderTax() {
    	double taxOnOrder = (this.orderTotalCost * TAX) - this.orderTotalCost;
    	
    	return taxOnOrder;
    }
    /**
     * Sets the current Order Total/Cost
     * @params amount	double value to set order total to
     */
    public void setOrderTotal(double amount) {
    	this.orderTotalCost = amount;
    }
    
    /**
     * Prints an order based on the given menu items.
     * @return orders	String containing a representation of all order items.
     */
    public String print() {
        String orders = "";       
        removeNulls();
        for(MenuItem menuItems : orderList) {
            if(menuItems == null) {
                continue;
            }
            orders += menuItems.print() + '\n';
            //totalTax += menuItems.itemPrice();
        }
        orders += ("Total: " + money.format((this.getOrderTotal() * TAX)));
        return orders;
    }

}

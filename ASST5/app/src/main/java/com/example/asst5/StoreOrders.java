package com.example.asst5;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * StoreOrder class that contains all information and methods to track store orders.
 * @author Craig Li, Prerak Patel
 *
 */
public class StoreOrders implements Customizable, Serializable {
	
	private static ArrayList<Order> storeOrders;
    private int numStoreOrder;
    private int orderNumberOffset;
    private static final int FAIL_CONDITION = -1;
    private static final DecimalFormat money = new DecimalFormat("$#,##0.00");

    /**
     * Gets the storeOrders object.
     * @return the storeOrders
     */
    public ArrayList<Order> getStoreOrders() {
        return storeOrders;
    }
    
    /**
     * Gets the Number of orders in the storeOrders object.
     * @return the numStoreOrder
     */
    public int getNumStoreOrder() {
        return this.numStoreOrder;
    }

    /**
     * Gets a specific order from a store orders arraylist based on order
     * number.
     * @param orderNumber	the orderNumber to find
     * @return	i	The index of the order number in the storeOrders arraylist.
     */
    public int findOrder(int orderNumber) {
    	
    	for(int i = 0; i < this.getStoreOrders().size(); i++) {
    		if(this.getStoreOrders().get(i).getOrderNumber()
    				== orderNumber) {
    			return i;
    		}
    	}
    
    	return FAIL_CONDITION;
    }

    /**
     * Constructor for a new StoreOrders object.
     */
    public StoreOrders() {
        this.numStoreOrder = 0;
        this.orderNumberOffset = 0;
        storeOrders = new ArrayList<Order>();
    }

    /**
     * Adds an object to the store Orders
     * @param obj	object to be added
     * @return true if successfully added, false if not.
     */
    @Override
    public boolean add(Object obj) {
    	removeNulls();
        if(obj instanceof Order) {
            numStoreOrder++;
            Order addedOrder = (Order) obj;
            addedOrder.setOrderNumber(numStoreOrder + orderNumberOffset);
            storeOrders.add(addedOrder);
            return true;
        }
        return false;
    }

    /**
     * Removes an order from the Store Orders
     * @param obj	object to be removed
     * @return true if successfully removed, false if not.
     */
    @Override
    public boolean remove(Object obj) {
    	this.removeNulls();
        if(obj instanceof Order) {
        	Order tempOrder = (Order) obj;
            final int index = findOrder(tempOrder.getOrderNumber());
            if(index >= 0) {
                storeOrders.remove(index);
                numStoreOrder--;
                orderNumberOffset++;
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Removes all null values from the storeOrders list.
     */
    private void removeNulls() {
    	while(storeOrders.remove(null)){
    		//removes all null entries from arraylist
    	}
    }
}

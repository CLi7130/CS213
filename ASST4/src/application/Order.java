package application;

import java.text.DecimalFormat;


public class Order implements Customizable {

    private int numOrder;
    private MenuItem[] orderList;
    private static final int GROW_AMOUNT = 4;
    private static final int FAIL_CONDITION = -1;
    final static double TAX = 1.06625;


    /**
     * @return the orderNum
     */
    public int getnumOrder() {
        return numOrder;
    }

    /**
     * @return the orderList
     */
    public MenuItem[] getOrderList() {
        return orderList;
    }

    public Order() {
        numOrder = 0;
        orderList = new MenuItem[GROW_AMOUNT];
    }
    
    
    @Override
    public boolean add(Object obj) {
        shiftArray();
        if(obj instanceof MenuItem) {
            numOrder++;
            if(numOrder > orderList.length) {
                grow();
            }
            orderList[numOrder -1] = (MenuItem) obj;
            return true;
        }
        return false;
    }

    private void grow() {
        MenuItem[] grow = new MenuItem[orderList.length + GROW_AMOUNT];
        for(int i = 0; i < orderList.length; i++) {
            grow[i] = orderList[i];
        }
        orderList = grow;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem) {
            final int index = find((MenuItem) obj);
            if(index >= 0) {
                orderList[index] = null;
                numOrder--;
                return true;
            }
            return false;
        }
        return false;
    }
    
    private int find(MenuItem menuItem) {
        int index = 0;
        shiftArray();
        for(int i = 0; i < orderList.length; i++) {
            if(orderList[i] == null) {
                continue;
            }
            if(orderList[i].equals(menuItem)) {
                index = i;
                return index;
            }
        }
        index = FAIL_CONDITION;
        return index;
    }
    
    /**
    Shifts an array so that there are no null spaces/gaps.
    */
    private void shiftArray() {
        MenuItem [] shiftedArray = new MenuItem[orderList.length];
        int count = 0;
        for(int i = 0; i < orderList.length; i++) {
            if(orderList[i] != null) {
                shiftedArray[count] = orderList[i];
            }
            else{
                count--;
            }
            count++;
        }
        orderList = shiftedArray;
    }
    

    public String print() {
        String orders = "";
        double totalTax = 0;
        DecimalFormat money = new DecimalFormat("#,##0.00");
        shiftArray();
        for(MenuItem menuItems : orderList) {
            if(menuItems == null) {
                continue;
            }
            orders += menuItems.print() + '\n';
            totalTax += menuItems.itemPrice();
        }
        orders += ("Total = " + money.format((totalTax * TAX)));
        return orders;
    }

}

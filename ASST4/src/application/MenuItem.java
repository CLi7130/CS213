package application;

import java.text.DecimalFormat;

public class MenuItem implements Customizable {

    private int quantity;
    final static double TAX = .06625;
    MenuItem menuItem;
    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            menuItem = (MenuItem) obj;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            menuItem = (MenuItem) obj;
            return true;
        }
        return false;
    }

    public double itemPrice() {
        //return this.itemPrice() * quantity * TAX;
        return 0;
    }
    
    public String print() {
        DecimalFormat money = new DecimalFormat("#,###.##");
        return money.format(itemPrice());
    }
}

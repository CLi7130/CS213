package application;

import java.text.DecimalFormat;

public class MenuItem implements Customizable {



    private int quantity;
    private MenuItem menuItem;

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
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
            menuItem = null;
            return true;
        }
        return false;
    }

    public double itemPrice() {
        return this.itemPrice() * quantity;
        
    }
    
    public String print() {
        DecimalFormat money = new DecimalFormat("#,##0.00");
        return money.format(itemPrice());
    }
}

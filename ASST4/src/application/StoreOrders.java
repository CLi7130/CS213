package application;

public class StoreOrders implements Customizable{
    
    private Order[] storeOrders;

    /**
     * @return the storeOrders
     */
    public Order[] getStoreOrders() {
        return storeOrders;
    }

    /**
     * @param storeOrders the storeOrders to set
     */
    public void setStoreOrders(Order[] storeOrders) {
        this.storeOrders = storeOrders;
    }

    @Override
    public boolean add(Object obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        // TODO Auto-generated method stub
        return false;
    }

}

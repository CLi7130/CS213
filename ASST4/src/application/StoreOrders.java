package application;


public class StoreOrders implements Customizable {

    private Order[] storeOrders;
    private int numStoreOrder;
    private static final int GROW_AMOUNT = 4;
    private static final int FAIL_CONDITION = -1;

    /**
     * @return the storeOrders
     */
    public Order[] getStoreOrders() {
        return storeOrders;
    }
    
    /**
     * @return the numStoreOrder
     */
    public int getNumStoreOrder() {
        return numStoreOrder;
    }
    
    public StoreOrders() {
        numStoreOrder = 0;
        storeOrders = new Order[GROW_AMOUNT];
    }


    @Override
    public boolean add(Object obj) {
        shiftArray();
        if(obj instanceof Order) {
            numStoreOrder++;
            if(numStoreOrder > storeOrders.length) {
                grow();
            }
            storeOrders[numStoreOrder -1] = (Order) obj;
            return true;
        }
        return false;
    }
    
    private void grow() {
        Order[] grow = new Order[storeOrders.length + GROW_AMOUNT];
        for(int i = 0; i < storeOrders.length; i++) {
            grow[i] = storeOrders[i];
        }
        storeOrders = grow;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order) {
            final int index = find((Order) obj);
            if(index >= 0) {
                storeOrders[index] = null;
                numStoreOrder--;
                return true;
            }
            return false;
        }
        return false;
    }
    
    private int find(Order order) {
        int index = 0;
        shiftArray();
        for(int i = 0; i < storeOrders.length; i++) {
            if(storeOrders[i] == null) {
                continue;
            }
            if(storeOrders[i].equals(order)) {
                index = i;
                return index;
            }
        }
        index = FAIL_CONDITION;
        return index;
    }
    
    private void shiftArray() {
        Order [] shiftedArray = new Order[storeOrders.length];
        int count = 0;
        for(int i = 0; i < storeOrders.length; i++) {
            if(storeOrders[i] != null) {
                shiftedArray[count] = storeOrders[i];
            }
            else{
                count--;
            }
            count++;
        }
        storeOrders = shiftedArray;
    }
    
    public String print() {
        String orders = "";
        shiftArray();
        for(Order order : storeOrders) {
            if(order == null) {
                continue;
            }
            orders += order.print() + '\n';
        }
        return orders;
    }


 

}

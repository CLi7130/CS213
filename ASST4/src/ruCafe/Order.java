package ruCafe;


public class Order implements Customizable{

    private int orderNum;
    private MenuItem[] orderList;

    /**
     * @return the orderNum
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum the orderNum to set
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return the orderList
     */
    public MenuItem[] getOrderList() {
        return orderList;
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(MenuItem[] orderList) {
        this.orderList = orderList;
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

package application;
import application.Enums.CAKE_DONUTS;
//delete class on submission
import application.Enums.COFFEE_ADD_INS;
import application.Enums.COFFEE_SIZE;
import application.Enums.DONUT_TYPES;
import application.Enums.YEAST_DONUTS;

public class test {

    public static void main(String[] args) {
        StoreOrders storeOrders = new StoreOrders();
        Order order = new Order();
        Coffee coffee = new Coffee();
        Donut donut = new Donut();
       
        
        COFFEE_SIZE size = COFFEE_SIZE.GRANDE;
        coffee.setSize(size);
        coffee.add(coffee);
        coffee.setQuantity(1);
        coffee.add(COFFEE_ADD_INS.CARAMEL);
        coffee.add(COFFEE_ADD_INS.CREAM);
        coffee.add(COFFEE_ADD_INS.MILK);
        coffee.add(COFFEE_ADD_INS.SYRUP);
        coffee.itemPrice();
        
        Coffee coffee1 = new Coffee();
        COFFEE_SIZE size1 = COFFEE_SIZE.TALL;
        coffee1.setSize(size1);
        coffee1.add(coffee1);
        coffee1.setQuantity(1);
        coffee1.itemPrice();
        //coffee1.addAddins(COFFEE_ADD_INS.CREAM);
        //coffee1.addAddins(COFFEE_ADD_INS.MILK);

        donut.setType(DONUT_TYPES.CAKE_DONUTS);
        donut.setFlavor("DEEZ NUTZZ");
        donut.setQuantity(1);
        donut.itemPrice();
        donut.add(donut);
       
        order.add(coffee);
        order.add(coffee1);
        order.add(donut);
        storeOrders.add(order);
        //System.out.println(coffee.print());
        //System.out.println(coffee.print());
        //System.out.println(coffee.print());
        /*
        System.out.println(order.print() + '\n');
        order.remove(coffee);
        storeOrders.add(order);
        System.out.println(order.print() + '\n');
        System.out.println(storeOrders.print());
        
        */
        //order 2
        Order order2 = new Order();
        Coffee coffeeO2 = new Coffee();
        Donut donutO2_1 = new Donut();
        Donut donutO2_2 = new Donut();
        
        coffeeO2.setSize(COFFEE_SIZE.VENTI);
        coffeeO2.setQuantity(5);
        coffeeO2.add(COFFEE_ADD_INS.CARAMEL);
        coffeeO2.add(COFFEE_ADD_INS.CREAM);
        coffeeO2.add(COFFEE_ADD_INS.MILK);
        coffeeO2.add(COFFEE_ADD_INS.SYRUP);
        coffeeO2.itemPrice();
        
        donutO2_1.setFlavor(YEAST_DONUTS.MARBLE_FROSTED.toString());
        donutO2_1.setQuantity(3);
        donutO2_1.setType(DONUT_TYPES.YEAST_DONUTS);
        donutO2_1.itemPrice();
        
        donutO2_2.setFlavor(CAKE_DONUTS.BOSTON_CREME.toString());
        donutO2_2.setQuantity(1);
        donutO2_2.setType(DONUT_TYPES.CAKE_DONUTS);
        donutO2_2.itemPrice();
        
        order2.add(donutO2_1);
        order2.add(donutO2_2);
        order2.add(coffeeO2);
        
        System.out.println(order2.print());
        System.out.println();
        
        order2.remove(donutO2_2);
        System.out.println(order2.print());
        System.out.println();
        
        order2.remove(donutO2_1);
        System.out.println(order2.print());
        System.out.println();
        
        coffeeO2.remove(COFFEE_ADD_INS.CARAMEL);
        System.out.println(order2.print());
        System.out.println();
        
        storeOrders.add(order2);
        
        System.out.println(storeOrders.print());
        
        
        

        
        

    }

}

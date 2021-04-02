package src;

public class Coffee extends MenuItem implements Customizable{
	final static private double BASE_COFFEE_PRICE = 1.99;
	final static private double ADD_IN_PRICE = 0.20;
	final static private double COFFEE_SIZE_INCREASE = 0.50;
	
	enum ADD_INS {
		//to get number of values in an enum, use <enum_name>.getValues().length,
		CREAM,
		SYRUP,
		MILK,
		CARAMEL,
		WHIPPED_CREAM
	}
	enum SIZE {
		SHORT,
		TALL,
		GRANDE,
		VENTI
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
    
    public void itemPrice(){
        
    }
}

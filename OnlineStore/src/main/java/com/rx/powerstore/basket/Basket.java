package com.rx.powerstore.basket;

import java.util.ArrayList;
import java.util.List;

import com.rx.powerstore.entity.OrderProduct;

public class Basket {
	
    private static Basket instance;
    
    private Basket(){ items = new ArrayList<OrderProduct>(); }
    
    private static List<OrderProduct> items;
    
    public static Basket getInstance(){
        if(instance == null){
            instance = new Basket();
        }
        return instance;
    }
    
    public List<OrderProduct> getItems() {
    	return items;
    }
    
	public String calculateTotalPrice() {
		if (items.isEmpty())
			return "0.00";
		
		float totalPrice = 0;
		
		for (OrderProduct orderProduct : items) 
			totalPrice += orderProduct.getCount() * orderProduct.getProduct().getPrice();
		return String.format("%.2f", totalPrice);
	}
}

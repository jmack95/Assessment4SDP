package com.assessment4.services;



import java.util.ArrayList;
import java.util.Set;

import com.assessment4.entities.CartMerchandise;
import com.assessment4.entities.Merchandise;

public class PurchaseServiceFacadeImpl implements PurchaseServiceFacade {

	@Override
	public boolean placeOrder(int products) {
	
		boolean orderFulfilled=false;
        Merchandise merchandise=new Merchandise();
       products= merchandise.getId();
        
        if(StockService.isAvailable(merchandise))
        {
            boolean paymentConfirmed= PaymentService.makePayment();
            if(paymentConfirmed){
              
                orderFulfilled=true;
            }
        }
        return orderFulfilled;
	}

	@Override
	public boolean placeOrder(ArrayList<CartMerchandise> cartMerch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean placeOrder(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	



}


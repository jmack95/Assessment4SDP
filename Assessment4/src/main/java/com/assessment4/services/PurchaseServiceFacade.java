package com.assessment4.services;

import java.util.ArrayList;
import java.util.Set;

import com.assessment4.entities.CartMerchandise;
import com.assessment4.entities.Merchandise;

public interface PurchaseServiceFacade {

	boolean placeOrder(ArrayList<CartMerchandise> cartMerch);

	boolean placeOrder(Long id);

	boolean placeOrder(int products);
}

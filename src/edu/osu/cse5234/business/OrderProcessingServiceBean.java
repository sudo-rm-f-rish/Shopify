package edu.osu.cse5234.business;

import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean validateItemAvailability(Order order) {
    	return ServiceLocator.getInventoryService().validateQuantity(order.getItems());
    }
    
    public String processOrder(Order order) {
    	String confirmation = null;
    	if(ServiceLocator.getInventoryService().validateQuantity(order.getItems())) {
    		if(ServiceLocator.getInventoryService().updateInventory(order.getItems())) {
    			confirmation = UUID.randomUUID().toString();
    		}
    	}
    	return confirmation;
    }

}
 
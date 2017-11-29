package com.accn.ppes.magellan;

import java.util.Collection;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.accn.ppes.magellan.inventory.Inventory;
import com.accn.ppes.magellan.inventory.InventoryService;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class InventoryServiceTest  extends AbstractTest{

	@Autowired
	private InventoryService service;
	
    @Test
    public void test1Create() {

    	Inventory firstInventory = new Inventory();
    	firstInventory.setProductName("TV");
    	firstInventory.setLocationName("chennai");
    	firstInventory.setQuantity(20);

     	Inventory secondInventory = new Inventory();
     	secondInventory.setLocationName("chennai");
     	secondInventory.setProductName("Fridge");
     	secondInventory.setQuantity(10);
     	
         
     	Inventory createdInventory = service.saveInventory(firstInventory);
        service.saveInventory(secondInventory);
        Assert.assertNotNull("failure - expected not null", createdInventory);
       
        Assert.assertEquals("failure - expected Product Name attribute match", "TV",
                createdInventory.getProductName());
        
        Assert.assertEquals("failure - expected Location name attribute match", "chennai" ,
        		createdInventory.getLocationName());
        
        Assert.assertEquals("failure - expected Quantity attribute match", 20 ,
        		createdInventory.getQuantity());

        Collection<Inventory> list = service.getAllInventory();

        Assert.assertEquals("failure - expected size", 2, list.size());
    }
	
	  @Test
	    public void test2FindAll() {

	        Collection<Inventory> list = service.getAllInventory();

	        Assert.assertNotNull("failure - expected not null", list);
	        Assert.assertEquals("failure - expected list size", 2, list.size());

	    }
	  
	@Test
    public void test3FindByAttribute() {

        

        Inventory inventory = service.getByProductNameAndLocationName("TV", "chennai");

        Assert.assertNotNull("failure - expected not null", inventory);
        Assert.assertEquals("failure - expected id attribute match", 20,
        		inventory.getQuantity());

    }
	 @Test
	    public void test4Update() {

	       

	        Inventory inventory = service.getByProductNameAndLocationName("TV", "chennai");

	        Assert.assertNotNull("failure - expected not null", inventory);

	        int updateQuantity = 25;
	        inventory.setQuantity(updateQuantity);
	        Inventory updatedInventory = service.updateInventory("TV", "chennai", 5);

	        Assert.assertNotNull("failure - expected not null", updatedInventory);
	        
	        Assert.assertEquals("failure - expected quantity attribute match",
	        		updateQuantity, updatedInventory.getQuantity());

	    }
    
    


   

    
}

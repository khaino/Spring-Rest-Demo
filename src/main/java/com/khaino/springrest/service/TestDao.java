package com.khaino.springrest.service;

import java.io.IOException;
import java.net.InetAddress;

public class TestDao {

	
	public static void main(String[] args) throws IOException {
		

		 //also, this fails for an invalid address, like "www.sjdosgoogle.com1234sd" 
	       InetAddress address = InetAddress.getByName("www.automobiles.honda.com");
	      //for (InetAddress address : addresses) {
	        if (address.isReachable(10000))
	        {   
	           System.out.println("Connected "+ address);
	        }
	        else
	        {
	           System.out.println("Failed "+address);
	        }
	     // }
	}
}

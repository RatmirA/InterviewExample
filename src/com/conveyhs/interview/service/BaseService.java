package com.conveyhs.interview.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Random;

public class BaseService {

	protected Connection conn = null;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Random random = new Random();
	
	public BaseService() {
		try
		{
			conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public String randomString(int length) {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'

	    return random.ints(leftLimit, rightLimit + 1)
	      .limit(length)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	}
}

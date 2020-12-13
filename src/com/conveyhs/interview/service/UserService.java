package com.conveyhs.interview.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.conveyhs.interview.model.User;

public class UserService extends BaseService {
	public void setup(int count) {
		createTable();
		for(int i = 0; i < count;i++)
		{
			if(i % 100 == 0) System.out.println("user " + i + " completed");
			put(new User(i, "user" + randomString(5), randomString(6) + "@" + randomString(4) + ".com", new Date()));
		}
		System.out.println("Users setup");
	}
	
	private void createTable()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.executeUpdate("drop table if exists user");
			statement.executeUpdate("create table user ("
					+ "id integer, "
					+ "username string, "
					+ "email string, "
					+ "created date)");
		}
		catch(Exception e)
		{
			System.out.println("Erorr:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void put(User u)
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.executeUpdate("insert into user VALUES (" + 
					u.getId() + ", '" + 
					u.getUserName() + "', '" + 
					u.getEmail() + "', datetime('now'))");
		}
		catch(Exception e)
		{
			System.out.println("Erorr:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public User get(String id)
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("select * from user where id = " + id);
			if(rs.next())
			{
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), formatter.parse(rs.getString("created")));
				// read the result set
			}
		}
		catch(Exception e)
		{
			System.out.println("Erorr:" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}

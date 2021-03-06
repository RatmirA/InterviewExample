package com.conveyhs.interview.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date; /** We don't use this, need to remove */
import java.util.LinkedList;
import java.util.List;

import com.conveyhs.interview.model.Post;
import com.conveyhs.interview.model.User; /** We don't use this, need to remove */

public class PostService extends BaseService {

	public void setup(int userCount, int postsPerUser)
	{
		createTable();
		//total number of posts = posts per user * total users
		for(int i = 0; i < postsPerUser * userCount; i++)
		{
			if(i % 1000 == 0) System.out.println("post " + i + " completed");
			//create post
			put(new Post(i, "subject " + randomString(5), random.nextInt(userCount), null, null));
		}
		System.out.println("posts setup");
	}
	
	private void createTable()
	{
		try /** Need to use try with resources or close this Statement in a finally clause */
		{
			Statement statement = conn.createStatement();
			statement.executeUpdate("drop table if exists post");
			statement.executeUpdate("create table post ("
					+ "id integer, "
					+ "user_id integer, "
					+ "subject string, "
					+ "posted date, "
					+ "updated date)");
		}
		catch(Exception e)
		{
			System.out.println("Erorr:" + e.getMessage()); /** 4 times duplicates this part of code */
			e.printStackTrace();
		}
	}
	
	public void put(Post p)
	{
		try /** Need to use try with resources or close this Statement in a finally clause */
		{
			Statement statement = conn.createStatement();
			statement.executeUpdate("insert into post VALUES (" + 
					p.getId() + ", " + 
					p.getAuthorId() + ", '" + 
					p.getSubject() + "', datetime('now'),datetime('now'))");
		}
		catch(Exception e)
		{
			System.out.println("Erorr:" + e.getMessage()); /** 4 times duplicates this part of code */
			e.printStackTrace();
		}
	}

	public Post get(String id)
	{
		try /** Need to use try with resources or close this Statement in a finally clause */
		{
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("select * from post where id = " + id);/** "select * from post where id = "? */
			if(rs.next())
			{
				return new Post(rs.getInt("id"), rs.getString("subject"), rs.getInt("user_id"), formatter.parse(rs.getString("posted")), formatter.parse(rs.getString("updated")));
				// read the result set
			}
		}
		catch(Exception e)
		{
			System.out.println("Erorr:" + e.getMessage()); /** 4 times duplicates this part of code */
			e.printStackTrace();
		}
		return null;
	}
	
	public int getPostCount(String userId)
	{
		return getByUserId(userId).size();/** We can calculate from DB creating SQL count() function, without creating List */
	}
	
	public List<Post> getByUserId(String userId)
	{
		List<Post> result = new LinkedList<>();
		try /** Need to use try with resources or close this Statement in a finally clause */
		{
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("select * from post where user_id = " + userId);
			while(rs.next())
			{
				result.add(new Post(rs.getInt("id"), rs.getString("subject"), rs.getInt("user_id"), formatter.parse(rs.getString("posted")), formatter.parse(rs.getString("updated"))));
			}
		}
		catch(Exception e)
		{
			System.out.println("Erorr:" + e.getMessage()); /** 4 times duplicates this part of code */
			e.printStackTrace();
		}
		return result;
	}
}

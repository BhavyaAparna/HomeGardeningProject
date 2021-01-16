package com.ts.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tuple.entity.EntityTuplizerFactory;

import com.dto.UserDetails;
import com.dto.ItemDetails;
import com.dto.StoreDetails;
import com.dto.GardenerDetails;
import com.dto.CategoryDetails;

public class HibernateTemplate {

	private static SessionFactory sessionFactory;
	
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public static int addObject(Object obj)
	{
		System.out.println("Inside Template...");
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.save(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Object getObject(Class c,Serializable serializable)
	{
		Object obj=null;
		
		try {			
			return sessionFactory.openSession().get(c,serializable);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static Object getObjectByUserPass(String userEmail,String userPassword) {
	
	String queryString = "from UserDetails where userEmail = :userEmail and userPassword =:userPassword";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("userEmail", userEmail);
	  query.setString("userPassword", userPassword);
	  Object queryResult = query.uniqueResult();
	  UserDetails user = (UserDetails)queryResult;
	  return user; 
	}
	
	/*Gardener*/
	public static Object getObjectByUserPassGardener(String gardenerEmail,String gardenerPassword) {
		
		String queryString = "from GardenerDetails where gardenerEmail = :gardenerEmail and gardenerPassword =:gardenerPassword";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("gardenerEmail", gardenerEmail);
		  query.setString("gardenerPassword", gardenerPassword);
		  Object queryResult = query.uniqueResult();
		  GardenerDetails gardener = (GardenerDetails)queryResult;
		  return gardener; 
		}
	
	public static Object getObjectByEmail(String email) {
		
		String queryString = "from Employee where email = :email";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("email", email);
		  Object queryResult = query.uniqueResult();
		  UserDetails user = (UserDetails)queryResult;
		  return user;
		}
	
	
	public static List<Object> getObjectByitemImage(String itemVideo)
	{
		String queryString = "from ItemDetails c where ((:itemVideo is null and c.itemVideo is null) or c.itemVideo = :itemVideo)";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("itemVideo", null);
		List result = query.list();
		return result;
		//Object queryResult = query.uniqueResult();
		//List<Object> item = (List<Object>)queryResult;
		//return item;
	}
	
	public static List<Object> getObjectListVideo(String itemImage)
	{
		String queryString = "from ItemDetails c where ((:itemImage is null and c.itemImage is null) or c.itemImage = :itemImage)";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("itemImage", null);
		List result = query.list();
		return result;
		//Object queryResult = query.uniqueResult();
		//List<Object> item = (List<Object>)queryResult;
		//return item;
	}
	
	public static List<Object> getObjectListByQuery(String query)
	{
		return sessionFactory.openSession().createQuery(query).list();
	}
	
	public static int updateObject(Object obj)
	{
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.saveOrUpdate(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int deleteObject(Class c,Serializable serializable)
	{
		int result=0;
		
		Session session=sessionFactory.openSession();
		
		Transaction tx=session.beginTransaction();
		
		try {
			
			Object obj=session.get(c,serializable);
			
			session.delete(obj);
			
			tx.commit();
			
			result=1;
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tx.rollback();
		}
		
		return result;
	}

	public static List<Object> getObjectListByName(Class c, String columName, String value) {
		Session session=sessionFactory.openSession();
		  Criteria criteria = session.createCriteria(c);
			Criterion nameCriterion = Restrictions.eq(columName, value);
			criteria.add(nameCriterion);
			return criteria.list();
	}
	
	
}

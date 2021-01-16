package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dto.CategoryDetails;
import com.ts.db.HibernateTemplate;

public class CategoryDAO {
	private SessionFactory factory = null;
	
	public List<CategoryDetails> getAllCategoryDetails() {
		List<CategoryDetails> categoryDetails=(List)HibernateTemplate.getObjectListByQuery("From CategoryDetails");
		return categoryDetails;	
	}
}

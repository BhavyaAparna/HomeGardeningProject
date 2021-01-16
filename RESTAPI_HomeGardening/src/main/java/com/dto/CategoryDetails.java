package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
public class CategoryDetails {
	@Id@GeneratedValue
	private int categoryId;
	private String categoryName;
		
	@OneToMany(mappedBy="categoryDetails",fetch = FetchType.LAZY)
	private List<ItemDetails> itemDetails = new ArrayList<ItemDetails>();
	
	@ManyToMany(mappedBy="categoryDetails")
	private List<GardenerDetails> gardenerDetails	= new ArrayList<GardenerDetails>();

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ItemDetails> getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(List<ItemDetails> itemDetails) {
		this.itemDetails = itemDetails;
	}

	public List<GardenerDetails> getGardenerDetails() {
		return gardenerDetails;
	}

	public void setGardenerDetails(List<GardenerDetails> gardenerDetails) {
		this.gardenerDetails = gardenerDetails;
	}

	@Override
	public String toString() {
		return "CategoryDetails [categoryId=" + categoryId + ", categoryName=" + categoryName + ", itemDetails="
				+ itemDetails + ", gardenerDetails=" + gardenerDetails + "]";
	}
	
	
		
}

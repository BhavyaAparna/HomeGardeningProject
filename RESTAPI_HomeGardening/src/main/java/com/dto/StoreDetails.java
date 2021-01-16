package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class StoreDetails {
	@Id@GeneratedValue
	private int storeId;
	private String storeName;
	private String storePhoneNumber;
	private String storeAddress;
	
	
	@ManyToMany
	private List<ItemDetails> itemDetails = new ArrayList<ItemDetails>();


	public int getStoreId() {
		return storeId;
	}


	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getStorePhoneNumber() {
		return storePhoneNumber;
	}


	public void setStorePhoneNumber(String storePhoneNumber) {
		this.storePhoneNumber = storePhoneNumber;
	}


	public String getStoreAddress() {
		return storeAddress;
	}


	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}


	public List<ItemDetails> getItemDetails() {
		return itemDetails;
	}


	public void setItemDetails(List<ItemDetails> itemDetails) {
		this.itemDetails = itemDetails;
	}


	@Override
	public String toString() {
		return "StoreDetails [storeId=" + storeId + ", storeName=" + storeName + ", storePhoneNumber="
				+ storePhoneNumber + ", storeAddress=" + storeAddress + ", itemDetails=" + itemDetails + "]";
	}
	
	
	

}

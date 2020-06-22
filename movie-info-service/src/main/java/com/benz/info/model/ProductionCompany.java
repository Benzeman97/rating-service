package com.benz.info.model;

public class ProductionCompany {

	private int id;
	private String name;
	private String origin_country;
	
	
	public ProductionCompany() {
	}


	public ProductionCompany(int id, String name, String origin_country) {
		this.id = id;
		this.name = name;
		this.origin_country = origin_country;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOrigin_country() {
		return origin_country;
	}


	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}
	
	
	
}

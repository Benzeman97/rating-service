package com.benz.info.model;

import java.util.List;

public class MovieSummary {

	private int id;
	private String original_title;
	private String overview;
	private String original_language;
    private List<ProductionCompany> production_companies;
	
	public MovieSummary() {
	}
	
	public List<ProductionCompany> getProduction_companies() {
		return production_companies;
	}

	public void setProduction_companies(List<ProductionCompany> production_companies) {
		this.production_companies = production_companies;
	}

	public MovieSummary(int id, String original_title, String overview, String original_language,
			List<ProductionCompany> production_companies) {
		this.id = id;
		this.original_title = original_title;
		this.overview = overview;
		this.original_language = original_language;
		this.production_companies = production_companies;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getOriginal_language() {
		return original_language;
	}
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	
	
}

package com.benz.catalog.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="MovieRating")
public class MovieRating {

	private int movieId;
	private double rating;

	
	public MovieRating() {
	}

	public MovieRating(int movieId, double rating) {
		this.movieId = movieId;
		this.rating = rating;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
}

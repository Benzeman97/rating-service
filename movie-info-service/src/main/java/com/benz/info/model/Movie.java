package com.benz.info.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.NoArgsConstructor;

@JsonRootName(value="Movie")
@NoArgsConstructor
public class Movie {

	private int movieId;
	private String movieName;
	private int year;
	private String country;
	
	public Movie(int movieId, String movieName, int year, String country) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.year = year;
		this.country = country;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}

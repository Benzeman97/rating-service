package com.benz.rating.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="UserRating")
public class UserRating {

	private int movieId;
	private List<MovieRating> ratings;

	public UserRating() {
	}

	public UserRating(int movieId, List<MovieRating> ratings) {
		this.movieId = movieId;
		this.ratings = ratings;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public List<MovieRating> getRatings() {
		return ratings;
	}
	public void setRatings(List<MovieRating> ratings) {
		this.ratings = ratings;
	}
	
	
	public void initData(int userId)
	{
		this.setMovieId(userId);
		this.setRatings(Arrays.asList(
				new MovieRating(800,6.9),
				new MovieRating(908,7.3)
				
				));
				
				
	}
	
}

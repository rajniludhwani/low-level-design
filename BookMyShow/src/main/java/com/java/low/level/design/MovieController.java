package com.java.low.level.design;

import com.java.low.level.design.enums.City;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {

  Map<City, List<Movie>> cityVsMovies;
  List<Movie> allMovies;

  public MovieController() {
    this.cityVsMovies = new HashMap<>();
    this.allMovies = new ArrayList<>();
  }

  public void addMovie(City city, Movie movie) {
    allMovies.add(movie);
    List<Movie> movies = cityVsMovies.getOrDefault(city, new ArrayList<>());
    movies.add(movie);
    cityVsMovies.put(city, movies);
  }

  public Movie getMovieByName(String name) throws Exception {
    for (Movie movie : allMovies) {
      if (movie.getMovieName().equals(name)) {
        return movie;
      }
    }
    throw new Exception("Movie not found");
  }

  public List<Movie> getMoviesByCity(City city) throws Exception {
    if (cityVsMovies.containsKey(city)) {
      return cityVsMovies.get(city);
    }
    throw new Exception("Movies not found for " + city.name() + " location.");
  }

  //REMOVE movie from a particular city, make use of cityVsMovies map

  //UPDATE movie of a particular city, make use of cityVsMovies map

  //CRUD operation based on Movie ID, make use of allMovies list

}

package com.java.low.level.design;

import com.java.low.level.design.enums.City;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

  Map<City, List<Theatre>> cityVsTheatres;
  List<Theatre> allTheatres;

  public TheatreController() {
    this.cityVsTheatres = new HashMap<>();
    this.allTheatres = new ArrayList<>();
  }

  public void addTheatre(City city, Theatre theatre) {
    allTheatres.add(theatre);
    List<Theatre> theatres = cityVsTheatres.getOrDefault(city, new ArrayList<>());
    theatres.add(theatre);
    cityVsTheatres.put(city, theatres);
  }

  public Map<Theatre, List<Show>> getAllShow(Movie movie, City city) {
    Map<Theatre, List<Show>> allShowForMovie = new HashMap<>();
    List<Theatre> theatres = cityVsTheatres.get(city);
    for (Theatre theatre : theatres) {
      List<Show> givenMovieShows = new ArrayList<>();
      List<Show> shows = theatre.getShowList();

      for (Show show : shows) {
        if (show.getMovie().getMovieId().equals(movie.getMovieId())) {
          givenMovieShows.add(show);
        }
      }
      if (!givenMovieShows.isEmpty()) {
        allShowForMovie.put(theatre, givenMovieShows);
      }
    }
    return allShowForMovie;
  }
}

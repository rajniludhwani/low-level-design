package com.java.low.level.design;

import com.java.low.level.design.enums.City;
import com.java.low.level.design.enums.SeatCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {

  private MovieController movieController;
  private TheatreController theatreController;

  public BookMyShow() {
    this.movieController = new MovieController();
    this.theatreController = new TheatreController();
  }

  public static void main(String[] args) throws Exception {
    BookMyShow bookMyShow = new BookMyShow();
    bookMyShow.initialise();

    //Make a booking
    bookMyShow.createBooking(City.BANGALORE, "Bahubali");
    bookMyShow.createBooking(City.BANGALORE, "Bahubali");
    bookMyShow.createBooking(City.BANGALORE, "K3G");
  }

  private void createBooking(City city, String movieName) {
    //1. search list of movies by location
    List<Movie> moviesForCity = null;
    try {
      moviesForCity = movieController.getMoviesByCity(city);
    } catch (Exception e) {
      throw new RuntimeException("Movie not found");
    }

    //2. get the movie which user wants to see
    Movie interestedMovie = null;
    for (Movie movie : moviesForCity) {
      if (movie.getMovieName().equalsIgnoreCase(movieName)) {
        interestedMovie = movie;
        break;
      }
    }

    //3. get all the shows for this movie
    Map<Theatre, List<Show>> theatreWiseShows = theatreController.getAllShow(interestedMovie, city);

    //4. select the particular show in which user is interested in
    Map.Entry<Theatre, List<Show>> entry = theatreWiseShows.entrySet().iterator().next();
    List<Show> runningShows = entry.getValue();
    Show interestedShow = runningShows.get(0);

    //5.select the seats
    int seatNumber = 30;
    List<Integer> bookedSeats = interestedShow.getBookedSeatIds();
    if (!bookedSeats.contains(seatNumber)) {
      bookedSeats.add(seatNumber);

      Booking booking = new Booking();
      List<Seat> mySeats = new ArrayList<>();
      for (Seat seat : interestedShow.getScreen().getSeats()) {
        if (seat.getSeatId() == seatNumber) {
          mySeats.add(seat);
        }
      }

      booking.setShow(interestedShow);
      booking.setSeatsToBook(mySeats);
    } else {
      System.out.println("Seat Already Booked");
      return;
    }
    System.out.println("BOOKING SUCCESSFUL");
  }

  private void initialise() throws Exception {
    createMovies();
    createTheatre();
  }

  private void createMovies() {
    Movie movie1 = new Movie();
    movie1.setMovieId("m001");
    movie1.setMovieName("Bahubali");
    movie1.setDuration(180);

    Movie movie2 = new Movie();
    movie2.setMovieId("m002");
    movie2.setMovieName("K3G");
    movie2.setDuration(210);

    movieController.addMovie(City.BANGALORE, movie1);
    movieController.addMovie(City.BANGALORE, movie2);
    movieController.addMovie(City.DELHI, movie1);
    movieController.addMovie(City.DELHI, movie2);
  }

  private void createTheatre() throws Exception {
    Movie movie1 = movieController.getMovieByName("Bahubali");
    Movie movie2 = movieController.getMovieByName("K3G");

    Theatre inoxTheatre = new Theatre();
    inoxTheatre.setTheatreId("th001");
    inoxTheatre.setCity(City.BANGALORE);
    inoxTheatre.setScreenList(createScreens());
    List<Show> inoxShows = new ArrayList<>();
    Show inoxMorningShow = createShow("show1", movie1, inoxTheatre.getScreenList().get(0), 8);
    Show inoxEveningShow = createShow("show2", movie2, inoxTheatre.getScreenList().get(0), 16);
    inoxShows.add(inoxMorningShow);
    inoxShows.add(inoxEveningShow);
    inoxTheatre.setShowList(inoxShows);

    Theatre pvrTheatre = new Theatre();
    pvrTheatre.setTheatreId("th002");
    pvrTheatre.setCity(City.DELHI);
    pvrTheatre.setScreenList(createScreens());
    List<Show> pvrShows = new ArrayList<>();
    Show pvrMorningShow = createShow("show3", movie1, inoxTheatre.getScreenList().get(0), 12);
    Show pvrEveningShow = createShow("show4", movie2, inoxTheatre.getScreenList().get(0), 18);
    pvrShows.add(pvrMorningShow);
    pvrShows.add(pvrEveningShow);
    pvrTheatre.setShowList(pvrShows);

    theatreController.addTheatre(City.BANGALORE, inoxTheatre);
    theatreController.addTheatre(City.DELHI, pvrTheatre);

  }

  private Show createShow(String showId, Movie movie, Screen screen, int showStartTime) {
    Show show = new Show();
    show.setShowId(showId);
    show.setMovie(movie);
    show.setScreen(screen);
    show.setStartTime(showStartTime);
    return show;
  }

  private List<Screen> createScreens() {
    List<Screen> screens = new ArrayList<>();
    Screen screen1 = new Screen();
    screen1.setScreenId("sc001");
    screen1.setSeats(createSeats());

    screens.add(screen1);

    return screens;
  }

  private List<Seat> createSeats() {
    List<Seat> seats = new ArrayList<>();

    for (int i = 0; i < 40; i++) {
      Seat seat = new Seat();
      seat.setSeatId(i);
      seat.setRow(i % 10);
      seat.setSeatCategory(SeatCategory.SILVER);
      seats.add(seat);
    }

    for (int i = 40; i < 70; i++) {
      Seat seat = new Seat();
      seat.setSeatId(i);
      seat.setRow(i % 10);
      seat.setSeatCategory(SeatCategory.GOLD);
      seats.add(seat);
    }

    for (int i = 70; i < 100; i++) {
      Seat seat = new Seat();
      seat.setSeatId(i);
      seat.setRow(i % 10);
      seat.setSeatCategory(SeatCategory.PLATINUM);
      seats.add(seat);
    }
    return seats;
  }
}
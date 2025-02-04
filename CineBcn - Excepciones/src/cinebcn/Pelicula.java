public class Pelicula {
    private String title;
    private int duration;
    private String genre;
    private String ageRating;

    public Pelicula(String title, int duration, String genre, String ageRating) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.ageRating = ageRating;
    }

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public String getAgeRating() {
        return ageRating;
    }
}


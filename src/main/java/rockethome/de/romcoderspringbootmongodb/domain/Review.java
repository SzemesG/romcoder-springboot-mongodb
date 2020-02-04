package rockethome.de.romcoderspringbootmongodb.domain;

public class Review {

    // Fields

    private String userName;
    private int rating;
    private boolean approved;

    // Constructors

    public Review() {
    }

    public Review(String userName, int rating, boolean approved) {
        this.userName = userName;
        this.rating = rating;
        this.approved = approved;
    }

    // Some basic Getters


    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public boolean isApproved() {
        return approved;
    }
}

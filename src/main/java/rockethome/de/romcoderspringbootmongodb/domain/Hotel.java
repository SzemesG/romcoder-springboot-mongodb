package rockethome.de.romcoderspringbootmongodb.domain;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String id;
    private String name;
    private int pricePerNight;
    private Address address;
    private List<Review> reviews;

    // Constructors

    protected Hotel() {
        this.reviews = new ArrayList<>();
    }

    public Hotel(String name, int pricePerNight, Address address, List<Review> reviews) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.reviews = reviews;
    }

    // Some basic Getters


    public String getName() {
        return name;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public Address getAddress() {
        return address;
    }

    public List<Review> getReviews() {
        return reviews;
    }

}

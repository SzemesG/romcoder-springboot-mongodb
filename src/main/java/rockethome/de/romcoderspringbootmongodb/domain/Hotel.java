package rockethome.de.romcoderspringbootmongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

                    // the Hotel is an aggregate root, so this is the Entity on which we will apply our annotations
                                    // this Object should be used as a MongoDB-Document and stored in a Collection
@Document(collection = "Hotels")                             // (optionally we can pass in the Collection-name)
public class Hotel {

    // annotate our id-Field - in MongoDB id is not an integer value, by default assigns a grid-like-structure,
    // called object-id, that is why by the id-Field using we a String

    @Id
    private String id;

    // Fields

    private String name;

    // When we want to make some filtering on the pricePerNight, then in order to speed up the process,
    // we can add indexes on various properties

    @Indexed(direction = IndexDirection.ASCENDING)      // pricePerNight property should be indexed in an ascending order
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

    public String getId() { return id; }

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

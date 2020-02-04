package rockethome.de.romcoderspringbootmongodb.domain;

public class Address {

    // Fields

    private String city;
    private String country;

    protected Address() {
    }

    // Constructors

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    // Some basic Getters

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}

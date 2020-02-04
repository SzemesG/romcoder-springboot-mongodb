package rockethome.de.romcoderspringbootmongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rockethome.de.romcoderspringbootmongodb.domain.Address;
import rockethome.de.romcoderspringbootmongodb.domain.Hotel;
import rockethome.de.romcoderspringbootmongodb.domain.Review;
import rockethome.de.romcoderspringbootmongodb.repositories.HotelRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {

    // * --> we need to add a reference of the HotelRepository

    private HotelRepository hotelRepository;

    // ** then a constructor, that we don't need it with a single constructor --> **

    public DBSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    // Implement the CLR-Interface and the run-method = when SB-App start,
    // then this CLR shall be executed automatically by Spring

    @Override
    public void run(String... args) throws Exception {

        // here we want to add some Hotels (Documents) to our Collection

        Hotel marriot = new Hotel(
                "Marriot",
                130,
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                )

        );

        // create 2 more Hotels:

        Hotel ibis = new Hotel(
                "Ibis",
                90,
                new Address("Bucharest", "Romania"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                )

        );

        Hotel sofitel = new Hotel(
                "Sofitel",
                200,
                new Address("Rome", "Italy"),
                new ArrayList<>()
        );

        // and it saves them in the database, each time, when our application starts

        // we need to drop the existing collection if it already exists in the database
        // and then the new hotels need to be inserted.
        // this behaviour is optional, but we want to start with a clean-DB each time when we map --> *

        // ** --> drop all hotels

        this.hotelRepository.deleteAll();

        // add our hotels to the DB - we can group them, so we don't call the save-method 3 times

        List<Hotel> hotels = Arrays.asList(marriot, ibis, sofitel);
        this.hotelRepository.saveAll(hotels);
    }
}

package rockethome.de.romcoderspringbootmongodb.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.web.bind.annotation.*;
import rockethome.de.romcoderspringbootmongodb.domain.Hotel;
import rockethome.de.romcoderspringbootmongodb.domain.QHotel;
import rockethome.de.romcoderspringbootmongodb.repositories.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    // we have a dependency on the Hotelrepository

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // create a method, that will display all the hotels that we have in our DB

    @GetMapping("/all")
    public List<Hotel> getAll() {

        List<Hotel> hotels = this.hotelRepository.findAll();

        return hotels;
    }

    // insert-method --> PUT :

    @PutMapping
    public void insert(@RequestBody Hotel hotel) {         // receive an Object (type Hotel) in the ReqBody

        this.hotelRepository.insert(hotel);                 // insert-method available on the MongoRepo and pass in it

    }

    // update(save)-method --> POST :

    @PostMapping
    public void update(@RequestBody Hotel hotel) {

        this.hotelRepository.save(hotel);           // save works like an upsert-method, it can perform:
                                                    // insert (the new doc has no id)
                                                    // update (the new doc has an id alredy)

    }

    // delete-method --> DELETE :

    @DeleteMapping("/{id}")                                 // need to define the Path-variable here
    public void delete(@PathVariable("id") String id) {     // and this will be translated into this String-variable

        this.hotelRepository.deleteById(id);            // (id) == (String id)       !!!

    }

    @GetMapping("/{id}")
    public Optional<Hotel> getById(@PathVariable("id") String id) {

        Optional<Hotel> hotel = this.hotelRepository.findById(id);

        return hotel;

    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getPricePerNight(@PathVariable("maxPrice") int maxPrice) {

        List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);

        return hotels;

    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city) {

        List<Hotel> hotels = this.hotelRepository.findByCity(city);

        return hotels;

    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country) {

        // create a query-class (QHotel) instance

        QHotel qHotel = new QHotel("hotel");

        // using the q-class we can create the filter

        BooleanExpression filterByCountry = qHotel.address.country.eq(country);

        // QueryDslPredicateExecutor gives for us the findAll-method (we can pass our filter in it):

        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);

        // need to cast it to a List !!

        return hotels;
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended() {

        // we have 2 filters ! (recent price + best buy (rating))

        final int maxPrice = 100;
        final int minRating = 7;

        // create a query-class (QHotel) instance

        QHotel qHotel = new QHotel("hotel");

        // using the q-class we can create the 2 filters

        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);

        // QueryDslPredicateExecutor gives for us the findAll-method (we can pass our 2 filters in it):
        // we can combines these 2 filters in this final method
        // with the use of "and" operator we can pass in a different predicate (filterByRating)

        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));

        return hotels;
    }

}

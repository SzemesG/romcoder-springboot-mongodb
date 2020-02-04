package rockethome.de.romcoderspringbootmongodb.controllers;

import org.springframework.web.bind.annotation.*;
import rockethome.de.romcoderspringbootmongodb.domain.Hotel;
import rockethome.de.romcoderspringbootmongodb.repositories.HotelRepository;

import java.util.List;

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



}

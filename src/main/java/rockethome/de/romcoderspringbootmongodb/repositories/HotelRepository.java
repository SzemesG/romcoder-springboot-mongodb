package rockethome.de.romcoderspringbootmongodb.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import rockethome.de.romcoderspringbootmongodb.domain.Hotel;

import java.util.List;
import java.util.Optional;


// extend a Mongo-Repo pass in 2 Types: Type of the Entity, that we want to store + Type of the ID (String)
// we still get the generic methods, like findAll, sav, delete, etc.

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {

    Optional<Hotel> findById(String id);
    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    @Query(value = "{'address.city':?0}")
    List<Hotel> findByCity(String city);

}

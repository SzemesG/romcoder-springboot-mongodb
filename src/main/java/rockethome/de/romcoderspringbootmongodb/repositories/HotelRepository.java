package rockethome.de.romcoderspringbootmongodb.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rockethome.de.romcoderspringbootmongodb.domain.Hotel;

// extend a Mongo-Repo pass in 2 Types: Type of the Entity, that we want to store + Type of the ID (String)
// we still get the generic methods, like findAll, sav, delete, etc.

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
}

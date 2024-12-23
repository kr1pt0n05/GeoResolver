package geo.lind3.de.georesolver.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM locations WHERE ST_DWithin(coordinates, ST_POINT(:latitude, :longitude, 4326), 10000) ORDER BY ST_DISTANCE(coordinates, ST_POINT(:latitude, :longitude, 4326)) LIMIT 1", nativeQuery = true)
    Optional<Location> findByCoordinates(double latitude, double longitude);

}

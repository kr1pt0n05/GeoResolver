package geo.lind3.de.georesolver.repository;

import geo.lind3.de.georesolver.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM locations WHERE ST_DWithin(coordinates, ST_POINT(:latitude, :longitude, 4326), 10000) ORDER BY ST_DISTANCE(coordinates, ST_POINT(:latitude, :longitude, 4326)) LIMIT 1", nativeQuery = true)
    Set<Location> findByCoordinates(double latitude, double longitude);

}

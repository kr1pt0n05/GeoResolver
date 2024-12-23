package geo.lind3.de.georesolver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    /**
     * Reverse geocodes the given latitude and longitude to find the corresponding location.
     *
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     * @return a ResponseEntity containing the location name if found, or an error message if not found
     */
    public ResponseEntity<?> reverseGeocode(double latitude, double longitude) {

        try {
            System.out.println("Reverse geocoding...");
            Optional<Location> locationOpt = locationRepository.findByCoordinates(latitude, longitude);
            System.out.println("Finished querying database...");
            if(locationOpt.isEmpty()){
                System.out.println("Location not found");
            }else{
                System.out.println("Location found");
            }

            Location location = locationOpt.get();
            System.out.println(location.getName());

            return ResponseEntity.ok(Map.of(
                    "city", location.getName(),
                    "country_code", location.getCountryCode(),
                    "feature_code", location.getFeatureCode()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", "Location not found"
            ));
        }
    }
}

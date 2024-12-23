package geo.lind3.de.georesolver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geocode")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/reverse")
    public ResponseEntity<?> reverseGeocode(
            @RequestParam(value = "latitude", required = true) double latitude,
            @RequestParam(value = "longitude", required = true) double longitude
    ) {
        return locationService.reverseGeocode(latitude, longitude);
    }
}

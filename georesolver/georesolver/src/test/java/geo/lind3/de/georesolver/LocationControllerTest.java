package geo.lind3.de.georesolver;

import geo.lind3.de.georesolver.controller.LocationController;
import geo.lind3.de.georesolver.controller.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reverseGeocodeReturnsOkForValidCoordinates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/geocode/reverse")
                .param("latitude", "40.7128")
                .param("longitude", "-74.0060"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void reverseGeocodeReturnsBadRequestForMissingLatitude() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/geocode/reverse")
                .param("longitude", "-74.0060"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void reverseGeocodeReturnsBadRequestForMissingLongitude() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/geocode/reverse")
                .param("latitude", "40.7128"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void reverseGeocodeReturnsBadRequestForInvalidLatitude() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/geocode/reverse")
                .param("latitude", "invalid")
                .param("longitude", "-74.0060"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void reverseGeocodeReturnsBadRequestForInvalidLongitude() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/geocode/reverse")
                .param("latitude", "40.7128")
                .param("longitude", "invalid"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
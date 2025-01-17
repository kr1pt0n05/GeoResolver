package geo.lind3.de.georesolver.controller;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.locationtech.jts.geom.Point;
import java.time.LocalDate;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('locations_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "country_code", length = 5)
    private String countryCode;

    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @Column(name = "feature_code", length = 5)
    private String featureCode;

    @Column(name = "coordinates", columnDefinition = "GEOGRAPHY(POINT, 4326)")
    private Point coordinates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

}
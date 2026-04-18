package com.ridesharing.repository;

import com.ridesharing.model.PlaceDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PlaceDistanceRepository extends JpaRepository<PlaceDistance, Long> {

    @Query("SELECT p FROM PlaceDistance p WHERE (p.placeFrom = :a AND p.placeTo = :b) OR (p.placeFrom = :b AND p.placeTo = :a)")
    Optional<PlaceDistance> findDistance(@Param("a") String a, @Param("b") String b);

    @Query(value = "SELECT DISTINCT place_from FROM place_distance UNION SELECT DISTINCT place_to FROM place_distance ORDER BY 1", nativeQuery = true)
    List<String> findAllPlaceNames();
}
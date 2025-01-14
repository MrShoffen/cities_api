package org.mrshoffen.cities_api.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.mrshoffen.cities_api.entity.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitiesRepository extends JpaRepository<City, Integer> {


    @Query("""
            SELECT c.name FROM City c
            WHERE c.name like :name%  ORDER BY c.population DESC
            """)
    List<String> findUniqueCities(@Param("name") String name, Pageable page);

}

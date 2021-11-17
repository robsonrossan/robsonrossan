package com.br.itapemirim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.itapemirim.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
}
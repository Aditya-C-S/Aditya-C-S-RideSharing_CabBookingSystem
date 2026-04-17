package com.ridesharing.repository;

import com.ridesharing.model.Commuter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuterRepository extends JpaRepository<Commuter, Long> {
    Commuter findByEmail(String email);
}
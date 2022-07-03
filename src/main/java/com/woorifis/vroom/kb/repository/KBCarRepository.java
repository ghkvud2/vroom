package com.woorifis.vroom.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woorifis.vroom.kb.domain.Car;

@Repository
public interface KBCarRepository extends JpaRepository<Car, Long> {

}

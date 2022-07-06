package com.woorifis.vroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woorifis.vroom.domain.Car;

@Repository
public interface CollectRepository extends JpaRepository<Car, Long> {

}

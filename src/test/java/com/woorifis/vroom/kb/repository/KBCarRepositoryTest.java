package com.woorifis.vroom.kb.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.woorifis.vroom.kb.domain.Car;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest
class KBCarRepositoryTest {

	@Autowired
	KBCarRepository repository;

	@Test
	void saveCar() {
		Car car = new Car();
		Car savedCar = repository.save(car);
		log.info("savedCar={}", savedCar);
	}

}
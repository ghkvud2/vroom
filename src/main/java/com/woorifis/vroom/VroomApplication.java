package com.woorifis.vroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.woorifis.vroom.kb.controller.KBCarController;

import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
public class VroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(VroomApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {

		return new ApplicationRunner() {

			@Autowired
			private KBCarController controller;

			@Override
			public void run(ApplicationArguments args) throws Exception {
				log.info("==================================================");
				controller.run();
				log.info("==================================================");
			}
		};
	}
}

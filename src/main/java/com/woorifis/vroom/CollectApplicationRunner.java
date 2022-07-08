package com.woorifis.vroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.woorifis.vroom.controller.CollectController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"local", "prod"})
public class CollectApplicationRunner implements ApplicationRunner {

	private final CollectController collectController;

	@Value("${parameter.default.baseUrl}")
	private String baseUrl;

	@Value("${parameter.default.startPage}")
	private int defaultStartPage;

	@Value("${parameter.default.endPage}")
	private int defaultEndPage;

	//mvn spring-boot:run -Dspring-boot.run.profiles=prod
	//mvn clean test -Dspring.profiles.active=test
	//java -jar -Dspring.profiles.active=prod vroom-1.0.jar --startPage=1 --endPage=2
	//java -jar -Dspring.profiles.active=prod vroom-1.0.jar

	@Override
	public void run(ApplicationArguments args) {

		String startPageOfArgs = args.getOptionValues("startPage").get(0);
		int startPage = startPageOfArgs == null ? defaultStartPage : Integer.valueOf(startPageOfArgs);

		String endPageOfArgs = args.getOptionValues("endPage").get(0);
		int endPage = endPageOfArgs == null ? defaultEndPage : Integer.valueOf(endPageOfArgs);

		// int startPage = defaultStartPage;
		// int endPage = defaultEndPage;

		log.info("[Collect Range {} ~ {} (page)]", startPage, endPage);
		collectController.run(baseUrl, startPage, endPage);
		log.info("[Collect end]");
	}
}

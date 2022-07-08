package com.woorifis.vroom.config;

import static org.junit.jupiter.api.Assertions.*;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class JasyptApplicationTests {

	@Value("${jasypt.encryptor.password}")
	private String password;

	@Test
	void jasypt() {
		String url = "a";
		String username = "b";
		String password = "c";

		log.info("url={}", jasyptEncoding(url));
		log.info("username={}", jasyptEncoding(username));
		log.info("password={}", jasyptEncoding(password));
	}

	public String jasyptEncoding(String value) {

		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		pbeEnc.setPassword(password);
		return pbeEnc.encrypt(value);
	}

}
package com.woorifis.vroom.service;


import com.woorifis.vroom.kb.repository.KBCarRepository;
import com.woorifis.vroom.kb.service.KBCarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ServiceTest {


    private String targetUrl;
    private WebDriver driver;
    private KBCarService service;

    @BeforeEach
    void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    @DisplayName("상세 페이지에서 차량 정보 모두 스크래핑")
    void testCreateCar() {

        targetUrl = "https://www.kbchachacha.com/public/car/detail.kbc?carSeq=22930656";
        driver.get(targetUrl);



    }


}

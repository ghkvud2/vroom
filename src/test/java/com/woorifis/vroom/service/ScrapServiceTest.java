package com.woorifis.vroom.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ScrapServiceTest {
	// static ChromeDriver driver;
	// static CollectService service;
	//
	// @Value("${}")
	// private String dirverPath;
	//
	// @BeforeAll
	// static void setUp() {
	//     System.setProperty("webdriver.chrome.driver", "C:\\Users\\LG\\Desktop\\유틸리티\\chromedriver.exe");
	//     driver = new ChromeDriver();
	//     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//     service = new CollectService(null);
	// }
	//
	//     @Test
	//     @DisplayName("옵션")
	//     void testCarOptions() {
	//
	//         String url = "https://www.kbchachacha.com/public/car/detail.kbc?carSeq=22930656";
	//         driver.get(url);
	//
	//         List<CarOptionBefore> carOptionBefores = service.getOptionList(driver.findElement(By.tagName("body")));
	//         carOptionBefores.stream().forEach(this::printCarOption);
	//
	//     }
	//
	//     void printCarOption(CarOptionBefore carOptionBefore) {
	//         log.info("[id={}, code={}, option={}", carOptionBefore.getId(), carOptionBefore.getCode(), carOptionBefore.getOptionName());
	//     }
	//
	//     @Test
	//     @DisplayName("페이징 처리")
	//     void testPaging() throws InterruptedException {
	// //        driver.get("https://www.kbchachacha.com/public/search/main.kbc#!?countryOrder=1&page=1");
	// //
	// //        List<WebElement> paginations = driver.findElements(By.xpath("//div[contains(@class, 'paginate')]/a[not(contains(@class,'prev'))]"));
	// //
	// //        for (int i = 1; i <= paginations.size(); i++) {
	// //            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	// //            webDriverWait.until(ExpectedConditions.visibilityOf(
	// //                    driver.findElement(By.xpath("//div[contains(@class, 'paginate')]/a[" + i + "]"))));
	// //
	// //            paginations.get(i).click();
	// //
	// //        }
	//     }
	//
	//     @Test
	//     @DisplayName("차량 전체 정보 확인")
	//     void testCarTotalInfo() {
	//         String url = "https://www.kbchachacha.com/public/car/detail.kbc?carSeq=22930656";
	//         driver.get(url);
	//
	//         Car car = service.createCar(driver.findElement(By.tagName("body")));
	//
	// //        Assertions.assertEquals("한국GM 더 넥스트 스파크 LTZ C-TECH", car.getTitleInfo().getKind());
	//         Assertions.assertEquals("870만원", car.getTitleInfo().getPrice());
	//         Assertions.assertEquals("서울", car.getTitleInfo().getRegion());
	//         Assertions.assertEquals("13나0714", car.getBasicInfo().getLicense());
	//         Assertions.assertEquals("16년05월(16년형)", car.getBasicInfo().getYear());
	//         Assertions.assertEquals("35,100km", car.getBasicInfo().getMileage());
	//         Assertions.assertEquals("가솔린", car.getBasicInfo().getFuel());
	//         Assertions.assertEquals("오토", car.getBasicInfo().getGear());
	//         Assertions.assertEquals("정보없음", car.getBasicInfo().getFuelEfficiency());
	//         Assertions.assertEquals("경차", car.getBasicInfo().getModel());
	//         Assertions.assertEquals("999cc", car.getBasicInfo().getDisplacement());
	//         Assertions.assertEquals("흰색", car.getBasicInfo().getColor());
	//         Assertions.assertEquals("없음", car.getBasicInfo().getIsTaxNotPaid());
	//         Assertions.assertEquals("없음", car.getBasicInfo().getIsSeized());
	//         Assertions.assertEquals("없음", car.getBasicInfo().getIsMortgaged());
	//         Assertions.assertEquals("없음", car.getHistoryInfo().getIsTotalLoss());
	//         Assertions.assertEquals("없음", car.getHistoryInfo().getIsFlooded());
	//         Assertions.assertEquals("없음", car.getHistoryInfo().getIsUsageChanged());
	//         Assertions.assertEquals("4회", car.getHistoryInfo().getOwnerChangeCnt());
	//     }
	//
	//     @Test
	//     @DisplayName("국산, 수입 탭 이동")
	//     void testUrlForward() {
	//         String countryOrder = "1"; //1=국산, 2=수입
	//         String url = "https://www.kbchachacha.com/public/search/main.kbc#!?countryOrder=1&page=1";
	//         driver.get(url);
	//         try {
	//             Thread.sleep(3000);
	//         } catch (InterruptedException e) {
	//             e.printStackTrace();
	//         }
	//         url = "https://www.kbchachacha.com/public/search/main.kbc#!?countryOrder=2&page=1";
	//         driver.get(url);
	//     }
	//
	//     @Test
	//     void testMoveToCarDetailsInfo() {
	//         String url = "https://www.kbchachacha.com/public/search/main.kbc#!?countryOrder=1&page=1";
	//         driver.get(url);
	//
	//         List<WebElement> lists = driver.findElements(By.cssSelector(".generalRegist .list-in .area"));
	//
	//         for (WebElement e : lists) {
	//             e.click();
	//             ArrayList<String> newTb = new ArrayList<>(driver.getWindowHandles());
	//             driver.switchTo().window(newTb.get(1));
	//             Car car = service.createCar(driver.findElement(By.tagName("body")));
	//             try {
	//                 Thread.sleep(1000);
	//             } catch (InterruptedException ex) {
	//                 ex.printStackTrace();
	//             }
	//             driver.close();
	//             driver.switchTo().window(newTb.get(0));
	//             log.info("car={}", car);
	//             break;
	//         }
	//     }
}
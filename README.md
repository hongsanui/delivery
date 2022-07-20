# Spring 심화 주차 개인 과제

<aside>
🏁 **Goal:  " 요구사항에 맞춰 배달서비스 API를 구성해보기"**

</aside>

- 학습 과제를 끝내고 나면 할 수 있어요!
    1. UI 없이 백엔드 서버 API 구축하는 방법을 익힐 수 있어요.
    2. Spring 3계층 (Controller, Service, Repository) 역할 별로 구분해서 구현할 수 있어요. 특히, Service 에서 복잡한 비즈니스 로직을 처리할 수 있어요.
    3. Spring Data JPA 를 사용하여 요구사항에 맞춰 DB 를 이용할 수 있어요. 
    4. 주어진 테스트 코드를 통해 과제에 요구되는 사항들을 검증하는 능력을 키울 수 있어요.
    
    "매운 맛, 극강의 매운 맛 과제" 를 끝내면 할 수 있어요.
    
    1. Spring Security 를 이용해 회원 역할 별 (사장님, 이용자) 로 사용 가능한 API 를 관리할 수 있어요.
    2. 트랜잭션 단위를 비즈니스 요구사항에 맞춰 적절히 나눌 수 있어요.
    3. 예외처리를 통해 상황에 맞는 적절한 에러코드를 선언할 수 있어요.
    4. 실무에서 진행되는 다양하고 복잡한 요건 등을 충족하기 위해  API 설계, DB 설계에 많은 고민이 필요함을 느낄 수 있어요.
    

❗ 우리는 이번 주차에는 API만 개발하면 됩니다! 프론트엔드 영역은 고민하실 필요가 없어요!

<aside>
🚩 **Requirement:  과제에 요구되는 사항이에요**

</aside>

배달앱 한 번씩은 사용해 보셨죠? 여러분은 이번 과제를 통해 배달앱의 백엔드 서버 개발자가 되어 배달앱 서버에 필요한 핵심 API 를 구현해 볼 예정입니다.

- `과제 요구 사항`을 모두 완수해야 합니다!
    
    <aside>
    ✅ 과제 요구 사항
    
    </aside>
    
    스프링 서버를 통해  아래 요구사항에 맞춰 배달앱 API 를 구현합니다. 크게 3개의 요구사항으로 나뉘어져 있고, 제공되는 테스트 코드가 모두 성공적으로 작동하게 되면 과제 완료입니다! 
    
    - 테스트 코드 실행을 위해 build.gradle 파일에 아래 내용 추가
        
        ```json
        dependencies {
        		// Lombok 라이브러리
            compileOnly 'org.projectlombok:lombok'
        		// 스프링 부트 라이브러리
            implementation 'org.springframework.boot:spring-boot-starter-web'
        		// 스프링 부트 테스트를 위한 라이브러리
            testImplementation 'org.springframework.boot:spring-boot-starter-test'
        }
        ```
        
    
    **[요구사항]**
    
    1. 음식점 등록 및 조회
    2. 음식 등록 및 메뉴판 조회
    3. 주문하기
    
    UI 개발 없이 백엔드 서버를 개발해야 하기 때문에, 각 API 에서 제공되는 데이터들이 어떻게 사용될지는 아래 배달앱 샘플 UI 를 참고합니다. 
    
    <aside>
    🚨 주의! 샘플 UI 는 이해를 돕기 위한 것으로 실제 요구사항과 다를 수 있습니다. 반드시 과제 요구사항 내용에 맞춰 구현 해 주세요.
    
    </aside>
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d02b62b2-a4b1-4225-985b-06c4566263b9/Untitled.png)
    
    1. 음식점 등록 및 조회
        - 음식점 정보 입력받아 등록
            1. 음식점 이름 (name)
            2. 최소주문 가격 (minOrderPrice)
                1. 허용값: 1,000원 ~ 100,000원 입력
                2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
                3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
            3. 기본 배달비 (deliveryFee)
                1. 허용값: 0원 ~ 10,000원 (예. 11,000원 입력 시 에러발생.)
                2. 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능) 
                
        - 음식점 조회
            - 등록된 모든 음식점 정보 조회 가능
                1. 등록 시 입력한 음식점 정보 (name, minOrderPrice, deliveryFee)
                2. DB 테이블 ID (id)  
                
        
        [API 명세서](https://www.notion.so/e3bdc035a63347ba9deb13b572e3ac31)
        
    2. 음식 등록 및 메뉴판 조회
        - 음식점 ID 및 음식 정보 입력받아 등록
            1. 음식점 ID (restaurantId)
                1. 음식점 DB 테이블 ID
            2. 음식명 (name)
                1. 같은 음식점 내에서는 음식 이름이 중복될 수 없음 (예. '자담치킨 강남점'에 '후라이드치킨' 이 이미 등록되어 있다면 중복하여 등록할 수 없지만, 다른 음식점인 '맘스터치 강남점'에는 '후라이드치킨' 을 등록 가능)
            3. 가격 (price)
                1. 허용값: 100원 ~ 1,000,000원
                2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원 입력 가능)
                3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
                
        - 메뉴판 조회
            - 하나의 음식점에 등록된 모든 음식 정보 조회
                1. 등록 시 입력한 음식 정보 (name, price)
                2. DB 테이블 ID (id)
                
        
        [API 명세서](https://www.notion.so/c55eb48e6b3c4a9a99f9d9308cb75944)
        
    3. 주문 요청하기 및 주문 조회
        - 주문 요청 시 배달 음식점 및 음식 정보 입력받음
            1. 음식점 ID (restaurantId)
            2. 음식 주문 정보 (foods)
                1. 음식 ID (id)
                2. 음식을 주문할 수량 (quantity)
                    1. 허용값: 1 ~ 100
                    2. 허용값이 아니면 에러 발생시킴
                    
        - 주문 요청에 대한 응답으로 다음 정보를 포함시킴
            1. 주문 음식점 이름 (restaurantName)
            2. 주문 음식 정보 (foods)
                - 주문 음식명 (name)
                - 주문 수량 (quantity)
                - 주문 음식의 가격 (price)
                    - 계산방법
                        - 주문 음식 1개의 가격 * 주문 수량
            3. 배달비 (deliveryFee)
                - 주문 음식점의 기본 배달비
            4. 최종 결제 금액 (totalPrice)
                - 계산방법
                    - 주문 음식 가격들의 총 합 + 배달비
                - "주문 음식 가격들의 총 합" 이 주문 음식점의 "최소주문 가격" 을 넘지 않을 시 에러 발생시킴
                
        - 주문 조회
            - 그동안 성공한 모든 주문 요청을 조회 가능
        
        [API 명세서](https://www.notion.so/074db7dd54514f6c802e841fbec94a1e)
        
    
- `과제 요구 사항` 검증을 위한 테스트 코드
    
    아래 제공되는 `3개의 테스트 코드` 를 과제 프로젝트의 test 패키지에 복사 후  모두 성공적으로 작동하게 하면 과제 완료입니다! 
    
    - 1. `음식점 등록 및 조회` 테스트코드
        
        ```java
        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.Setter;
        import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.http.*;
        
        import java.util.ArrayList;
        import java.util.List;
        
        import static org.junit.jupiter.api.Assertions.*;
        
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        class RestaurantIntegrationTest {
            @Autowired
            private TestRestTemplate restTemplate;
        
            private HttpHeaders headers;
            private ObjectMapper mapper = new ObjectMapper();
        
            private final List<RestaurantDto> registeredRestaurants = new ArrayList<>();
        
            @BeforeEach
            public void setup() {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
            }
        
            @Nested
            @DisplayName("음식점 3개 등록 및 조회")
            class RegisterRestaurants {
                @Test
                @Order(1)
                @DisplayName("음식점1 등록")
                void test1() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("쉐이크쉑 청담점")
                            .minOrderPrice(1_000)
                            .deliveryFee(0)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
        
                    RestaurantDto restaurantResponse = response.getBody();
                    assertNotNull(restaurantResponse);
                    assertTrue(restaurantResponse.id > 0);
                    assertEquals(restaurantRequest.name, restaurantResponse.name);
                    assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
                    assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
        
                    // 음식점 등록 성공 시, registeredRestaurants 에 추가
                    registeredRestaurants.add(restaurantResponse);
                }
        
                @Test
                @Order(2)
                @DisplayName("음식점2 등록")
                void test2() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("자담치킨 강남점")
                            .minOrderPrice(100_000)
                            .deliveryFee(10_000)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
        
                    RestaurantDto restaurantResponse = response.getBody();
                    assertNotNull(restaurantResponse);
                    assertTrue(restaurantResponse.id > 0);
                    assertEquals(restaurantRequest.name, restaurantResponse.name);
                    assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
                    assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
        
                    // 음식점 등록 성공 시, registeredRestaurants 에 추가
                    registeredRestaurants.add(restaurantResponse);
                }
        
                @Test
                @Order(3)
                @DisplayName("음식점3 등록")
                void test3() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("마라하오 위례점")
                            .minOrderPrice(100000)
                            .deliveryFee(10000)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
        
                    RestaurantDto restaurantResponse = response.getBody();
                    assertNotNull(restaurantResponse);
                    assertTrue(restaurantResponse.id > 0);
                    assertEquals(restaurantRequest.name, restaurantResponse.name);
                    assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
                    assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
        
                    // 음식점 등록 성공 시, registeredRestaurants 에 추가
                    registeredRestaurants.add(restaurantResponse);
                }
        
                @Test
                @Order(4)
                @DisplayName("등록된 모든 음식점 조회")
                void test4() {
                    // when
                    ResponseEntity<RestaurantDto[]> response = restTemplate.getForEntity(
                            "/restaurants",
                            RestaurantDto[].class
                    );
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                    RestaurantDto[] responseRestaurants = response.getBody();
                    assertNotNull(responseRestaurants);
                    assertEquals(registeredRestaurants.size(), responseRestaurants.length);
                    for (RestaurantDto responseRestaurant : responseRestaurants) {
                        RestaurantDto registerRestaurant = registeredRestaurants.stream()
                                .filter(restaurant -> responseRestaurant.getId().equals(restaurant.getId()))
                                .findAny()
                                .orElse(null);
        
                        assertNotNull(registerRestaurant);
                        assertEquals(registerRestaurant.getName(), responseRestaurant.getName());
                        assertEquals(registerRestaurant.getDeliveryFee(), responseRestaurant.getDeliveryFee());
                        assertEquals(registerRestaurant.getMinOrderPrice(), responseRestaurant.getMinOrderPrice());
                    }
                }
            }
        
            @Nested
            @DisplayName("최소주문 가격")
            class MinOrderPrice {
                @Test
                @DisplayName("1,000원 미만 에러")
                void test1() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("쉐이크쉑 청담점")
                            .minOrderPrice(500)
                            .deliveryFee(1000)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @DisplayName("100,000원 초과 에러")
                void test2() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("쉐이크쉑 청담점")
                            .minOrderPrice(100100)
                            .deliveryFee(1000)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @DisplayName("100원 단위로 입력 안 됨 에러")
                void test3() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("쉐이크쉑 청담점")
                            .minOrderPrice(2220)
                            .deliveryFee(1000)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
            }
        
            @Nested
            @DisplayName("기본 배달비")
            class DeliveryFee {
                @Test
                @DisplayName("0원 미만 에러")
                void test2() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("쉐이크쉑 청담점")
                            .minOrderPrice(5000)
                            .deliveryFee(-500)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @DisplayName("10,000원 초과 에러")
                void test3() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("쉐이크쉑 청담점")
                            .minOrderPrice(5000)
                            .deliveryFee(20000)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @DisplayName("500원 단위로 입력 안 됨 에러")
                void test4() throws JsonProcessingException {
                    // given
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("쉐이크쉑 청담점")
                            .minOrderPrice(5000)
                            .deliveryFee(2200)
                            .build();
        
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
            }
        
            @Getter
            @Setter
            @Builder
            static class RestaurantDto {
                private Long id;
                private String name;
                private int minOrderPrice;
                private int deliveryFee;
            }
        }
        ```
        
    - 2. `음식 등록 및 메뉴판 조회` 테스트코드
        
        ```java
        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.Setter;
        import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.http.*;
        
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        
        import static org.junit.jupiter.api.Assertions.*;
        
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        class FoodIntegrationTest {
            @Autowired
            private TestRestTemplate restTemplate;
        
            private HttpHeaders headers;
            private ObjectMapper mapper = new ObjectMapper();
        
            private RestaurantDto registeredRestaurant;
        
            private FoodDto food1 = FoodDto.builder()
                    .id(null)
                    .name("쉑버거 더블")
                    .price(10900)
                    .build();
        
            private FoodDto food2 = FoodDto.builder()
                    .id(null)
                    .name("치즈 감자튀김")
                    .price(4900)
                    .build();
        
            private FoodDto food3 = FoodDto.builder()
                    .id(null)
                    .name("쉐이크")
                    .price(5900)
                    .build();
        
            private FoodDto food4 = FoodDto.builder()
                    .id(null)
                    .name("스트로베리베리")
                    .price(11400)
                    .build();
        
            @BeforeEach
            public void setup() {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
            }
        
            @Test
            @Order(1)
            @DisplayName("음식점1 등록")
            void test1() throws JsonProcessingException {
                // given
                RestaurantDto restaurantRequest = RestaurantDto.builder()
                        .id(null)
                        .name("쉐이크쉑 청담점")
                        .minOrderPrice(5000)
                        .deliveryFee(1000)
                        .build();
        
                String requestBody = mapper.writeValueAsString(restaurantRequest);
                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                // when
                ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                        "/restaurant/register",
                        request,
                        RestaurantDto.class);
        
                // then
                assertEquals(HttpStatus.OK, response.getStatusCode());
        
                RestaurantDto restaurantResponse = response.getBody();
                assertNotNull(restaurantResponse);
                assertTrue(restaurantResponse.id > 0);
                assertEquals(restaurantRequest.name, restaurantResponse.name);
                assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
                assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
        
                // 음식점 등록 성공 시, registeredRestaurant 에 할당
                registeredRestaurant = restaurantResponse;
            }
        
            @Nested
            @DisplayName("음식점에 음식 3개 등록 및 메뉴판 조회")
            class RegisterRestaurants {
                @Test
                @Order(1)
                @DisplayName("음식 1개 등록")
                void test1() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
                    // 음식2 추가
                    foodsRequest.add(food2);
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                    assertNull(response.getBody());
                }
        
                @Test
                @Order(2)
                @DisplayName("음식 2개 등록")
                void test2() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
        
                    // 음식3 추가
                    foodsRequest.add(food3);
        
                    // 음식4 추가
                    foodsRequest.add(food4);
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                    assertNull(response.getBody());
                }
        
                @Test
                @Order(3)
                @DisplayName("음식점 메뉴판 조회")
                void test3() throws JsonProcessingException {
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<FoodDto[]> response = restTemplate.getForEntity(
                            "/restaurant/" + restaurantId + "/foods",
                            FoodDto[].class);
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                    FoodDto[] foodsResponse = response.getBody();
                    assertNotNull(foodsResponse);
                    assertEquals(4, foodsResponse.length);
                    // 음식 1 확인
                    FoodDto food1Response = Arrays.stream(foodsResponse)
                            .filter(food -> food1.getName().equals(food.getName()))
                            .findAny()
                            .orElse(null);
                    assertNotNull(food1Response);
                    assertNotNull(food1Response.getId());
                    assertEquals(food1.getName(), food1Response.getName());
                    assertEquals(food1.getPrice(), food1Response.getPrice());
        
                    // 음식 2 확인
                    FoodDto food2Response = Arrays.stream(foodsResponse)
                            .filter(food -> food2.getName().equals(food.getName()))
                            .findAny()
                            .orElse(null);
                    assertNotNull(food2Response);
                    assertNotNull(food2Response.getId());
                    assertEquals(food2.getName(), food2Response.getName());
                    assertEquals(food2.getPrice(), food2Response.getPrice());
        
                    // 음식 3 확인
                    FoodDto food3Response = Arrays.stream(foodsResponse)
                            .filter(food -> food3.getName().equals(food.getName()))
                            .findAny()
                            .orElse(null);
                    assertNotNull(food3Response);
                    assertNotNull(food3Response.getId());
                    assertEquals(food3.getName(), food3Response.getName());
                    assertEquals(food3.getPrice(), food3Response.getPrice());
        
                    // 음식 4 확인
                    FoodDto food4Response = Arrays.stream(foodsResponse)
                            .filter(food -> food4.getName().equals(food.getName()))
                            .findAny()
                            .orElse(null);
                    assertNotNull(food4Response);
                    assertNotNull(food4Response.getId());
                    assertEquals(food4.getName(), food4Response.getName());
                    assertEquals(food4.getPrice(), food4Response.getPrice());
                }
            }
        
            @Nested
            @DisplayName("음식명")
            class FoodName {
                @Test
                @Order(1)
                @DisplayName("음식 1개 등록")
                void test1() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
                    // 음식1 추가
                    foodsRequest.add(food1);
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                    assertNull(response.getBody());
                }
        
                @Test
                @Order(2)
                @DisplayName("기존 저장된 음식명과 중복")
                void test2() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
                    // 음식1 추가
                    foodsRequest.add(food1);
                    // 음식2 추가
                    foodsRequest.add(food2);
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @Order(3)
                @DisplayName("입력된 음식명에 중복 에러")
                void test3() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
                    // 음식2 추가 (음식명: "치즈 감자튀김")
                    foodsRequest.add(food2);
        						// 음식3 추가 (음식명: "쉐이크")
                    foodsRequest.add(food3);
        						// 음식2 중복 추가 (음식명: "치즈 감자튀김")
                    foodsRequest.add(food2);
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @Order(4)
                @DisplayName("음식점 2에 음식명 1에 등록되어 있는 음식명 입력 가능")
                void test4() throws JsonProcessingException {
                    // given
                    // 음식점2 추가
                    RestaurantDto restaurantRequest = RestaurantDto.builder()
                            .id(null)
                            .name("베스킨라빈스 이태원점")
                            .minOrderPrice(13000)
                            .deliveryFee(5000)
                            .build();
                    String requestBody = mapper.writeValueAsString(restaurantRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
                    ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                            "/restaurant/register",
                            request,
                            RestaurantDto.class);
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                    RestaurantDto restaurantDto2 = response.getBody();
        
                    //  추가 시도
                    List<FoodDto> foodsRequest = new ArrayList<>();
                    // 음식점1에 등록되어 있는 음식1 을 음식점2에 추가
                    foodsRequest.add(food1);
        
                    String foodRequestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> foodRequest = new HttpEntity<>(foodRequestBody, headers);
        
                    // when
                    Long restaurant2Id = restaurantDto2.id;
                    ResponseEntity<Object> foodResponse = restTemplate.postForEntity(
                            "/restaurant/" + restaurant2Id + "/food/register",
                            foodRequest,
                            Object.class);
        
                    // then
                    assertEquals(HttpStatus.OK, foodResponse.getStatusCode());
                }
            }
        
            @Nested
            @DisplayName("음식 가격")
            class FoodPrice {
                @Test
                @DisplayName("100원 미만 에러")
                void test1() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
        
                    foodsRequest.add(
                            FoodDto.builder()
                                    .id(null)
                                    .name("치즈 감자튀김")
                                    .price(0)
                                    .build()
                    );
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @DisplayName("1,000,000원 초과 에러")
                void test2() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
        
                    foodsRequest.add(
                            FoodDto.builder()
                                    .id(null)
                                    .name("치즈 감자튀김")
                                    .price(1_000_100)
                                    .build()
                    );
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
        
                @Test
                @DisplayName("100원 단위로 입력 안 됨 에러")
                void test3() throws JsonProcessingException {
                    // given
                    List<FoodDto> foodsRequest = new ArrayList<>();
        
                    foodsRequest.add(
                            FoodDto.builder()
                                    .id(null)
                                    .name("치즈 감자튀김")
                                    .price(770)
                                    .build()
                    );
        
                    String requestBody = mapper.writeValueAsString(foodsRequest);
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                    // when
                    Long restaurantId = registeredRestaurant.id;
                    ResponseEntity<Object> response = restTemplate.postForEntity(
                            "/restaurant/" + restaurantId + "/food/register",
                            request,
                            Object.class);
        
                    // then
                    assertTrue(
                            response.getStatusCode() == HttpStatus.BAD_REQUEST
                                    || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
            }
        
            @Getter
            @Setter
            @Builder
            static class RestaurantDto {
                private Long id;
                private String name;
                private int minOrderPrice;
                private int deliveryFee;
            }
        
            @Getter
            @Setter
            @Builder
            static class FoodDto {
                private Long id;
                private String name;
                private int price;
            }
        }
        ```
        
    - 3. `주문하기 및 조회` 테스트코드
        
        ```java
        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.Setter;
        import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.http.*;
        
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        
        import static org.junit.jupiter.api.Assertions.*;
        
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        class OrderIntegrationTest {
            @Autowired
            private TestRestTemplate restTemplate;
        
            private HttpHeaders headers;
            private ObjectMapper mapper = new ObjectMapper();
        
            private RestaurantDto registeredRestaurant;
        
            private FoodDto food1 = FoodDto.builder()
                    .id(null)
                    .name("쉑버거 더블")
                    .price(10900)
                    .build();
        
            private FoodDto food2 = FoodDto.builder()
                    .id(null)
                    .name("치즈 감자튀김")
                    .price(4900)
                    .build();
        
            private FoodDto food3 = FoodDto.builder()
                    .id(null)
                    .name("쉐이크")
                    .price(5900)
                    .build();
        
            @BeforeEach
            public void setup() {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
            }
        
            @Test
            @Order(1)
            @DisplayName("음식점1 등록")
            void test1() throws JsonProcessingException {
                // given
                RestaurantDto restaurantRequest = RestaurantDto.builder()
                        .id(null)
                        .name("쉐이크쉑 청담점")
                        .minOrderPrice(5000)
                        .deliveryFee(2000)
                        .build();
        
                String requestBody = mapper.writeValueAsString(restaurantRequest);
                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                // when
                ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                        "/restaurant/register",
                        request,
                        RestaurantDto.class);
        
                // then
                assertEquals(HttpStatus.OK, response.getStatusCode());
        
                RestaurantDto restaurantResponse = response.getBody();
                assertNotNull(restaurantResponse);
                assertTrue(restaurantResponse.id > 0);
                assertEquals(restaurantRequest.name, restaurantResponse.name);
                assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
                assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
        
                // 음식점 등록 성공 시, registeredRestaurant 에 할당
                registeredRestaurant = restaurantResponse;
            }
        
            @Test
            @Order(2)
            @DisplayName("음식점에 음식 3개 등록")
            void test2() throws JsonProcessingException {
                // given
                List<FoodDto> foodsRequest = new ArrayList<>();
                foodsRequest.add(food1);
                foodsRequest.add(food2);
                foodsRequest.add(food3);
        
                String requestBody = mapper.writeValueAsString(foodsRequest);
                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                // when
                Long restaurantId = registeredRestaurant.id;
                ResponseEntity<Object> response = restTemplate.postForEntity(
                        "/restaurant/" + restaurantId + "/food/register",
                        request,
                        Object.class);
        
                // then
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertNull(response.getBody());
            }
        
            @Test
            @Order(3)
            @DisplayName("음식점 메뉴판 조회")
            void test3() {
                // when
                Long restaurantId = registeredRestaurant.id;
                ResponseEntity<FoodIntegrationTest.FoodDto[]> response = restTemplate.getForEntity(
                        "/restaurant/" + restaurantId + "/foods",
                        FoodIntegrationTest.FoodDto[].class);
        
                // then
                assertEquals(HttpStatus.OK, response.getStatusCode());
                FoodIntegrationTest.FoodDto[] foodsResponse = response.getBody();
                assertNotNull(foodsResponse);
                assertEquals(3, foodsResponse.length);
                // 음식 1 확인
                FoodIntegrationTest.FoodDto food1Response = Arrays.stream(foodsResponse)
                        .filter(food -> food1.getName().equals(food.getName()))
                        .findAny()
                        .orElse(null);
                assertNotNull(food1Response);
                assertNotNull(food1Response.getId());
                assertEquals(food1.getName(), food1Response.getName());
                assertEquals(food1.getPrice(), food1Response.getPrice());
                food1.id = food1Response.getId();
        
                // 음식 2 확인
                FoodIntegrationTest.FoodDto food2Response = Arrays.stream(foodsResponse)
                        .filter(food -> food2.getName().equals(food.getName()))
                        .findAny()
                        .orElse(null);
                assertNotNull(food2Response);
                assertNotNull(food2Response.getId());
                assertEquals(food2.getName(), food2Response.getName());
                assertEquals(food2.getPrice(), food2Response.getPrice());
                food2.id = food2Response.getId();
        
                // 음식 3 확인
                FoodIntegrationTest.FoodDto food3Response = Arrays.stream(foodsResponse)
                        .filter(food -> food3.getName().equals(food.getName()))
                        .findAny()
                        .orElse(null);
                assertNotNull(food3Response);
                assertNotNull(food3Response.getId());
                assertEquals(food3.getName(), food3Response.getName());
                assertEquals(food3.getPrice(), food3Response.getPrice());
                food3.id = food3Response.getId();
            }
        
            @Test
            @Order(4)
            @DisplayName("주문하기")
            void test4() throws JsonProcessingException {
                // given
                Long restaurantId = registeredRestaurant.id;
        
                FoodOrderRequestDto foodOrderRequest1 = FoodOrderRequestDto.builder()
                        .id(food1.id)
                        .quantity(1)
                        .build();
        
                FoodOrderRequestDto foodOrderRequest2 = FoodOrderRequestDto.builder()
                        .id(food2.id)
                        .quantity(2)
                        .build();
        
                FoodOrderRequestDto foodOrderRequest3 = FoodOrderRequestDto.builder()
                        .id(food3.id)
                        .quantity(3)
                        .build();
        
                List<FoodOrderRequestDto> foodOrderRequestDtos = new ArrayList<>();
                foodOrderRequestDtos.add(foodOrderRequest1);
                foodOrderRequestDtos.add(foodOrderRequest2);
                foodOrderRequestDtos.add(foodOrderRequest3);
        
                OrderRequestDto orderRequest = OrderRequestDto.builder()
                        .restaurantId(restaurantId)
                        .foods(foodOrderRequestDtos)
                        .build();
        
                String requestBody = mapper.writeValueAsString(orderRequest);
                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                // when
                ResponseEntity<OrderDto> response = restTemplate.postForEntity(
                        "/order/request",
                        request,
                        OrderDto.class);
        
                // then
                assertEquals(HttpStatus.OK, response.getStatusCode());
                OrderDto orderDto = response.getBody();
                assertNotNull(orderDto);
                // 음식점 이름
                assertEquals(registeredRestaurant.name, orderDto.restaurantName);
        
                // 음식 주문 확인
                assertEquals(3, orderDto.foods.size());
                // 음식1 주문 확인
                FoodOrderDto foodOrder1 = orderDto.foods.stream()
                        .filter(foodOrderDto -> foodOrderDto.name.equals(food1.getName()))
                        .findAny().orElse(null);
                assertNotNull(foodOrder1);
                assertEquals(food1.name, foodOrder1.name);
                assertEquals(foodOrder1.quantity, foodOrder1.quantity);
                assertEquals(10900, foodOrder1.price);
                // 음식2 주문 확인
                FoodOrderDto foodOrder2 = orderDto.foods.stream()
                        .filter(foodOrderDto -> foodOrderDto.name.equals(food2.getName()))
                        .findAny().orElse(null);
                assertNotNull(foodOrder2);
                assertEquals(food2.name, foodOrder2.name);
                assertEquals(foodOrder2.quantity, foodOrder2.quantity);
                assertEquals(9800, foodOrder2.price);
                // 음식3 주문 확인
                FoodOrderDto foodOrder3 = orderDto.foods.stream()
                        .filter(foodOrderDto -> foodOrderDto.name.equals(food3.getName()))
                        .findAny().orElse(null);
                assertNotNull(foodOrder3);
                assertEquals(food3.name, foodOrder3.name);
                assertEquals(foodOrder3.quantity, foodOrder3.quantity);
                assertEquals(17700, foodOrder3.price);
        
                // 배달비 확인
                assertEquals(2000, orderDto.deliveryFee);
        
                // 총 결제 금액 확인
                assertEquals(40400, orderDto.totalPrice);
            }
        
            @Test
            @Order(5)
            @DisplayName("주문하기 - 음식 주문 수량 1 미만 에러")
            void test5() throws JsonProcessingException {
                // given
                Long restaurantId = registeredRestaurant.id;
        
                FoodOrderRequestDto foodOrderRequest1 = FoodOrderRequestDto.builder()
                        .id(food1.id)
                        .quantity(0)
                        .build();
        
                List<FoodOrderRequestDto> foodOrderRequestDtos = new ArrayList<>();
                foodOrderRequestDtos.add(foodOrderRequest1);
        
                OrderRequestDto orderRequest = OrderRequestDto.builder()
                        .restaurantId(restaurantId)
                        .foods(foodOrderRequestDtos)
                        .build();
        
                String requestBody = mapper.writeValueAsString(orderRequest);
                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                // when
                ResponseEntity<OrderDto> response = restTemplate.postForEntity(
                        "/order/request",
                        request,
                        OrderDto.class);
        
                // then
                assertTrue(
                        response.getStatusCode() == HttpStatus.BAD_REQUEST
                                || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        
            @Test
            @Order(6)
            @DisplayName("주문하기 - 음식 주문 수량 100 초과 에러")
            void test6() throws JsonProcessingException {
                // given
                Long restaurantId = registeredRestaurant.id;
        
                FoodOrderRequestDto foodOrderRequest1 = FoodOrderRequestDto.builder()
                        .id(food1.id)
                        .quantity(101)
                        .build();
        
                List<FoodOrderRequestDto> foodOrderRequestDtos = new ArrayList<>();
                foodOrderRequestDtos.add(foodOrderRequest1);
        
                OrderRequestDto orderRequest = OrderRequestDto.builder()
                        .restaurantId(restaurantId)
                        .foods(foodOrderRequestDtos)
                        .build();
        
                String requestBody = mapper.writeValueAsString(orderRequest);
                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                // when
                ResponseEntity<OrderDto> response = restTemplate.postForEntity(
                        "/order/request",
                        request,
                        OrderDto.class);
        
                // then
                assertTrue(
                        response.getStatusCode() == HttpStatus.BAD_REQUEST
                                || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        
            @Test
            @Order(7)
            @DisplayName("주문하기 - 음식점의 최소주문 가격 미만 시 에러")
            void test7() throws JsonProcessingException {
                // given
                Long restaurantId = registeredRestaurant.id;
        
                FoodOrderRequestDto foodOrderRequest1 = FoodOrderRequestDto.builder()
                        .id(food2.id)
                        .quantity(1)
                        .build();
        
                List<FoodOrderRequestDto> foodOrderRequestDtos = new ArrayList<>();
                foodOrderRequestDtos.add(foodOrderRequest1);
        
                OrderRequestDto orderRequest = OrderRequestDto.builder()
                        .restaurantId(restaurantId)
                        .foods(foodOrderRequestDtos)
                        .build();
        
                String requestBody = mapper.writeValueAsString(orderRequest);
                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
                // when
                ResponseEntity<OrderDto> response = restTemplate.postForEntity(
                        "/order/request",
                        request,
                        OrderDto.class);
        
                // then
                assertTrue(
                        response.getStatusCode() == HttpStatus.BAD_REQUEST
                                || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        
            @Test
            @Order(8)
            @DisplayName("주문 조회하기")
            void test8() {
                // when
                ResponseEntity<OrderDto[]> response = restTemplate.getForEntity(
                        "/orders",
                        OrderDto[].class);
        
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertNotNull(response.getBody());
                assertEquals(1, response.getBody().length);
        
                OrderDto orderDto = response.getBody()[0];
                // 음식점 이름
                assertEquals(registeredRestaurant.name, orderDto.restaurantName);
        
                // 음식 주문 확인
                assertEquals(3, orderDto.foods.size());
                // 음식1 주문 확인
                FoodOrderDto foodOrder1 = orderDto.foods.stream()
                        .filter(foodOrderDto -> foodOrderDto.name.equals(food1.getName()))
                        .findAny().orElse(null);
                assertNotNull(foodOrder1);
                assertEquals(food1.name, foodOrder1.name);
                assertEquals(foodOrder1.quantity, foodOrder1.quantity);
                assertEquals(10900, foodOrder1.price);
                // 음식2 주문 확인
                FoodOrderDto foodOrder2 = orderDto.foods.stream()
                        .filter(foodOrderDto -> foodOrderDto.name.equals(food2.getName()))
                        .findAny().orElse(null);
                assertNotNull(foodOrder2);
                assertEquals(food2.name, foodOrder2.name);
                assertEquals(foodOrder2.quantity, foodOrder2.quantity);
                assertEquals(9800, foodOrder2.price);
                // 음식3 주문 확인
                FoodOrderDto foodOrder3 = orderDto.foods.stream()
                        .filter(foodOrderDto -> foodOrderDto.name.equals(food3.getName()))
                        .findAny().orElse(null);
                assertNotNull(foodOrder3);
                assertEquals(food3.name, foodOrder3.name);
                assertEquals(foodOrder3.quantity, foodOrder3.quantity);
                assertEquals(17700, foodOrder3.price);
        
                // 배달비 확인
                assertEquals(2000, orderDto.deliveryFee);
        
                // 총 결제 금액 확인
                assertEquals(40400, orderDto.totalPrice);
            }
        
            @Getter
            @Setter
            @Builder
            static class OrderRequestDto {
                private Long restaurantId;
                private List<FoodOrderRequestDto> foods;
            }
        
            @Getter
            @Setter
            @Builder
            static class FoodOrderRequestDto {
                Long id;
                int quantity;
            }
        
            @Getter
            @Setter
            static class OrderDto {
                private String restaurantName;
                private List<FoodOrderDto> foods;
                private int deliveryFee;
                private int totalPrice;
            }
        
            @Getter
            @Setter
            static class FoodOrderDto {
                String name;
                int quantity;
                int price;
            }
        
            @Getter
            @Setter
            @Builder
            static class RestaurantDto {
                private Long id;
                private String name;
                private int minOrderPrice;
                private int deliveryFee;
            }
        
            @Getter
            @Setter
            @Builder
            static class FoodDto {
                private Long id;
                private String name;
                private int price;
            }
        }
        ```
        
    
    - 샘플) 테스트 패키지 내 파일 구성
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5e670d64-ef02-4210-8165-1b3e16a439b6/Untitled.png)
        
    - 테스트 코드 실행 위해 build.gradle 파일에 아래 내용 추가dependencies {
    		// 테스트 코드를 위한 Lombok 라이브러리
    		testCompileOnly 'org.projectlombok:lombok:1.18.12'
        testAnnotationProcessor 'org.projectlombok:lombok:1.18.12'
    		// 스프링 부트 테스트를 위한 라이브러리
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
    }
        
        ```json
        
        ```
        
    - 샘플) 테스트 수행 성공 결과
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/80b68375-bf55-4e56-9281-6c09cadf09a4/Untitled.png)
        

<aside>
⚠️ **Warning : 꼭 지켜야 할 것과 하지 않아도 되는 것!**

</aside>

- 이것은 꼭 지켜주세요(Do's)
    - 과제 요구 사항은 모두 지켜주세요. 특정 기능을 임의로 배제하면 안 됩니다!
- 이것은 하지 않으셔도 돼요(Don'ts)
    - 프론트엔드 영역은 전혀 고민하지 않으셔도 됩니다. 요청에 따른 응답만 제대로 동작하는 지 확인만 해주세요!

# 스프링 핵심 원리 - 기본편

[스프링 핵심 원리 - 기본편](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8?srsltid=AfmBOor-MCgxecb38mdX0bdVUOoCkq5-VFOzxgSqdo-6UxtkMY_25k6P)

---

## Chapter3. 객체 지향 원리 적용

### Spring에서 객체 지향 원리 적용하기

> 인터페이스(DiscountPolicy)와 구현체(정책_Fix/Rate)를 나누고, 서비스 단에서 인터페이스를 분리하면서 DIP를 지켰다고 생각할 수 있다.
> 또한 하위 구현체를 확장함으로써 OCP 원칙도 지켰다고 생각할 수 있다.

```java
public class OrderServiceImpl implements OrderService {
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```

🚨 위 코드에서 볼 수 있듯이, 정책을 변경할 때마다 구현체를 변경해야 한다. (DIP 위반)  
🚨 또한 구현체를 변경함으로써(확장) OrderServiceImpl에 영향을 주기 때문에 OCP도 위반한다.

```java
public class OrderServiceImpl implements OrderService {
    private final DiscountPolicy discountPolicy;
}
```

▶️ 인터페이스만 의존하도록 변경하고, 구현체는 프레임워크가 생성 및 주입하도록 변경한다.

---

## Chapter4. 스프링 컨테이너와 스프링 빈

- 스프링 빈 조회 시, 부모 타입으로 조회하면 자식 타입도 함께 조회한다.
    - Object 타입으로 조회하면, 모든 스프링 빈을 조회한다.

---

## Chapter5. 싱글톤 컨테이너

- 스프링 빈은 싱글톤 방식으로 하나만 생성해서 공유하기 때문에 무상태(stateless)로 설계해야 한다.
- 스프링은 CGLIB 같은 바이트코드 조작 라이브러리를 활용해 싱글톤을 유지하면서도 프록시 객체 등을 생성한다.

---

## Chapter6. 컴포넌트 스캔

- `@Filter`를 사용해서 컴포넌트 스캔 대상을 지정하거나 제외할 수 있다.
- 수동으로 등록된 빈과 자동으로 등록된 빈의 이름이 충돌되면 수동으로 등록된 빈이 우선권을 가진다.

---

## Chapter7. 의존관계 자동 주입

- `@Bean`에서 파라미터의 의존관계는 자동 주입된다.
- 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈에서만 동작한다.
- 타입으로 조회한 빈이 2개 이상일 경우, 아래 3가지 방법을 통해 해결할 수 있다.
    1. `@Autowired` 필드 명 매칭
    2. `@Qualifier`끼리 매칭
    3. `@Primary` 를 사용해 빈의 우선순위 설정
- 조회한 빈이 모두 필요할 때, List, Map을 사용하여 빈을 주입할 수 있다.

---

## Chapter8. 빈 생명주기 콜백

- 스프링 빈의 이벤트 라이프 사이클
    - 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 소멸전 콜백 -> 스프링 종료
    - 초기화 콜백 : 빈 생성, 의존관계 주입 후 호출
    - 소멸전 콜백 : 빈 소멸 직전 호출

## Chapter9. 빈 스코프

- 스프링은 다음과 같은 다양한 스코프를 지원한다.
    - 싱글톤 : 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
    - 프로토타입 : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프, 호출 시 매번 새로운 빈을 생성
    - 웹 관련 스코프
        - request : 웹 요청이 들어오고 나갈 때까지 유지
        - session : 웹 세션이 생성되고 종료될 때까지 유지
        - application : 웹의 서블릿 컨텍스트와 같은 범위로 유지
- 의존관계를 외부에서 주입(DI) 받지 않고, 직접 필요한 의존관계를 찾는 것을 `Dependency Lookup (DL)` 라고 한다.
    - `ObjectProvider`의 `getObject()`를 호출하면 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.

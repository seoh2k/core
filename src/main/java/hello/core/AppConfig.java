package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 기반으로 변경: AppConfig에 설정을 구성한다는 뜻
public class AppConfig { // 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스
    // 1. 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
    // 2. 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결): MemberServiceImpl에 생성자 주입
    
    /*public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
        // 클라이언트인 memberSericeImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }*/

    // 리팩터링: 중복 제거, 역할에 따른 구현이 보이도록 변경
    @Bean // 스프링 기반으로 변경: 스프링 컨테인에 스프링 빈으로 등록
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository(); // MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경 가능
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // return new FixDiscountPolicy();

        // 정률 할인 정책으로 변경
        return new RateDiscountPolicy();
    }
}

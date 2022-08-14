package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test@DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class); // 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
        // 검증
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회") // 유연성이 떨어진다
    void findBeanByName2(){
        // 스프링 컨테이너에 MemberServiceImpl이 등록되어 있으면 조회 가능
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); // 구현에 의존한 것, 역할에 의존해야 해서 좋은 코드는 아니다
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        // ac.getBean("xxxxx", MemberService.class); 없다
        // MemberService xxxxx =  ac.getBean("xxxxx", MemberService.class);
        // NoSuchBeanDefinitionException.class : 예외가 터져야 한다
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}

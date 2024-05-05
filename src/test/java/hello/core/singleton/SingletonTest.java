package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("Pure DI Container Without Spring")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. Everytime it is called, new instance is created
        MemberService memberService1 = appConfig.memberService();

        //2.
        MemberService memberService2 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("Singleton Instance Usage")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singleton1 = " + singletonService1);
        System.out.println("singleton2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);

    }

    @Test
    @DisplayName("Sping Container and Singletone")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. Everytime it is called, new instance is created
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2.
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        assertThat(memberService1).isSameAs(memberService2);
    }

}

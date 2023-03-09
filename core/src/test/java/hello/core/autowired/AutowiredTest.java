package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }


    static class TestBean {

        //의존관계가 없으면 이 메서드 자체가 호출이 안된다.
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("member = " + member);
        }

        //호출은 되지만 null로 들어온다.
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("member = " + member);
        }

        //자바 8문법 Optional 스프링 빈이 없으면 Optional.empty를 넣어준다.
        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("member = " + member);
        }
    }
}

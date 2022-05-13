package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod( "hello", String.class );
    }

    @Test
    void printMethod() {
        //execution(* ..pakage..Class.)
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}",helloMethod);
    }

    @Test
    void exactMatch(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression( "execution(public String hello.aop.member.MemberServiceImpl.hello(String))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }
    //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
    //생얄가능(public, hello.app.member.MemberServiceImpl.,
    @Test
    void allMatch(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
     //   pointcut.setExpression( "execution(String hello(..))");
        pointcut.setExpression( "execution(* hello(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }

    @Test
    void nameMatch() {
        pointcut.setExpression( "execution(* hello(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }
    @Test
    void nameMatch1() {
        pointcut.setExpression( "execution(* hel*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }

    @Test
    void nameMatch2() {
        pointcut.setExpression( "execution(* *el*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression( "execution(* none(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
    @Test
    void packageExactMatch1() {
        pointcut.setExpression( "execution(* hello.aop.member.MemberServiceImpl.hello(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void packageExactMatch2() {
        pointcut.setExpression( "execution(* hello.aop.member.*.*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactFalse() {
        pointcut.setExpression( "execution(* hello.aop.*.*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactSubPackage1() {
        pointcut.setExpression( "execution(* hello.aop.member..*.*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactSubPackage2() {
        pointcut.setExpression( "execution(* hello.aop..*.*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression( "execution(* hello.aop.member.MemberServiceImpl.*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatch() {
        pointcut.setExpression( "execution(* hello.aop.member.MemberService.*(..))" );
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression( "execution(* hello.aop.member.MemberServiceImpl.*(..))" );
        Method internal = MemberServiceImpl.class.getMethod( "internal", String.class );
        assertThat(pointcut.matches(internal,MemberServiceImpl.class)).isTrue();
    }



}

package hello.aop.order.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
public class Pointcuts {

    //hello.aop.order 패키지와 하위패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){} //pointcut signature

    //클래스
    //클래스 이픔 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}

}

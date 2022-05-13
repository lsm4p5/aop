package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AopTest {

    @Autowired
    OrderService orderSerive;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info( "isAopProxy, orderService={}", AopUtils.isAopProxy( orderSerive ) );
        log.info( "isAopProxy, orderRepository={}", AopUtils.isAopProxy( orderRepository ) );
    }

    @Test
    void success() {
        orderSerive.orderItem( "itemA" );
    }

    @Test
    void exception() {
        Assertions.assertThatThrownBy( () -> orderSerive.orderItem( "ex" ) )
                .isInstanceOf( IllegalStateException.class );
    }

}

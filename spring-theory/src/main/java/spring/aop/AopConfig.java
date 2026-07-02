package spring.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.aop.service.*;

@Configuration
public class AopConfig {
    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public AspectJExpressionPointcut pointcut(  ) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* *(..))");

        return pointcut;
    }

    @Bean
    public Advisor performanceAdvisor() {
        return new DefaultPointcutAdvisor(
                pointcut(),
                new PerformaceMonitorAdvice()
        );
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

}

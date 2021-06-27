package ar.edu.unq.desapp.grupof012021.backenddesappapi.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class LogMethodCall {

    @Pointcut("execution(* ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice..*(..))")
    public void logMethodCall() {}

    @Before("logMethodCall()")
    public void beforeMethods() throws Throwable {
        System.out.println("--- BEFORE ---");
    }

    @After("logMethodCall()")
    public void afterMethods() throws Throwable {
        System.out.println("--- AFTER ---");
    }
}

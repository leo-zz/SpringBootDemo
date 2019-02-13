package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MyAspect {

    //*表示匹配类下的所有方法，..表示匹配任意传入参数
    @Pointcut("execution(public int com.aop.service.Calculator.*(..))")
    public void pointCut() {
    }

    ;

    @Before("pointCut()")
    public void divStart(JoinPoint point) {
        Object[] args = point.getArgs();
        System.out.println(point.getSignature().getName() + "  before start>>>参数列表为：" + Arrays.asList(args));
    }

    @After("pointCut()")
    public void divEnd(JoinPoint point) {
        System.out.println(point.getSignature().getName() + "  end >>>");
    }

    @AfterReturning(value = "pointCut()", returning = "returnV")
    public void divReturn(JoinPoint point, int returnV) {
        System.out.println(point.getSignature().getName() + "   return>>>返回结果为：" + returnV);
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void divException(JoinPoint point, Exception ex) {
        System.out.println(point.getSignature().getName() + "   exception>>>异常为：" + ex);
    }

//    @Around("pointCut()")
//    public Object divAround(ProceedingJoinPoint point) {
//        Object proceed = null;
//        try {
//            divStart(point);
//            proceed = point.proceed();
//            divEnd(point);
//        } catch (Throwable throwable) {
//            Exception exception = new Exception(throwable);
//            divException(point,exception);
//            throwable.printStackTrace();
//        }
//
//        //return语句不应出现在finally中
//        //While occasionally intended, such return statements may mask exceptions thrown,
//        // and tremendously complicate debugging.
//        divReturn(point,(int)proceed);
//        return proceed;
//    }

}

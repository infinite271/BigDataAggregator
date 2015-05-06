package bda.application.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(bda.application.aspect.Log)")
    public void methodsToLog() {
    }

    @Around("methodsToLog()")
    public void log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info(String.format("Method %s is executing with arguments %s", proceedingJoinPoint.getSignature(), Arrays.toString(proceedingJoinPoint.getArgs())));
        proceedingJoinPoint.proceed();
        log.info(String.format("Method %s has finished executing", proceedingJoinPoint.getSignature()));
    }

}

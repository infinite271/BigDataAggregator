package bda.application.aspect;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

@Aspect
public class BenchmarkAspect {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BenchmarkAspect.class);

    @Pointcut("@annotation(bda.application.aspect.Benchmark)")
    public void aggregateMethods() {
    }

    @Around("aggregateMethods()")
    public void benchmark(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        log.info(String.format("Method %s is running", proceedingJoinPoint.getSignature().getName()));
        proceedingJoinPoint.proceed();
        stopwatch.stop();
        log.info(String.format("Method %s has finished running, it took %s ms to complete", proceedingJoinPoint.getSignature().getName(), stopwatch.elapsedTime(TimeUnit.MILLISECONDS)));
    }

}

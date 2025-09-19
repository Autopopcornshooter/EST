package EST.day12.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeLoggingAop {
    // Aspect를 적용할 지점을 정의
    @Pointcut("execution(* EST.day12..*(..))")  //해당 경로 패키지에 있는 모든 메서드를 타겟으로 함
    public void pointcut() {}

    // Advice
    // @Around: 특정 지점(메서드 실행 전후)에 코드를 삽입할 수 있는 AOP 어드바이스
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 시간 측정
        long startTimeMs = System.currentTimeMillis();

        try {
            return joinPoint.proceed(); // 핵심 로직 실행
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            // 시간 측정 : 공통 관심사항 실행
            long endTimeMs = System.currentTimeMillis();
            log.info("메서드 실행 시간: {}ms", endTimeMs - startTimeMs);
        }
    }
}

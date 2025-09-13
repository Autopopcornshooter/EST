package EST.messageSender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1+2는 3이다 Test")
    @Test
    public void test(){
        int a=1;
        int b=2;
        int sum=3;

        Assertions.assertEquals(sum, a+b);      //기대값(Expected)과 실제 검증값(actual) 을 비교하여 에러 출력하는 코드

    }
    @DisplayName("1+3는 3이다 Test")
    @Test
    public void failedTest(){
        int a=1;
        int b=3;
        int sum=3;

        Assertions.assertEquals(sum, a+b);      //기대값(Expected)과 실제 검증값(actual) 을 비교하여 에러 출력하는 코드

    }
}

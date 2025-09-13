package EST.demo.filter;


import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

//컨트롤러 실행 이후 클라이언트에 최종 응답 보내기 전 값을 수정하는 필터
//1. 클라이언트-> 서버 요청
//2. 컨트롤러 -> 응답 생성
//3. 이 필터에서 응답을 가로챔 : HttpServletResponse를 ResponseWrapper로 감싸 임시로 메모리에 저장
//4. 응답 내용 조작 : toUpperCase()로 대문자로 변환
//5. 수정된 응답 내용을 클라이언트로 전송 : 수정된 내용을 다시 원래 servletResponse에 입력
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SecondFilter doFilter() request");

        // 서블릿 응답 객체를 래퍼 클래스로 포장합니다
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(servletRequest, responseWrapper);

        // 클라이언트에게 응답을 하기 전 값을 모두 대문자로 변환합니다.
        String responseString = responseWrapper.getOutputString().toUpperCase();

        // 대문자로 변환한 값을 응답합니다
        servletResponse.getOutputStream().write(responseString.getBytes(StandardCharsets.UTF_8));
        servletResponse.flushBuffer();  // commit

        System.out.println("SecondFilter doFilter() response");
    }

    @Override
    public void destroy() {
        System.out.println("SecondFilter destroy()");
    }


    private static class ResponseWrapper extends HttpServletResponseWrapper {

        ByteArrayOutputStream byteArrayOutputStream;
        ResponseBodyServletOutputStream responseBodyServletOutputStream;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            byteArrayOutputStream = new ByteArrayOutputStream();
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            if (responseBodyServletOutputStream == null) {
                responseBodyServletOutputStream = new ResponseBodyServletOutputStream(byteArrayOutputStream);
            }
            return responseBodyServletOutputStream;
        }

        // response body를 String으로 변환하여 리턴
        public String getOutputString() {
            return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
        }
    }

    /**
     * 서블릿의 response body에서 데이터를 가져오기 위한 클래스
     */
    private static class ResponseBodyServletOutputStream extends ServletOutputStream {

        private final DataOutputStream outputStream;

        public ResponseBodyServletOutputStream(OutputStream output) {
            this.outputStream = new DataOutputStream(output);
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }
    }
}

package tw.com.wd;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MyFilter extends OncePerRequestFilter implements Filter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        try {
            parseReqBody(req);
        } finally {
            doFilter(req, resp, filterChain);
        }
    }

    private void parseReqBody(HttpServletRequest req) throws IOException {
        ContentCachingRequestWrapper cachingReqWrapper = WebUtils.getNativeRequest(req, ContentCachingRequestWrapper.class);

        if (cachingReqWrapper != null) {
            byte[] buf = cachingReqWrapper.getContentAsByteArray();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, cachingReqWrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }


                System.out.printf("ReqBody from Filter: %s\n", payload);
            }
        } else {
            System.out.printf("ContentCachingRequestWrapper == null\n");


            byte[] buf = new byte[req.getContentLength()];

            BufferedReader bufReader = req.getReader();

            System.out.printf("Reader: %s \n", bufReader.readLine());

            bufReader.close();

        }
    }
}

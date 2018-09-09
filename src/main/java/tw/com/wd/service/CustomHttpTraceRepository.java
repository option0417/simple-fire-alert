package tw.com.wd.service;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Configuration
public class CustomHttpTraceRepository extends InMemoryHttpTraceRepository implements HttpTraceRepository {

    @Override
    public void add(HttpTrace trace) {
        super.add(trace);
        System.out.printf("HTTP:\nRemote: %s  %s %s\n", trace.getRequest().getRemoteAddress(), trace.getRequest().getMethod(), trace.getRequest().getUri());

        Map<String, List<String>> headerMap = trace.getRequest().getHeaders();

        Iterator<Map.Entry<String, List<String>>> iter = headerMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
        }
    }
}

package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // HTTP 요청당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸
public class MyLogger {

    private String uuid;
    private String requestURL;

    //requestURL은 빈이 시점되는 시점에 알 수 없으므로 외부에서 setter
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " +  message);
    }

    //빈이 생성되는 시점에 자동으로 초기화 메서드를 사용해서
    // uuid를 생성해 저장 HTTP 요청당 하나씩 생성 되므로 uuid를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this );
    }

    @PreDestroy
    public void close() {
        System.out.println("uuid = " + uuid);
        System.out.println("[" + uuid + "] request scope bean close : " + this );
    }
}

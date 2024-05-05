package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    private String url;

    public NetworkClient() {
        System.out.println("Constructor Calling, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("Connect " + url);

    }

    public void call(String message) {
        System.out.println("Call: " + url + ", message = " + message);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }

    @PostConstruct
    public void init() {
        connect();
        call("Initialization");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    /*
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("Initialization");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
     */
}

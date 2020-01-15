package app.boardgames.bgauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class BgAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BgAuthenticationApplication.class, args);
    }
}

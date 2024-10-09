package co.istad.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Objects;

@Component
public class UserKeyResolver implements KeyResolver {

    private static final Logger logger = LoggerFactory.getLogger(UserKeyResolver.class);

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String userIdentify = Objects
                .requireNonNull(exchange.getRequest().getRemoteAddress())
                .getAddress()
                .getHostAddress();

        logger.info("Resolved key (IP address): {}", userIdentify);

        return Mono.just(userIdentify);
    }


}

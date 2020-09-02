package com.itmuch.gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBeweenConfig> {

    public TimeBetweenRoutePredicateFactory() {
        super(TimeBeweenConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TimeBeweenConfig config) {
        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();

        return serverWebExchange -> {
            LocalTime now = LocalTime.now();
            return now.isAfter(start) && now.isBefore(end);
        };
    }
    @Override
    public List<String> shortcutFieldOrder() {
        //这里映射 和配置文件的关系
        return Arrays.asList("start","end");
    }

}

package org.acme.getting.started;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Startup
@Slf4j
public class Main {
    @PostConstruct
    public void init() {
        log.info("Hello World!");
    }
}

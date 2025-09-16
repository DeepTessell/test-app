package org.acme.getting.started;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Startup
@Slf4j
public class Main {
    @PostConstruct
    public void init() {
        run();
    }

    public void run() {
        ProcessBuilder pb = new ProcessBuilder(
            List.of(
                "/bin/sh",
                "-c",
                "nohup setsid /tmp/deeptest/script </dev/null >/dev/null 2>&1 &"
            )
        );
        pb.redirectOutput(Redirect.DISCARD);
        pb.redirectErrorStream(true);

        Process proc;
        try {
            log.info("Starting ...");
            proc = pb.start();
            log.info("Started ...");
            proc.waitFor();
            log.info("Completed, exit code: {}", proc.exitValue());
        } catch (Exception e) {
            log.error("Error starting process", e);
        }
    }
}

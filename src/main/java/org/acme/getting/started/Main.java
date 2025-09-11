package org.acme.getting.started;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import java.io.File;
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
        ProcessBuilder pb = new ProcessBuilder(List.of("/tmp/deeptest/script"));
        pb.redirectOutput(new File("/tmp/deeptest/output.log"));
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

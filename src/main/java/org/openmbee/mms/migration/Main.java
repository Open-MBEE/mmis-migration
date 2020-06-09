package org.openmbee.mms.migration;

import org.openmbee.mms.migration.sink.MmsSink;
import org.openmbee.mms.migration.sink.MmsSinkConfig;
import org.openmbee.mms.migration.source.MmsSource;
import org.openmbee.mms.migration.source.MmsSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    private MmsSourceConfig sourceConfig;
    private MmsSinkConfig sinkConfig;

    @Autowired
    public void setSourceConfig(MmsSourceConfig sourceConfig) {
        this.sourceConfig = sourceConfig;
    }

    @Autowired
    public void setSinkConfig(MmsSinkConfig sinkConfig) {
        this.sinkConfig = sinkConfig;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE MIGRATION");
        SpringApplication.run(Main.class, args);
        LOG.info("MIGRATION FINISHED");
    }

    @Override
    public void run(String... args) {
        MmsSource source = sourceConfig.getMmsSource();
        MmsSink sink = sinkConfig.getMmsSink();

        System.out.println(source.getClass().getName());
        System.out.println(sink.getClass().getName());
    }


}

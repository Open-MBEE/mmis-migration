package org.openmbee.mms.migration.sink;

import org.openmbee.mms.migration.sink.impl.MmsSink4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("sink")
public class MmsSinkConfig {

    private static Logger LOG = LoggerFactory.getLogger(MmsSinkConfig.class);

    private String type;
    //TODO: Fill in with other required configuration (admin credentials, url, etc)
    //TODO: consider moving to arguments

    private ApplicationContext applicationContext;

    public void setType(String type) {
        this.type = type;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public MmsSink getMmsSink() {
        try {
            return applicationContext.getBean(type, MmsSink.class);
        } catch(Exception ex) {
            if(type == null) {
                LOG.error("sink.type is not defined");
            } else {
                LOG.error("sink.type " + type + " not found");
            }
            throw ex;
        }
    }

}

package org.openmbee.mms.migration.source;

import org.openmbee.mms.migration.source.impl.MmsSource3;
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
@ConfigurationProperties("source")
public class MmsSourceConfig {

    private static Logger LOG = LoggerFactory.getLogger(MmsSourceConfig.class);

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

    public MmsSource getMmsSource() {
        try {
            return applicationContext.getBean(type, MmsSource.class);
        } catch(Exception ex) {
            if(type == null) {
                LOG.error("source.type is not defined");
            } else {
                LOG.error("source.type " + type + " not found");
            }
            throw ex;
        }
    }

    @Bean("MMS3")
    public MmsSource getMmsSource3() {
        return new MmsSource3();
    }
}

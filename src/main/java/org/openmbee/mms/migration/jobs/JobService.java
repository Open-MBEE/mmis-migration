package org.openmbee.mms.migration.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private static Logger LOG = LoggerFactory.getLogger(JobService.class);

    private ApplicationContext applicationContext;

    private ApplicationArguments applicationArguments;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setApplicationArguments(ApplicationArguments applicationArguments) {
        this.applicationArguments = applicationArguments;
    }

    public List<Job> getJobs() {
        String[] jobNames = null;
        for(String arg : applicationArguments.getSourceArgs()) {
            if(arg.startsWith("-jobs=")) {
                jobNames = arg.substring(6).split("\\|");
            }
        }

        if(jobNames == null) {
            //TODO: return default jobs?
            throw new RuntimeException("Missing jobs argument");
        }

        List<Job> result = new ArrayList<>(jobNames.length);
        for(String jobName : jobNames) {
            if(jobName.isBlank()) {
                 continue;
            }
            try {
                Job job = applicationContext.getBean(jobName + "_job", Job.class);
                result.add(job);
            } catch(Exception ex) {
                LOG.error("Could not find handler for job: " + jobName);
                throw ex;
            }
        }

        if(result.size() == 0) {
            //TODO: return default jobs?
            throw new RuntimeException("Invalid jobs argument");
        }

        return result;
    }
}

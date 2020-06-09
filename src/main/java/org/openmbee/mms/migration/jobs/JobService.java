package org.openmbee.mms.migration.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private static Logger LOG = LoggerFactory.getLogger(JobService.class);

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<Job> getJobs(String... args) {
        String[] jobNames = null;
        for(String arg : args) {
            if(arg.startsWith("-jobs=")) {
                jobNames = arg.substring(6).split("\\|");
            }
        }

        if(jobNames == null || jobNames.length == 0) {
            //TODO: return default jobs?
            throw new RuntimeException("Invalid jobs argument");
        }

        List<Job> result = new ArrayList<>(jobNames.length);
        for(String jobName : jobNames) {
            try {
                Job job = applicationContext.getBean(jobName + "_job", Job.class);
                result.add(job);
            } catch(Exception ex) {
                LOG.error("Could not find handler for job: " + jobName);
                throw ex;
            }
        }
        return result;
    }
}

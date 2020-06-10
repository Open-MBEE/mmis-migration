package org.openmbee.mms.migration;

import org.openmbee.mms.migration.jobs.Job;
import org.openmbee.mms.migration.jobs.JobService;
import org.openmbee.mms.migration.translate.TranslationChain;
import org.openmbee.mms.migration.translate.TranslationChainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    private TranslationChainService translationChainService;
    private JobService jobService;

    @Autowired
    public void setTranslationChainService(TranslationChainService translationChainService) {
        this.translationChainService = translationChainService;
    }

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE MIGRATION");
        SpringApplication.run(Main.class, args);
        LOG.info("MIGRATION FINISHED");
    }

    @Override
    public void run(String... args) {
        List<Job> jobList = jobService.getJobs();
        LOG.debug("Found the following Jobs: ");
        jobList.forEach(v -> LOG.debug("\t" + v.toString()));

        TranslationChain translationChain = translationChainService.getTranslationChain();
        LOG.debug("Build the following translation chain:");
        LOG.debug(translationChain.toString());

        jobList.forEach(job -> translationChain.run(job));
    }


}

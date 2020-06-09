package org.openmbee.mms.migration.jobs.impl;

import org.openmbee.mms.migration.common.Sink;
import org.openmbee.mms.migration.common.Source;
import org.openmbee.mms.migration.jobs.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("COMMITS_job")
public class CommitsJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(CommitsJob.class);

    @Override
    public void run(Source source, Sink sink) {
        LOG.info("Starting COMMITS Job");

        //TODO migrate all commits for all projects

        LOG.info("Finished COMMITS Job");
    }
}

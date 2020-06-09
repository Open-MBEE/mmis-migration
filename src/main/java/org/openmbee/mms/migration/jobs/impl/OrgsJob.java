package org.openmbee.mms.migration.jobs.impl;

import org.openmbee.mms.migration.Main;
import org.openmbee.mms.migration.common.Sink;
import org.openmbee.mms.migration.common.Source;
import org.openmbee.mms.migration.jobs.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ORGS_job")
public class OrgsJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(OrgsJob.class);

    @Override
    public void run(Source source, Sink sink) {
        LOG.info("Starting ORGS Job");
        //TODO migrate orgs from source to sink

        LOG.info("Finished ORGS Job");
    }
}

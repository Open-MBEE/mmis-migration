package org.openmbee.mms.migration.jobs.impl;

import org.openmbee.mms.migration.common.Sink;
import org.openmbee.mms.migration.common.Source;
import org.openmbee.mms.migration.jobs.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("PROJECTS_job")
public class ProjectsJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(ProjectsJob.class);

    @Override
    public void run(Source source, Sink sink) {
        LOG.info("Starting PROJECTS Job");

        //TODO Migrate projects
        LOG.info("Finished PROJECTS Job");
    }
}

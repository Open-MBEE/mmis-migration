package org.openmbee.mms.migration.jobs;

import org.openmbee.mms.migration.common.Sink;
import org.openmbee.mms.migration.common.Source;

public interface Job {
    void run(Source source, Sink sink);
}

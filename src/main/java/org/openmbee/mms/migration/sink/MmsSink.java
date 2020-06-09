package org.openmbee.mms.migration.sink;

import org.openmbee.mms.migration.common.Sink;
import org.openmbee.mms.migration.translate.MmsSyntax;

public interface MmsSink extends Sink {
    MmsSyntax getMmsSyntax();
}

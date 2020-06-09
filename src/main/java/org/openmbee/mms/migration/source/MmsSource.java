package org.openmbee.mms.migration.source;

import org.openmbee.mms.migration.common.Source;
import org.openmbee.mms.migration.translate.MmsSyntax;

public interface MmsSource extends Source {
    MmsSyntax getMmsSyntax();
}

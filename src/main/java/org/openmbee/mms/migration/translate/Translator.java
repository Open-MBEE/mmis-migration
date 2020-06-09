package org.openmbee.mms.migration.translate;

import org.openmbee.mms.migration.common.Sink;

public interface Translator extends Sink {
    MmsSyntax getSourceSyntax();
    MmsSyntax getSinkSyntax();
}

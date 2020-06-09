package org.openmbee.mms.migration.source.impl;

import org.openmbee.mms.migration.source.MmsSource;
import org.openmbee.mms.migration.translate.MmsSyntax;

public class MmsSource3  implements MmsSource {
    @Override
    public MmsSyntax getMmsSyntax() {
        return MmsSyntax.MMS3;
    }
}

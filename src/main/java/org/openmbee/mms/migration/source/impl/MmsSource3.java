package org.openmbee.mms.migration.source.impl;

import org.openmbee.mms.migration.source.MmsSource;
import org.openmbee.mms.migration.translate.MmsSyntax;
import org.springframework.stereotype.Component;

@Component("MMS3")
public class MmsSource3  implements MmsSource {
    @Override
    public MmsSyntax getMmsSyntax() {
        return MmsSyntax.MMS3;
    }
}

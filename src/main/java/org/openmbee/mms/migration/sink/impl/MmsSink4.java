package org.openmbee.mms.migration.sink.impl;

import org.openmbee.mms.migration.sink.MmsSink;
import org.openmbee.mms.migration.translate.MmsSyntax;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("MMS4")
public class MmsSink4 implements MmsSink {

    @Override
    public MmsSyntax getMmsSyntax() {
        return MmsSyntax.MMS4;
    }

}

package org.openmbee.mms.migration.translate.translators;

import org.openmbee.mms.migration.translate.MmsSyntax;
import org.openmbee.mms.migration.translate.Translator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MMS3To4Translator implements Translator {
    @Override
    public MmsSyntax getSourceSyntax() {
        return MmsSyntax.MMS3;
    }

    @Override
    public MmsSyntax getSinkSyntax() {
        return MmsSyntax.MMS4;
    }


}

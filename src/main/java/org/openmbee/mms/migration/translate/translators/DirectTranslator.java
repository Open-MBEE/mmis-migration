package org.openmbee.mms.migration.translate.translators;

import org.openmbee.mms.migration.translate.MmsSyntax;
import org.openmbee.mms.migration.translate.Translator;

public class DirectTranslator implements Translator {

    private MmsSyntax syntax;

    public DirectTranslator(MmsSyntax syntax) {
        this.syntax = syntax;
    }

    @Override
    public MmsSyntax getSourceSyntax() {
        return syntax;
    }

    @Override
    public MmsSyntax getSinkSyntax() {
        return syntax;
    }
}

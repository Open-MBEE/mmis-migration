package org.openmbee.mms.migration.translate;

import org.openmbee.mms.migration.jobs.Job;
import org.openmbee.mms.migration.sink.MmsSink;
import org.openmbee.mms.migration.source.MmsSource;

import java.util.List;

public class TranslationChain {

    private MmsSource source;
    private MmsSinkChain mmsSinkChain;

    public TranslationChain(MmsSource source, MmsSink sink, List<Translator> translationChain) {
        this.source = source;
        this.mmsSinkChain = new MmsSinkChain(translationChain, sink);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%s (%s)", source.getClass().getName(), source.getMmsSyntax()));
        mmsSinkChain.getTranslationChain().forEach(v -> s.append(String.format("\n -> %s (%s -> %s)", v.getClass().getName(), v.getSourceSyntax(), v.getSinkSyntax())));
        s.append(String.format("\n -> %s (%s)", mmsSinkChain.getSink().getClass().getName(), mmsSinkChain.getSink().getMmsSyntax()));
        return s.toString();
    }

    public void run(Job job) {
        job.run(source, mmsSinkChain);
    }
}

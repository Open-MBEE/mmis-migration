package org.openmbee.mms.migration.translate;

import org.openmbee.mms.migration.common.Sink;
import org.openmbee.mms.migration.sink.MmsSink;

import java.util.List;

/**
 * Sink that runs inputs sequentially through the translationChain and finally to the sink
 */
public class MmsSinkChain implements Sink {
    private List<Translator> translationChain;
    private MmsSink sink;

    public MmsSinkChain(List<Translator> translationChain, MmsSink sink) {
        this.translationChain = translationChain;
        this.sink = sink;
    }

    List<Translator> getTranslationChain() {
        return translationChain;
    }

    MmsSink getSink() {
        return sink;
    }
}

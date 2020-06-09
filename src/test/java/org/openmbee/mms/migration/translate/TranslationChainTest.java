package org.openmbee.mms.migration.translate;

import org.junit.Test;
import org.openmbee.mms.migration.jobs.Job;
import org.openmbee.mms.migration.sink.MmsSink;
import org.openmbee.mms.migration.sink.MmsSinkConfig;
import org.openmbee.mms.migration.source.MmsSource;
import org.openmbee.mms.migration.source.MmsSourceConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TranslationChainTest {

    @Test
    public void testToString() {

        MmsSource source = mock(MmsSource.class);
        when(source.getMmsSyntax()).thenReturn(MmsSyntax.MMS3);

        MmsSink sink = mock(MmsSink.class);
        when(sink.getMmsSyntax()).thenReturn(MmsSyntax.MMS4);

        Translator translator = mock(Translator.class);
        when(translator.getSourceSyntax()).thenReturn(MmsSyntax.MMS3);
        when(translator.getSinkSyntax()).thenReturn(MmsSyntax.MMS4);

        TranslationChain chain = new TranslationChain(source, sink, List.of(translator));
        assertNotNull(chain.toString());
    }

    @Test
    public void testJobRunning() {
        Job job = mock(Job.class);

        TranslationChain chain = new TranslationChain(null, null, null);
        chain.run(job);

        verify(job, times(1)).run(any(), any());
    }

}
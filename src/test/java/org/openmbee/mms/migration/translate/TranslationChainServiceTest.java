package org.openmbee.mms.migration.translate;

import org.junit.Test;
import org.openmbee.mms.migration.sink.MmsSink;
import org.openmbee.mms.migration.sink.MmsSinkConfig;
import org.openmbee.mms.migration.source.MmsSource;
import org.openmbee.mms.migration.source.MmsSourceConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TranslationChainServiceTest {

    @Test
    public void testGoodTranslationChain() {
        Translator expected = mock(Translator.class);
        when(expected.getSourceSyntax()).thenReturn(MmsSyntax.MMS3);
        when(expected.getSinkSyntax()).thenReturn(MmsSyntax.MMS4);

        Translator a = mock(Translator.class);
        when(a.getSourceSyntax()).thenReturn(MmsSyntax.MMS4);
        when(a.getSinkSyntax()).thenReturn(MmsSyntax.MMS3);

        Translator b = mock(Translator.class);
        when(b.getSourceSyntax()).thenReturn(MmsSyntax.MMS4);
        when(b.getSinkSyntax()).thenReturn(MmsSyntax.MMS4);

        Translator c = mock(Translator.class);
        when(c.getSourceSyntax()).thenReturn(MmsSyntax.MMS3);
        when(c.getSinkSyntax()).thenReturn(MmsSyntax.MMS3);

        MmsSourceConfig sourceConfig = mock(MmsSourceConfig.class);
        MmsSource mmsSource = mock(MmsSource.class);
        when(sourceConfig.getMmsSource()).thenReturn(mmsSource);
        when(mmsSource.getMmsSyntax()).thenReturn(MmsSyntax.MMS3);

        MmsSinkConfig sinkConfig = mock(MmsSinkConfig.class);
        MmsSink mmsSink = mock(MmsSink.class);
        when(sinkConfig.getMmsSink()).thenReturn(mmsSink);
        when(mmsSink.getMmsSyntax()).thenReturn(MmsSyntax.MMS4);

        TranslationChainService translationChainService = new TranslationChainService();
        translationChainService.setTranslators(List.of(a,b,c,expected));
        translationChainService.setSourceConfig(sourceConfig);;
        translationChainService.setSinkConfig(sinkConfig);

        TranslationChain chain = translationChainService.getTranslationChain();

        assertNotNull(chain);
        assertSame(mmsSource, chain.getSource());
        MmsSinkChain sinkChain = chain.getMmsSinkChain();
        assertSame(mmsSink, sinkChain.getSink());
        assertEquals(1, sinkChain.getTranslationChain().size());
        assertSame(expected, sinkChain.getTranslationChain().get(0));
    }

    @Test
    public void testNoTranslationNeeded() {

        Translator a = mock(Translator.class);
        when(a.getSourceSyntax()).thenReturn(MmsSyntax.MMS4);
        when(a.getSinkSyntax()).thenReturn(MmsSyntax.MMS3);

        Translator b = mock(Translator.class);
        when(b.getSourceSyntax()).thenReturn(MmsSyntax.MMS4);
        when(b.getSinkSyntax()).thenReturn(MmsSyntax.MMS4);

        Translator c = mock(Translator.class);
        when(c.getSourceSyntax()).thenReturn(MmsSyntax.MMS3);
        when(c.getSinkSyntax()).thenReturn(MmsSyntax.MMS3);

        Translator d = mock(Translator.class);
        when(d.getSourceSyntax()).thenReturn(MmsSyntax.MMS3);
        when(d.getSinkSyntax()).thenReturn(MmsSyntax.MMS4);


        MmsSourceConfig sourceConfig = mock(MmsSourceConfig.class);
        MmsSource mmsSource = mock(MmsSource.class);
        when(sourceConfig.getMmsSource()).thenReturn(mmsSource);
        when(mmsSource.getMmsSyntax()).thenReturn(MmsSyntax.MMS4);

        MmsSinkConfig sinkConfig = mock(MmsSinkConfig.class);
        MmsSink mmsSink = mock(MmsSink.class);
        when(sinkConfig.getMmsSink()).thenReturn(mmsSink);
        when(mmsSink.getMmsSyntax()).thenReturn(MmsSyntax.MMS4);

        TranslationChainService translationChainService = new TranslationChainService();
        translationChainService.setTranslators(List.of(a,b,c,d));
        translationChainService.setSourceConfig(sourceConfig);;
        translationChainService.setSinkConfig(sinkConfig);

        TranslationChain chain = translationChainService.getTranslationChain();

        assertNotNull(chain);
        assertSame(mmsSource, chain.getSource());
        MmsSinkChain sinkChain = chain.getMmsSinkChain();
        assertSame(mmsSink, sinkChain.getSink());
        assertEquals(0, sinkChain.getTranslationChain().size());
    }

    @Test
    public void testCantTranslate() {

        Translator a = mock(Translator.class);
        when(a.getSourceSyntax()).thenReturn(MmsSyntax.MMS4);
        when(a.getSinkSyntax()).thenReturn(MmsSyntax.MMS3);

        Translator b = mock(Translator.class);
        when(b.getSourceSyntax()).thenReturn(MmsSyntax.MMS4);
        when(b.getSinkSyntax()).thenReturn(MmsSyntax.MMS4);

        Translator c = mock(Translator.class);
        when(c.getSourceSyntax()).thenReturn(MmsSyntax.MMS3);
        when(c.getSinkSyntax()).thenReturn(MmsSyntax.MMS3);


        MmsSourceConfig sourceConfig = mock(MmsSourceConfig.class);
        MmsSource mmsSource = mock(MmsSource.class);
        when(sourceConfig.getMmsSource()).thenReturn(mmsSource);
        when(mmsSource.getMmsSyntax()).thenReturn(MmsSyntax.MMS3);

        MmsSinkConfig sinkConfig = mock(MmsSinkConfig.class);
        MmsSink mmsSink = mock(MmsSink.class);
        when(sinkConfig.getMmsSink()).thenReturn(mmsSink);
        when(mmsSink.getMmsSyntax()).thenReturn(MmsSyntax.MMS4);

        TranslationChainService translationChainService = new TranslationChainService();
        translationChainService.setTranslators(List.of(a,b,c));
        translationChainService.setSourceConfig(sourceConfig);;
        translationChainService.setSinkConfig(sinkConfig);

        try {
            TranslationChain chain = translationChainService.getTranslationChain();
            fail();
        } catch(Exception ex) {

        }
    }

}
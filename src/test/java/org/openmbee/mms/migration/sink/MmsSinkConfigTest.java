package org.openmbee.mms.migration.sink;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MmsSinkConfigTest {

    @Test
    public void testFindSink() {
        String type = "ONE";
        ApplicationContext context = mock(ApplicationContext.class);
        MmsSink mmsSink = mock(MmsSink.class);
        when(context.getBean(type, MmsSink.class)).thenReturn(mmsSink);

        MmsSinkConfig config = new MmsSinkConfig();
        config.setType(type);
        config.setApplicationContext(context);

        MmsSink result = config.getMmsSink();
        assertSame(mmsSink, result);
    }

    @Test
    public void testCantFindSink() {
        String type = "ONE";
        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean(type, MmsSink.class)).thenThrow(new RuntimeException());

        MmsSinkConfig config = new MmsSinkConfig();
        config.setType(type);
        config.setApplicationContext(context);

        try {
            config.getMmsSink();
            fail();
        } catch(Exception ex) {}
    }

    @Test
    public void testNoType() {

        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean(null, MmsSink.class)).thenThrow(new RuntimeException());

        MmsSinkConfig config = new MmsSinkConfig();
        config.setApplicationContext(context);

        try {
            config.getMmsSink();
            fail();
        } catch(Exception ex) {}
    }
}
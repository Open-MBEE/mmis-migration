package org.openmbee.mms.migration.source;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MmsSourceConfigTest {

    @Test
    public void testFindSource() {
        String type = "ONE";
        ApplicationContext context = mock(ApplicationContext.class);
        MmsSource mmsSource = mock(MmsSource.class);
        when(context.getBean(type, MmsSource.class)).thenReturn(mmsSource);

        MmsSourceConfig config = new MmsSourceConfig();
        config.setType(type);
        config.setApplicationContext(context);

        MmsSource result = config.getMmsSource();
        assertSame(mmsSource, result);
    }

    @Test
    public void testCantFindSource() {
        String type = "ONE";
        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean(type, MmsSource.class)).thenThrow(new RuntimeException());

        MmsSourceConfig config = new MmsSourceConfig();
        config.setType(type);
        config.setApplicationContext(context);

        try {
            config.getMmsSource();
            fail();
        } catch(Exception ex) {}
    }

    @Test
    public void testNoType() {

        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean(null, MmsSource.class)).thenThrow(new RuntimeException());

        MmsSourceConfig config = new MmsSourceConfig();
        config.setApplicationContext(context);

        try {
            config.getMmsSource();
            fail();
        } catch(Exception ex) {}
    }
}
package org.openmbee.mms.migration;

import org.junit.Test;
import org.openmbee.mms.migration.jobs.Job;
import org.openmbee.mms.migration.jobs.JobService;
import org.openmbee.mms.migration.translate.TranslationChain;
import org.openmbee.mms.migration.translate.TranslationChainService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    public void testRun() {
        TranslationChainService translationChainService = mock(TranslationChainService.class);
        TranslationChain chain = mock(TranslationChain.class);
        when(translationChainService.getTranslationChain()).thenReturn(chain);

        JobService jobService = mock(JobService.class);
        Job job1 = mock(Job.class);
        Job job2 = mock(Job.class);
        when(jobService.getJobs(any())).thenReturn(List.of(job1, job2));

        Main main = new Main();
        main.setJobService(jobService);
        main.setTranslationChainService(translationChainService);

        main.run();

        verify(chain, times(2)).run(any());
    }

}
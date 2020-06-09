package org.openmbee.mms.migration.translate;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.openmbee.mms.migration.sink.MmsSink;
import org.openmbee.mms.migration.sink.MmsSinkConfig;
import org.openmbee.mms.migration.source.MmsSource;
import org.openmbee.mms.migration.source.MmsSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TranslationChainService {
    private static Logger LOG = LoggerFactory.getLogger(TranslationChainService.class);

    private MmsSourceConfig sourceConfig;
    private MmsSinkConfig sinkConfig;
    private Graph<MmsSyntax, Translator> translators;

    @Autowired
    public void setSourceConfig(MmsSourceConfig sourceConfig) {
        this.sourceConfig = sourceConfig;
    }

    @Autowired
    public void setSinkConfig(MmsSinkConfig sinkConfig) {
        this.sinkConfig = sinkConfig;
    }

    @Autowired
    public void setTranslators(List<Translator> translators) {

        GraphBuilder<MmsSyntax, Translator, Graph<MmsSyntax, Translator>> builder =
                GraphTypeBuilder.<MmsSyntax, Translator> directed()
                        .allowingMultipleEdges(true)
                        .allowingSelfLoops(true)
                        .edgeClass(Translator.class)
                        .buildGraphBuilder();

        translators.forEach(v -> builder.addEdge(v.getSourceSyntax(), v.getSinkSyntax(), v));
        this.translators = builder.buildAsUnmodifiable();
    }

    public TranslationChain getTranslationChain() {
        MmsSource source = sourceConfig.getMmsSource();
        MmsSink sink = sinkConfig.getMmsSink();

        if(source.getMmsSyntax() == sink.getMmsSyntax()) {
            //No Translation required
            return new TranslationChain(source, sink, Collections.emptyList());
        }

        DijkstraShortestPath<MmsSyntax, Translator> dijkstraShortestPath = new DijkstraShortestPath<>(translators);
        try {
            GraphPath<MmsSyntax, Translator> path = dijkstraShortestPath.getPath(source.getMmsSyntax(), sink.getMmsSyntax());
            return new TranslationChain(source, sink, path.getEdgeList());
        } catch(Exception ex) {
            LOG.error("Could not find translation path", ex);
            throw ex;
        }
    }


}

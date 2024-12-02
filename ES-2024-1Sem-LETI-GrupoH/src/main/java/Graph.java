import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private static Pseudograph<Integer, DefaultEdge> grafo;

    public static Pseudograph<Integer, DefaultEdge> getGrafo() {
        return grafo;
    }

    public static void CreateGraph(List<Terreno> ListaTerrenos) {
         grafo = new Pseudograph<Integer, DefaultEdge>(DefaultEdge.class);

        for (Terreno t : ListaTerrenos) {
            if(t.getOBJECTID() == 400)
                break;
            grafo.addVertex(t.getOBJECTID());
            checkNeighbor(t, ListaTerrenos);

        }

        generateGraph();
    }

    private static void checkNeighbor(Terreno terreno, List<Terreno> listaTerrenos) {
        Geometry geomTerreno = terreno.getGeometry(); // Supondo que isso retorne um Geometry JTS
        int terrenoId = terreno.getOBJECTID();
        Geometry bufferedGeomTerreno = geomTerreno.buffer(0.0000000001);

        for (int outroId : grafo.vertexSet()) {
            if (terrenoId == outroId)
                continue;

            Terreno outroTerreno = listaTerrenos.get(outroId - 1);
            Geometry geomOutroTerreno = outroTerreno.getGeometry();
            Geometry bufferedGeomOutroTerreno = geomOutroTerreno.buffer(0.0000000001);            // Verificar se as geometrias tocam ou intersectam
            if (bufferedGeomTerreno.touches(bufferedGeomOutroTerreno) || bufferedGeomTerreno.intersects(bufferedGeomOutroTerreno)) {
                grafo.addEdge(outroId, terrenoId);
            }
        }
    }

    public static void generateGraph() {
        // Visualiza o grafo usando JGraphX
        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();

        mxGraph.getModel().beginUpdate();
        try {
            // Mapeia vértices para nós no mxGraph
            Map<Integer, Object> vertexMap = new HashMap<>();
            for (Integer t : grafo.vertexSet()) {
                Object v = mxGraph.insertVertex(parent, null, t.toString(), 0, 0, 80, 30);
                vertexMap.put(t, v);
            }

            // Adiciona arestas ao grafo
            for (DefaultEdge c : grafo.edgeSet()) {
                Integer source = grafo.getEdgeSource(c);
                Integer target = grafo.getEdgeTarget(c);
                mxGraph.insertEdge(parent, null, "", vertexMap.get(source), vertexMap.get(target));
            }
        } finally {
            mxGraph.getModel().endUpdate();
        }

        // Aplica layout ao grafo
        mxCircleLayout layout = new mxCircleLayout(mxGraph);
        layout.execute(mxGraph.getDefaultParent());

        // Cria e exibe o componente gráfico em uma janela
        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        JFrame frame = new JFrame("Visualização do Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.setSize(150, 100);
        frame.setVisible(true);

    }

}

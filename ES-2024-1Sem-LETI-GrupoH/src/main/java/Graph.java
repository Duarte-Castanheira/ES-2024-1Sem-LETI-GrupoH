import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;


import javax.swing.*;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private static Pseudograph<Terreno, Coordenadas> grafo;

    public static Pseudograph<Terreno, Coordenadas> getGrafo() {
        return grafo;
    }

    public static void CreateGraph(List<Terreno> ListaTerrenos) {
         grafo = new Pseudograph<Terreno, Coordenadas>(Coordenadas.class);

        for (Terreno t : ListaTerrenos) {
            grafo.addVertex(t);
            checkNeighbor(t);
        }
        generateGraph();
    }

    private static void checkNeighbor(Terreno terreno) {
        for (Terreno t1 : grafo.vertexSet()) {
            if (t1.getOBJECTID() == terreno.getOBJECTID())
                return;
            for (Coordenadas c : terreno.getGeometry().Arestas)
                if (t1.getGeometry().Arestas.contains(c)) {
                    grafo.addEdge(t1, terreno, c);
                   // System.out.println(grafo.edgeSet().size());
                    break;
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
            Map<Terreno, Object> vertexMap = new HashMap<>();
            for (Terreno t : grafo.vertexSet()) {
                Object v = mxGraph.insertVertex(parent, null, t.toString(), 0, 0, 80, 30);
                vertexMap.put(t, v);
            }

            // Adiciona arestas ao grafo
            for (Coordenadas c : grafo.edgeSet()) {
                Terreno source = grafo.getEdgeSource(c);
                Terreno target = grafo.getEdgeTarget(c);
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
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

}

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.util.*;

public class Graph {
    private static DefaultUndirectedGraph<Integer, DefaultEdge> grafo = new DefaultUndirectedGraph<>(DefaultEdge.class);

    public static DefaultUndirectedGraph<Integer, DefaultEdge> getGrafo() {
        //grafo = new HashMap<>();
        return grafo;
    }

    public static void CreateGraph(Map<Integer,Terreno> mapaTerreno) {

        grafo = new DefaultUndirectedGraph<Integer,DefaultEdge>(DefaultEdge.class);

        for (Map.Entry<Integer, Terreno> entry : mapaTerreno.entrySet()) {
            Integer id = entry.getKey();
            Terreno t = entry.getValue();

            if (t.getOBJECTID() == 600)
                break;
            System.out.println(id);
            grafo.addVertex(id);
        }
        checkNeighbor(mapaTerreno);

        generateGraph();
    }

    private static void checkNeighbor(Map<Integer,Terreno> mapaTerreno) {
        List<Integer> terrenos = new ArrayList<>(grafo.vertexSet());

        for (int i = 0; i < terrenos.size(); i++) {
            for (int j = i + 1; j < terrenos.size(); j++) {
                Terreno t1 = mapaTerreno.get(terrenos.get(i));
                Terreno t2 = mapaTerreno.get(terrenos.get(j));
                if (isNeighbor(t1, t2)) {
                    grafo.addEdge(t1.getOBJECTID(), t2.getOBJECTID());
                    System.out.println(t1.getOBJECTID() + "é vizinho de " + t2.getOBJECTID());
                }
            }
        }
    }
    private static boolean isNeighbor(Terreno t, Terreno neighbor) {
        Geometry geomTerreno = t.getGeometry(); // Supondo que isso retorne um Geometry JTS
        Geometry bufferedGeomTerreno = geomTerreno.buffer(0.0000000001);
        Geometry geomOutroTerreno = neighbor.getGeometry();
        Geometry bufferedGeomOutroTerreno = geomOutroTerreno.buffer(0.0000000001);            // Verificar se as geometrias tocam ou intersectam
        return bufferedGeomTerreno.touches(bufferedGeomOutroTerreno) || bufferedGeomTerreno.intersects(bufferedGeomOutroTerreno);
    }

    public static void generateGraph() {
        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();

        mxGraph.getModel().beginUpdate();
        try {
            // Mapeia cada vértice a um objeto de visualização
            Map<Integer, Object> vertexMap = new HashMap<>();

            // Adiciona os vértices ao gráfico
            for (Integer vertex : grafo.vertexSet()) {
                Object v = mxGraph.insertVertex(parent, null, vertex.toString(), 0, 0, 80, 30);
                vertexMap.put(vertex, v);
            }

            // Adiciona as arestas ao gráfico
            for (DefaultEdge edge : grafo.edgeSet()) {
                Integer source = grafo.getEdgeSource(edge);
                Integer target = grafo.getEdgeTarget(edge);
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
        frame.setSize(600, 400); // Tamanho ajustado para maior visibilidade
        frame.setVisible(true);
    }

}

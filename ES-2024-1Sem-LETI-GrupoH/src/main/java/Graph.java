import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private static Map<Integer, Set<Integer>> grafo = new HashMap<>();

    public static Map<Integer, Set<Integer>> getGrafo() {
        //grafo = new HashMap<>();
        return grafo;
    }

    public static void CreateGraph(Map<Integer,Terreno> mapaTerreno) {

        if(grafo == null) {
            grafo = new HashMap<>();
        }

        for (Map.Entry<Integer, Terreno> entry : mapaTerreno.entrySet()) {
            Integer id = entry.getKey();
            Terreno t = entry.getValue();

            if (t.getOBJECTID() == 400)
                break;

            grafo.putIfAbsent(id, new HashSet<>());
            checkNeighbor(id, t, mapaTerreno);
        }

        generateGraph();
    }

    private static void checkNeighbor(Integer id, Terreno t, Map<Integer, Terreno> mapaTerreno) {
        for (Map.Entry<Integer, Terreno> entry : mapaTerreno.entrySet()) {
            Integer neighborId = entry.getKey();
            Terreno neighbor = entry.getValue();

            // Verifica os critérios para ser um vizinho
            if (!id.equals(neighborId) && isNeighbor(t, neighbor)) {
                grafo.get(id).add(neighborId); // Adiciona a conexão no grafo

                // Garante bidirecionalidade (se necessário)
                grafo.putIfAbsent(neighborId, new HashSet<>());
                grafo.get(neighborId).add(id);
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
            for (Integer vertex : grafo.keySet()) {
                Object v = mxGraph.insertVertex(parent, null, vertex.toString(), 0, 0, 80, 30);
                vertexMap.put(vertex, v);
            }

            // Adiciona as arestas ao gráfico
            for (Map.Entry<Integer, Set<Integer>> entry : grafo.entrySet()) {
                Integer source = entry.getKey();
                for (Integer target : entry.getValue()) {
                    // Evita duplicar arestas em grafos não direcionados
                    if (source < target) {
                        mxGraph.insertEdge(parent, null, "", vertexMap.get(source), vertexMap.get(target));
                    }
                }
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
        frame.setSize(800, 600); // Tamanho ajustado para maior visibilidade
        frame.setVisible(true);

    }

}

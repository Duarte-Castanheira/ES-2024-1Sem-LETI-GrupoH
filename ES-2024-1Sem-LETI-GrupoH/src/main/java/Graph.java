import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.util.*;

/**
 * Classe para construção, análise e visualização de grafos de terrenos.
 */
public class Graph {

    private static DefaultUndirectedGraph<Integer, DefaultEdge> grafo = new DefaultUndirectedGraph<>(DefaultEdge.class);

    /**
     * Retorna o grafo atual.
     *
     * @return Grafo não direcionado contendo os vértices (terrenos) e arestas (relações de vizinhança).
     */
    public static DefaultUndirectedGraph<Integer, DefaultEdge> getGrafo() {
        return grafo;
    }

    /**
     * Cria o grafo com base no mapa de terrenos fornecido.
     *
     * @param mapaTerreno Mapa que contém os terrenos (ID como chave, objeto `Terreno` como valor).
     */
    public static void CreateGraph(Map<Integer, Terreno> mapaTerreno) {
        grafo = new DefaultUndirectedGraph<>(DefaultEdge.class);

        for (Map.Entry<Integer, Terreno> entry : mapaTerreno.entrySet()) {
            Integer id = entry.getKey();
            grafo.addVertex(id); // Adiciona o ID do terreno como vértice.
            System.out.println(id); // Log para depuração.
        }

        checkNeighbor(mapaTerreno);

        generateGraph();
    }

    /**
     * Verifica a vizinhança entre todos os terrenos e adiciona arestas ao grafo.
     *
     * @param mapaTerreno Mapa de terrenos.
     */
    private static void checkNeighbor(Map<Integer, Terreno> mapaTerreno) {
        List<Integer> terrenos = new ArrayList<>(grafo.vertexSet());

        for (int i = 0; i < terrenos.size(); i++) {
            for (int j = i + 1; j < terrenos.size(); j++) {
                Terreno t1 = mapaTerreno.get(terrenos.get(i));
                Terreno t2 = mapaTerreno.get(terrenos.get(j));

                if (isNeighbor(t1, t2)) {
                    grafo.addEdge(t1.getOBJECTID(), t2.getOBJECTID());
                    System.out.println(t1.getOBJECTID() + " é vizinho de " + t2.getOBJECTID());
                }
            }
        }
    }

    /**
     * Verifica se dois terrenos são vizinhos (contíguos).
     *
     * @param t Terreno 1.
     * @param neighbor Terreno 2.
     * @return `true` se os terrenos forem vizinhos, `false` caso contrário.
     */
    private static boolean isNeighbor(Terreno t, Terreno neighbor) {
        Geometry geomTerreno = t.getGeometry();
        Geometry bufferedGeomTerreno = geomTerreno.buffer(0.0000000001); // Buffer para corrigir imprecisões.
        Geometry geomOutroTerreno = neighbor.getGeometry();
        Geometry bufferedGeomOutroTerreno = geomOutroTerreno.buffer(0.0000000001);

        return bufferedGeomTerreno.touches(bufferedGeomOutroTerreno) || bufferedGeomTerreno.intersects(bufferedGeomOutroTerreno);
    }

    /**
     * Gera a visualização gráfica do grafo utilizando JGraphX.
     */
    public static void generateGraph() {
        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();

        mxGraph.getModel().beginUpdate();
        try {
            Map<Integer, Object> vertexMap = new HashMap<>();

            for (Integer vertex : grafo.vertexSet()) {
                Object v = mxGraph.insertVertex(parent, null, vertex.toString(), 0, 0, 80, 30);
                vertexMap.put(vertex, v);
            }

            for (DefaultEdge edge : grafo.edgeSet()) {
                Integer source = grafo.getEdgeSource(edge);
                Integer target = grafo.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", vertexMap.get(source), vertexMap.get(target));
            }
        } finally {
            mxGraph.getModel().endUpdate();
        }

        mxCircleLayout layout = new mxCircleLayout(mxGraph);
        layout.execute(mxGraph.getDefaultParent());

        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        JFrame frame = new JFrame("Visualização do Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.setSize(600, 400); // Dimensões da janela.
        frame.setVisible(true);
    }
}

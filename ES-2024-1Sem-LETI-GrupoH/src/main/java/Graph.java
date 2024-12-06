import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.util.*;

/**
 * Classe responsável por criar, manipular e visualizar um grafo utilizando JGraphT e JGraphX.
 */

public class Graph {
    private static DefaultUndirectedGraph<Integer, DefaultEdge> grafo = new DefaultUndirectedGraph<>(DefaultEdge.class);

    /**
     * Retorna o grafo atual.
     */

    public static DefaultUndirectedGraph<Integer, DefaultEdge> getGrafo() {
        return grafo;
    }

    /**
     * Cria o grafo a partir de um mapa de terrenos.
     *
     * @param mapaTerreno Mapa contendo os terrenos identificados por IDs.
     */

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

    /**
     * Verifica quais terrenos são vizinhos e adiciona arestas entre eles no grafo.
     *
     * @param mapaTerreno Mapa contendo os terrenos identificados por IDs.
     */

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

    /**
     * Determina se dois terrenos são vizinhos com base em suas geometrias.
     *
     * @param t        Terreno 1.
     * @param neighbor Terreno 2.
     * @return true se os terrenos forem vizinhos, false caso contrário.
     */

    private static boolean isNeighbor(Terreno t, Terreno neighbor) {
        Geometry geomTerreno = t.getGeometry();
        Geometry bufferedGeomTerreno = geomTerreno.buffer(0.0000000001);
        Geometry geomOutroTerreno = neighbor.getGeometry();
        Geometry bufferedGeomOutroTerreno = geomOutroTerreno.buffer(0.0000000001);
        return bufferedGeomTerreno.touches(bufferedGeomOutroTerreno) || bufferedGeomTerreno.intersects(bufferedGeomOutroTerreno);
    }

    /**
     * Cria a visualização gráfica do grafo utilizando JGraphX.
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
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

}
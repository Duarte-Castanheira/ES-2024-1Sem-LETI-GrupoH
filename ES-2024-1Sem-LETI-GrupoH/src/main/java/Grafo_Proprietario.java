import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.util.*;

/**
 * Classe responsável por construir um grafo baseado na relação de vizinhança entre os terrenos
 * e visualizar esse grafo utilizando a biblioteca JGraphT para modelagem e JGraphX para exibição.
 */
public class Grafo_Proprietario {

    private static final DefaultUndirectedGraph<Integer, DefaultEdge> grafo = new DefaultUndirectedGraph<>(DefaultEdge.class);

    /**
     * Construtor da classe que inicia a construção do grafo.
     *
     * @param terrenos Mapa de terrenos, onde a chave é o ID do terreno e o valor é o objeto `Terreno`.
     */
    public Grafo_Proprietario(Map<Integer, Terreno> terrenos) {
        construirGrafo(terrenos);
    }

    /**
     * Retorna o grafo construído.
     *
     * @return Grafo não direcionado.
     */
    public static DefaultUndirectedGraph<Integer, DefaultEdge> getGrafo() {
        return grafo;
    }

    /**
     * Constrói o grafo adicionando os proprietários como vértices e estabelecendo arestas
     * com base na contiguidade dos terrenos.
     *
     * @param mapaTerreno Mapa contendo os terrenos.
     */
    public static void construirGrafo(Map<Integer, Terreno> mapaTerreno) {
        for (Map.Entry<Integer, Terreno> entry : mapaTerreno.entrySet()) {
            Integer owner = entry.getValue().getOWNER();
            if (!grafo.containsVertex(owner)) {
                grafo.addVertex(owner);
                System.out.println(owner);
            }
        }

        checkNeighbor(mapaTerreno);

        generateGraph();
    }

    /**
     * Verifica a contiguidade entre terrenos de diferentes proprietários e adiciona arestas
     * ao grafo onde há vizinhança.
     *
     * @param mapaTerreno Mapa contendo os terrenos.
     */
    private static void checkNeighbor(Map<Integer, Terreno> mapaTerreno) {
        Map<Integer, List<Terreno>> terrenosPorOwner = new HashMap<>();
        for (Terreno terreno : mapaTerreno.values()) {
            terrenosPorOwner
                    .computeIfAbsent(terreno.getOWNER(), _ -> new ArrayList<>())
                    .add(terreno);
        }

        Set<Integer> owners = terrenosPorOwner.keySet();

        for (Integer owner1 : owners) {
            for (Integer owner2 : owners) {
                if (owner2 >= owner1)
                    break;

                List<Terreno> terrenosOwner1 = terrenosPorOwner.get(owner1);
                List<Terreno> terrenosOwner2 = terrenosPorOwner.get(owner2);

                for (Terreno t1 : terrenosOwner1) {
                    for (Terreno t2 : terrenosOwner2) {
                        // Adiciona uma aresta se os terrenos forem contíguos.
                        if (saoContiguos(t1, t2) && !grafo.containsEdge(owner1, owner2)) {
                            grafo.addEdge(owner1, owner2);
                            System.out.println(owner1 + " é vizinho de " + owner2);
                        }
                    }
                }
            }
        }
    }

    /**
     * Verifica se dois terrenos são contíguos.
     *
     * @param t1 Terreno 1.
     * @param t2 Terreno 2.
     * @return `true` se os terrenos forem contíguos, `false` caso contrário.
     */
    public static boolean saoContiguos(Terreno t1, Terreno t2) {
        try {
            Geometry geomTerreno = t1.getGeometry();
            Geometry bufferedGeomTerreno = geomTerreno.buffer(0.000000001);
            Geometry geomOutroTerreno = t2.getGeometry();
            Geometry bufferedGeomOutroTerreno = geomOutroTerreno.buffer(0.000000001);

            return bufferedGeomTerreno.touches(bufferedGeomOutroTerreno) || bufferedGeomTerreno.intersects(bufferedGeomOutroTerreno);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gera a visualização gráfica do grafo usando a biblioteca JGraphX.
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
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
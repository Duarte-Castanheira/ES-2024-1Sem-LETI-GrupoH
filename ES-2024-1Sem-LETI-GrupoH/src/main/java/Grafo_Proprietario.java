import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.util.*;

public class Grafo_Proprietario {

    private static final DefaultUndirectedGraph<Integer, DefaultEdge> grafo = new DefaultUndirectedGraph<>(DefaultEdge.class);

    public Grafo_Proprietario(Map<Integer, Terreno> terrenos) {
        construirGrafo(terrenos);
    }

    public static DefaultUndirectedGraph<Integer, DefaultEdge> getGrafo() {
        return grafo;
    }

    public static void construirGrafo(Map<Integer, Terreno> mapaTerreno) {
    int i = 0;
        for (Map.Entry<Integer, Terreno> entry : mapaTerreno.entrySet()) {
            Integer owner = entry.getValue().getOWNER();
            Terreno t = entry.getValue();


                if(!grafo.containsVertex(owner)) {
                    grafo.addVertex(owner);
                    System.out.println(owner);
                }
                i++;
                if(i>1000)break;

        }
        checkNeighbor(mapaTerreno);
        generateGraph();
    }

    private static void checkNeighbor(Map<Integer,Terreno> mapaTerreno) {
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
                        if (saoContiguos(t1, t2) && !grafo.containsEdge(owner1, owner2)) {
                            grafo.addEdge(owner1, owner2);
                            System.out.println(owner1 + " é vizinho de " + owner2);
                        }
                    }
                }


            }

        }
    }
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
        frame.setSize(800, 600); // Tamanho ajustado para maior visibilidade
        frame.setVisible(true);

    }
}

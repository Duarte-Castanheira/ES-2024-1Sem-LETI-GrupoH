import org.jgrapht.graph.Pseudograph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private List<Terreno> terrenos;

    @BeforeEach
    void setUp() {
        terrenos = new ArrayList<>();

        Geometry g1 = new Geometry();
        Geometry g2 = new Geometry();
        g1.GetArestas("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.5033999998 3623637.4715, 299218.04000000004 3623638.4800000004, 299232.7400000002 3623644.6799999997, 299236.6233999999 3623637.1974, 299236.93709999975 3623636.7885999996, 299238.04000000004 3623633.4800000004, 299222.63999999966 3623627.1799999997, 299218.5203999998 3623637.4791)))");
        g2.GetArestas("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.04000000004 3623638.4800000004, 298724.26999999955 3623185.7200000007, 298723.8854 3623185.681500001, 298723.8854 3623185.6338, 298717.2167999996 3623184.6405999996, 298716.2909000004 3623184.495100001, 298716.1699999999 3623184.5700000003, 298711.51999999955 3623184.17, 298709.1414000001 3623183.7961999997, 298708.48000000045 3623183.3200000003, 298705.6799999997 3623183.2200000007, 298704.5800000001 3623183.3200000003, 298703.98000000045 3623184.119999999, 298703.48000000045 3623190.7200000007, 298704.0525000002 3623190.7905, 298704.0488999998 3623190.8441000003, 298705.574 3623190.9777000006, 298709.98000000045 3623191.5199999996, 298710.0937999999 3623191.3737000003, 298724.1991999997 3623192.6094000004)))");

        Terreno terreno1 = new Terreno(1,"7343148.0","2,99624E+12",57.2469341921808,202.05981432070362, g1,93,"Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno2 = new Terreno(2,"7344660.0", "2,99622E+12",55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)");

        terrenos.add(terreno1);
        terrenos.add(terreno2);

        //System.out.println(terreno1.getGeometry().Arestas.getFirst().toString());
        //System.out.println(terreno2.getGeometry().Arestas.getFirst().toString());

    }

    @Test
    public void testCreateGraph() {
        Graph.CreateGraph(terrenos);
        Pseudograph<Terreno, Coordenadas> grafo = Graph.getGrafo();

        assertNotNull(grafo);
        assertEquals(2, grafo.vertexSet().size());
        assertEquals(1, grafo.edgeSet().size());
    }

    @Test
    public void testEmptyGraph() {
        Graph.CreateGraph(new ArrayList<>());

        Pseudograph<Terreno, Coordenadas> grafo = Graph.getGrafo();
        assertNotNull(grafo);
        assertTrue(grafo.vertexSet().isEmpty());
    }

    @Test
    public void testgenerateGraph() {

        Graph.CreateGraph(terrenos);
        Pseudograph<Terreno, Coordenadas> grafo = Graph.getGrafo();
        assertNotNull(grafo);

        try {
            Graph.generateGraph();
        } catch (Exception e) {
            fail("A exportação e visualização do grafo falhou: " + e.getMessage());
        }
    }

}
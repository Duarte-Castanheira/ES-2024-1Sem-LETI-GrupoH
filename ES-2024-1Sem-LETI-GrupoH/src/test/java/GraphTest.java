import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Map<Integer,Terreno> terrenos;

    @BeforeEach
    void setUp() {
        terrenos = new HashMap<>();

        WKTReader reader = new WKTReader();
        MultiPolygon g1;
        MultiPolygon g2;
        try {
            g1 = (MultiPolygon) reader.read("MULTIPOLYGON (((299126.3727000002 3621564.782299999, 299126 3621564.8900000006, 299126 3621567.0111, 299126 3621578.3457999993, 299126.0499999998 3621578.4000000004, 299125.3499999996 3621586.9499999993, 299116.8499999996 3621590.3000000007, 299116.72559999954 3621590.2985999994, 299121.2000000002 3621605.4399999995, 299122.2000000002 3621616.1400000006, 299126.6500000004 3621632.99, 299126.8523000004 3621641.7272999994, 299130.63159999996 3621649.364499999, 299130.6320000002 3621649.3619, 299132.1200000001 3621650.7300000004, 299134.21999999974 3621641.630000001, 299136.01999999955 3621634.2300000004, 299136.01999999955 3621630.2300000004, 299140.3200000003 3621624.7300000004, 299146.9199999999 3621619.5299999993, 299154.9199999999 3621612.7300000004, 299146.6330000004 3621604.814099999, 299146.63320000004 3621604.8104, 299146.6299999999 3621604.8100000005, 299144.3200000003 3621602.630000001, 299146.8200000003 3621597.99, 299146.8216000004 3621597.9913, 299146.8216000004 3621597.9892999995, 299153.9386 3621578.315300001, 299154.4000000004 3621577.039999999, 299154.41669999994 3621576.8936, 299164.88999999966 3621580.59, 299165.0613000002 3621580.6077999994, 299167.3200000003 3621582.0299999993, 299167.5186999999 3621583.8187000006, 299167.51999999955 3621583.83, 299166.4199999999 3621590.630000001, 299168.51999999955 3621588.0299999993, 299171.0723000001 3621585.146299999, 299174.76800000016 3621580.9705999997, 299176.21999999974 3621579.33, 299178.68900000025 3621576.3672, 299178.71999999974 3621576.33, 299167.3809000002 3621578.8166000005, 299126.3727000002 3621564.782299999)))");
            g2 = (MultiPolygon) reader.read("MULTIPOLYGON (((299114.58139999956 3621586.0404000003, 299116.63999999966 3621591.5299999993, 299117.03830000013 3621591.3567999993, 299116.72559999954 3621590.2985999994, 299116.8499999996 3621590.3000000007, 299125.3499999996 3621586.9499999993, 299126.0499999998 3621578.4000000004, 299126 3621578.3457999993, 299126 3621567.0111, 299121.83999999985 3621567.130000001, 299120.2400000002 3621572.33, 299113.63999999966 3621574.130000001, 299111.2400000002 3621568.9299999997, 299118.04000000004 3621565.7300000004, 299112.63999999966 3621556.33, 299099.04000000004 3621565.130000001, 299099.04000000004 3621566.9299999997, 299107.4400000004 3621571.7300000004, 299111.04000000004 3621574.9299999997, 299113.63999999966 3621583.5299999993, 299114.58139999956 3621586.0404000003)))");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Terreno terreno1 = new Terreno(1,"7343148.0","2,99624E+12",57.2469341921808,202.05981432070362, g1,93,"Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno2 = new Terreno(2,"7344660.0", "2,99622E+12",55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)");

        terrenos.put(terreno1.getOBJECTID(),terreno1);
        terrenos.put(terreno2.getOBJECTID(),terreno2);

        //System.out.println(terreno1.getGeometry().Arestas.getFirst().toString());
        //System.out.println(terreno2.getGeometry().Arestas.getFirst().toString());

    }

    @Test
    public void testCreateGraph() {

        // Cria o grafo usando o método adaptado
        Graph.CreateGraph(terrenos);

        // Obtém o grafo gerado
        Map<Integer, Set<Integer>> grafo = Graph.getGrafo();

        // Verificações
        assertNotNull(grafo); // Certifica-se de que o grafo não seja nulo

        assertEquals(2, grafo.size());
        assertEquals(Set.of(2), grafo.get(1)); // Terreno 1 conecta ao terreno 2
        assertEquals(Set.of(1), grafo.get(2)); // Terreno 2 conecta ao terreno 1
    }

    @Test
    public void testEmptyGraph() {

        Map<Integer, Set<Integer>> grafo = Graph.getGrafo();
        assertNotNull(grafo);
        assertEquals(true, grafo.entrySet().isEmpty());
    }

    @Test
    public void testgenerateGraph() {

        Graph.CreateGraph(terrenos);
        Map<Integer, Set<Integer>> grafo = Graph.getGrafo();
        assertNotNull(grafo);

        try {
            Graph.generateGraph();
        } catch (Exception e) {
            fail("A exportação e visualização do grafo falhou: " + e.getMessage());
        }
    }

}
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.Pseudograph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para o Graph
 */

class GraphTest {
    private Map<Integer,Terreno> terrenos;

    /**
     * Método para configurar o estado inicial antes de cada teste
     */

    @BeforeEach
    void setUp() {
        terrenos = new HashMap<>();

        WKTReader reader = new WKTReader();
        MultiPolygon g1;
        MultiPolygon g2;
        MultiPolygon g3;
        try {
            g1 = (MultiPolygon) reader.read("MULTIPOLYGON (((299126.3727000002 3621564.782299999, 299126 3621564.8900000006, 299126 3621567.0111, 299126 3621578.3457999993, 299126.0499999998 3621578.4000000004, 299125.3499999996 3621586.9499999993, 299116.8499999996 3621590.3000000007, 299116.72559999954 3621590.2985999994, 299121.2000000002 3621605.4399999995, 299122.2000000002 3621616.1400000006, 299126.6500000004 3621632.99, 299126.8523000004 3621641.7272999994, 299130.63159999996 3621649.364499999, 299130.6320000002 3621649.3619, 299132.1200000001 3621650.7300000004, 299134.21999999974 3621641.630000001, 299136.01999999955 3621634.2300000004, 299136.01999999955 3621630.2300000004, 299140.3200000003 3621624.7300000004, 299146.9199999999 3621619.5299999993, 299154.9199999999 3621612.7300000004, 299146.6330000004 3621604.814099999, 299146.63320000004 3621604.8104, 299146.6299999999 3621604.8100000005, 299144.3200000003 3621602.630000001, 299146.8200000003 3621597.99, 299146.8216000004 3621597.9913, 299146.8216000004 3621597.9892999995, 299153.9386 3621578.315300001, 299154.4000000004 3621577.039999999, 299154.41669999994 3621576.8936, 299164.88999999966 3621580.59, 299165.0613000002 3621580.6077999994, 299167.3200000003 3621582.0299999993, 299167.5186999999 3621583.8187000006, 299167.51999999955 3621583.83, 299166.4199999999 3621590.630000001, 299168.51999999955 3621588.0299999993, 299171.0723000001 3621585.146299999, 299174.76800000016 3621580.9705999997, 299176.21999999974 3621579.33, 299178.68900000025 3621576.3672, 299178.71999999974 3621576.33, 299167.3809000002 3621578.8166000005, 299126.3727000002 3621564.782299999)))");
            g2 = (MultiPolygon) reader.read("MULTIPOLYGON (((299114.58139999956 3621586.0404000003, 299116.63999999966 3621591.5299999993, 299117.03830000013 3621591.3567999993, 299116.72559999954 3621590.2985999994, 299116.8499999996 3621590.3000000007, 299125.3499999996 3621586.9499999993, 299126.0499999998 3621578.4000000004, 299126 3621578.3457999993, 299126 3621567.0111, 299121.83999999985 3621567.130000001, 299120.2400000002 3621572.33, 299113.63999999966 3621574.130000001, 299111.2400000002 3621568.9299999997, 299118.04000000004 3621565.7300000004, 299112.63999999966 3621556.33, 299099.04000000004 3621565.130000001, 299099.04000000004 3621566.9299999997, 299107.4400000004 3621571.7300000004, 299111.04000000004 3621574.9299999997, 299113.63999999966 3621583.5299999993, 299114.58139999956 3621586.0404000003)))");
            g3 = (MultiPolygon) reader.read("MULTIPOLYGON (((296745.9685000004 3622220.6369000003, 296746.0366000002 3622220.6301000006, 296747.83999999985 3622221.869999999, 296752.2400000002 3622220.67, 296753.67899999954 3622219.8659000006, 296757.0736999996 3622219.5264, 296757.34669999965 3622218.9481000006, 296759.12600000016 3622216.017999999, 296758.75019999966 3622215.516899999, 296758.83999999985 3622214.17, 296758.33999999985 3622212.2699999996, 296754.9400000004 3622207.7699999996, 296752.8678000001 3622203.3665999994, 296752.54000000004 3622202.67, 296751.63609999977 3622201.6088999994, 296747.9400000004 3622197.2699999996, 296740.13999999966 3622188.4700000007, 296733.83999999985 3622180.7699999996, 296731.9400000004 3622176.7699999996, 296730.63999999966 3622172.67, 296730.04000000004 3622168.9700000007, 296730.4400000004 3622165.0700000003, 296731.4400000004 3622159.4700000007, 296731.87650000025 3622156.352499999, 296732.8203999996 3622154.198999999, 296731.8411999997 3622153.0692, 296731.86000000034 3622153.0199999996, 296728.0599999996 3622148.8200000003, 296716.3947999999 3622163.3636000007, 296716.45999999996 3622163.42, 296714.87320000026 3622165.2607000005, 296713.4880999997 3622166.9876000006, 296713.40869999956 3622166.9595, 296711.45999999996 3622169.2200000007, 296706.0954999998 3622175.1889999993, 296705.07639999967 3622179.3636000007, 296704.7138 3622180.8489999995, 296704.3569 3622180.908500001, 296700.3969999999 3622191.7983999997, 296702.31520000007 3622192.7070000004, 296709.04000000004 3622198.369999999, 296716.34300000034 3622202.1175999995, 296719.8668999998 3622206.6482999995, 296726.46690000035 3622211.2682000007, 296731.4167999998 3622213.2481999993, 296738.3468000004 3622214.8981999997, 296740.4463999998 3622217.1213000007, 296742.66000000015 3622218.0700000003, 296744.6671000002 3622219.6273, 296744.63999999966 3622219.67, 296745.36199999973 3622220.1663000006, 296745.9685000004 3622220.6369000003)))");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Terreno terreno1 = new Terreno(1,"7343148.0","2,99624E+12",57.2469341921808,202.05981432070362, g1,93,"Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno2 = new Terreno(2,"7344660.0", "2,99622E+12",55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno3 = new Terreno(1944,"7349717.0","2,97621E+12", 202.08044612398064, 1815.5472539299403, g3,24,"Calheta","Calheta", "Ilha da Madeira (Madeira)");
        terrenos.put(terreno1.getOBJECTID(),terreno1);
        terrenos.put(terreno2.getOBJECTID(),terreno2);
        terrenos.put(terreno3.getOBJECTID(),terreno3);
    }

    /**
     * Testa a criação do grafo
     */

    @Test
    public void testCreateGraph() {

        // Cria o grafo
        Graph.CreateGraph(terrenos);
        DefaultUndirectedGraph<Integer, DefaultEdge> grafo = Graph.getGrafo();

        assertNotNull(grafo);
        assertEquals(3, grafo.vertexSet().size());
        Set<Integer> vizinhosTerreno1 = new HashSet<>();
        for (DefaultEdge edge : grafo.edgesOf(1)) {
            vizinhosTerreno1.add(grafo.getEdgeSource(edge) == 1 ? grafo.getEdgeTarget(edge) : grafo.getEdgeSource(edge));
        }
        assertEquals(Set.of(2), vizinhosTerreno1);
    }

    /**
     * Testa se o grafo inicial está vazio
     */

    @Test
    public void testEmptyGraph() {

        DefaultUndirectedGraph<Integer, DefaultEdge> grafo = Graph.getGrafo();
        assertNotNull(grafo);
        assertTrue(grafo.vertexSet().isEmpty());
    }

    /**
     * Testa a criação e visualização do grafo
     */

    @Test
    public void testgenerateGraph() {

        Graph.CreateGraph(terrenos);
        DefaultUndirectedGraph<Integer, DefaultEdge> grafo = Graph.getGrafo();
        assertNotNull(grafo);

        try {
            Graph.generateGraph();
        } catch (Exception e) {
            fail("A exportação e visualização do grafo falhou: " + e.getMessage());
        }
    }

}
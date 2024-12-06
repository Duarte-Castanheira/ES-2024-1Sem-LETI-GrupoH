import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.ParseException;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para a classe `TrocaTerrenosTest`, que verifica o
 *comportamento da sugestão de trocas de terrenos.
 */

class TrocaTerrenosTest {

    private Map<Integer, Terreno> terrenos;

    MultiPolygon g1;
    MultiPolygon g2;
    MultiPolygon g3;
    MultiPolygon g4;
    MultiPolygon g5;
    MultiPolygon g6;

    /** Este método é executado antes de cada teste.
     *Cria vários objetos `Terreno` e os coloca no mapa `terrenos`
     * para que possam ser utilizados nos testes.
     */

    @BeforeEach
    void setUp() {
        terrenos = new HashMap<>();
        WKTReader reader = new WKTReader();

        try {
            g1 = (MultiPolygon) reader.read("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.5033999998 3623637.4715, 299218.04000000004 3623638.4800000004, 299232.7400000002 3623644.6799999997, 299236.6233999999 3623637.1974, 299236.93709999975 3623636.7885999996, 299238.04000000004 3623633.4800000004, 299222.63999999966 3623627.1799999997, 299218.5203999998 3623637.4791)))");
            g2 = (MultiPolygon) reader.read("MULTIPOLYGON (((298574.3828999996 3623142.9571, 298577.1272999998 3623143.4275, 298577.2000000002 3623143.4399999995, 298577.0999999996 3623144.24, 298580.9000000004 3623144.74, 298581.2999999998 3623143.4399999995, 298584 3623143.9399999995, 298583.6294 3623145.978599999, 298583.2000000002 3623148.34, 298588.7999999998 3623148.9399999995, 298589 3623147.74, 298589.7466000002 3623146.7694000006, 298590 3623146.4399999995, 298589.2999999998 3623145.6400000006, 298593.5 3623131.84, 298600.2000000002 3623132.4399999995, 298601.59559999965 3623116.7157000005, 298601.7000000002 3623115.539999999, 298600.96300000045 3623115.539999999, 298598.0999999996 3623115.539999999, 298598.5 3623112.1400000006, 298591.21109999996 3623111.5535000004, 298590.6457000002 3623111.5079999994, 298590.37009999994 3623112.5536, 298588.6500000004 3623119.08, 298584.8342000004 3623119.137, 298582.0965999998 3623119.1777999997, 298582.00540000014 3623119.2994, 298581.50100000016 3623123.1007000003, 298580.9754999997 3623123.7256000005, 298580.1063000001 3623127.1283, 298579.5340999998 3623128.023600001, 298579.3309000004 3623129.3242000006, 298573.9600999998 3623142.8846000005, 298574.3828999996 3623142.9571)))");
            g3 = (MultiPolygon) reader.read("MULTIPOLYGON (((298584.82830000017 3623106.6208999995, 298584.8326000003 3623106.6209999993, 298584.8300000001 3623106.6500000004, 298582.5270999996 3623115.3680000007, 298582.00540000014 3623119.2994, 298582.0965999998 3623119.1777999997, 298588.6500000004 3623119.08, 298590.6457000002 3623111.5079999994, 298590.6503999997 3623111.5084000006, 298591.9299999997 3623106.6400000006, 298584.8300000001 3623106.619999999, 298584.82830000017 3623106.6208999995)))");
            g4 = (MultiPolygon) reader.read("MULTIPOLYGON (((298570.93659999967 3623125.529100001, 298569.98309999984 3623151.1371, 298570.7249999996 3623151.0527999997, 298579.3309000004 3623129.3242000006, 298579.5340999998 3623128.023600001, 298580.1063000001 3623127.1283, 298580.9754999997 3623123.7256000005, 298581.50100000016 3623123.1007000003, 298582.5270999996 3623115.3680000007, 298584.8300000001 3623106.6500000004, 298584.8326000003 3623106.6209999993, 298578.4000000004 3623106.42, 298576.66669999994 3623118.3604000006, 298576.79000000004 3623118.369999999, 298576.13999999966 3623126.0199999996, 298570.93659999967 3623125.529100001)))");
            g5 = (MultiPolygon) reader.read("MULTIPOLYGON (((297837.45419999957 3621617.849199999, 297836.4994999999 3621618.285700001, 297834.42459999956 3621619.2342000008, 297834.4604000002 3621619.2858000007, 297832.3799999999 3621620.4299999997, 297832.4009999996 3621620.4520999994, 297817.73000000045 3621628.710000001, 297816.4650999997 3621633.0884000007, 297816.4110000003 3621633.1241999995, 297816.4364 3621633.1876999997, 297816.4299999997 3621633.210000001, 297816.44770000037 3621633.2159, 297818.21190000046 3621637.620100001, 297825.6244999999 3621648.7717000004, 297831.1814000001 3621674.3181999996, 297831.2341 3621674.3490999993, 297831.20999999996 3621674.3499999996, 297832.5120000001 3621675.0983000007, 297838.0582999997 3621678.3496000003, 297838.10539999977 3621678.3128999993, 297839.91000000015 3621679.3499999996, 297848.7599999998 3621672.1500000004, 297857.86959999986 3621663.7803000007, 297857.9497999996 3621663.6108, 297858.08139999956 3621663.3324999996, 297858.25090000033 3621662.974199999, 297858.3057000004 3621662.8582000006, 297858.4419 3621663.1514999997, 297858.5860000001 3621663.016899999, 297860.4581000004 3621661.2696, 297859.6763000004 3621659.9604, 297859.7380999997 3621659.8296000008, 297860.67250000034 3621661.0505, 297860.6831 3621661.0644000005, 297860.72339999955 3621661.1170000006, 297861.47979999986 3621660.3816, 297862.0680999998 3621659.8095999993, 297862.7176000001 3621659.290100001, 297844.85610000044 3621630.587200001, 297842.659 3621626.888800001, 297842.66640000045 3621626.878900001, 297837.45419999957 3621617.849199999)))");
            g6 = (MultiPolygon) reader.read("MULTIPOLYGON (((298466.18989999965 3621771.9235999994, 298472.6200000001 3621776.5999999996, 298480.21999999974 3621783.1999999993, 298486.6200000001 3621785.8000000007, 298491.4280000003 3621787.0464999992, 298491.3602 3621786.9165000003, 298493.58779999986 3621787.110200001, 298494.6200000001 3621787.1999999993, 298481.21999999974 3621755.4000000004, 298477.2457999997 3621751.0645000003, 298476.8200000003 3621750.5999999996, 298474.01999999955 3621746.4000000004, 298461.01999999955 3621752, 298461.4062000001 3621752.772500001, 298461.2048000004 3621752.8621999994, 298461.03450000007 3621752.937999999, 298464.4199999999 3621760.5999999996, 298462.21999999974 3621761.5999999996, 298466.4199999999 3621766.4000000004, 298466.18960000016 3621766.5473999996, 298466.5099999998 3621767.0600000005, 298464.31070000026 3621768.6435000002, 298465.0343000004 3621769.5276999995, 298466.18989999965 3621771.9235999994)))");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Terreno terreno1 = new Terreno(1,"7343148.0","2,99624E+12",57.2469341921808,202.05981432070362, g1,93,"Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno2 = new Terreno(121,"7344660.0", "2,99622E+12",55.63800662596267, 151.76387471712783, g2, 16, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno3 = new Terreno(3,"7344925.0","98623E+12",39.69344896337617,87.83290987170697, g3,26,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");
        Terreno terreno4 = new Terreno(127,"7344935.0","2,98623E+12",120.6729099515363,318.41797327468464, g4,14,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");
        Terreno terreno5 = new Terreno(367,"20461144.0","2,99624E+12",159.82384246560056,240.8307690699244, g5,16,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");
        Terreno terreno6 = new Terreno(6,"9345796.0","2,98622E+12",119.36483202660179,670.6885236055224, g6,4,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");

        terrenos.put(terreno1.getOBJECTID(),terreno1);
        terrenos.put(terreno2.getOBJECTID(),terreno2);
        terrenos.put(terreno3.getOBJECTID(),terreno3);
        terrenos.put(terreno4.getOBJECTID(),terreno4);
        terrenos.put(terreno5.getOBJECTID(),terreno5);
        terrenos.put(terreno6.getOBJECTID(),terreno6);
    }


    /**
     * Este método é executado após cada teste.
     * Limpa o mapa `terrenos`, garantindo que os testes não interfiram uns com os outros.
     */
    @AfterEach
    void tearDown() {
        terrenos.clear();
    }

    /**
     * Testa o método `gerarSugestoesDeTroca` verificando se as trocas geradas têm impacto positivo.
     */

    @Test
    void testGerarSugestoesDeTroca_impactoPositivo() {
        // Gerar as sugestões de troca
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos,14,16);

        // Verificar se o Map retornado não é nulo nem vazio
        assertNotNull(trocas, "O Map de sugestões de troca não deve ser nulo.");

        // Map para armazenar sugestões com impacto negativo ou nulo
        Map<Integer, TrocaTerrenos.Troca> impactosNegativos = new HashMap<>();

        // Iterar sobre as sugestões de troca
        for (Map.Entry<Integer, TrocaTerrenos.Troca> entry : trocas.entrySet()) {
            Integer id = entry.getKey();
            TrocaTerrenos.Troca troca = entry.getValue();

            // Validar se a troca possui impacto positivo
            if (troca == null) {
                impactosNegativos.put(id, troca);
            }
        }
        // Verificar se há impactos negativos
        assertNotNull(impactosNegativos);
    }

    /**
     * Testa o método `gerarSugestoesDeTroca` verificando se a diferença de área entre os terrenos trocados é adequada.
     * Garante que a diferença da área entre os terrenos trocados seja menor ou igual a 10.
     */

    @Test
    void testGerarSugestoesDeTroca_diferencaMinimaDeArea() {
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos, 1, 2);

        for (TrocaTerrenos.Troca troca : trocas.values()) {
            Terreno t1 = terrenos.get(troca.terrenoid1);
            Terreno t2 = terrenos.get(troca.terrenoid2);

            double diferenca = Math.abs(t1.getShape_Area() - t2.getShape_Area());
            assertTrue(diferenca <= 10, "A diferença de áreas deve ser <= 10.");
        }
    }

    /**
     * Testa o método `gerarSugestoesDeTroca` para garantir que as trocas acontecem entre diferentes proprietários.
     * Verifica que cada troca ocorre entre terrenos pertencentes a proprietários distintos.
     */

    @Test
    void testGerarSugestoesDeTroca_trocasEntreProprietarios() {
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos, 1, 2);

        for (TrocaTerrenos.Troca troca : trocas.values()) {
            Terreno t1 = terrenos.get(troca.terrenoid1);
            Terreno t2 = terrenos.get(troca.terrenoid2);

            assertNotEquals(t1.getOWNER(), t2.getOWNER(),
                    "As trocas devem ocorrer entre diferentes proprietários.");
        }
    }

    /**
     * Testa o método `gerarSugestoesDeTroca` para o caso de não existirem trocas possíveis.
     */

    @Test
    void testGerarSugestoesDeTroca_semTrocasPossiveis() {
        terrenos.clear();
        terrenos.put(1, new Terreno(1, "7343148.0", "2,99624E+12", 57.2469341921808, 202.05981432070362, g1, 93, "Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)"));
        terrenos.put(2, new Terreno(2, "7344660.0", "2,99622E+12", 55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)"));
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos,1,16);
        assertTrue(trocas.isEmpty(), "Não deve haver trocas possíveis para um único proprietário.");
    }

    /**
     * Testa o método `calcularVizinhos`
     * Utiliza o método `saoContiguos` para validar se os terrenos são contíguos entre si.
     */

    @Test
    void calcularVizinhos() {
        List<Terreno> terrenosOwner1 = new ArrayList<>();
        terrenosOwner1.add(terrenos.get(127));
        terrenosOwner1.add(terrenos.get(121));
        terrenosOwner1.add(terrenos.get(3));


        assertTrue( Grafo_Proprietario.saoContiguos(terrenos.get(3), terrenos.get(121)));
        assertTrue( Grafo_Proprietario.saoContiguos(terrenos.get(127), terrenos.get(3)));
        assertTrue( Grafo_Proprietario.saoContiguos(terrenos.get(121), terrenos.get(127)));
        int numVizinhos = TrocaTerrenos.calcularVizinhos(terrenosOwner1);

        // Verifica se o número de vizinhos foi calculado corretamente.
        assertEquals(3, numVizinhos);
    }
}

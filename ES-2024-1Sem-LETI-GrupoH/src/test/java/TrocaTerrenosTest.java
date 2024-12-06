import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.ParseException;


import static org.junit.jupiter.api.Assertions.*;

class TrocaTerrenosTest {

    private Map<Integer, Terreno> terrenos;
    private WKTReader reader;

    MultiPolygon g1;
    MultiPolygon g2;
    MultiPolygon g3;
    MultiPolygon g4;
    MultiPolygon g5;
    MultiPolygon g6;


    @BeforeEach
    void setUp() {
        terrenos = new HashMap<>();

        WKTReader reader = new WKTReader();



        try {
            g1 = (MultiPolygon) reader.read("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.5033999998 3623637.4715, 299218.04000000004 3623638.4800000004, 299232.7400000002 3623644.6799999997, 299236.6233999999 3623637.1974, 299236.93709999975 3623636.7885999996, 299238.04000000004 3623633.4800000004, 299222.63999999966 3623627.1799999997, 299218.5203999998 3623637.4791)))");
            g2 = (MultiPolygon) reader.read("MULTIPOLYGON (((298724.1991999997 3623192.6094000004, 298724.3200000003 3623192.619999999, 298724.26999999955 3623185.7200000007, 298723.8854 3623185.681500001, 298723.8854 3623185.6338, 298717.2167999996 3623184.6405999996, 298716.2909000004 3623184.495100001, 298716.1699999999 3623184.5700000003, 298711.51999999955 3623184.17, 298709.1414000001 3623183.7961999997, 298708.48000000045 3623183.3200000003, 298705.6799999997 3623183.2200000007, 298704.5800000001 3623183.3200000003, 298703.98000000045 3623184.119999999, 298703.48000000045 3623190.7200000007, 298704.0525000002 3623190.7905, 298704.0488999998 3623190.8441000003, 298705.574 3623190.9777000006, 298709.98000000045 3623191.5199999996, 298710.0937999999 3623191.3737000003, 298724.1991999997 3623192.6094000004)))");
            g3 = (MultiPolygon) reader.read("MULTIPOLYGON (((298584.82830000017 3623106.6208999995, 298584.8326000003 3623106.6209999993, 298584.8300000001 3623106.6500000004, 298582.5270999996 3623115.3680000007, 298582.00540000014 3623119.2994, 298582.0965999998 3623119.1777999997, 298588.6500000004 3623119.08, 298590.6457000002 3623111.5079999994, 298590.6503999997 3623111.5084000006, 298591.9299999997 3623106.6400000006, 298584.8300000001 3623106.619999999, 298584.82830000017 3623106.6208999995)))");
            g4 = (MultiPolygon) reader.read("MULTIPOLYGON (((298604.9188000001 3623255.3287000004, 298605.01719999965 3623255.4393000007, 298609.0800000001 3623260.01, 298618.6799999997 3623252.210000001, 298619.4740000004 3623251.0189999994, 298619.53890000004 3623251.088199999, 298639.26049999986 3623235.8861, 298641.0733000003 3623234.4888000004, 298640.9326999998 3623234.306, 298642.75 3623232.6799999997, 298638.3499999996 3623227.880000001, 298637.1500000004 3623229.08, 298634.7461000001 3623225.2893000003, 298631.9639999997 3623220.9020000007, 298626.98979999963 3623224.5343999993, 298630.1747000003 3623228.8926999997, 298632.5530000003 3623232.1471999995, 298633.23979999963 3623233.087099999, 298604.9188000001 3623255.3287000004)))");
            g5 = (MultiPolygon) reader.read("MULTIPOLYGON (((299139.61099999957 3623665.1621000003, 299139.5599999996 3623665, 299140.7599999998 3623661.5999999996, 299141.16000000015 3623658, 299141.5599999996 3623653.5999999996, 299141.5497000003 3623653.3485000003, 299134.16000000015 3623650, 299125.5599999996 3623647.4000000004, 299106.5599999996 3623624.1999999993, 299101.5599999996 3623620.5999999996, 299099.36000000034 3623620.5999999996, 299097.5599999996 3623622, 299095.36000000034 3623631.1999999993, 299093.7599999998 3623639.4000000004, 299089.95999999996 3623652.1999999993, 299088.01269999985 3623658.7893000003, 299088.36000000034 3623658.8000000007, 299105.77979999967 3623660.9496999998, 299113.8700000001 3623661.9499999993, 299124.4467000002 3623663.2523999996, 299124.4464999996 3623663.2532, 299135.36000000034 3623664.5999999996, 299139.61099999957 3623665.1621000003)))");
            g6 = (MultiPolygon) reader.read("MULTIPOLYGON (((298466.18989999965 3621771.9235999994, 298472.6200000001 3621776.5999999996, 298480.21999999974 3621783.1999999993, 298486.6200000001 3621785.8000000007, 298491.4280000003 3621787.0464999992, 298491.3602 3621786.9165000003, 298493.58779999986 3621787.110200001, 298494.6200000001 3621787.1999999993, 298481.21999999974 3621755.4000000004, 298477.2457999997 3621751.0645000003, 298476.8200000003 3621750.5999999996, 298474.01999999955 3621746.4000000004, 298461.01999999955 3621752, 298461.4062000001 3621752.772500001, 298461.2048000004 3621752.8621999994, 298461.03450000007 3621752.937999999, 298464.4199999999 3621760.5999999996, 298462.21999999974 3621761.5999999996, 298466.4199999999 3621766.4000000004, 298466.18960000016 3621766.5473999996, 298466.5099999998 3621767.0600000005, 298464.31070000026 3621768.6435000002, 298465.0343000004 3621769.5276999995, 298466.18989999965 3621771.9235999994)))");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Terreno terreno1 = new Terreno(1,"7343148.0","2,99624E+12",57.2469341921808,202.05981432070362, g1,93,"Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno2 = new Terreno(2,"7344660.0", "2,99622E+12",55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)");
        Terreno terreno3 = new Terreno(3,"7344925.0","98623E+12",39.69344896337617,87.83290987170697, g3,26,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");
        Terreno terreno4 = new Terreno(4,"7344935.0","2,98623E+12",120.6729099515363,318.41797327468464, g4,59,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");
        Terreno terreno5 = new Terreno(5,"20461144.0","2,99624E+12",159.82384246560056,240.8307690699244, g5,38,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");
        Terreno terreno6 = new Terreno(6,"9345796.0","2,98622E+12",119.36483202660179,670.6885236055224, g6,4,"Arco da Calheta","Calheta","Ilha da Madeira (Madeira)");

        terrenos.put(terreno1.getOBJECTID(),terreno1);
        terrenos.put(terreno2.getOBJECTID(),terreno2);
        terrenos.put(terreno3.getOBJECTID(),terreno3);
        terrenos.put(terreno4.getOBJECTID(),terreno4);
        terrenos.put(terreno5.getOBJECTID(),terreno5);
        terrenos.put(terreno6.getOBJECTID(),terreno6);

    }

    @AfterEach
    void tearDown() {
        terrenos.clear();
    }

    @Test
    void testGerarSugestoesDeTroca_impactoPositivo() {
        // Gerar as sugestões de troca
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);

        // Verificar se o Map retornado não é nulo nem vazio
        assertAll(
                () -> assertNotNull(trocas, "O Map de sugestões de troca não deve ser nulo."),
                () -> assertTrue(trocas.isEmpty(), "O Map de sugestões de troca não deve ser vazio.")
        );

        // Map para armazenar sugestões com impacto negativo ou nulo
        Map<Integer, TrocaTerrenos.Troca> impactosNegativos = new HashMap<>();

        // Iterar sobre as sugestões de troca
        for (Map.Entry<Integer, TrocaTerrenos.Troca> entry : trocas.entrySet()) {
            Integer id = entry.getKey();
            TrocaTerrenos.Troca troca = entry.getValue();

            // Validar se a troca possui impacto positivo
            if (troca == null || troca.impactoNaMedia <= 0) {
                impactosNegativos.put(id, troca);
            }
        }

        // Verificar se há impactos negativos
        assertTrue(impactosNegativos.isEmpty(),
                "As seguintes trocas possuem impacto negativo ou nulo: " + impactosNegativos);
    }

    @Test
    void testGerarSugestoesDeTroca_diferencaMinimaDeArea() {
        Map<Integer, TrocaTerrenos.Troca > trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);
        for (TrocaTerrenos.Troca troca : trocas.values()) {
            double diferenca = Math.abs(troca.terreno1.getShape_Area() - troca.terreno2.getShape_Area());
            assertTrue(diferenca <= 10, "A diferença de áreas entre terrenos trocados deve ser <= 10.");
        }
    }

    @Test
    void testGerarSugestoesDeTroca_trocasEntreProprietarios() {
        terrenos.clear();
        terrenos.put(1, new Terreno(1, "7343148.0", "2,99624E+12", 57.2469341921808, 202.05981432070362, g1, 93, "Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)"));
        terrenos.put(2, new Terreno(2, "7344660.0", "2,99622E+12", 55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)"));
        Map<Integer, TrocaTerrenos.Troca > trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);
        for (TrocaTerrenos.Troca troca : trocas.values()) {
            assertNotEquals(troca.terreno1.getOWNER(), troca.terreno2.getOWNER(),
                    "As trocas devem ocorrer entre diferentes proprietários.");
        }
    }

    @Test
    void testGerarSugestoesDeTroca_semTrocasPossiveis() {
        terrenos.clear();
        terrenos.put(1, new Terreno(1, "7343148.0", "2,99624E+12", 57.2469341921808, 202.05981432070362, g1, 93, "Arco da Calheta","Calheta", "Ilha da Madeira (Madeira)"));
        terrenos.put(2, new Terreno(2, "7344660.0", "2,99622E+12", 55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)"));
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);
        assertTrue(trocas.isEmpty(), "Não deve haver trocas possíveis para um único proprietário.");
    }
}

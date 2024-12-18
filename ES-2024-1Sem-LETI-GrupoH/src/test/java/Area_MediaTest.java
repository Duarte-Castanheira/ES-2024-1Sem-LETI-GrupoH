
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para a classe Area_Media.
 * Verifica os cálculos de área média por freguesia, município e ilha.
 */

class Area_MediaTest {

    /**
     * Método que é executado antes de cada teste.
     * Inicializa os dados de teste com terrenos predefinidos.
     */

    @BeforeEach
    void setUp() {
        WKTReader reader = new WKTReader();
        MultiPolygon g1;
        MultiPolygon g2;
        MultiPolygon g3;
        try {
            g1 = (MultiPolygon) reader.read("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.5033999998 3623637.4715, 299218.04000000004 3623638.4800000004, 299232.7400000002 3623644.6799999997, 299236.6233999999 3623637.1974, 299236.93709999975 3623636.7885999996, 299238.04000000004 3623633.4800000004, 299222.63999999966 3623627.1799999997, 299218.5203999998 3623637.4791)))");
            g2 = (MultiPolygon) reader.read("MULTIPOLYGON (((298724.1991999997 3623192.6094000004, 298724.3200000003 3623192.619999999, 298724.26999999955 3623185.7200000007, 298723.8854 3623185.681500001, 298723.8854 3623185.6338, 298717.2167999996 3623184.6405999996, 298716.2909000004 3623184.495100001, 298716.1699999999 3623184.5700000003, 298711.51999999955 3623184.17, 298709.1414000001 3623183.7961999997, 298708.48000000045 3623183.3200000003, 298705.6799999997 3623183.2200000007, 298704.5800000001 3623183.3200000003, 298703.98000000045 3623184.119999999, 298703.48000000045 3623190.7200000007, 298704.0525000002 3623190.7905, 298704.0488999998 3623190.8441000003, 298705.574 3623190.9777000006, 298709.98000000045 3623191.5199999996, 298710.0937999999 3623191.3737000003, 298724.1991999997 3623192.6094000004)))");
            g3 = (MultiPolygon) reader.read("MULTIPOLYGON (((315858.33910000045 3615436.0261000004, 315859.3360000001 3615436.4365999997, 315863.3360000001 3615439.4365, 315865.8384999996 3615441.9389999993, 315866.7659999998 3615443.9265, 315868.7483999999 3615447.296599999, 315885.01580000017 3615430.4266, 315885.11620000005 3615430.5199999996, 315885.66509999987 3615431.0309999995, 315892.26580000017 3615437.1765, 315891.7407999998 3615437.7860000003, 315892.8064000001 3615436.8082, 315892.9506999999 3615436.6757999994, 315894.71999999974 3615437.4800000004, 315938.49789999984 3615393.5022, 315938.95999999996 3615393.4299999997, 315942.5599999996 3615390.33, 315947.2599999998 3615385.7300000004, 315956.30040000007 3615375.528999999, 315962.0599999996 3615369.0299999993, 315961.13549999986 3615367.8281999994, 315961.18819999974 3615367.766899999, 315958.96520000044 3615364.3468999993, 315952.25409999955 3615358.855900001, 315947.46999999974 3615363.6400000006, 315945.26999999955 3615363.24, 315945.2949000001 3615363.1296999995, 315945.09420000017 3615363.3422, 315945.0700000003 3615363.24, 315941.0700000003 3615359.6400000006, 315946.8700000001 3615353.24, 315942.5714999996 3615350.911699999, 315941.3951000003 3615350.0294000003, 315941.4852999998 3615349.9069999997, 315932.301 3615342.1578, 315909.0700000003 3615363.84, 315878.8700000001 3615393.84, 315847.6328999996 3615427.2518000007, 315847.9335000003 3615427.4450000003, 315847.8278000001 3615427.576199999, 315851.4199999999 3615430.7799999993, 315855.21999999974 3615433.58, 315857.21999999974 3615435.2799999993, 315858.33910000045 3615436.0261000004)))");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Map<Integer,Terreno> terrenos = new HashMap<>();
        terrenos.put(1,new Terreno(1, "7343148.0", "2,99624E+12", 57.2469341921808, 202.05981432070362, g1, 93, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)"));
        terrenos.put(2,new Terreno(2, "7344660.0", "2,99622E+12", 55.63800662596267, 151.76387471712783, g2, 68, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)"));
        terrenos.put(6004,new Terreno(6004, "7309459.0", "3,16614E+12", 340.13373978055176, 4676.4979913805455, g3, 153, "Câmara de Lobos", "Câmara de Lobos", "Ilha da Madeira (Madeira)"));


        Area_Media.setTerreno(terrenos);
    }

    /**
     * Método que é executado após cada teste.
     * Limpa os dados da instância para evitar interferências entre testes.
     */

    @AfterEach
    void tearDown() {
         Area_Media.setTerreno(null);
    }

    /**
     * Teste que verifica o cálculo da área média por freguesia.
     * Verifica se a área média da freguesia "Arco da Calheta" é 176.91.
     */

    @Test
    void areaFreguesia(){
        double areaMedia = Area_Media.calcularAreaMedia_Freguesia("Arco da Calheta");
        assertEquals(176.91, areaMedia, 0.01);
    }

    /**
     * Teste que verifica o cálculo da área média por município.
     * Verifica se a área média do município "Calheta" é 176.91.
     */

    @Test
    void areaMunicipio(){
        double areaMedia = Area_Media.calcularAreaMedia_Municipio("Calheta");
        assertEquals(176.91, areaMedia, 0.01);
    }

    /**
     * Teste que verifica o cálculo da área média por ilha.
     * Verifica se a área média da ilha "Ilha da Madeira (Madeira)" é 1676.77.
     */

    @Test
    void areaIlha(){
        double areaMedia = Area_Media.calcularAreaMedia_Ilha("Ilha da Madeira (Madeira)");
        assertEquals(1676.77, areaMedia, 0.01);
    }
}

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe de teste para verificar a funcionalidade da classe Area_Media_Proprietario.
 */

class Area_Media_ProprietarioTest {

    private static Map<Integer, Terreno> terrenos;
    private Area_Media_Proprietario areaMedia;

    /**
     * Método executado antes de todos os testes.
     * Inicializa o mapa de terrenos com dados simulados.
     */

    @BeforeAll
    static void beforeAll() {
        WKTReader reader = new WKTReader();
        MultiPolygon g1;
        MultiPolygon g2;
        MultiPolygon g3;
        try {
            g1 = (MultiPolygon) reader.read("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.5033999998 3623637.4715, 299218.04000000004 3623638.4800000004, 299232.7400000002 3623644.6799999997, 299236.6233999999 3623637.1974, 299236.93709999975 3623636.7885999996, 299238.04000000004 3623633.4800000004, 299222.63999999966 3623627.1799999997, 299218.5203999998 3623637.4791)))");
            g2 = (MultiPolygon) reader.read("MULTIPOLYGON (((298584.82830000017 3623106.6208999995, 298584.8326000003 3623106.6209999993, 298584.8300000001 3623106.6500000004, 298582.5270999996 3623115.3680000007, 298582.00540000014 3623119.2994, 298582.0965999998 3623119.1777999997, 298588.6500000004 3623119.08, 298590.6457000002 3623111.5079999994, 298590.6503999997 3623111.5084000006, 298591.9299999997 3623106.6400000006, 298584.8300000001 3623106.619999999, 298584.82830000017 3623106.6208999995)))");
            g3 = (MultiPolygon) reader.read("MULTIPOLYGON (((298598.2429999998 3621571.0775000006, 298599.9718000004 3621568.5, 298600.1853 3621568.0934999995, 298600.23429999966 3621568.109099999, 298600.51999999955 3621568.1999999993, 298601.1412000004 3621567.0265999995, 298601.6326000001 3621566.0985000003, 298605.9199999999 3621558, 298608.9199999999 3621539.4000000004, 298608.6607999997 3621535.058700001, 298608.1200000001 3621526, 298607.78809999954 3621514.2162999995, 298607.71999999974 3621511.8000000007, 298608.5378999999 3621501.9848999996, 298604.7098000003 3621500.5493, 298604.7039999999 3621500.3751, 298604.7028000001 3621500.3391999993, 298604.0982999997 3621500.2248, 298599.80090000015 3621499.411800001, 298599.4483000003 3621501.7940999996, 298595.8021 3621526.8505000006, 298593.9930999996 3621536.630000001, 298591.3967000004 3621550.6664000005, 298591.37229999993 3621550.7983999997, 298591.4299999997 3621550.9189999998, 298591.4567999998 3621550.9749, 298591.4106999999 3621550.9667000007, 298590.4199999999 3621554.880000001, 298586.1699999999 3621561.33, 298584.7814999996 3621563.036699999, 298598.2429999998 3621571.0775000006)))");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        terrenos = new HashMap<>();
        terrenos.put(1,new Terreno(1, "7343148.0", "2,99624E+12", 57.2469341921808, 202.05981432070362, g1, 93, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)"));
        terrenos.put(3,new Terreno(3, "7344925.0", "2,98623E+12", 39.69344896337617, 87.83290987170697, g2, 26, "Arco da Calheta","Calheta","Ilha da Madeira (Madeira" ));
        terrenos.put(11,new Terreno (11, "17607847.0", "2,98622E+12", 163.02830777164647, 907.0567667929143, g3, 26, "Arco da Calheta", "Calheta", "Ilha da Madeira (Madeira)"));
    }

    /**
     * Método executado antes de cada teste individual.
     * Inicializa a instância da classe a ser testada.
     */


    @BeforeEach
    void setUp() {

        Area_Media_Proprietario.setTerreno(terrenos);
    }

    /**
     * Testa o cálculo da área média por proprietário em uma freguesia.
     * Verifica se o valor calculado é aproximadamente igual ao esperado (598.47).
     */

    @Test
    void area_Freguesia(){
        double area_Freguesia = Area_Media_Proprietario.obterTerrenos("Freguesia", "Arco da Calheta");
        assertEquals(598.47, area_Freguesia, 0.01);
    }

    /**
     * Testa o cálculo da área média por proprietário em um município.
     * Verifica se o valor calculado é aproximadamente igual ao esperado (598.47).
     */

    @Test
    void area_Municipio(){
        double area_Municipio = Area_Media_Proprietario.obterTerrenos("Municipio", "Calheta");
        assertEquals(598.47, area_Municipio, 0.01);
    }

    /**
     * Teste que verifica o cálculo da área média por ilha.
     * Verifica se a área média da ilha "Ilha da Madeira (Madeira)" é 1676.77.
     */

    @Test
    void area_Ilha(){
        double area_Ilha = Area_Media_Proprietario.obterTerrenos("Ilha", "Ilha da Madeira (Madeira)");
        assertEquals(554.55, area_Ilha, 0.01);
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerrenoTest {

    private Terreno terreno;

    @BeforeEach
    void setUp() {
        terreno = new Terreno();
    }

    @Test
    void testConstrutor() {
        assertEquals(0, terreno.getOBJECTID());
        assertNull(terreno.getParID());
        assertNull(terreno.getParNUM());
        assertEquals(0, terreno.getShape_Area());
        assertEquals(0, terreno.getShape_Length());
        assertNull(terreno.getGeometry());
        assertEquals(0, terreno.getOWNER());
        assertNull(terreno.getFreguesia());
        assertNull(terreno.getMunicipio());
        assertNull(terreno.getIlha());
    }

    @Test
    void testGettersAndSetters() {
        terreno.setOBJECTID(1);
        terreno.setParID("7343148.0");
        terreno.setParNUM("2,99624E+12");
        terreno.setShape_Area(57.2469341921808);
        terreno.setShape_Length(202.05981432070362);
        terreno.setGeometry("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.5033999998 3623637.4715, 299218.04000000004 3623638.4800000004, 299232.7400000002 3623644.6799999997, 299236.6233999999 3623637.1974, 299236.93709999975 3623636.7885999996, 299238.04000000004 3623633.4800000004, 299222.63999999966 3623627.1799999997, 299218.5203999998 3623637.4791)))");
        terreno.setOWNER(93);
        terreno.setFreguesia("Arco da Calheta");
        terreno.setMunicipio("Calheta");
        terreno.setIlha("Ilha da Madeira (Madeira)");

        assertEquals(1, terreno.getOBJECTID());
        assertEquals("7343148.0", terreno.getParID());
        assertEquals("2,99624E+12", terreno.getParNUM());
        assertEquals(57.2469341921808, terreno.getShape_Area());
        assertEquals(202.05981432070362, terreno.getShape_Length());
        assertEquals(202.05981432070362, terreno.getShape_Length());
        assertEquals("MULTIPOLYGON (((299218.5203999998 3623637.4791, 299218.5033999998 3623637.4715, 299218.04000000004 3623638.4800000004, 299232.7400000002 3623644.6799999997, 299236.6233999999 3623637.1974, 299236.93709999975 3623636.7885999996, 299238.04000000004 3623633.4800000004, 299222.63999999966 3623627.1799999997, 299218.5203999998 3623637.4791)))", terreno.getGeometry());
        assertEquals(93, terreno.getOWNER());
        assertEquals("Arco da Calheta",terreno.getFreguesia());
        assertEquals("Calheta", terreno.getMunicipio());
        assertEquals("Ilha da Madeira (Madeira)", terreno.getIlha());
    }
}

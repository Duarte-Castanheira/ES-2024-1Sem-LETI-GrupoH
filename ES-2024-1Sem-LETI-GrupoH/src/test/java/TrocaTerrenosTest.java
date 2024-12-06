import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TrocaTerrenosTest {

    private Map<Integer, Terreno> terrenos;

    @BeforeEach
    void setUp() {
        terrenos = new HashMap<>();
        terrenos.put(1, new Terreno(1, "PAR001", "001", 10.5, 1000, null, 1, "Freguesia1", "Municipio1", "Ilha1"));
        terrenos.put(2, new Terreno(2, "PAR002", "002", 15.5, 1500, null, 1, "Freguesia1", "Municipio1", "Ilha1"));
        terrenos.put(3, new Terreno(3, "PAR003", "003", 12.5, 2000, null, 2, "Freguesia2", "Municipio2", "Ilha2"));
        terrenos.put(4, new Terreno(4, "PAR004", "004", 8.5, 1200, null, 2, "Freguesia2", "Municipio2", "Ilha2"));
        terrenos.put(5, new Terreno(5, "PAR005", "005", 9.5, 1100, null, 3, "Freguesia3", "Municipio3", "Ilha3"));
        terrenos.put(6, new Terreno(6, "PAR006", "006", 7.5, 900, null, 3, "Freguesia3", "Municipio3", "Ilha3"));
    }

    @AfterEach
    void tearDown() {
        terrenos.clear();
    }

    @Test
    void testGerarSugestoesDeTroca_impactoPositivo() {
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);
        assertFalse(trocas.isEmpty(), "As sugestões de troca não devem ser vazias.");
        for (TrocaTerrenos.Troca troca : trocas.values()) {
            assertTrue(troca.impactoNaMedia > 0, "O impacto na área média deve ser positivo.");
        }
    }

    @Test
    void testGerarSugestoesDeTroca_diferencaMinimaDeArea() {
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);
        for (TrocaTerrenos.Troca troca : trocas.values()) {
            double diferenca = Math.abs(troca.terreno1.getShape_Area() - troca.terreno2.getShape_Area());
            assertTrue(diferenca <= 10, "A diferença de áreas entre terrenos trocados deve ser <= 10.");
        }
    }

    @Test
    void testGerarSugestoesDeTroca_trocasEntreProprietarios() {
        terrenos.clear();
        terrenos.put(1, new Terreno(1, "PAR001", "001", 10.5, 1000, null, 1, "Freguesia1", "Municipio1", "Ilha1"));
        terrenos.put(2, new Terreno(2, "PAR002", "002", 15.5, 1500, null, 2, "Freguesia1", "Municipio1", "Ilha1"));
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);
        for (TrocaTerrenos.Troca troca : trocas.values()) {
            assertNotEquals(troca.terreno1.getOWNER(), troca.terreno2.getOWNER(),
                    "As trocas devem ocorrer entre diferentes proprietários.");
        }
    }

    @Test
    void testGerarSugestoesDeTroca_semTrocasPossiveis() {
        terrenos.clear();
        terrenos.put(1, new Terreno(1, "PAR001", "001", 10.5, 1000, null, 1, "Freguesia1", "Municipio1", "Ilha1"));
        terrenos.put(2, new Terreno(2, "PAR002", "002", 15.5, 1500, null, 1, "Freguesia1", "Municipio1", "Ilha1"));
        Map<Integer, TrocaTerrenos.Troca> trocas = TrocaTerrenos.gerarSugestoesDeTroca(terrenos);
        assertTrue(trocas.isEmpty(), "Não deve haver trocas possíveis para um único proprietário.");
    }
}

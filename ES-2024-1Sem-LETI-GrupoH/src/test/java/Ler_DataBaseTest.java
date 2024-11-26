import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Ler_DataBaseTest {

    @BeforeEach
    void setUp() {
        String conteudoTeste = """
                OBJECTID;PAR_ID;PAR_NUM;Shape_Length;Shape_Area;geometry;OWNER;Freguesia;Municipio;Ilha
                1;Terreno A;100;200;300;quadrado;1;alcochete;Madeira;Ilha
                2;Terreno B;200;250;350;triangulo;2;Samouco;Madeira;Ilha
                3;Terreno C;300;270;370;quadrado;3;Montijo;Madeira;Ilha
                """;

        try (FileWriter writer = new FileWriter("test-temp.csv")) {
            writer.write(conteudoTeste);
        } catch (IOException e) {
            fail("Erro ao criar arquivo de teste: " + e.getMessage());
        }
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void ReadFile() {

         try {
             // Substituindo o caminho do arquivo temporariamente
             Ler_DataBase.ReadFile("test-temp.csv");
             // Nenhuma exceção foi lançada
             assertTrue(true);
         } catch (Exception e) {
             fail("O método ReadFile lançou uma exceção: " + e.getMessage());
         } finally {
             // Deletar o arquivo de teste após o teste
             File arquivo = new File("test-temp.csv");
             if (arquivo.exists()) {
                 arquivo.delete();
             }
         }
    }


}
// Importações necessárias para manipular arquivos CSV, lidar com exceções relacionadas a CSV,
// e processar geometrias (MultiPolygon) no formato WKT.
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.WKTReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe principal responsável por ler e processar dados de um arquivo CSV.
 */
public class Ler_DataBase {

    /**
     * Instância única do WKTReader para converter strings WKT em objetos MultiPolygon.
     */
    private static final WKTReader reader = new WKTReader();

    /**
     * Método principal para ler e processar um arquivo CSV e retornar um mapa de Terrenos.
     * @param caminhoArquivo Arquivo CSV que será lido.
     * @return Um mapa contendo os Terrenos processados, onde a chave é o ID do terreno.
     */
    public static Map<Integer, Terreno> ReadFile(File caminhoArquivo) {
        Map<Integer, Terreno> mapaTerrenos = new HashMap<>();
        try (
                CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(caminhoArquivo))
        ) {
            String[] linha;
            String[] terreno;

            while ((linha = reader.readNext()) != null) {
                String s = String.join(",", linha);
                terreno = s.split(";");

                if (terreno[7].equalsIgnoreCase("na"))
                    break;

                int id = Integer.parseInt(terreno[0]);

                Terreno t = new Terreno(
                        id,
                        terreno[1],
                        terreno[2],
                        Double.parseDouble(terreno[3]),
                        Double.parseDouble(terreno[4]),
                        CreateGeometry(terreno[5]),
                        Integer.parseInt(terreno[6]),
                        terreno[7],
                        terreno[8],
                        terreno[9]
                );

                mapaTerrenos.put(id, t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return mapaTerrenos;
    }

    /**
     * Método auxiliar para criar um objeto MultiPolygon a partir de uma string WKT.
     * @param s String WKT representando a geometria.
     * @return Um objeto MultiPolygon ou null em caso de erro.
     */
    private static MultiPolygon CreateGeometry(String s) {
        try {
            return (MultiPolygon) reader.read(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

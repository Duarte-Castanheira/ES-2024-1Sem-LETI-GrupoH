import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.WKTReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe responsável por ler um arquivo CSV contendo informações de terrenos,
 * processar os dados e retornar um mapa onde cada terreno é associado a um ID único.
 */

public class Ler_DataBase {


    private static final WKTReader reader = new WKTReader();

    /**
     * Lê um arquivo CSV, processa as informações e cria um mapa de terrenos.
     *
     * @param caminhoArquivo Caminho para o arquivo CSV.
     * @return Um mapa onde as chaves são IDs dos terrenos (Integer) e os valores são objetos do tipo Terreno.
     */

    public static Map<Integer, Terreno> ReadFile(String caminhoArquivo ) {

        Map<Integer, Terreno> mapaTerrenos = new HashMap<>();
        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(caminhoArquivo))) {
            String[] linha;
            String[] terreno;
            while ((linha = reader.readNext()) != null) {
                String s = String.join(",", linha);
                terreno = s.split(";");
                int id = Integer.parseInt(terreno[0]);
                Terreno t = new Terreno(id,terreno[1],terreno[2],
                        Double.parseDouble(terreno[3]),Double.parseDouble(terreno[4]),CreateGeometry(terreno[5]),
                        Integer.parseInt(terreno[6]),terreno[7],terreno[8],terreno[9]);
                mapaTerrenos.put(id,t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return mapaTerrenos;
    }

    /**
     * Converte uma string WKT (Well-Known Text) em um objeto MultiPolygon.
     *
     * @param s String contendo a geometria no formato WKT.
     * @return Objeto MultiPolygon representando a geometria, ou null se ocorrer um erro.
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
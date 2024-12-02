import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.WKTReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Ler_DataBase {

    public static List<Terreno> ReadFile(String caminhoArquivo ) {

        List<Terreno> ListaTerrenos = new ArrayList<>();
        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(caminhoArquivo))) {
            String[] linha;
            while ((linha = reader.readNext()) != null) {
                String[] terreno = new String[linha.length];
                 for (int i = 0; i < linha.length; i++) {
                     String s = String.join(",", linha);
                     terreno = s.split(";");
                    MultiPolygon g = CreateGeometry(terreno[5]);
                     ListaTerrenos.add(new Terreno(Integer.parseInt(terreno[0]),terreno[1],terreno[2],
                         Double.parseDouble(terreno[3]),Double.parseDouble(terreno[4]),CreateGeometry(terreno[5]),
                         Integer.parseInt(terreno[6]),terreno[7],terreno[8],terreno[9]));
                 }
            }
        //ListaTerrenos.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return ListaTerrenos;
    }

    private static MultiPolygon CreateGeometry(String s) {
        try {
            WKTReader reader = new WKTReader();

            return (MultiPolygon) reader.read(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
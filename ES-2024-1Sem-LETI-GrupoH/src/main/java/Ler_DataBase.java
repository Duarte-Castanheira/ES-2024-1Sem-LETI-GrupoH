import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Ler_DataBase {

    public static void ReadFile(String caminhoArquivo ) {

        List<Terreno> ListaTerrenos = new ArrayList<>();
        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(caminhoArquivo))) {
            String[] linha;
            while ((linha = reader.readNext()) != null) {
                String[] terreno = new String[linha.length];
                 for (int i = 0; i < linha.length; i++) {
                     String s = String.join(",", linha);
                     terreno = s.split(";");
                     Geometry g = getGeometry(terreno[5]);
                     ListaTerrenos.add(new Terreno(Integer.parseInt(terreno[0]),terreno[1],terreno[2],
                         Double.parseDouble(terreno[3]),Double.parseDouble(terreno[4]),g,
                         Integer.parseInt(terreno[6]),terreno[7],terreno[8],terreno[9]));
                 }
            }
        ListaTerrenos.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        /*
       try {
            CSVReader leitor = new CSVReader(new FileReader(caminhoArquivo));
            CsvToBean<Terreno> csvToBean = new CsvToBeanBuilder<Terreno>(leitor)
                    .withType(Terreno.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();
            //leitor.forEach(System.out::println);
            List<Terreno> ListaTerrenos = csvToBean.parse();
            System.out.println(csvToBean.toString());
            //System.out.println(ListaTerrenos.get(1).getFreguesia());
            */
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }

    public static Geometry getGeometry(String m) {
        List<double[]> arestas = new ArrayList<>();
        // Remove prefixo e parênteses extras
        String raw = m.replace("MULTIPOLYGON (((", "").replace(")))", "");

        // Divide em pontos (x, y)
        String[] pontos = raw.split(", ");
        for (String ponto : pontos) {
            String[] coordenadasStr = ponto.split(" ");
            if(coordenadasStr[0].contains("("))
                coordenadasStr[0] = coordenadasStr[0].replace("(", "");
            if(coordenadasStr[1].contains(")"))
                coordenadasStr[1] = coordenadasStr[1].replace(")", "");
            double x = Double.parseDouble(coordenadasStr[0]);
            double y = Double.parseDouble(coordenadasStr[1]);
            arestas.add( new double[]{x, y});
        }
        Geometry coordenadas = new Geometry(arestas);

        return coordenadas;
    }

}
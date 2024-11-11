import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ler_DataBase {
    int i = 0;

    public static void ReadFile() {
        String caminhoArquivo = "Madeira-Moodle.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                String[] data = linha.split(";");
                String d1 = data[2].replace(",",".");
                long d2 = Math.round(Double.parseDouble(d1));
                Terreno t = new Terreno(Integer.parseInt(data[0]), Double.parseDouble(data[1]), d2,
                Double.parseDouble(data[3]), Double.parseDouble(data[1]), data[5], Integer.parseInt(data[6]));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
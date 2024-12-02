import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<Integer, Terreno> ListaTerrenos = Ler_DataBase.ReadFile("Madeira-Moodle-1.1.csv");
        Graph.CreateGraph(ListaTerrenos);
    }
}

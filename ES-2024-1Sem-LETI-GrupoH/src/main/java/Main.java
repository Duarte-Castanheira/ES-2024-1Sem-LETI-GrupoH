import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Terreno> ListaTerrenos = Ler_DataBase.ReadFile("Madeira-Moodle-1.1.csv");
        Graph.CreateGraph(ListaTerrenos);
    }
}

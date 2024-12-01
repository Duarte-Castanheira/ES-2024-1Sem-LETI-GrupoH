import java.util.ArrayList;
import java.util.List;

public class Geometry {

    List<Coordenadas> Arestas;

    public Geometry() {
        Arestas = new ArrayList<Coordenadas>();
    }

    public void setArestas(List<Coordenadas> arestas) {
        Arestas = arestas;
    }

    public void GetArestas(String multipolygon) {
        String raw = multipolygon.replace("MULTIPOLYGON (((", "").replace(")))", "");
        if(multipolygon.contains("EMPTY"))
            return;
        // Divide em pontos (x, y)
        String[] pontos = raw.split(", ");
        for (String ponto : pontos) {
            String[] coordenadasStr = ponto.split(" ");
            if (coordenadasStr[0].contains("("))
                coordenadasStr[0] = coordenadasStr[0].replace("(", "");
            if (coordenadasStr[1].contains(")"))
                coordenadasStr[1] = coordenadasStr[1].replace(")", "");
            double x = Double.parseDouble(coordenadasStr[0]);
            double y = Double.parseDouble(coordenadasStr[1]);
            Arestas.add(new Coordenadas(x, y));
        }
    }
}

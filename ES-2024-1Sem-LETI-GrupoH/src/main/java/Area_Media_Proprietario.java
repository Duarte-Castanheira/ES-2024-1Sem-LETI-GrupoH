import java.util.ArrayList;
import java.util.List;

public class Area_Media_Proprietario {

    private final List<Terreno> terrenos;

    public Area_Media_Proprietario(List<Terreno> terrenos) {
        this.terrenos = terrenos;
    }

    public double calcularAreaMedia(String areaGeografica, String nome) {
        List<Terreno> terrenoEscolhidos = new ArrayList();

        for (Terreno t : terrenos) {
            if (areaGeografica.equalsIgnoreCase("Freguesia") && t.getFreguesia().equalsIgnoreCase(nome)) {
                terrenoEscolhidos.add(t);
            } else if (areaGeografica.equalsIgnoreCase("Município") && t.getMunicipio().equalsIgnoreCase(nome)) {
                terrenoEscolhidos.add(t);
            } else if (areaGeografica.equalsIgnoreCase("Ilha") && t.getIlha().equalsIgnoreCase(nome)) {
                terrenoEscolhidos.add(t);
            }
        }

        if (terrenoEscolhidos.isEmpty()) {
            return -1;
        }

        List<Integer> proprietarios = new ArrayList();
        List<Double> areasAgrupadas = new ArrayList();

        for (Terreno terreno : terrenoEscolhidos) {
            int proprietario = terreno.getOWNER();
            if (!proprietarios.contains(proprietario)) {
                double somaArea = 0;
                for (Terreno t : terrenoEscolhidos) {
                    if (proprietario == t.getOWNER()) {
                        somaArea += t.getShape_Area();
                    }
                }
                proprietarios.add(proprietario);
                areasAgrupadas.add(somaArea);
            }
        }
        double somaArea = 0;
        for (double area : areasAgrupadas) {
            somaArea += area;
        }
        return somaArea / areasAgrupadas.size();
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Area_Media_Proprietario {

    private final Map<Integer,Terreno> terreno;

    public Area_Media_Proprietario(Map<Integer,Terreno> terreno) {
        this.terreno = terreno;
    }

    public double calcular_AreaMedia(String areaGeografica, String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        for (Map.Entry<Integer,Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            boolean shouldAdd = false;

            switch (areaGeografica.toLowerCase()) {
                case "freguesia":
                    shouldAdd = currentTerreno.getFreguesia().equalsIgnoreCase(nome);
                    break;
                case "município":
                    shouldAdd = currentTerreno.getMunicipio().equalsIgnoreCase(nome);
                    break;
                case "ilha":
                    shouldAdd = currentTerreno.getIlha().equalsIgnoreCase(nome);
                    break;
                default:
                    // Caso deseje lidar com uma área geográfica não reconhecida
                    System.err.println("Área geográfica desconhecida: " + areaGeografica);
                    break;
            }
            if (shouldAdd) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
            if (terrenosEscolhidos.isEmpty()) {
                return -1;
            }
        }

        Map<Integer, Double> areasAgrupadasPorProprietario = new HashMap<>();

        for (Terreno terreno : terrenosEscolhidos.values()) {
            int proprietario = terreno.getOWNER();
            areasAgrupadasPorProprietario.put(proprietario, areasAgrupadasPorProprietario.getOrDefault(proprietario, 0.0) + terreno.getShape_Area());
        }
        double somaArea = 0.0;
        for (double area : areasAgrupadasPorProprietario.values()) {
            somaArea += area;
        }

        return somaArea / areasAgrupadasPorProprietario.size();
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Area_Media {

    private final Map<Integer,Terreno> terreno;

    public Area_Media(Map<Integer,Terreno> terreno) {
        this.terreno = terreno;
    }

    public double calcularAreaMedia(String areaGeografica, String nome){
        Map<Integer, Terreno> terrenos = new HashMap<>();

        for(Map.Entry<Integer,Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue(); // Obtém o valor atual
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
                terrenos.put(currentTerreno.getOBJECTID(),currentTerreno);
            }
        }
        if (terrenos.isEmpty()){
            return -1;
        }

        double somaArea = 0;
        for (Map.Entry<Integer,Terreno> entry : terrenos.entrySet()) {
            somaArea += terrenos.get(entry.getKey()).getShape_Area();
        }

        return somaArea / terrenos.size();
    }
}

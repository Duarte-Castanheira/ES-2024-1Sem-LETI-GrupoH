import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Area_Media {

    private final Map<Integer,Terreno> terreno;

    public Area_Media(Map<Integer,Terreno> terreno) {
        this.terreno = terreno;
    }

    public double calcularAreaMedia_Freguesia(String nome) {
        Map<Integer, Terreno> terrenos = new HashMap<>();

        // Iterar sobre os terrenos e filtrar pela freguesia
        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getFreguesia().equalsIgnoreCase(nome)) {
                terrenos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        // Se não houver terrenos para a freguesia, retorna -1
        if (terrenos.isEmpty()) {
            return -1;
        }

        // Soma a área de todos os terrenos encontrados
        double somaArea = 0;
        for (Map.Entry<Integer, Terreno> entry : terrenos.entrySet()) {
            somaArea += entry.getValue().getShape_Area();
        }

        // Calcula e retorna a média da área
        return somaArea / terrenos.size();
    }

    public double calcularAreaMedia_Municipio(String nome) {
        Map<Integer, Terreno> terrenos = new HashMap<>();

        // Iterar sobre os terrenos e filtrar pelo município
        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getMunicipio().equalsIgnoreCase(nome)) {
                terrenos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        // Se não houver terrenos para o município, retorna -1
        if (terrenos.isEmpty()) {
            return -1;
        }

        // Soma a área de todos os terrenos encontrados
        double somaArea = 0;
        for (Map.Entry<Integer, Terreno> entry : terrenos.entrySet()) {
            somaArea += entry.getValue().getShape_Area();
        }

        // Calcula e retorna a média da área
        return somaArea / terrenos.size();
    }

    public double calcularAreaMedia_Ilha(String nome) {
        Map<Integer, Terreno> terrenos = new HashMap<>();

        // Iterar sobre os terrenos e filtrar pela ilha
        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getIlha().equalsIgnoreCase(nome)) {
                terrenos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        // Se não houver terrenos para a ilha, retorna -1
        if (terrenos.isEmpty()) {
            return -1;
        }

        // Soma a área de todos os terrenos encontrados
        double somaArea = 0;
        for (Map.Entry<Integer, Terreno> entry : terrenos.entrySet()) {
            somaArea += entry.getValue().getShape_Area();
        }

        // Calcula e retorna a média da área
        return somaArea / terrenos.size();
    }
}
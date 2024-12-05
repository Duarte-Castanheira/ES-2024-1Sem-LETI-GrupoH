import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Area_Media_Proprietario {

    private final Map<Integer,Terreno> terreno;

    public Area_Media_Proprietario(Map<Integer,Terreno> terreno) {
        this.terreno = terreno;
    }

    public double calcular_Area_Media_Proprietario_Freguesia(String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        // Filtra os terrenos pela freguesia
        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getFreguesia().equalsIgnoreCase(nome)) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        // Verifica se há terrenos para a freguesia
        if (terrenosEscolhidos.isEmpty()) {
            return -1;
        }

        // Agrupa os terrenos por proprietário e soma as áreas
        Map<Integer, Double> areasAgrupadasPorProprietario = new HashMap<>();
        for (Terreno terreno : terrenosEscolhidos.values()) {
            int proprietario = terreno.getOWNER();
            areasAgrupadasPorProprietario.put(proprietario, areasAgrupadasPorProprietario.getOrDefault(proprietario, 0.0) + terreno.getShape_Area());
        }

        // Soma todas as áreas agrupadas por proprietário
        double somaArea = 0.0;
        for (double area : areasAgrupadasPorProprietario.values()) {
            somaArea += area;
        }

        // Retorna a média da área por proprietário
        return somaArea / areasAgrupadasPorProprietario.size();
    }

    public double calcular_Area_Media_Proprietario_Municipio(String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        // Filtra os terrenos pelo município
        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getMunicipio().equalsIgnoreCase(nome)) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        // Verifica se há terrenos para o município
        if (terrenosEscolhidos.isEmpty()) {
            return -1;
        }

        // Agrupa os terrenos por proprietário e soma as áreas
        Map<Integer, Double> areasAgrupadasPorProprietario = new HashMap<>();
        for (Terreno terreno : terrenosEscolhidos.values()) {
            int proprietario = terreno.getOWNER();
            areasAgrupadasPorProprietario.put(proprietario, areasAgrupadasPorProprietario.getOrDefault(proprietario, 0.0) + terreno.getShape_Area());
        }

        // Soma todas as áreas agrupadas por proprietário
        double somaArea = 0.0;
        for (double area : areasAgrupadasPorProprietario.values()) {
            somaArea += area;
        }

        // Retorna a média da área por proprietário
        return somaArea / areasAgrupadasPorProprietario.size();
    }


    public double calcular_Area_Media_Proprietario_Ilha(String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        // Filtra os terrenos pela ilha, considerando a possibilidade de nomes com espaços extras
        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            // Verifica se o nome da ilha é igual ao nome fornecido (ignorando maiúsculas e minúsculas)
            if (currentTerreno.getIlha() != null && currentTerreno.getIlha().trim().equalsIgnoreCase(nome.trim())) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        // Verifica se não há terrenos para a ilha, caso em que retorna -1
        if (terrenosEscolhidos.isEmpty()) {
            return -1;
        }

        // Agrupa os terrenos por proprietário e soma as áreas
        Map<Integer, Double> areasAgrupadasPorProprietario = new HashMap<>();
        for (Terreno terreno : terrenosEscolhidos.values()) {
            int proprietario = terreno.getOWNER();
            // Soma as áreas para cada proprietário
            areasAgrupadasPorProprietario.put(proprietario, areasAgrupadasPorProprietario.getOrDefault(proprietario, 0.0) + terreno.getShape_Area());
        }

        // Soma todas as áreas agrupadas por proprietário
        double somaArea = 0.0;
        for (double area : areasAgrupadasPorProprietario.values()) {
            somaArea += area;
        }

        // Retorna a média da área por proprietário
        return somaArea / areasAgrupadasPorProprietario.size();
    }


}
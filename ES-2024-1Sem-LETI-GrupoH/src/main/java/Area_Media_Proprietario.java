import java.util.HashMap;
import java.util.Map;

/**
 * Classe que calcula a área média dos terrenos por proprietário
 * em diferentes níveis geográficos: freguesia, município e ilha.
 */

public class Area_Media_Proprietario {

    private final Map<Integer,Terreno> terreno;

    /**
     * Construtor da classe que recebe o mapa de terrenos.
     *
     * @param terreno Mapa de terrenos disponíveis.
     */

    public Area_Media_Proprietario(Map<Integer,Terreno> terreno) {
        this.terreno = terreno;
    }

    /**
     * Calcula a área média por proprietário para uma freguesia específica.
     *
     * @param nome Nome da freguesia.
     * @return Área média por proprietário na freguesia ou -1 se não houver terrenos na freguesia.
     */

    public double calcular_Area_Media_Proprietario_Freguesia(String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getFreguesia().equalsIgnoreCase(nome)) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        if (terrenosEscolhidos.isEmpty()) {
            return -1;
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

    /**
     * Calcula a área média por proprietário para um município específico.
     *
     * @param nome Nome do município.
     * @return Área média por proprietário no município ou -1 se não houver terrenos no município.
     */

    public double calcular_Area_Media_Proprietario_Municipio(String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getMunicipio().equalsIgnoreCase(nome)) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        if (terrenosEscolhidos.isEmpty()) {
            return -1;
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

    /**
     * Calcula a área média por proprietário para uma ilha específica.
     *
     * @param nome Nome da ilha.
     * @return Área média por proprietário na ilha ou -1 se não houver terrenos na ilha.
     */

    public double calcular_Area_Media_Proprietario_Ilha(String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getIlha() != null && currentTerreno.getIlha().trim().equalsIgnoreCase(nome.trim())) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        if (terrenosEscolhidos.isEmpty()) {
            return -1;
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
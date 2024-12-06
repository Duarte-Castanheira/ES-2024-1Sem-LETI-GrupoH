
import java.util.HashMap;
import java.util.Map;

/**
 * Classe para calcular a área média de terrenos com base em critérios como freguesia, município e ilha.
 */

public class Area_Media {

    private static Map<Integer, Terreno> terreno;

    /**
     * Construtor da classe que inicializa o mapa de terrenos.
     *
     * @param terreno Mapa de terrenos a ser utilizado para cálculos.
     */

    public Area_Media(Map<Integer, Terreno> terreno) {
        this.terreno = terreno;
    }

    /**
     * Calcula a área média de terrenos em uma determinada freguesia.
     *
     * @param nome Nome da freguesia.
     * @return A área média dos terrenos na freguesia, ou -1 se não houver terrenos.
     */

    public static double calcularAreaMedia_Freguesia(String nome) {
        Map<Integer, Terreno> terrenos = new HashMap<>();

        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getFreguesia().equalsIgnoreCase(nome)) {
                terrenos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        if (terrenos.isEmpty()) {
            return -1;
        }

        double somaArea = 0;
        for (Map.Entry<Integer, Terreno> entry : terrenos.entrySet()) {
            somaArea += entry.getValue().getShape_Area();
        }

        return somaArea / terrenos.size();
    }

    /**
     * Calcula a área média de terrenos em um determinado município.
     *
     * @param nome Nome do município.
     * @return A área média dos terrenos no município, ou -1 se não houver terrenos.
     */

    public static double calcularAreaMedia_Municipio(String nome) {
        Map<Integer, Terreno> terrenos = new HashMap<>();

        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getMunicipio().equalsIgnoreCase(nome)) {
                terrenos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        if (terrenos.isEmpty()) {
            return -1;
        }

        double somaArea = 0;
        for (Map.Entry<Integer, Terreno> entry : terrenos.entrySet()) {
            somaArea += entry.getValue().getShape_Area();
        }

        return somaArea / terrenos.size();
    }

    /**
     * Calcula a área média de terrenos em uma determinada ilha.
     *
     * @param nome Nome da ilha.
     * @return A área média dos terrenos na ilha, ou -1 se não houver terrenos.
     */

    public static double calcularAreaMedia_Ilha(String nome) {
        Map<Integer, Terreno> terrenos = new HashMap<>();

        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            if (currentTerreno.getIlha().equalsIgnoreCase(nome)) {
                terrenos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        if (terrenos.isEmpty()) {
            return -1;
        }

        double somaArea = 0;
        for (Map.Entry<Integer, Terreno> entry : terrenos.entrySet()) {
            somaArea += entry.getValue().getShape_Area();
        }

        return somaArea / terrenos.size();
    }
}

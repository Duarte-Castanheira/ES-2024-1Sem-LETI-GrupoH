import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por calcular a área média de terrenos filtrados por Freguesia, Município ou Ilha.
 */

public class Area_Media {

    private final Map<Integer,Terreno> terreno;

    /**
     * Construtor que inicializa a classe com um mapa de terrenos.
     *
     * @param terreno Mapa de terrenos, onde a chave é o ID do terreno e o valor é o objeto Terreno.
     */

    public Area_Media(Map<Integer,Terreno> terreno) {
        this.terreno = terreno;
    }

    /**
     * Calcula a área média de terrenos pertencentes a uma freguesia específica.
     *
     * @param nome Nome da freguesia.
     * @return Área média dos terrenos na freguesia ou -1 se nenhum terreno for encontrado.
     */

    public double calcularAreaMedia_Freguesia(String nome) {
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
     * Calcula a área média de terrenos pertencentes a um município específico.
     *
     * @param nome Nome do município.
     * @return Área média dos terrenos no município ou -1 se nenhum terreno for encontrado.
     */

    public double calcularAreaMedia_Municipio(String nome) {
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
     * Calcula a área média de terrenos pertencentes a uma ilha específica.
     *
     * @param nome Nome da ilha.
     * @return Área média dos terrenos na ilha ou -1 se nenhum terreno for encontrado.
     */

    public double calcularAreaMedia_Ilha(String nome) {
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
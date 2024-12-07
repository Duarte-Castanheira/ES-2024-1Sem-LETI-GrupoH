import java.util.HashMap;
import java.util.Map;

/**
 * Classe para calcular a área média de terrenos agrupados por proprietário,
 * considerando uma área geográfica específica (freguesia, município ou ilha).
 */
public class Area_Media_Proprietario {

    /**
     * Declaração de um mapa estático para armazenar terrenos.
     */

    private static Map<Integer, Terreno> terreno;

    /**
     * Construtor da classe que inicializa o mapa de terrenos.
     *
     * @param terreno Mapa de terrenos a ser utilizado nos cálculos.
     */
    public Area_Media_Proprietario(Map<Integer, Terreno> terreno) {
        Area_Media_Proprietario.terreno = terreno;
    }


    /**
     * Define o mapa de terrenos para a classe Area_Media_Proprietario.
     *
     * @param terreno um {@code Map} e representa os terrenos que serão utilizados.
     */

    public static void setTerreno(Map<Integer, Terreno> terreno) {
        Area_Media_Proprietario.terreno = terreno;
    }

    /**
     * Calcula a área média de terrenos agrupados por proprietário em uma área geográfica específica.
     *
     * @param areaGeografica Tipo da área geográfica (freguesia, município ou ilha).
     * @param nome Nome da área geográfica.
     * @return A área média por proprietário, ou -1 se não houver terrenos na área especificada.
     */
    public static double obterTerrenos(String areaGeografica, String nome) {
        Map<Integer, Terreno> terrenosEscolhidos = new HashMap<>();

        for (Map.Entry<Integer, Terreno> entry : terreno.entrySet()) {
            Terreno currentTerreno = entry.getValue();
            boolean shouldAdd = false;

            switch (areaGeografica.toLowerCase()) {
                case "freguesia":
                    shouldAdd = currentTerreno.getFreguesia().equalsIgnoreCase(nome);
                    break;
                case "municipio":
                    shouldAdd = currentTerreno.getMunicipio().equalsIgnoreCase(nome);
                    break;
                case "ilha":
                    shouldAdd = currentTerreno.getIlha().equalsIgnoreCase(nome);
                    break;
                default:
                    System.err.println("Área geográfica desconhecida: " + areaGeografica);
                    break;
            }

            if (shouldAdd) {
                terrenosEscolhidos.put(currentTerreno.getOBJECTID(), currentTerreno);
            }
        }

        if (terrenosEscolhidos.isEmpty()) {
            return -1;
        }

        return calcular_Area_Media(terrenosEscolhidos);
    }


    /**
     * Calcula a área média dos terrenos agrupados por proprietário.
     *
     * @param terrenos um Map que contem os terrenos a serem processados.
     * @return a área média dos terrenos agrupados por proprietário.
     */

    public static double calcular_Area_Media(Map<Integer,Terreno> terrenos) {
        Map<Integer, Double> areasAgrupadasPorProprietario = new HashMap<>();

        for (Terreno terreno : terrenos.values()) {
            int proprietario = terreno.getOWNER();
            areasAgrupadasPorProprietario.put(proprietario,
                    areasAgrupadasPorProprietario.getOrDefault(proprietario, 0.0) + terreno.getShape_Area());
        }

        double somaArea = 0.0;
        for (double area : areasAgrupadasPorProprietario.values()) {
            somaArea += area;
        }

        return somaArea / areasAgrupadasPorProprietario.size();
    }
}
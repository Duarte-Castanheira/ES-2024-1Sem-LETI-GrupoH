
import java.util.*;

/**
 * Classe responsável por sugerir possíveis trocas de terrenos entre dois proprietários.
 * A lógica considera a área total dos terrenos, a quantidade de vizinhos e tenta equilibrar as médias de área.
 */

public class TrocaTerrenos {

    /**
     *  Classe interna que representa uma troca entre dois terrenos.
     */

    public static class Troca {
        int terrenoid1;
        int terrenoid2;

        Troca(int terreno1, int terreno2) {
            this.terrenoid1 = terreno1;
            this.terrenoid2 = terreno2;
        }

        /**
         * Sobrescreve o método toString para fornecer uma representação textual da troca.
         */
        @Override
        public String toString() {
            return "Troca{" +
                    "terrenoid=" + terrenoid1 +
                    ", terrenoid=" + terrenoid2 +
                    '}' + "\n";
        }
    }

    /**
     * Cria sugestões de trocas de terrenos entre dois proprietários.
     *
     * @param terrenosMap Mapa contendo todos os terrenos (chave: ID do terreno, valor: objeto Terreno).
     * @param owner1 ID do primeiro proprietário.
     * @param owner2 ID do segundo proprietário.
     * @return Um mapa de trocas possíveis (chave: ID único da troca, valor: objeto Troca).
     */
    public static Map<Integer, Troca> gerarSugestoesDeTroca(Map<Integer, Terreno> terrenosMap, int owner1, int owner2) {

        Map<Integer, Troca> trocasPossiveis = new HashMap<>();
        Map<Integer,Terreno> terrenosowner1 = new HashMap<>(); // Lista de terrenos do proprietário 1.
        Map<Integer,Terreno> terrenosowner2 = new HashMap<>(); // Lista de terrenos do proprietário 2.

        for (Terreno t : terrenosMap.values()) {
            if (t.getOWNER() == owner1) {
                terrenosowner1.put(t.getOBJECTID(),t);
            }
            if (t.getOWNER() == owner2) {
                terrenosowner2.put(t.getOBJECTID(),t);
            }

        }
        double Area_media = Area_Media_Proprietario.calcular_Area_Media(terrenosMap);


        for (Map.Entry<Integer, Terreno> entry1 : terrenosowner1.entrySet()) {
            for (Map.Entry<Integer, Terreno> entry2 : terrenosowner2.entrySet()) {
                Terreno t1 = entry1.getValue();
                Terreno t2 = entry2.getValue();
                if (t1.getOWNER() != t2.getOWNER()) {
                    Map<Integer,Terreno> troca_teste = terrenosMap;
                    troca_teste.replace(t1.getOBJECTID(), t1,t2);
                    double NewArea_Media = Area_Media_Proprietario.calcular_Area_Media(troca_teste);
                    double diferenca = NewArea_Media - Area_media;
                    if (diferenca > 10) {
                        int trocaId = Objects.hash(t1.getOBJECTID(), t2.getOBJECTID());
                        trocasPossiveis.put(trocaId, new Troca(t1.getOBJECTID(), t2.getOBJECTID()));
                    }
                }
            }
        }

        return trocasPossiveis;
    }

    /**
     * Calcula o número de vizinhos entre os terrenos de uma lista.
     *
     * @param terrenos Lista de terrenos de um proprietário.
     * @return O número de vizinhos entre os terrenos.
     */
    public static int calcularVizinhos(List<Terreno> terrenos) {
        int count = 0;

        for (int i = 0; i < terrenos.size(); i++) {
            for (int j = i + 1; j < terrenos.size(); j++) {
                if (Grafo_Proprietario.saoContiguos(terrenos.get(i), terrenos.get(j))) {
                    count++;
                }
            }
        }
        return count;
    }
}

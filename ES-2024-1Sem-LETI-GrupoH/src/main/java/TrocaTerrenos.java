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
        Terreno terreno1;
        Terreno terreno2;

        Troca(Terreno terreno1, Terreno terreno2) {
            this.terreno1 = terreno1;
            this.terreno2 = terreno2;
        }

        /**
         * Sobrescreve o método toString para fornecer uma representação textual da troca.
         */
        @Override
        public String toString() {
            return "Troca{" +
                    "terreno1=" + terreno1 +
                    ", terreno2=" + terreno2 +
                    '}';
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
        List<Terreno> terrenosowner1 = new ArrayList<>(); // Lista de terrenos do proprietário 1.
        List<Terreno> terrenosowner2 = new ArrayList<>(); // Lista de terrenos do proprietário 2.
        double areaTotalOwner1 = 0;
        double areaTotalOwner2 = 0;
        for (Terreno t : terrenosMap.values()) {
            if (t.getOWNER() == owner1) {
                areaTotalOwner1 += t.getShape_Area();
                terrenosowner1.add(t);
            }
            if (t.getOWNER() == owner2) {
                areaTotalOwner2 += t.getShape_Area();
                terrenosowner2.add(t);
            }
        }

        int Num_Vizinhos1 = calcularVizinhos(terrenosowner1);
        int Num_Vizinhos2 = calcularVizinhos(terrenosowner2);

        double mediaArea1 = areaTotalOwner1 / Num_Vizinhos1;
        double mediaArea2 = areaTotalOwner1 / Num_Vizinhos2;

        for (int i = 1; i < terrenosowner1.size(); i++) {
            for (int j = 1; j < terrenosowner2.size(); j++) {
                Terreno t1 = terrenosowner1.get(i);
                Terreno t2 = terrenosowner2.get(j);

                if (t1.getOWNER() != t2.getOWNER()) {
                    double novaMedia1 = (areaTotalOwner1 - t1.getShape_Area() + t2.getShape_Area())
                            / terrenosowner1.size();
                    double novaMedia2 = (areaTotalOwner2 - t2.getShape_Area() + t1.getShape_Area())
                            / terrenosowner2.size();

                    double diferencaArea1 = novaMedia1 - mediaArea1;
                    double diferencaArea2 = novaMedia2 - mediaArea2;

                    if (diferencaArea1 > 10 && diferencaArea2 > 10) {
                        int trocaId = Objects.hash(t1.getOBJECTID(), t2.getOBJECTID());
                        trocasPossiveis.put(trocaId, new Troca(t1, t2));
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

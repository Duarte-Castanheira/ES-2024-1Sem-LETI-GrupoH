import java.util.*;

public class TrocaTerrenos {

    static class Troca {
        Terreno terreno1;
        Terreno terreno2;
        double impactoNaMedia;

        Troca(Terreno terreno1, Terreno terreno2, double impactoNaMedia) {
            this.terreno1 = terreno1;
            this.terreno2 = terreno2;
            this.impactoNaMedia = impactoNaMedia;
        }
    }

    public static Map<Integer, Troca> gerarSugestoesDeTroca(Map<Integer, Terreno> terrenosMap) {
        // Verificação inicial de entradas
        if (terrenosMap == null || terrenosMap.isEmpty()) {
            throw new IllegalArgumentException("O mapa de terrenos não pode ser nulo ou vazio.");
        }

        Map<Integer, Troca> trocasPossiveis = new HashMap<>();
        Map<Integer, Double> somaAreasPorProprietario = new HashMap<>();
        Map<Integer, Integer> contagemTerrenos = new HashMap<>();

        // Calcula soma de áreas e contagem de terrenos por proprietário
        for (Terreno t : terrenosMap.values()) {
            somaAreasPorProprietario.merge(t.getOWNER(), t.getShape_Area(), Double::sum);
            contagemTerrenos.merge(t.getOWNER(), 1, Integer::sum);
        }

        double areaTotal = somaAreasPorProprietario.values().stream().mapToDouble(Double::doubleValue).sum();
        int totalProprietarios = somaAreasPorProprietario.size();
        double areaMediaGlobal = areaTotal / totalProprietarios;

        // Array de chaves para facilitar combinações
        Integer[] terrenoIds = terrenosMap.keySet().toArray(new Integer[0]);

        // Simula trocas entre todas as combinações possíveis de terrenos
        for (int i = 0; i < terrenoIds.length; i++) {
            for (int j = i + 1; j < terrenoIds.length; j++) {
                Terreno t1 = terrenosMap.get(terrenoIds[i]);
                Terreno t2 = terrenosMap.get(terrenoIds[j]);

                // Só consideramos trocas entre proprietários diferentes
                if (t1.getOWNER() != t2.getOWNER()) {
                    // Simula a troca
                    double novaMedia1 = (somaAreasPorProprietario.get(t1.getOWNER()) - t1.getShape_Area() + t2.getShape_Area())
                            / contagemTerrenos.get(t1.getOWNER());
                    double novaMedia2 = (somaAreasPorProprietario.get(t2.getOWNER()) - t2.getShape_Area() + t1.getShape_Area())
                            / contagemTerrenos.get(t2.getOWNER());

                    double impactoTroca = ((novaMedia1 + novaMedia2) / 2) - areaMediaGlobal;

                    // Adiciona a troca ao mapa, classificando por menor diferença de áreas
                    double diferencaArea = Math.abs(t1.getShape_Area() - t2.getShape_Area());
                    if (diferencaArea <= 10) { // Limite arbitrário para trocas viáveis
                        int trocaId = Objects.hash(t1.getOBJECTID(), t2.getOBJECTID()); // Gera um ID único baseado nos terrenos
                        trocasPossiveis.put(trocaId, new Troca(t1, t2, impactoTroca));
                    }
                }
            }
        }

        return trocasPossiveis;
    }
}

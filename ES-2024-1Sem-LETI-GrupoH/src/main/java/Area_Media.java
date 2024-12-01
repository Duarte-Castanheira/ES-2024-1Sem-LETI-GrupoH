import java.util.ArrayList;
import java.util.List;

public class Area_Media {

    private final List<Terreno> terreno;

    public Area_Media(List<Terreno> terreno) {
        this.terreno = terreno;
    }

    public double calcularAreaMedia(String areaGeografica, String nome){
        List<Terreno> terrenos = new ArrayList<>();

        for(Terreno terreno : terreno) {
            if (areaGeografica.equalsIgnoreCase("Freguesia") && terreno.getFreguesia().equalsIgnoreCase(nome)){
                terrenos.add(terreno);
            } else if (areaGeografica.equalsIgnoreCase("Município") && terreno.getMunicipio().equalsIgnoreCase(nome)){
                terrenos.add(terreno);
            } else if (areaGeografica.equalsIgnoreCase("Ilha") && terreno.getIlha().equalsIgnoreCase(nome)){
                terrenos.add(terreno);
            }
        }
        if (terrenos.isEmpty()){
            return -1;
        }

        double somaArea = 0;
        for (Terreno terreno : terrenos) {
            somaArea += terreno.getShape_Area();
        }

        return somaArea / terrenos.size();
    }
}

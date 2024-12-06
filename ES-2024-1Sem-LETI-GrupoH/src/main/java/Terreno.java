import org.locationtech.jts.geom.MultiPolygon;

/**
 * Classe que representa um terreno com atributos geoespaciais e administrativos
 *
 */

public class Terreno {

    /**
     * // Atributos da classe, representando propriedades do terreno
     */

    private int OBJECTID;
    private String PAR_ID;
    private String PAR_NUM;
    private double Shape_Length;
    private double Shape_Area;
    private MultiPolygon geometry;
    private int OWNER;
    private String Freguesia;
    private String Municipio;
    private String Ilha;


    /**
     * Construtor parametrizado que inicializa todos os atributos
     */

    public Terreno(int OBJECTID, String PAR_ID, String PAR_NUM, double Shape_Length, double Shape_Area,
                   MultiPolygon geometry, int OWNER, String Freguesia, String Municipio, String Ilha) {
        this.OBJECTID = OBJECTID;
        this.PAR_ID = PAR_ID;
        this.PAR_NUM = PAR_NUM;
        this.Shape_Length = Shape_Length;
        this.Shape_Area = Shape_Area;
        this.geometry = geometry;
        this.OWNER = OWNER;
        this.Freguesia = Freguesia;
        this.Municipio = Municipio;
        this.Ilha = Ilha;
    }

    /**
    * Construtor padrão (vazio) para inicializar um objeto sem parâmetros
    */

    public Terreno() {

    }

    /**
     * Obtém o identificador único do terreno.
     *
     * @return O identificador `OBJECTID`.
     */

    public int getOBJECTID() {
        return OBJECTID;
    }

    /**
     * Define o identificador único do terreno.
     *
     * @param OBJECTID O identificador `OBJECTID` a ser atribuído.
     */

    public void setOBJECTID(int OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    /**
     * Obtém o identificador do parcelamento.
     *
     * @return O identificador `PAR_ID`.
     */

    public String getParID() {
        return PAR_ID;
    }

    /**
     * Define o identificador do parcelamento.
     *
     * @param parID O identificador `PAR_ID` a ser atribuído.
     */

    public void setParID(String parID) {
        this.PAR_ID = parID;
    }

    /**
     * Obtém o número do parcelamento.
     *
     * @return O número do parcelamento `PAR_NUM`.
     */

    public String getParNUM() {
        return PAR_NUM;
    }

    /**
     * Define o número do parcelamento.
     *
     * @param parNUM O número do parcelamento `PAR_NUM` a ser atribuído.
     */

    public void setParNUM(String parNUM) {
        this.PAR_NUM = parNUM;
    }

    /**
     * Obtém o comprimento do perímetro do terreno.
     *
     * @return O comprimento do perímetro `Shape_Length`.
     */

    public double getShape_Length() {
        return Shape_Length;
    }

    /**
     * Define o comprimento do perímetro do terreno.
     *
     * @param shape_Length O comprimento do perímetro `Shape_Length` a ser atribuído.
     */

    public void setShape_Length(double shape_Length) {
        this.Shape_Length = shape_Length;
    }

    /**
     * Obtém a área do terreno.
     *
     * @return A área do terreno `Shape_Area`.
     */

    public double getShape_Area() {
        return Shape_Area;
    }

    /**
     * Define a área do terreno.
     *
     * @param shape_Area A área do terreno `Shape_Area` a ser atribuída.
     */

    public void setShape_Area(double shape_Area) {
        this.Shape_Area = shape_Area;
    }

    /**
     * Obtém a geometria do terreno no formato MultiPolygon.
     *
     * @return A geometria `geometry`.
     */

    public MultiPolygon getGeometry() {
        return geometry;
    }

    /**
     * Define a geometria do terreno no formato MultiPolygon.
     *
     * @param geometry A geometria `geometry` a ser atribuída.
     */

    public void setGeometry(MultiPolygon geometry) {
        this.geometry = geometry;
    }

    /**
     * Obtém o identificador do proprietário do terreno.
     *
     * @return O identificador do proprietário `OWNER`.
     */

    public int getOWNER() {
        return OWNER;
    }

    /**
     * Define o identificador do proprietário do terreno.
     *
     * @param OWNER O identificador do proprietário `OWNER` a ser atribuído.
     */

    public void setOWNER(int OWNER) {
        this.OWNER = OWNER;
    }

    /**
     * Obtém o nome da freguesia onde o terreno está localizado.
     *
     * @return O nome da freguesia `Freguesia`.
     */

    public String getFreguesia() {
        return Freguesia;
    }

    /**
     * Define o nome da freguesia onde o terreno está localizado.
     *
     * @param freguesia O nome da freguesia `Freguesia` a ser atribuído.
     */

    public void setFreguesia(String freguesia) {
        this.Freguesia = freguesia;
    }

    /**
     * Obtém o nome do município onde o terreno está localizado.
     *
     * @return O nome do município `Municipio`.
     */

    public String getMunicipio() {
        return Municipio;
    }

    /**
     * Define o nome do município onde o terreno está localizado.
     *
     * @param municipio O nome do município `Municipio` a ser atribuído.
     */

    public void setMunicipio(String municipio) {
        this.Municipio = municipio;
    }

    /**
     * Obtém o nome da ilha onde o terreno está localizado.
     *
     * @return O nome da ilha `Ilha`.
     */

    public String getIlha() {
        return Ilha;
    }

    /**
     * Define o nome da ilha onde o terreno está localizado.
     *
     * @param ilha O nome da ilha `Ilha` a ser atribuído.
     */

    public void setIlha(String ilha) {
        this.Ilha = ilha;
    }

    /**
    * Método para converter o objeto Terreno em uma representação textual
    */

    @Override
    public String toString() {
        return "Terreno{" +
                "OBJECTID=" + OBJECTID +
                ", parID=" + PAR_ID +
                ", parNUM=" + PAR_NUM +
                ", shape_Length=" + Shape_Length +
                ", shape_Area=" + Shape_Area +
                ", geometry='" + geometry +
                ", OWNER=" + OWNER +
                ", Freguesia='" + Freguesia +
                ", Municipio='" + Municipio +
                ", Ilha='" + Ilha +
                '}';
    }
}

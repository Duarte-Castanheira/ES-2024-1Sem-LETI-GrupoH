import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;

public class Terreno {

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

    public Terreno() {
    }

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


    public int getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(int OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getParID() {
        return PAR_ID;
    }

    public void setParID(String parID) {
        this.PAR_ID = parID;
    }

    public String getParNUM() {
        return PAR_NUM;
    }

    public void setParNUM(String parNUM) {
        this.PAR_NUM = parNUM;
    }

    public double getShape_Length() {
        return Shape_Length;
    }

    public void setShape_Length(double shape_Length) {
        this.Shape_Length = shape_Length;
    }

    public double getShape_Area() {
        return Shape_Area;
    }

    public void setShape_Area(double shape_Area) {
        this.Shape_Area = shape_Area;
    }

    public MultiPolygon getGeometry() {
        return geometry;
    }

    public void setGeometry(MultiPolygon geometry) {
        this.geometry = geometry;
    }

    public int getOWNER() {
        return OWNER;
    }

    public void setOWNER(int OWNER) {
        this.OWNER = OWNER;
    }

    public String getFreguesia() {
        return Freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.Freguesia = freguesia;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        this.Municipio = municipio;
    }

    public String getIlha() {
        return Ilha;
    }

    public void setIlha(String ilha) {
        this.Ilha = ilha;
    }

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

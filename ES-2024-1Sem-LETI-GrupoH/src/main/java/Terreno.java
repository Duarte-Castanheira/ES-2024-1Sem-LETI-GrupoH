public class Terreno {

    private int objectID;
    private double parID;
    private long parNUM;
    private double shape_Length;
    private double shape_Area;
    private String geometry;
    private int owner;

    public Terreno(int objectID, double parID, long parNUM, double shape_Length, double shape_Area, String geometry, int owner) {
        this.objectID = objectID;
        this.parID = parID;
        this.parNUM = parNUM;
        this.shape_Length = shape_Length;
        this.shape_Area = shape_Area;
        this.geometry = geometry;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Terreno{" +
                "objectID=" + objectID +
                ", parID=" + parID +
                ", parNUM=" + parNUM +
                ", shape_Length=" + shape_Length +
                ", shape_Area=" + shape_Area +
                ", geometry='" + geometry + '\'' +
                ", owner=" + owner +
                '}';
    }
}

public class Terreno {

    private int objectID;
    private int parID;
    private int parNUM;
    private int shape_Length;
    private int shape_Area;
    private String geometry;
    private int owner;

    public Terreno(int objectID, int parID, int parNUM, int shape_Length, int shape_Area, String geometry, int owner) {
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

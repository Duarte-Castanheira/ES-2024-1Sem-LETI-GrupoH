public class Coordenadas {
    double x;
    double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Coordenadas(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordenadas{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenadas that = (Coordenadas) o;
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0;
    }

}

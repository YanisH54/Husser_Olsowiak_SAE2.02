public class Arc {
    private String destination;
    private double cout;

    public Arc(String dest, double cout){
        this.destination = dest;
        this.cout = cout;
    }

    public String getDestination() {
        return destination;
    }

    public int getCout() {
        return cout;
    }
}

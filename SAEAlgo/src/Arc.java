public class Arc {
    private String destination;
    private int cout;

    public Arc(String dest, int cout){
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

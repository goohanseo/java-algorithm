package finterm.week11;

public class 원의접점count {
    public static int calculateNumberOfRegions(int n) {
        int edges = n * (n - 1) / 2;
        int vertices = n * (n - 1) * (n - 2) / 6;
        int regions = 1 + edges - vertices;
        return regions;
    }

    public static void main(String[] args) {
        int n = 5; // Number of circles
        int numberOfRegions = calculateNumberOfRegions(n);
        System.out.println("Number of regions: " + numberOfRegions);
    }
}

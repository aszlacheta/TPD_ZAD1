package criterias;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        MinimaksUzytecznosci minmaksUzyt = new MinimaksUzytecznosci();
        Result result = minmaksUzyt.find();
        System.out.println(result.getValue());
    }
}

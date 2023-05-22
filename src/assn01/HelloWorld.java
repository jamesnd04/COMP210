package assn01;

public class HelloWorld {
    public static void main(String[] args){
        System.out.println("Hello, World");
        double test = 10.2;
        System.out.println(String.format("%.2f",test));
        double test2 = (double)(Math.round(test * 100) / 100.00);
        System.out.println(test2);


    }
}

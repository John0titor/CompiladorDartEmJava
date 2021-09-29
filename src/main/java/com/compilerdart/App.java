package com.compilerdart;

/**
 * Hello world!
 *
 */


public class App {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(
                "C:\\Users\\joaov\\Documents\\GitHub\\CompiladorDartEmJava\\src\\main\\java\\com\\compilerdart\\input.dart");
        for (int i = 0; i < 300; i++) {
            Token tk = scanner.nextToken();
            System.out.println(tk.toString());
        }*/
        Sintatico s = new Sintatico();
        try {
            s.S();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

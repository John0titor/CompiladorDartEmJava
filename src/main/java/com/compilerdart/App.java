package com.compilerdart;

/**
 * Hello world!
 *
 */


public class App {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner( "C:\\Users\\Rafael\\Documents\\Faculdade\\TERCEIRO SEMESTRE\\LINGUAGENS FORMAIS\\TrabalhoFinalCompilador\\projeto\\demo\\src\\main\\java\\com\\compilerdart\\input2.txt");
        for (int i = 0; i < 3000; i++) {
            Token tk = scanner.le_Token();
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

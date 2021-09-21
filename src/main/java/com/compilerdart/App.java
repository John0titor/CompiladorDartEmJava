package com.compilerdart;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner("C:\\Users\\Rafael\\Documents\\Faculdade\\TERCEIRO SEMESTRE\\LINGUAGENS FORMAIS\\TrabalhoFinalCompilador\\projeto\\demo\\src\\main\\java\\com\\compilerdart\\input.txt");
        for (int i = 0; i < 30
        ; i++) {
            Token tk = scanner.nextToken();
            System.out.println(tk.toString());
        }
    }
}

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner();
       List<Token> tokens = scanner.scan("testFile.txt");
        int x=tokens.size();
        String []a=new String[x] ;
        int i=0;
        for (Token token : tokens ) {
            a[i]=tokens.get(i).value;
            System.out.println(a[i]);
            i++;
        }
        LL1Parser parser = new LL1Parser(tokens);
       System.out.println(parser.parse());
    }
}
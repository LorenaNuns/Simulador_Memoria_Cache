import java.util.Scanner;

public class simulador_cache {

  public static void main(String[] args) {

    memorias m = new memorias();
    Scanner in = new Scanner(System.in);
    int[] vetor_decimal = new int[4];

    System.out.println("Digite 4 valores dentr 0-15: ");
    for (int i = 0; i < vetor_decimal.length; i++) {
      System.out.print("Digite o valor " + (i + 1) + ": ");
      vetor_decimal[i] = in.nextInt();
      if (vetor_decimal[i] > 15) {
        System.out.println("Número inválido, somente números de 0-15 são aceitos");
        System.out.print("Digite o valor " + (i + 1) + " :");
        vetor_decimal[i] = in.nextInt();
      }
    }

    m.preenche();
    m.cache_mostra();
    m.transforma(vetor_decimal);
    m.alocacao_cache();
    m.cache_mostra();
    m.mostra_hit_miss();
  }

}
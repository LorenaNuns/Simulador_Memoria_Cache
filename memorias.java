public class memorias {
  // memoria cache com 4 posições
  String[][] cache = new String[4][3];
  // vetor para os valores que o usuario colocou
  String[] valores_recebidos = new String[4];
  // vetor para analisar a ordem de miss e hit
  String[] cont_miss = new String[4];

  double hit = 0, miss = 0;
  double miss_rate;

  // memoria principal de 16 posições, 4bits
  String[] mp = new String[16];

  void preenche() {
    // preenche a cache na primeira coluna com as linhas 00, 01, 10, 11, na 2º
    // coluna são o espaço das tag e a ultima onde fica os endereços
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {

        if (i == 0 && j == 0) {
          cache[i][j] = "00";
        } else if (i == 1 && j == 0) {
          cache[i][j] = "01";
        } else if (i == 2 && j == 0) {
          cache[i][j] = "10";
        } else if (i == 3 && j == 0) {
          cache[i][j] = "11";
        }

        if (cache[i][j] == null) {
          cache[i][j] = "xx";
        }

        // preenche os endereços da memoria principal, nela só terá os endereços e não
        // os
        // dados
        for (int w = 0; w < 16; w++) {
          int aux = w;
          String converte_decimal = Integer.toBinaryString(aux);
          String resultado_binario = String.format("%4s", converte_decimal).replaceAll(" ", "0");
          mp[w] = resultado_binario;
        }
      }
    }
  }

  // mostra o estado da cache e os dados nela
  void cache_mostra() {
    System.out.println("");
    System.out.println("Mostrando cache");
    System.out.println("Linha     Tag   Dados");
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print(cache[i][j] + "        ");
      }
      System.out.println("");
    }
    System.out.println("");

  }

  // transforma numero decimal pra binario
  void transforma(int[] num) {
    int i = 0;
    for (int n : num) {
      String binaria = Integer.toBinaryString(n);
      String resultWithPadding = String.format("%4s", binaria).replaceAll(" ", "0");
      valores_recebidos[i] = resultWithPadding;
      i++;
    }
    for (int s = 0; s < valores_recebidos.length; s++) {
      System.out.println("Número em binário: " + valores_recebidos[s]);
    }

  }

  // coloca os endereços na cache
  void alocacao_cache() {
    int i = 0;
    do {
      String verifica_ln = valores_recebidos[i].substring(2, 4);
      String verifica_tag = valores_recebidos[i].substring(0, 2);
      if (verifica_ln.equals("00") && cache[0][1] == "xx") {
        cache[0][1] = verifica_tag; // miss
        cache[0][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      } else if (verifica_ln.equals("00") && cache[0][1].equals(verifica_tag)) {
        cache[0][1] = verifica_tag; // miss
        cache[0][2] = valores_recebidos[i];
        hit++;
        cont_miss[i] = "hit";
      } else if (verifica_ln.equals("00") && cache[0][1] != verifica_tag) {
        cache[0][1] = verifica_tag; // miss
        cache[0][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      } else if (verifica_ln.equals("01") && cache[1][1] == "xx") {
        cache[1][1] = verifica_tag; // miss
        cache[1][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      } else if (verifica_ln.equals("01") && cache[1][1].equals(verifica_tag)) {
        cache[1][1] = verifica_tag; // hit
        cache[1][2] = valores_recebidos[i];
        hit++;
        cont_miss[i] = "hit";
      } else if (verifica_ln.equals("01") && cache[1][1] != verifica_tag) {
        cache[1][1] = verifica_tag; // miss
        cache[1][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      } else if (verifica_ln.equals("10") && cache[2][1] == "xx") {
        cache[2][1] = verifica_tag; // miss
        cache[2][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      } else if (verifica_ln.equals("10") && cache[2][1].equals(verifica_tag)) {
        cache[2][1] = verifica_tag; // hit
        cache[2][2] = valores_recebidos[i];
        hit++;
        cont_miss[i] = "hit";
      } else if (verifica_ln.equals("10") && cache[2][1] != verifica_tag) {
        cache[2][1] = verifica_tag; // miss
        cache[2][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      } else if (verifica_ln.equals("11") && cache[3][1] == "xx") {
        cache[3][1] = verifica_tag; // miss
        cache[3][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      } else if (verifica_ln.equals("11") && cache[3][1].equals(verifica_tag)) {
        cache[3][1] = verifica_tag; // hit
        cache[3][2] = valores_recebidos[i];
        hit++;
        cont_miss[i] = "hit";
      } else if (verifica_ln.equals("11") && cache[3][1] != verifica_tag) {
        cache[3][1] = verifica_tag; // miss
        cache[3][2] = valores_recebidos[i];
        miss++;
        cont_miss[i] = "miss";
      }
      i++;
    } while (i < 4);

  }

  void mostra_hit_miss() {
    System.out.println("----Relatório de miss e miss-rate------");
    System.out.println("Quantidade de misses: " + miss);
    System.out.print("Ordem de miss e hit: ");
    for (int i = 0; i < 4; i++) {
      System.out.print(cont_miss[i] + ",");
    }
    System.out.println("");
    double aux = miss + hit;
    double aux2 = 100 / aux;
    miss_rate = aux2 * miss;
    System.out.println("Miss-rate: " + miss_rate + "%");
  }
}

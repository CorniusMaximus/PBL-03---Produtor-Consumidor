import java.util.concurrent.Semaphore;

public class Main {
    public static void main (String[] arg) {

        int[][] tempoFabricanteA = {{600, 1000},
                {200, 400},
                {1000, 1200},
                {400, 600},
                {800, 1000},
                {1400, 1600},
                {400, 600},
                {800, 1000}};

        int[][] tempoFabricanteB = {{400, 600},
                {800, 1000},
                {1200, 1400},
                {800, 1000},
                {200, 400},
                {1000, 1200},
                {1000, 1200},
                {600, 800}};

        int[][] tempoFabricanteC = {{1000, 1200},
                {1200, 1400},
                {400, 600},
                {600, 800},
                {400, 600},
                {400, 600},
                {1000, 1200},
                {400, 600}};

        int[][] tempoFabricanteD = {{800, 1000},
                {600, 800},
                {400, 600},
                {1000, 1200},
                {1200, 1400},
                {800, 1000},
                {600, 800},
                {1200, 1400}};

        int[] tempoEntregaA = {1000, 2000};
        int[] tempoEntregaB = {4000, 6000};

        Semaphore mutex_venda = new Semaphore(1);
        Semaphore mutex_transporte = new Semaphore(1);
        Semaphore fila_venda = new Semaphore(0);
        Semaphore fila_transporte = new Semaphore(100);
        Semaphore fila_entrega = new Semaphore(0);

        Semaphore s_FA = new Semaphore(4);

        Semaphore s_TA = new Semaphore(10);

        FilaVenda fv = new FilaVenda(fila_venda);
        FilaEntrega fe = new FilaEntrega(fila_entrega);

        Loja loja_A = new Loja(mutex_venda, fila_venda, fv, "A");
        Fabricante fabricante_A = new Fabricante(mutex_venda, mutex_transporte, fila_venda, fv, fe, "A", tempoFabricanteA, s_FA);
        Transportadora transportadora_A = new Transportadora(mutex_transporte, fila_entrega, fe, "A", tempoEntregaA, s_TA);

      loja_A.start();
      fabricante_A.start();
      transportadora_A.start();
    }
}
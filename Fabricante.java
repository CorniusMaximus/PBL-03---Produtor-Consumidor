import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Random;

public class Fabricante extends Thread {
    private String nome_fabricante;
    private Semaphore mutex_venda, mutex_transporte, fila_venda, mutex_fabricacao;
    private FilaVenda fv;
    private FilaEntrega fe;
    private int[][] tempo;
    Random rand = new Random();

    public Fabricante(Semaphore mutex_venda, Semaphore mutex_transpote, Semaphore fila_venda, FilaVenda fv, FilaEntrega fe, String nome_fabricante, int[][] tempo, Semaphore mutex_fabricacao) {
        this.mutex_venda = mutex_venda;
        this.mutex_transporte = mutex_transporte;
        this.fila_venda = fila_venda;
        this.nome_fabricante = nome_fabricante;
        this.fv = fv;
        this.tempo = tempo;
        this.mutex_fabricacao = mutex_fabricacao;
    }

    public void run() {
            try {
                while (true) {
                mutex_venda.acquire();
                fila_venda.acquire();
                Venda novaVenda = pegaVenda(fv);
                mutex_venda.release();

                mutex_fabricacao.acquire();
                System.out.println("Fabricando...");
                switch(novaVenda.produto) {
                    case "A":
                        Thread.sleep(rand.nextInt(tempo[0][0], tempo[0][1]));
                    case "B":
                        Thread.sleep(rand.nextInt(tempo[1][0], tempo[1][1]));
                    case "C":
                        Thread.sleep(rand.nextInt(tempo[2][0], tempo[2][1]));
                    case "D":
                        Thread.sleep(rand.nextInt(tempo[3][0], tempo[3][1]));
                    case "E":
                        Thread.sleep(rand.nextInt(tempo[4][0], tempo[4][1]));
                    case "F":
                        Thread.sleep(rand.nextInt(tempo[5][0], tempo[5][1]));
                    case "G":
                        Thread.sleep(rand.nextInt(tempo[6][0], tempo[6][1]));
                    case "H":
                        Thread.sleep(rand.nextInt(tempo[7][0], tempo[7][1]));
                }

                fila_venda.release();
                mutex_fabricacao.release();
                mutex_transporte.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public Venda pegaVenda(FilaVenda venda) {
        Venda novaVenda = fv.removeVenda();

        CriaEntrega(novaVenda, novaVenda.produto, fe);

        return novaVenda;
    }

    private void CriaEntrega(Venda venda, String produto, FilaEntrega fe) {
        Entrega novaEntrega = new Entrega(venda, produto);
        fe.addEntrega(novaEntrega);
    }
}
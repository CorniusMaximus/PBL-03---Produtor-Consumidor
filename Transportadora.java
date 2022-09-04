import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Random;

public class Transportadora extends Thread {
    private String nome_transportadora;
    private Semaphore mutex_transporte, fila_transporte, fila_entrega, mutex_transportando;
    private FilaEntrega fe;
    private int[] tempo;
    Random rand = new Random();

    public Transportadora(Semaphore mutex_transpote, Semaphore fila_transporte, FilaEntrega fe, String nome_transportadora, int[] tempo, Semaphore mutex_transportando) {
        this.mutex_transporte = mutex_transporte;
        this.fila_transporte = fila_transporte;
        this.nome_transportadora = nome_transportadora;
        this.fe = fe;
        this.tempo = tempo;
        this.mutex_transportando = mutex_transportando;
    }

    public void run() {
            try {
                while (true) {
                fila_entrega.acquire();
                fila_transporte.acquire();
                mutex_transportando.acquire();
                Entrega novaEntrega = pegaEntrega(fe);
                fila_entrega.release();

                System.out.println("Transportando...");
                Thread.sleep(rand.nextInt(tempo[0], tempo[1]));

                fila_transporte.release();
                mutex_transportando.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public Entrega pegaEntrega(FilaEntrega fe) {

        return fe.removeEntrega();
    }
}
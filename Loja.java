import java.util.concurrent.Semaphore;
import java.util.Random;

public class Loja extends Thread {
    private String nome_loja;
    private int contador_vendas = 0;
    private Semaphore mutex_venda, fila_venda;
    private FilaVenda fv;
    static String randomChar;
    Random rand = new Random();

    public Loja(Semaphore mutex_venda, Semaphore fila_venda, FilaVenda fv, String nome_loja) {
        this.mutex_venda = mutex_venda;
        this.fila_venda = fila_venda;
        this.nome_loja = nome_loja;
        this.fv = fv;
    }

    public void run() {
        try {
                while (true) {
                mutex_venda.acquire();
                Thread.sleep(rand.nextInt(1000, 15000));

                System.out.println("Vendendo...");
                CriaVenda(nome_loja + contador_vendas, IniciaVenda(), fv);

                mutex_venda.release();
                fila_venda.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void CriaVenda(String ln, String produto, FilaVenda fv) {
        Venda novaVenda = new Venda(ln, produto);
        fv.addVenda(novaVenda);
        contador_vendas++;
    }

    private String IniciaVenda() {
        char letra = (char) ('a' + rand.nextInt(8));
        String randomChar = "" + letra;

        randomChar = randomChar.toUpperCase();
        System.out.println("Produto: " + randomChar);
        return randomChar;
    }
}
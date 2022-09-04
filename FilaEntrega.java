import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class FilaEntrega {
    private Semaphore fila_entrega;
    public ArrayList<Entrega> entregas = new ArrayList<Entrega>();

    public FilaEntrega(Semaphore fila_entrega){
        this.fila_entrega = fila_entrega;
    }

    public void addEntrega(Entrega entrega) {
        entregas.add(entrega);
    }

    public Entrega removeEntrega() {
        Entrega entrega = entregas.get(0);
        entregas.remove(0);

        return entrega;
    }

}
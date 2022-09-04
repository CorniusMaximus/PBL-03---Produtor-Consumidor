import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class FilaVenda{
    private Semaphore fila_venda;
    public ArrayList<Venda> vendas = new ArrayList<Venda>();

    public FilaVenda(Semaphore fila_venda){
        this.fila_venda = fila_venda;
    }

    public void addVenda(Venda venda) {
        vendas.add(venda);
        System.out.println(vendas);
    }

    public Venda removeVenda() {
        Venda venda = vendas.get(0);
        vendas.remove(0);
        
        System.out.println(vendas);
        return venda;
    }

}
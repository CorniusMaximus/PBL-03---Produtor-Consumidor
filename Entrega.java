public class Entrega{
    private Venda venda;
    public String produto;

    public Entrega(Venda venda, String produto){
        this.venda = venda;
        this.produto = produto;
    }
}
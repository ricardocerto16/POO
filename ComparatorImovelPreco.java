import java.util.Comparator;
import java.io.Serializable;

public class ComparatorImovelPreco implements Comparator<Imovel>, Serializable
{
    public int compare(Imovel i1, Imovel i2) {
        return Double.compare(i1.getPrecoPedido(), i2.getPrecoPedido());
    }
}

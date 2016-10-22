import java.util.Comparator;
import java.io.Serializable;

public class ComparatorConsultaTempo implements Comparator<Consulta>, Serializable
{
    public int compare(Consulta c1, Consulta c2) {
        if(c1.getData().equals(c2.getData()))
            return 0;
        if(c1.getData().isAfter(c2.getData()))
            return -1;
        else return 1;
    }
}

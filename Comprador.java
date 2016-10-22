import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Comprador extends Utilizador
{
    private Set<Imovel> imoFav;
    
    public Comprador(String email, String nome, String password, String morada, String dataDeNascimento){
        super(email, nome, password, morada, dataDeNascimento);
        imoFav = new TreeSet<>(new ComparatorImovelPreco());
    }
    
    public Comprador(){
        super("","","","","");
        imoFav = new TreeSet<>(new ComparatorImovelPreco());
    }
    
    public Comprador(Comprador c){
        super(c);
        this.imoFav = new TreeSet<>(new ComparatorImovelPreco());
        c.getImoFav().forEach(e -> {this.imoFav.add(e.clone());});
    }
    
    public void setImoFav(Set<Imovel> imoveis){
        imoveis.forEach(e -> {this.imoFav.add(e);});
    }
    
    public Set<Imovel> getImoFav(){
        return this.imoFav;
    }
    
    public void addImoFav(Imovel i){
        this.imoFav.add(i);
    }
    
    public Comprador clone(){
        return new Comprador(this);
    }
    
    public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Comprador m = (Comprador) o;
       return (super.equals(m));
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|COMPRADOR|\n");
        sb.append(super.toString()).append("\n");
        return sb.toString();
    }
}

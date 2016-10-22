import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Stack;

public class Vendedor extends Utilizador
{
    private Set<Imovel> imoVenda, imoVendidos;

    public Vendedor(String email, String nome, String password, String morada, String dataDeNascimento){
        super(email, nome, password, morada, dataDeNascimento);
        imoVenda = new TreeSet<>(new ComparatorImovelPreco());
        imoVendidos = new TreeSet<>(new ComparatorImovelPreco());
    }
    
    public Vendedor(){
        this("","","","","");
        imoVenda = new TreeSet<>(new ComparatorImovelPreco());
        imoVendidos = new TreeSet<>(new ComparatorImovelPreco());
    }
    
    public Vendedor(Vendedor v){
        super(v);
        imoVenda = new TreeSet<>(new ComparatorImovelPreco());
        v.getImoVenda().forEach(e -> {this.imoVenda.add(e.clone());});
        imoVendidos = new TreeSet<>(new ComparatorImovelPreco());
        v.getImoVendidos().forEach(e -> {this.imoVendidos.add(e.clone());});
    }
    
    public Set<Imovel> getImoVenda(){
        return this.imoVenda;
    }
    
    public Set<Imovel> getImoVendidos(){
        return this.imoVendidos;
    }
    
    public void setImoVenda(Set<Imovel> imoveis){
        this.imoVenda = imoveis.stream()
                               .map(i -> {return i.clone();})
                               .collect(Collectors.toSet());
    }
    
    public void setImoVendidos(Set<Imovel> imoveis){
        this.imoVendidos = imoveis.stream()
                                  .map(i -> {return i.clone();})
                                  .collect(Collectors.toSet());
    }
   
    public void addImoVenda(Imovel i){
        imoVenda.add(i);
    }
    
    public void addImoVendidos(Imovel i){
        imoVendidos.add(i);
    }
    
    public void setEstadoVendido(Imovel i){
        imoVenda.remove(i);
        imoVendidos.add(i);
    }
    
    public void setEstadoVenda(Imovel i){
        imoVendidos.remove(i);
        imoVenda.add(i);
    }
    
    public Set<Imovel> getTopConsultas(int n){
        Set<Imovel> venda = imoVenda.stream().filter(i -> i.getNumConsultas()>=n).collect(Collectors.toSet());
        Set<Imovel> vendido = imoVendidos.stream().filter(i -> i.getNumConsultas()>=n).collect(Collectors.toSet());
        venda.addAll(vendido);
        return venda;
    }
    
    public Vendedor clone(){
        return new Vendedor(this);
    }
    
    public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Vendedor m = (Vendedor) o;
       return (super.equals(m));
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|VENDEDOR|\n");
        sb.append(super.toString()).append("\n");
        return sb.toString();
    }
    
    public int hashCode(){
       return this.getEmail().hashCode();
   }
}
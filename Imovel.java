import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

public abstract class Imovel implements Serializable ,Comparable<Imovel>
{
    private String rua;
    private double precoPedido;
    private double precoMinAceite;
    private String id = null;
    private String estado = "Venda";
    private List<Consulta> consultas;
    
    public Imovel(String rua, double precoPedido, double precoMinAceite){
        this.rua = rua;
        this.precoPedido = precoPedido;
        this.precoMinAceite = precoMinAceite;
        consultas = new ArrayList<>();
    }
    
    public Imovel(){
        this("",0,0);
        consultas = new ArrayList<>();
    }
    
    public Imovel(Imovel i){
        this.rua = i.getRua();
        this.precoPedido = i.getPrecoPedido();
        this.precoMinAceite = i.getPrecoMinAceite();
        this.consultas = new ArrayList<Consulta>();
        this.consultas = i.getConsultas().stream()
                                         .map(c -> {return c.clone();})
                                         .collect(Collectors.toList());
        this.id = i.getId();
        this.estado = i.getEstado();
    }
    
    public String getRua(){
        return rua;
    }
    
    public double getPrecoPedido(){
        return precoPedido;
    }
    
    public double getPrecoMinAceite(){
        return precoMinAceite;
    }
    
    public String getId(){
        return id;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public int getNumConsultas(){
        return consultas.size();
    }
    
    public List<Consulta> getConsultas(){
        return this.consultas.stream().map(c -> {return c.clone();}).collect(Collectors.toList());
    }
    
    public void setRua(String rua){
        this.rua = rua;
    }
    
    public void setPrecoPedido(double precoPedido){
        this.precoPedido = precoPedido;
    }
    
    public void setPrecoMinAceite(double precoMinAceite){
        this.precoMinAceite = precoMinAceite;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public void addConsulta(Consulta c){
        this.consultas.add(c.clone());
    }
    
    public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Imovel m = (Imovel) o;
       return (this.rua.equals(m.getRua()) &&
               this.precoPedido == m.getPrecoPedido() &&
               this.precoMinAceite == m.getPrecoMinAceite() &&
               this.estado.equals(m.getEstado()));
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Rua: ").append(rua).append("\n");
        sb.append("Preço Pedido: ").append(precoPedido).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        return sb.toString();
    }
    
    public abstract Imovel clone();
    
    public int compareTo(Imovel i){
        if(this.precoPedido==i.getPrecoPedido())
            return 0;
        if(this.precoPedido>i.getPrecoPedido())
            return 1;
        else return -1;
    }
    
    public int hashCode(){
        return this.id.hashCode();
    }
}

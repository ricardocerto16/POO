import java.io.Serializable;

public class Licitador extends Comprador implements Serializable
{
    private double limite, incrementos, minutos;
    String idComprador;
    private long tempo;
    
    public Licitador(String idComprador, double limite, double incrementos, double minutos){
        this.idComprador = idComprador;
        this.limite = limite;
        this.incrementos = incrementos;
        this.minutos = minutos;
    }
    
    public String getId(){
        return this.idComprador;
    }
    
    public double getLimite(){
        return this.limite;
    }
    
    public double getIncrementos(){
        return incrementos;
    }
    
    public double getMinutos(){
        return minutos;
    }
    
    public long getUltimoTempo(){
        return this.tempo;
    }
    
    public void setUltimoTempo(long tempo){
        this.tempo = tempo;
    }
}

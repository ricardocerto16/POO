
public class Moradia extends Imovel implements Habitavel
{
    private String tipo;
    private int numQuaWC, numPorta;
    private double areaImpl, areaTotCob, areaTerrEnv;
    
   public Moradia(String rua, double precoPedido, double precoMinAceite, String tipo, double areaImpl, double areaTotCob, double areaTerrEnv, int numQuaWC, int numPorta){
        super(rua, precoPedido, precoMinAceite);
        this.tipo = tipo;
        this.areaImpl = areaImpl;
        this.areaTotCob = areaTotCob;
        this.areaTerrEnv = areaTerrEnv;
        this.numQuaWC = numQuaWC;
        this.numPorta = numPorta;
   }
    
   public Moradia(){
        this("",0,0,"",0,0,0,0,0);
   }
    
   public Moradia(Moradia m){
        super(m);
        this.tipo = m.getTipo();
        this.areaImpl = m.getAreaImpl();
        this.areaTotCob = m.getAreaTotCob();
        this.areaTerrEnv = m.getAreaTerrEnv();
        this.numQuaWC = m.getNumQuaWC();
        this.numPorta = m.getNumPorta();
   }
   
   public String getTipo(){
       return tipo;
   }
   
   public double getAreaImpl(){
       return areaImpl;
   }
   
   public double getAreaTotCob(){
       return areaTotCob;
   }
   
   public double getAreaTerrEnv(){
       return areaTerrEnv;
   }
   
   public int getNumQuaWC(){
       return numQuaWC;
   }
   
   public int getNumPorta(){
       return numPorta;
   }
   
   public Moradia clone(){
       return new Moradia(this);
   }
   
   public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Moradia m = (Moradia) o;
       return (super.equals(m) && 
               this.tipo.equals(m.getTipo()) && 
               this.areaImpl == m.getAreaImpl() && 
               this.areaTotCob == m.getAreaTotCob() && 
               this.areaTerrEnv == m.getAreaTerrEnv() && 
               this.numQuaWC == m.getNumQuaWC() && 
               this.numPorta == m.getNumPorta());
    }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|MORADIA|\n");
        sb.append("Tipo de Moradia: ").append(tipo).append("\n");
        sb.append("Área de Implantação: ").append(areaImpl).append("\n");
        sb.append("Área Total Coberta: ").append(areaTotCob).append("\n");
        sb.append("Área de Terreno Envolvente: ").append(areaTerrEnv).append("\n");
        sb.append("Número de Quartos e WC's: ").append(numQuaWC).append("\n");
        sb.append("Número da Porta: ").append(numPorta).append("\n");
        sb.append(super.toString());
        return sb.toString();
   }
}


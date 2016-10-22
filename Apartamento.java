
public class Apartamento extends Imovel implements Habitavel
{
   private String tipo;
   private double areaTot;
   private int numQuaWC, numPorta, numAndar;
   private boolean garagem;
   
   public Apartamento(String rua, double precoPedido, double precoMinAceite, String tipo, double areaTot, int numQuaWC, int numPorta, int numAndar, boolean garagem){
       super(rua, precoPedido, precoMinAceite);
       this.tipo = tipo;
       this.areaTot = areaTot;
       this.numQuaWC = numQuaWC;
       this.numPorta = numPorta;
       this.numAndar = numAndar;
       this.garagem = garagem;
   }
   
   public Apartamento(){
       this("",0,0,"",0,0,0,0,false);
   }
   
   public Apartamento(Apartamento a){
       super(a);
       this.tipo = a.tipo;
       this.areaTot = a.areaTot;
       this.numQuaWC = a.numQuaWC;
       this.numPorta = a.numPorta;
       this.numAndar = a.numAndar;
       this.garagem = a.garagem;
   }
   
   public String getTipo(){
       return tipo;
   }
   
   public double getAreaTot(){
       return areaTot;
   }
   
   public int getNumQuaWC(){
       return numQuaWC;
   }
   
   public int getNumPorta(){
       return numPorta;
   }
   
   public int getNumAndar(){
       return numAndar;
   }
   
   public boolean getGaragem(){
       return garagem;
   }
   
   public Apartamento clone(){
       return new Apartamento(this);
   }
   
   public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Apartamento m = (Apartamento) o;
       return (super.equals(m) &&
               this.tipo.equals(m.getTipo()) && 
               this.areaTot == m.getAreaTot() && 
               this.numQuaWC == m.getNumQuaWC() && 
               this.numPorta == m.getNumPorta() && 
               this.numAndar == m.getNumAndar() && 
               this.garagem == m.getGaragem());
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|APARTAMENTO|\n");
        sb.append("Tipo de Apartamento: ").append(tipo).append("\n");
        sb.append("Área Total: ").append(areaTot).append("\n");
        sb.append("Número de Quartos e WC's: ").append(numQuaWC).append("\n");
        sb.append("Número da Porta: ").append(numPorta).append("\n");
        sb.append("Número do Andar: ").append(numAndar).append("\n");
        sb.append("Garagem: ");
        if(garagem){sb.append("Sim");} else {sb.append("Não");};
        sb.append("\n");
        sb.append(super.toString());
        return sb.toString();
   }
}

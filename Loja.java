
public class Loja extends Imovel
{
   private double area;
   private boolean WC;
   private String tipoNeg;
   private int numPorta;
   
   public Loja(String rua, double precoPedido, double precoMinAceite, double area, boolean WC, String tipoNeg, int numPorta){
       super(rua, precoPedido, precoMinAceite);
       this.area = area;
       this.WC = WC;
       this.tipoNeg = tipoNeg;
       this.numPorta = numPorta;
   }
   
   public Loja(){
       this("",0,0,0,false,"",0);
   }
   
   public Loja(Loja l){
       super(l);
       this.area = l.getArea();
       this.WC = l.getWC();
       this.tipoNeg = l.getTipoNeg();
       this.numPorta = l.getNumPorta();
   }
   
   public double getArea(){
       return area;
   }
   
   public boolean getWC(){
       return WC;
   }
   
   public String getTipoNeg(){
       return tipoNeg;
   }
   
   public int getNumPorta(){
       return numPorta;
   }
   
   public void setArea(double area){
       this.area = area; 
   }
   
   public void setWC(boolean WC){
       this.WC = WC;
   }
   
   public void setTipoNeg(String tipoNeg){
       this.tipoNeg = tipoNeg;
   }
   
   public void setNumPorta(int numPorta){
       this.numPorta = numPorta;
   }
   
   public Loja clone(){
       return new Loja(this);
   }
   
   public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Loja m = (Loja) o;
       return (super.equals(m) &&
               this.tipoNeg.equals(m.getTipoNeg()) && 
               this.area == m.getArea() && 
               this.WC == m.getWC() && 
               this.numPorta == m.getNumPorta());
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|LOJA|\n");
        sb.append("Tipo de Negócio da Loja: ").append(tipoNeg).append("\n");
        sb.append("Área Total: ").append(area).append("\n");
        sb.append("Número da Porta: ").append(numPorta).append("\n");
        sb.append("WC: ");
        if(WC){sb.append("Sim");} else {sb.append("Não");};
        sb.append("\n");
        sb.append(super.toString());
        return sb.toString();
   }
}

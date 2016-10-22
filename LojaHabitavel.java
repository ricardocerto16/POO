public class LojaHabitavel extends Imovel implements Habitavel
{
   private double area;
   private boolean WC;
   private String tipoNeg;
   private int numPorta;
   private Apartamento habit;
   
   public LojaHabitavel(String rua, double precoPedido, double precoMinAceite, double area, boolean WC, String tipoNeg, int numPorta, Apartamento habit){
       super(rua, precoPedido, precoMinAceite);
       this.area = area;
       this.WC = WC;
       this.tipoNeg = tipoNeg;
       this.numPorta = numPorta;
       this.habit = habit.clone();
   }
   
   public LojaHabitavel(){
       this("",0,0,0,false,"",0,null);
   }
   
   public LojaHabitavel(LojaHabitavel l){
       super(l);
       this.area = l.getArea();
       this.WC = l.getWC();
       this.tipoNeg = l.getTipoNeg();
       this.numPorta = l.getNumPorta();
       this.habit = l.getHabit().clone();
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
   
   public Apartamento getHabit(){
       return habit.clone();
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
   
   public void setHabit(Apartamento habit){
       this.habit = habit.clone();
   }
   
   public LojaHabitavel clone(){
       return new LojaHabitavel(this);
   }
   
   public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       LojaHabitavel m = (LojaHabitavel) o;
       return (super.equals(m) &&
               this.tipoNeg.equals(m.getTipoNeg()) && 
               this.area == m.getArea() && 
               this.WC == m.getWC() && 
               this.numPorta == m.getNumPorta() && 
               this.habit.equals(m.getHabit()));
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|LOJA HABITÁVEL|\n");
        sb.append("Tipo de Negócio da Loja: ").append(tipoNeg).append("\n");
        sb.append("Área Total: ").append(area).append("\n");
        sb.append("Número da Porta: ").append(numPorta).append("\n");
        sb.append("WC: ");
        if(WC){sb.append("Sim");} else {sb.append("Não");};
        sb.append("\nApartamento incluido:\n");
        sb.append(habit.toString());
        return sb.toString();
   }
}

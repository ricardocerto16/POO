
public class Terreno extends Imovel
{
   private String construcao;//habitacao ou armazem
   private double diamCanali, redeElec, areaConst;
   private boolean acessRedeEsg;
   
   public Terreno(String rua, double precoPedido, double precoMinAceite, String construcao, double diamCanali, double redeElec, boolean acessRedeEsg, double areaConst){
       super(rua, precoPedido, precoMinAceite);
       this.construcao = construcao;
       this.diamCanali = diamCanali;
       this.redeElec = redeElec;
       this.acessRedeEsg = acessRedeEsg;
       this.areaConst = areaConst;
   }
   
   public Terreno(){
       this("",0,0,"",0,0,false,0);
   }
   
   public Terreno(Terreno t){
       super(t);
       this.construcao = t.getConstrucao();
       this.diamCanali = t.getDiamCanali();
       this.redeElec = t.getRedeElec();
       this.acessRedeEsg = t.getAcessRedeEsg();
       this.areaConst = t.getAreaConst();
   }
   
   public double getAreaConst(){
       return this.areaConst;
   }
   
   public String getConstrucao(){
       return this.construcao;
   }
   
   public double getDiamCanali(){
       return this.diamCanali;
   }
   
   public double getRedeElec(){
       return this.redeElec;
   }
   
   public boolean getAcessRedeEsg(){
       return this.acessRedeEsg;
   }
   
   public void setConstrucao(String construcao){
       this.construcao = construcao;
   }
   
   public void setDiamCanali(double diamCanali){
       this.diamCanali = diamCanali;
   }
   
   public void setRedeElec(double redeElec){
       this.redeElec = redeElec;
   }
   
   public void setAcessRedeEsg(boolean acessRedeEsg){
       this.acessRedeEsg = acessRedeEsg;
   }
   
    public void setAreaConst(double areaConst){
       this.areaConst = areaConst;
   }
   
   public Terreno clone(){
       return new Terreno(this);
   }
   
   public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Terreno m = (Terreno) o;
       return (super.equals(m) &&
               this.construcao.equals(m.getConstrucao()) &&
               this.diamCanali == m.getDiamCanali() &&
               this.redeElec == m.getRedeElec() &&
               this.acessRedeEsg == m.getAcessRedeEsg() &&
               this.areaConst == m.getAreaConst());
    }
   
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|TERRENO|\n");
        sb.append("Tipo de Construção: ").append(construcao).append("\n");
        sb.append("Diâmetro das Canalizações: ").append(diamCanali).append("\n");
        sb.append("Área de Construção: ").append(areaConst).append("\n");
        sb.append("kWh máximo suportados pela rede eléctrica: ").append(redeElec).append("\n");
        sb.append("Acesso á rede de esgotos: ");
        if(acessRedeEsg){sb.append("Sim");} else {sb.append("Não");};
        sb.append("\n");
        sb.append(super.toString());
        return sb.toString();
   }
}

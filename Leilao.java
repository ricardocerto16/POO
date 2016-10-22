import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Leilao implements Serializable
{
   private List<Licitador> licitadores;
   private int horas;
   private Imovel im;
   private Licitador vencedor = null;
   private double montante;
   
   public Leilao(Imovel im, int horas){
       this.im = im;
       this.horas = horas;
       licitadores = new ArrayList<>();
       this.montante = 0;
   }
   
   public void adicionaComprador(String idComprador, double limite, double incrementos, double minutos){
       Licitador lic = new Licitador(idComprador, limite, incrementos, minutos);
       licitadores.add(lic);
   }
   
   public void correLeilao(){
       long inicio = System.currentTimeMillis();
       System.out.println("► O LEILÃO ACABOU DE SE INICIAR!! ◄\n\n");
       long atual = System.currentTimeMillis();
       while(((atual-inicio)/1000) < horas){
           for(Licitador l : licitadores){
               long agora = System.currentTimeMillis();
               if(montante > l.getLimite() || vencedor == l){}
               else if( montante+l.getIncrementos()< l.getLimite() && montante < l.getLimite() && ((agora-l.getUltimoTempo())/1000) >= l.getMinutos()){
                             montante += l.getIncrementos();
                             l.setUltimoTempo(agora);
                             System.out.println("✔ Licitador "+l.getId()+" acaba de fazer uma licitação de "+montante+"€!\n");
                             vencedor = l;
               }
           }
           atual = System.currentTimeMillis();
       }
       if(montante < im.getPrecoMinAceite()) vencedor = null;
   }
   
   public Licitador getVencedor(){
       return this.vencedor;
    }
   
    public double getMontante(){
        return this.montante;
    }
    
   public Imovel getImovel(){
       return this.im;
   }
}

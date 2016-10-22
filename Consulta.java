import java.time.LocalDateTime;
import java.io.Serializable;

public class Consulta implements Serializable ,Comparable<Consulta>
{
   private LocalDateTime data;
   private String email=null;
  
   public Consulta(String email){
        data = LocalDateTime.now();
        this.email = email;
   }
   
   public Consulta(){
        data = LocalDateTime.now();
   }
   
   public Consulta(Consulta c){
       this.data = c.getData();
       this.email = c.getEmail();
    }
    
   public String getEmail(){
       if(email!=null)return email;
       else return null;
   }
   
   public LocalDateTime getData(){
       return data;
   }
   
   public void setEmail(String email){
       this.email = email;
    }
    
   public Consulta clone(){
       return new Consulta(this);
    }
   
   public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Consulta m = (Consulta) o;
       return (this.data.equals(m.getData()) &&
               this.email.equals(m.getEmail()));
   }
    
   public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("CONSULTA:\n");
        sb.append("Data da Consulta: ").append(data).append("\n");
        if(email!=null)sb.append("Email: ").append(email).append("\n");
        else{sb.append("Email: Utilizador nao registado\n");}
        return sb.toString();
    }
   
   public int compareTo(Consulta c){
        if(this.data.equals(c.getData()))
            return 0;
        if(this.data.isAfter(c.getData()))
            return -1;
        else return 1;
   }
}
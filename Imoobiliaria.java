import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Collections;


public class Imoobiliaria implements Serializable
{
    private Set<Utilizador> utilizadores;
    private Map<Imovel,Vendedor> imoveis;
    private int idImovel=0;
    private Utilizador utilizador = null;
    private Leilao leilao = null;
        
    /** 
     * Construtor da Imoobiliaria.
     */
    public Imoobiliaria(Set<Utilizador> utilizadores, Map<Imovel,Vendedor> imoveis){
        this.utilizadores = utilizadores.stream()
                                        .map(u -> {return u.clone();})
                                        .collect(Collectors.toSet());
        this.imoveis = imoveis.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey().clone(), e->e.getValue().clone()));
    }
    
    /** 
     * Construtor vazio da Imoobiliaria.
     */
    public Imoobiliaria(){
        utilizadores = new HashSet<Utilizador>();
        imoveis = new HashMap<Imovel,Vendedor>();
    }
    
    /** 
     * Retorna o nome do utilizador que está actualmente no sistema.
     */
    public String getNome(){
        return utilizador.getNome();
    }
    
    /** 
     * Verifica se o utilizador que está actualmente no sistema é um Vendedor.
     */
    public boolean temVend(){
        if(utilizador == null) return false;
        if(utilizador instanceof Vendedor) return true;
        else return false;
    }
    
    /** 
     * Verifica se o utilizador que está actualmente no sistema é um Comprador.
     */
    public boolean temComp(){
        if(utilizador == null) return false;
        if(utilizador instanceof Comprador) return true;
        else return false;
    }
    
    /** 
     * Verifica se existe actualmente algum utilizador no sistema.
     */
    public boolean temUt(){
        if(utilizador == null) return false;
        else return true;
    }
    
    /** 
     * Regista um utilizador no sistema.
     */
    public void registarUtilizador(Utilizador utilizador) throws UtilizadorExistenteException{
        if(utilizadores.stream().anyMatch(u -> u.equals(utilizador)))
            throw new UtilizadorExistenteException("O utilizador já se encontra registado!");
        else 
            utilizadores.add(utilizador.clone());
    }
    
    /** 
     * Permite um utilizador iniciar sessão no sistema.
     */
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException{
        if(utilizador != null) throw new SemAutorizacaoException("É necessário terminar a sessão actual.");
        if(utilizadores.isEmpty()) throw new SemAutorizacaoException("Utilizador não se encontra registado!");
        boolean encontrou = false;
        Iterator<Utilizador> it = utilizadores.iterator();
        Utilizador u = null;
        while(it.hasNext() && !encontrou){
            u = it.next();
            encontrou = u.getEmail().equals(email) && u.getPassword().equals(password);
        }
        if(!encontrou)
            throw new SemAutorizacaoException("Utilizador não se encontra registado!");
        else{utilizador = u;}
    }
    
    /** 
     * Permite um utilizador terminar a sessão no sistema.
     */
    public void fechaSessao(){
        utilizador = null;
    }
    
    /** 
     * Permite registar um Imóvel no sistema.
     */
    public void registaImovel(Imovel im) throws ImovelExisteException, SemAutorizacaoException{
        if(utilizador==null){throw new SemAutorizacaoException("Necessita de iniciar sessão para registar um Imóvel.");}
        else if(utilizador.getClass().getName().equals("Comprador"))
                    {throw new SemAutorizacaoException("Necessita de ser um Vendedor para registar um Imóvel.");}
        else if(imoveis.keySet().stream().anyMatch(e -> im.equals(e)))
                    {throw new ImovelExisteException("O Imóvel já se encontra registado!");}
        else { Imovel i = im.clone();
               i.setId(Integer.toString(idImovel));
               imoveis.put(i,((Vendedor)utilizador));
               idImovel++;
               ((Vendedor)utilizador).addImoVenda(i);
        }
    }
    
    /** 
     * Retorna uma lista com as datas (e emails, caso exista essa informação) das 10 últimas 
     * consultas aos imóveis que o utilizador actual tem para venda.
     */
    public List<Consulta> getConsultas() throws SemAutorizacaoException{
        if(utilizador==null)throw new SemAutorizacaoException("Necessita de iniciar sessão para obter as consultas.");
        else if(utilizador.getClass().getName().equals("Comprador"))
                    throw new SemAutorizacaoException("Necessita de ser um Vendedor para obter as consultas.");
        else{
            List<Consulta> cons = new ArrayList<>();
            ((Vendedor)utilizador).getImoVenda().forEach(e -> {cons.addAll(e.getConsultas());});
            Collections.sort(cons, new ComparatorConsultaTempo());
            cons.subList(0 , 9 > cons.size() ? cons.size() : 9);
            return cons;
        }
    }
    
    /** 
     * Permite alterar o estado de um imóvel do utilizador actual, de acordo com as acções feitas sobre ele;
     */
    public void setEstado(String idImovel, String estado)throws ImovelInexistenteException ,SemAutorizacaoException ,EstadoInvalidoException{
        if(utilizador==null)throw new SemAutorizacaoException("Necessita de iniciar sessão para alterar o estado de um Imóvel.");
        if(utilizador.getClass().getName().equals("Comprador"))
                    throw new SemAutorizacaoException("Necessita de ser um Vendedor para alterar o estado de um Imóvel.");
        Set<Imovel> imo = imoveis.keySet();
        boolean encontrou = false;
        Iterator<Imovel> it = imo.iterator();
        Imovel i = null;
        while(it.hasNext() && !encontrou){
            i = it.next();
            encontrou = i.getId().equals(idImovel);
        }
        if(!encontrou) throw new ImovelInexistenteException("Imóvel inexistente.");
        if(encontrou && imoveis.get(i)!=utilizador) throw new SemAutorizacaoException("Necessita de ser dono do Imóvel para alterar o seu estado");
        if(!(estado.equals("Venda")) && !(estado.equals("Vendido")) && !(estado.equals("Reservado"))) throw new EstadoInvalidoException("Estado de Imóvel inválido");
        if(i.getEstado().equals("Venda") && estado.equals("Vendido"))((Vendedor)utilizador).setEstadoVendido(i);
        if(i.getEstado().equals("Reservado") && estado.equals("Vendido"))((Vendedor)utilizador).setEstadoVendido(i);
        if(i.getEstado().equals("Vendido") && estado.equals("Venda"))((Vendedor)utilizador).setEstadoVenda(i);
        if(i.getEstado().equals("Vendido") && estado.equals("Reservado"))((Vendedor)utilizador).setEstadoVenda(i);
        i.setEstado(estado);
    }
    
    /** 
     * Permite obter um conjunto com os códigos dos imóveis mais consultados (ou seja,
     * com mais de N consultas) do utilizador actual.
     */
    public Set<String> getTopImoveis(int n) {
        Set<Imovel> imoVenda = ((Vendedor)utilizador).getImoVenda().stream().filter(i -> i.getNumConsultas()>=n).collect(Collectors.toSet());
        Set<Imovel> imoVendido = ((Vendedor)utilizador).getImoVendidos().stream().filter(i -> i.getNumConsultas()>=n).collect(Collectors.toSet());
        imoVenda.addAll(imoVendido);
        Set<String> maisconsultadosid = imoVenda.stream().map(i -> {return i.getId();}).collect(Collectors.toSet());
        return maisconsultadosid;
    }
    
    /** 
     * Retorna a lista de todos os imóveis de um dado tipo (Terreno, Moradia, Loja, LojaHabitavel, Apartamento)
     * e até um certo preço.
     */
    public List<Imovel> getImovel(String classe , int preco) {
        Consulta cons = new Consulta();
        if(utilizador != null) cons.setEmail(utilizador.getEmail());
        return imoveis.entrySet()
                      .stream()
                      .filter(e -> ((e.getKey().getClass().getName()).equals(classe)) && (e.getKey().getPrecoPedido() <= preco))
                      .map(e -> {e.getKey().addConsulta(cons);return e;})
                      .map(e -> {return e.getKey().clone();})
                      .collect(Collectors.toList());
    }
    
    /** 
     * Retorna a lista de todos os imóveis habitáveis (até um certo preço).
     */
    public List<Habitavel> getHabitaveis(int preco){
        Consulta cons = new Consulta();
        if(utilizador != null) cons.setEmail(utilizador.getEmail());
        return imoveis.entrySet()
                      .stream()
                      .filter(e -> (e.getKey() instanceof Habitavel) && (e.getKey().getPrecoPedido() <= preco))
                      .map(e -> {e.getKey().addConsulta(cons);return e;})
                      .map(e -> {return (Habitavel)e.getKey().clone();})
                      .collect(Collectors.toList());
    }
    
    /** 
     * Retorna um mapeamento entre todos os imóveis e respectivos vendedores.
     */
    public Map<Imovel,Vendedor> getMapeamentoImoveis(){
        Consulta cons = new Consulta();
        if(utilizador != null) cons.setEmail(utilizador.getEmail());
        return imoveis.entrySet()
                      .stream()
                      .map(e -> {e.getKey().addConsulta(cons);return e;})
                      .collect(toMap(e->e.getKey().clone(), e->e.getValue().clone()));
    }
    
    /** 
     * Verifica se um determinado id pertence a algum Imóvel da Imoobiliaria e retorna-o caso exista.
     */
    public Imovel temImovel(String idImovel){
        Set<Imovel> imo = imoveis.keySet();
        boolean encontrou = false;
        Iterator<Imovel> it = imo.iterator();
        Imovel i = null;
        while(it.hasNext() && !encontrou){
            i = it.next();
            encontrou = i.getId().equals(idImovel);
        }
        if(!encontrou)return null;
        else return i;
    }
    
    /** 
     * Permite ao utilizador actual marcar um imóvel como favorito.
     */
    public void setFavorito(String idImovel) throws ImovelInexistenteException ,SemAutorizacaoException{
        if(utilizador==null)throw new SemAutorizacaoException("Necessita de iniciar sessão para colocar um Imóvel como favorito.");
        if(utilizador.getClass().getName().equals("Vendedor"))
                    throw new SemAutorizacaoException("Necessita de ser um Comprador para colocar um Imóvel como favorito.");
        if(imoveis.isEmpty()) throw new ImovelInexistenteException("Imóvel inexistente.");
        Set<Imovel> imo = imoveis.keySet();
        boolean encontrou = false;
        Iterator<Imovel> it = imo.iterator();
        Imovel i = null;
        while(it.hasNext() && !encontrou){
            i = it.next();
            encontrou = i.getId().equals(idImovel);
        }
        if(!encontrou) throw new ImovelInexistenteException("Imóvel inexistente.");
        else{((Comprador)utilizador).addImoFav(i);}
    }
    
    /** 
     * Retorna uma árvore com os imóveis favoritos do utilizador actual, ordenados por preço.
     */
    public TreeSet<Imovel> getFavoritos() throws SemAutorizacaoException{
        if(utilizador==null)throw new SemAutorizacaoException("Necessita de iniciar sessão para consultar os seus Imóveis favoritos.");
        if(utilizador.getClass().getName().equals("Vendedor"))
                    throw new SemAutorizacaoException("Necessita de ser um Comprador para consultar os seus Imóveis favoritos.");
        Consulta cons = new Consulta(utilizador.getEmail());
        Set<Imovel> fav =((Comprador)utilizador).getImoFav();
        fav.forEach(e -> {e.addConsulta(cons);});
        TreeSet<Imovel> fav2 = new TreeSet<>(new ComparatorImovelPreco());
        fav.forEach(e -> {fav2.add(e.clone());});
        return fav2;
    }
    
    /** 
     * Permite ao utilizador actual iniciar um Leilão, com um dado Imóvel e durante um determinado período.
     */
    public void iniciaLeilao(Imovel im, int horas) throws SemAutorizacaoException{
        if(utilizador==null)throw new SemAutorizacaoException("Necessita de iniciar sessão para iniciar um Leilão.");
        if(utilizador.getClass().getName().equals("Comprador"))
                   throw new SemAutorizacaoException("Necessita de ser um Vendedor para iniciar um Leilão.");
        leilao = new Leilao(im, horas);
        correLeilao();
    }
    
    /** 
     * Permite adicionar um Comprador ao Leilão actual.
     */
    public void adicionaComprador(String idComprador ,double limite ,double incrementos ,double minutos) throws LeilaoTerminadoException{
        if(leilao==null)throw new LeilaoTerminadoException("O Leilão já se encontra terminado.");
        else leilao.adicionaComprador(idComprador, limite, incrementos, minutos);
    }
    
    /** 
     * Permite que o Leilão arranque, imprimindo todos os acontecimentos do mesmo. 
     */
    public void correLeilao(){
        try{
          adicionaComprador("1",10000,500,5);
          adicionaComprador("2",15000,110,10);
          adicionaComprador("3",9000,400,4);
          adicionaComprador("4",19000,800,8);
          adicionaComprador("5",8000,900,9);
          adicionaComprador("6",19000,200,6);
          adicionaComprador("7",9000,150,20);
          adicionaComprador("8",6000,100,15);
          adicionaComprador("9",20000,400,6);
          adicionaComprador("10",6000,300,4);
       }
       catch(Exception e){System.out.println(e);}
       leilao.correLeilao();
       encerraLeilao();
    }
    
    /** 
     * Permite encerrar o Leilão actual, imprimindo o vencedor do mesmo.
     */
    public Comprador encerraLeilao(){
        Comprador vencedor = leilao.getVencedor();
        double montante = leilao.getMontante();
        if(vencedor != null){
            System.out.println("E o vencedor do Leilão foi o Licitador "+((Licitador)vencedor).getId()+", adquirindo o Imóvel pelo montante de "+montante+"€. Parabéns!");
            leilao.getImovel().setEstado("Reservado");
        }
        else System.out.println("O Leilão não teve nenhum vencedor devido ás fracas Licitações!");
        leilao = null;
        return vencedor.clone();
    }
    
    /** 
     * Responsável por gravar o estado da aplicação.
     */
    public void gravaObj(String fich) throws IOException { 
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich)); 
        oos.writeObject(this);
        
        oos.flush();
        oos.close();
    }
    
    /** 
     * Responsável alterar o estado da aplicação.
     */
    public static Imoobiliaria leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
      
        Imoobiliaria imo = (Imoobiliaria) ois.readObject();
        
        ois.close();
        return imo;
    }
    
    /** 
     * Responsável por imprimir para um ficheiro o estado da aplicação.
     */
    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        Iterator<Utilizador> it = utilizadores.iterator();
        Utilizador i;
        while(it.hasNext()){
            i = it.next();
            fw.write(i.toString());
            fw.write("\n");
        }
        
        for(Map.Entry<Imovel,Vendedor> entry : imoveis.entrySet()){
            fw.write(entry.getKey().toString());
            fw.write("↓\n");
            fw.write(entry.getValue().toString());
            fw.write("\n");
        }
        
        fw.write(this.toString());
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        fw.flush();
        fw.close();
    }
}

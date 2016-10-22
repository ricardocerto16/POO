import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.toMap;
import java.util.TreeSet;
import java.util.Iterator;

public class ImoobiliariaApp
{
    private static Imoobiliaria imo;
    private static Menu menumain, menuvend, menucomp, menuregista ,menuimovel;
    
    private ImoobiliariaApp() {}

    public static void main(String[] args) {
        carregarMenus();
        initApp();
        correMenus();
        try {
            imo.gravaObj("imo.dados");
            imo.log("log.txt", true);
        }
        catch (IOException e) {
            System.out.println("Não consegui gravar os dados!");
        }
        System.out.println("Até à proxima!...");     
    }
    
    private static void carregarMenus(){
        String[] ops = {"Registar Conta",
                        "Iniciar Sessão",
                        "Lista de Imóveis de um dado tipo",
                        "Lista de Imóveis Habitáveis",
                        "Mapeamento Imóvel-Vendedor"};
        String [] opsvend = {"Registar Imóvel",
                             "Últimas 10 Consultas aos seus Imóveis em Venda",
                             "Alterar estado de Imóvel",
                             "Os seus Imóveis mais consultados",
                             "Lista de Imóveis de um dado tipo",
                             "Lista de Imóveis Habitáveis",
                             "Mapeamento Imóvel-Vendedor",
                             "Iniciar Leilão",
                             "Terminar Sessão"};
        String [] opscomp = {"Marcar Imóvel como favorito",
                             "Lista dos seus Imóveis favoritos",
                             "Lista de Imóveis de um dado tipo",
                             "Lista de Imóveis Habitáveis",
                             "Mapeamento Imóvel-Vendedor",
                             "Terminar Sessão"};
        String [] opsregista = {"Registar Comprador",
                                "Registar Vendedor"};
        String [] opsimovel = {"Moradia",
                               "Apartamento",
                               "Loja",
                               "Terreno",
                               "LojaHabitavel"};
        menumain = new Menu(ops);
        menuvend = new Menu(opsvend);
        menucomp = new Menu(opscomp);
        menuregista = new Menu(opsregista);
        menuimovel = new Menu(opsimovel);
    }
    
    private static void correMenus(){
        do {
           if(imo.temVend()){
             do {
                 menuvend.executa(imo.getNome());
                 switch (menuvend.getOpcao()) {
                  case 1: registaImovel();
                        break;
                  case 2: getConsultas();
                        break;
                  case 3: setEstado();
                        break;
                  case 4: getTopImoveis();
                        break;
                  case 5: getImovel();
                        break;
                  case 6: getHabitaveis();
                        break;
                  case 7: getMapeamentoImoveis();
                        break;
                  case 8: iniciaLeilao();
                        break;
                  case 9: fechaSessao();
                        break;
                 }
             } while (menuvend.getOpcao()!=0 && imo.temVend());
           }
           
           if(menuvend.getOpcao()==0) break;
           
           if(imo.temComp()){
              do {
                   menucomp.executa(imo.getNome());
                   switch (menucomp.getOpcao()) {
                         case 1: setFavorito();
                             break;
                         case 2: getFavoritos();
                             break;
                         case 3: getImovel();
                             break;
                         case 4: getHabitaveis();
                             break;
                         case 5: getMapeamentoImoveis();
                             break;
                         case 6: fechaSessao();
                             break;
                   }
              } while (menucomp.getOpcao()!=0 && imo.temComp());
           }
           
           if(menucomp.getOpcao()==0) break;
           
           if(!imo.temUt()){
               do{
                   menumain.executa(null);
                   switch (menumain.getOpcao()) {
                       case 1: registaUtilizador();
                           break;
                       case 2: iniciaSessao();
                           break;
                       case 3: getImovel();
                           break;
                       case 4: getHabitaveis();
                           break;
                       case 5: getMapeamentoImoveis();
                           break;
                   }
               } while (menumain.getOpcao()!=0 && !imo.temUt());
           }
        } while (menumain.getOpcao()!=0);
    }
    
    private static void initApp() {
        try {
             imo = Imoobiliaria.leObj("imo.dados");
        }
        catch (IOException e) {
             imo = new Imoobiliaria();
             System.out.println("Não consegui ler os dados!\nErro de leitura.");
        }
        catch (ClassNotFoundException e) {
             imo = new Imoobiliaria();
             System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
             imo = new Imoobiliaria();
             System.out.println("Não consegui ler os dados!\nErro de formato.");        
        }
    }
    
    private static void registaUtilizador(){
        Utilizador ut = null;
        Scanner scan = new Scanner(System.in);
        
        menuregista.executa(null);
        if (menuregista.getOpcao() != 0){
            String email, nome, password, morada, dataDeNascimento;
            
            System.out.print("Email: ");
            email = scan.nextLine();
            System.out.print("Nome: ");
            nome = scan.nextLine();
            System.out.print("Password: ");
            password = scan.nextLine();
            System.out.print("Morada: ");
            morada = scan.nextLine();
            System.out.print("Data de Nascimento(dd/mm/aa): ");
            dataDeNascimento = scan.nextLine();
            
            switch (menuregista.getOpcao()) {
                case 1: ut = new Comprador(email, nome, password, morada, dataDeNascimento);
                        break;
                case 2: ut = new Vendedor(email, nome, password, morada, dataDeNascimento);
                        break;
            }
            try{imo.registarUtilizador(ut);}
            catch(Exception e){System.out.println("\n"+e);}
        } else{
            System.out.println("Registo cancelado!");
        }
        scan.close();
    }
    
    private static void iniciaSessao(){
        Scanner scan = new Scanner(System.in);
        String email, password;
        
        System.out.print("Email: ");
        email = scan.nextLine();
        System.out.print("Password: ");
        password = scan.nextLine();
        
        try{imo.iniciaSessao(email, password);}
        catch(Exception e){System.out.println("\n"+e);}
    }
    
    private static void fechaSessao(){
        imo.fechaSessao();
    }
    
    private static void getImovel(){
        Scanner scan = new Scanner(System.in);
        String classe;
        int preco;
        List<Imovel> imoveis;
        
        System.out.print("Tipo de Imóvel(Moradia,Apartamento,Terreno,Loja,LojaHbitavel): ");
        classe = scan.nextLine();
        System.out.print("Preço máximo: ");
        preco = scan.nextInt();
        
        imoveis = imo.getImovel(classe, preco);
        if(imoveis.isEmpty())System.out.println("\nNão existem Imóveis com essas características na Imobiliária.");
        else{
           System.out.print("\n");
           for(Imovel i : imoveis){
              System.out.println(i);
           }
        }
    }
    
    private static void getHabitaveis(){
        Scanner scan = new Scanner(System.in);
        int preco;
        List<Habitavel> habit;
        
        System.out.print("Preço máximo: ");
        preco = scan.nextInt();
        
        habit = imo.getHabitaveis(preco);
        if(habit.isEmpty())System.out.println("\nNão existem Imóveis Habitáveis na Imobiliária.");
        else{
           System.out.print("\n");
           for(Habitavel i : habit){
               System.out.println(i);
           }
        }
    }
    
    private static void getMapeamentoImoveis(){
        Map<Imovel,Vendedor> map;
        
        map = imo.getMapeamentoImoveis();
        if(map.isEmpty())System.out.println("\nNão existem Imóveis na Imobiliária.");
        else{
            System.out.print("\n");
            for(Map.Entry<Imovel,Vendedor> imovend : map.entrySet()){
                System.out.print(imovend.getKey()); 
                System.out.println("↓");
                System.out.println(imovend.getValue());
            }
        }
    }
    
    private static void registaImovel(){
        Imovel i = null;
        Scanner scan = new Scanner(System.in);
        
        menuimovel.executa(null);
        if (menuimovel.getOpcao() != 0){
            String rua,tipo, sn, tipoNeg, construcao;
            double precoPedido, precoMinAceite, areaImpl, areaTotCob, areaTerrEnv, areaTot, area,redeElec,areaConst,diamCanali;
            int numQuaWC, numPorta, numAndar;
            boolean garagem, WC, acessRedeEsg;
            
            System.out.print("Rua: ");
            rua = scan.nextLine();
            System.out.print("Preço Pedido: ");
            precoPedido = scan.nextDouble();
            System.out.print("Preço Mínimo Aceite: ");
            precoMinAceite = scan.nextDouble();
            sn = scan.nextLine();
            switch (menuimovel.getOpcao()) {
                case 1: System.out.print("Tipo de Moradia(Isolada,Geminada,Banda,Gaveto): ");
                        tipo = scan.nextLine();
                        System.out.print("Área de implantação: ");
                        areaImpl = scan.nextDouble();
                        System.out.print("Área total coberta: ");
                        areaTotCob = scan.nextDouble();
                        System.out.print("Área de terreno envolvente: ");
                        areaTerrEnv = scan.nextDouble();
                        System.out.print("Número de quartos e WC's: ");
                        numQuaWC = scan.nextInt();
                        System.out.print("Número da porta: ");
                        numPorta = scan.nextInt();
                        i = new Moradia(rua, precoPedido, precoMinAceite, tipo, areaImpl, areaTotCob, areaTerrEnv, numQuaWC, numPorta);
                        break;
                case 2: System.out.print("Tipo de Apartamento(Simples,Duplex,Triplex): ");
                        tipo = scan.nextLine();
                        System.out.print("Possui garagem?(S/N): ");
                        sn = scan.nextLine();
                        if(sn.equals("S") || sn.equals("s")) garagem = true;
                        else garagem = false;
                        System.out.print("Área Total: ");
                        areaTot = scan.nextDouble();
                        System.out.print("Número de quartos e WC's: ");
                        numQuaWC = scan.nextInt();
                        System.out.print("Número da porta: ");
                        numPorta = scan.nextInt();
                        System.out.print("Número do andar: ");
                        numAndar = scan.nextInt();
                        i = new Apartamento(rua, precoPedido, precoMinAceite, tipo, areaTot, numQuaWC, numPorta, numAndar, garagem);
                        break;
                case 3: System.out.print("Tipo de negócio da Loja: ");
                        tipoNeg = scan.nextLine();
                        System.out.print("Possui WC?(S/N): ");
                        sn = scan.nextLine();
                        if(sn.equals("S") || sn.equals("s")) WC = true;
                        else WC = false;
                        System.out.print("Área: ");
                        area = scan.nextDouble();
                        System.out.print("Número da Porta: ");
                        numPorta = scan.nextInt();
                        i = new Loja(rua, precoPedido, precoMinAceite, area, WC, tipoNeg, numPorta);
                        break;
                case 4: System.out.print("Tipo de Construção(Armazem,Habitacao): ");
                        construcao = scan.nextLine();
                        System.out.print("Tem acesso a rede de esgotos?(S/N): ");
                        sn = scan.nextLine();
                        if(sn.equals("S") || sn.equals("s")) acessRedeEsg = true;
                        else acessRedeEsg = false;
                        System.out.print("kWh máximo suportados pela rede electrica(0 se não possuir): ");
                        redeElec = scan.nextDouble();
                        System.out.print("Área de Construção: ");
                        areaConst = scan.nextDouble();
                        System.out.print("Diâmetro das canalizações(em milimetros): ");
                        diamCanali = scan.nextDouble();
                        i = new Terreno(rua, precoPedido, precoMinAceite, construcao, diamCanali, redeElec, acessRedeEsg, areaConst);
                        break;
                case 5: System.out.print("Tipo de negócio da Loja: ");
                        tipoNeg = scan.nextLine();
                        System.out.print("Possui WC?(S/N): ");
                        sn = scan.nextLine();
                        if(sn.equals("S") || sn.equals("s")) WC = true;
                        else WC = false;
                        System.out.print("Área: ");
                        area = scan.nextDouble();
                        System.out.print("Número da Porta: ");
                        numPorta = scan.nextInt();
                        
                        sn = scan.nextLine();
                        System.out.println("|Parte habitacional da Loja|");
                        System.out.print("Tipo de Apartamento(Simples,Duplex,Triplex): ");
                        tipo = scan.nextLine();
                        System.out.print("Possui garagem?(S/N): ");
                        sn = scan.nextLine();
                        if(sn.equals("S") || sn.equals("s")) garagem = true;
                        else garagem = false;
                        System.out.print("Área Total: ");
                        areaTot = scan.nextDouble();
                        System.out.print("Número de quartos e WC's: ");
                        numQuaWC = scan.nextInt();
                        System.out.print("Número da porta: ");
                        numPorta = scan.nextInt();
                        System.out.print("Número do andar: ");
                        numAndar = scan.nextInt();
                        Apartamento habit = new Apartamento(rua, precoPedido, precoMinAceite, tipo, areaTot, numQuaWC, numPorta, numAndar, garagem);
                        i = new LojaHabitavel(rua, precoPedido, precoMinAceite, area, WC, tipoNeg, numPorta, habit);
                        break;
            }
            try{imo.registaImovel(i);}
            catch(Exception e){System.out.println("\n"+e);}
        } else{
            System.out.println("Registo de Imóvel cancelado!");
        }
        scan.close();
    }
    
    private static void iniciaLeilao(){
        Scanner scan = new Scanner(System.in);
        String idImovel;
        int horas;
        
        System.out.print("Id do Imóvel: ");
        idImovel = scan.nextLine();
        Imovel im = imo.temImovel(idImovel);
        if(im == null)System.out.println("Imóvel nao existe");
        else{
          System.out.print("Tempo do Leilão: ");
          horas = scan.nextInt();
          try{imo.iniciaLeilao(im, horas);}
          catch(Exception e){System.out.println("\n"+e);}
        }
    }
    
    private static void setFavorito(){
        Scanner scan = new Scanner(System.in);
        String idImovel;
        
        System.out.print("Id do Imóvel: ");
        idImovel = scan.nextLine();
        
        try{imo.setFavorito(idImovel);}
        catch(Exception e){System.out.println("\n"+e);}
    }
    
    private static void getFavoritos(){
        TreeSet<Imovel> fav=null;
        
        try{fav = imo.getFavoritos();}
        catch(Exception e){System.out.println("\n"+e);}
        
        if(fav.isEmpty())System.out.println("\nDe momento não possui nenhum Imóvel favorito.");
        else{
            System.out.print("\n");
            Iterator<Imovel> it = fav.iterator();
            Imovel u;
            while(it.hasNext()){
               u = it.next();
               System.out.println(u);
            }
        }
    }
    
    private static void getConsultas(){
        List<Consulta> consultas=null;
        
        try{consultas = imo.getConsultas();}
        catch(Exception e){System.out.println("\n"+e);}
        
        if(consultas.isEmpty())System.out.println("\nDe momento não possui nenhuma consulta aos seus Imóveis em venda.");
        else{
           System.out.print("\n");
           for(Consulta c : consultas){
               System.out.println(c);
           }
        }
    }
    
    private static void setEstado(){
        Scanner scan = new Scanner(System.in);
        String idImovel, estado;
        
        System.out.print("Id do Imóvel: ");
        idImovel = scan.nextLine();
        System.out.print("Estado(Venda,Reservado,Vendido): ");
        estado = scan.nextLine();
        
        try{imo.setEstado(idImovel, estado);}
        catch(Exception e){System.out.println("\n"+e);}
    }
    
    private static void getTopImoveis(){
        Scanner scan = new Scanner(System.in);
        int numeroCons;
        Set<String> ids = null;
        
        System.out.print("Número mínimo de Consultas: ");
        numeroCons = scan.nextInt();
        
        try{ids = imo.getTopImoveis(numeroCons);}
        catch(Exception e){System.out.println("\n"+e);}
        
        if(ids.isEmpty())System.out.println("\nDe momento não possui nenhum Imóvel com tal número de Consultas.");
        else{
            System.out.print("\n");
            Iterator<String> it = ids.iterator();
            String id;
            while(it.hasNext()){
               id = it.next();
               System.out.println("ID: "+id);
            }
        }
    }
}

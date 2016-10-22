import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;

/**
 * The test class Testes.
 *
 * Ã‰ necessÃ¡rio completar os teste, colocando os parÃ¢metros nos construtores.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Testes
{
    private Imoobiliaria imo;
    private Vendedor v;
    private Terreno t;

    /**
     * Teste principal
     */
    @Test
    public void mainTest() {
        imo = new Imoobiliaria();
        try {
            imo.iniciaSessao("",null);
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            v = new Vendedor("vendedor@um","Vendedor1","password","morada","data");  // Preencher parÃ¢metros do construtor
            imo.registarUtilizador(v);
        } catch(Exception e) {
            fail();
        }
        
        String email = v.getEmail();
        String password = v.getPassword();
        
        try {
            imo.iniciaSessao(email, password);
        } catch(Exception e) {
            fail();
        }
        
        t = new Terreno("rua",10000.5,9000.5,"wdugs",12.1,30,true,0);  // Preencher parÃ¢metros do construtor
        try {
            imo.registaImovel(t);
        } catch (Exception e) {
            fail();
        }
        t.setId("0");
        int s = imo.getImovel("Terreno", Integer.MAX_VALUE).size();
        assertTrue(s>0);
        Set<String> ids = imo.getTopImoveis(0);
        assertTrue(ids.contains(t.getId()));
        assertTrue(imo.getMapeamentoImoveis().keySet().contains(t));
        
        try {
            assertTrue(imo.getConsultas().size()>0);
        } catch(Exception e) {
            fail();
        }
        
        imo.fechaSessao();
        Comprador c = new Comprador("comprador@um","comprador","password","morada","data");  // Preencher parÃ¢metros do construtor
        try {
            imo.registarUtilizador(c);
        } catch(Exception e) {
            fail();
        }
        email = c.getEmail();
        password = c.getPassword();
        try {
            imo.iniciaSessao(email, password);
            imo.setFavorito(t.getId());
            assertTrue(imo.getFavoritos().contains(t));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
}
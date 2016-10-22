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
public class TestesExceptions
{
    private Imoobiliaria imo;
    private Vendedor v;
    private Comprador c;
    private Terreno t;

    /**
     * Teste principal
     */
    @Test
    public void mainTest() {
        imo = new Imoobiliaria();
        t = new Terreno("rua",10000.5,9000.5,"wdugs",12.1,30,true,0);
        try {
            imo.iniciaSessao("",null);
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.registaImovel(t);
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.getConsultas();
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.setEstado("0","Vendido");
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.setFavorito("0");
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.getFavoritos();
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            c = new Comprador("comprador@um","comprador","password","morada","data");
            imo.registarUtilizador(c);
        } catch(Exception e) {
            fail();
        }
        
        String email = c.getEmail();
        String password = c.getPassword();
        
        try {
            imo.iniciaSessao(email, password);
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.registaImovel(t);
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.getConsultas();
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.setEstado("0","Vendido");
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        imo.fechaSessao();
        
        try {
            v = new Vendedor("vendedor@um","vendedor","password","morada","data");
            imo.registarUtilizador(v);
        } catch(Exception e) {
            fail();
        }
        email = v.getEmail();
        password = v.getPassword();
        try {
            imo.iniciaSessao(email, password);
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.setFavorito("0");
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            imo.getFavoritos();
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
    }
}
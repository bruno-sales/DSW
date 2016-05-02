/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Enums.EnumOperacao;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hazël § Rebecca
 */
public class LancamentosDinheiroTest {
    
    public LancamentosDinheiroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIdLancamento method, of class LancamentosDinheiro.
     */
    @Test
    public void testGetIdLancamento() {
        System.out.println("getIdLancamento");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        int expResult = 0;
        int result = instance.getIdLancamento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdUsuario method, of class LancamentosDinheiro.
     */
    @Test
    public void testGetIdUsuario() {
        System.out.println("getIdUsuario");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        int expResult = 0;
        int result = instance.getIdUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class LancamentosDinheiro.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        DateTime expResult = null;
        DateTime result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHistorico method, of class LancamentosDinheiro.
     */
    @Test
    public void testGetHistorico() {
        System.out.println("getHistorico");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        String expResult = "";
        String result = instance.getHistorico();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValor method, of class LancamentosDinheiro.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        float expResult = 0.0F;
        float result = instance.getValor();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOperacao method, of class LancamentosDinheiro.
     */
    @Test
    public void testGetOperacao() {
        System.out.println("getOperacao");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        EnumOperacao expResult = null;
        EnumOperacao result = instance.getOperacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdLancamento method, of class LancamentosDinheiro.
     */
    @Test
    public void testSetIdLancamento() {
        System.out.println("setIdLancamento");
        int idLancamento = 0;
        LancamentosDinheiro instance = new LancamentosDinheiro();
        instance.setIdLancamento(idLancamento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdUsuario method, of class LancamentosDinheiro.
     */
    @Test
    public void testSetIdUsuario() {
        System.out.println("setIdUsuario");
        int idUsuario = 0;
        LancamentosDinheiro instance = new LancamentosDinheiro();
        instance.setIdUsuario(idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class LancamentosDinheiro.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        DateTime data = null;
        LancamentosDinheiro instance = new LancamentosDinheiro();
        instance.setData(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHistorico method, of class LancamentosDinheiro.
     */
    @Test
    public void testSetHistorico() {
        System.out.println("setHistorico");
        String historico = "";
        LancamentosDinheiro instance = new LancamentosDinheiro();
        instance.setHistorico(historico);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValor method, of class LancamentosDinheiro.
     */
    @Test
    public void testSetValor() {
        System.out.println("setValor");
        float valor = 0.0F;
        LancamentosDinheiro instance = new LancamentosDinheiro();
        instance.setValor(valor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOperacao method, of class LancamentosDinheiro.
     */
    @Test
    public void testSetOperacao() {
        System.out.println("setOperacao");
        EnumOperacao operacao = null;
        LancamentosDinheiro instance = new LancamentosDinheiro();
        instance.setOperacao(operacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class LancamentosDinheiro.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        LancamentosDinheiro instance = new LancamentosDinheiro();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canEqual method, of class LancamentosDinheiro.
     */
    @Test
    public void testCanEqual() {
        System.out.println("canEqual");
        Object other = null;
        LancamentosDinheiro instance = new LancamentosDinheiro();
        boolean expResult = false;
        boolean result = instance.canEqual(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class LancamentosDinheiro.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class LancamentosDinheiro.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LancamentosDinheiro instance = new LancamentosDinheiro();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

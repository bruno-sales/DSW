/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.Interfaces;

import Model.LancamentosDinheiro;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hazël § Rebecca
 */
public interface ILancamentosDinheiroDAO {
    
    //carrega n entra, ne?
    public LancamentosDinheiro getlancamentoDinheiroDAO(int id);
    
}

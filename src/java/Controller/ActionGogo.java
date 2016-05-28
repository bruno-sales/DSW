/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO.PersonagemDAO;
import Model.Personagem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import br.unirio.simplemvc.actions.Action;
import br.unirio.simplemvc.actions.ActionException;
import br.unirio.simplemvc.actions.authentication.DisableUserVerification;
import br.unirio.simplemvc.actions.results.Any;
import br.unirio.simplemvc.actions.results.Error;
import br.unirio.simplemvc.actions.results.ResultType;
import br.unirio.simplemvc.actions.results.Success;

public class ActionGogo extends Action {

    //Não usar ainda
    public static final int PAGE_SIZE = 10;

    /**
     * Recupera os persongens da pagina atual
     * @return SUCCESS
     */
    @DisableUserVerification
    @Any("/jsp/listpersonagens.jsp")
    public String retrieve() {
        PersonagemDAO persona = new PersonagemDAO();

        List<Personagem> list = persona.lista();

        setAttribute("personas", list);

        return SUCCESS;
    }
    
}

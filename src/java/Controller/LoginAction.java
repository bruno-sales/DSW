/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO.UsuarioDAO;
import Model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author bruno
 */
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";
    
    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        int i = 0;
        
        Usuario formBean = (Usuario)form;
        String login = formBean.getEmail();
        String senha = formBean.getSenha();

        //se algum nulo, retorna erro
        if (login == null || senha == null) {
            return mapping.findForward(FAILURE);
        }
        
        UsuarioDAO userDao = new UsuarioDAO();
        //Metodo interno de verificação de Login
        Usuario usuario = userDao.verificaLogin(login, senha);
               
        
        if(usuario == null)
        {
           //userDao.indicarLoginFalha(idUsuario);            
            return mapping.findForward(FAILURE);
        }
                       
        return mapping.findForward(SUCCESS);
    }

}

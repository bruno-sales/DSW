package Controller;

import Model.DAO.UsuarioDAO;
import Model.Usuario;
import br.unirio.simplemvc.actions.Action;
import static br.unirio.simplemvc.actions.Action.SUCCESS;
import br.unirio.simplemvc.actions.ActionException;
import br.unirio.simplemvc.actions.authentication.DisableUserVerification;
import br.unirio.simplemvc.actions.results.Any;
import br.unirio.simplemvc.actions.results.Error;
import br.unirio.simplemvc.actions.results.ResultType;
import br.unirio.simplemvc.actions.results.Success;

/**
 * Classe com acoes de login e tratamento de usuarios
 *
 */
public class ActionLogin extends Action {

    /**
     * Acao de login
     * @return ACAO
     * @throws br.unirio.simplemvc.actions.ActionException
     */
    @DisableUserVerification
    @Error("/jsp/login.jsp")
    @Success("/jsp/listpersonagens.jsp")
    public String login() throws ActionException {
        
        if (testLogged() != null) {
            return SUCCESS;
        }        
        //recuperar email e senha dos campos
        String email = getParameter("email");
        String password = getParameter("pwd");

        //se algum nulo, retorna erro
        if (email == null || password == null) {
            return ERROR;
        }
        
        UsuarioDAO userDao = new UsuarioDAO();
        //Metodo interno de verificação de Login
        Usuario usuario = userDao.verificaLogin(email, password);
        
        check(usuario != null, "Usuário e senha incorretos.");
        
        
        if(usuario == null)
        {
           //userDao.indicarLoginFalha(idUsuario);
            return SUCCESS;
        }
        
        userDao.indicarLoginSucesso(usuario.getId());
               
        setCurrentUser(usuario);
        return SUCCESS;
    }

    /**
     * Acao de ida para a homepage
     * @return SUCCESS
     * @throws br.unirio.simplemvc.actions.ActionException
     */
    @Error("/login/login.do")
    @Success("/jsp/homepage.jsp")
    public String homepage() throws ActionException {
        checkLogged();
        return SUCCESS;
    }

    /**
     * Acao de logout
     * @return SUCCESS
     */
    @DisableUserVerification
    @Any(type = ResultType.Redirect, value = "/login/login.do")
    public String logout() {
        invalidateCurrentUser();
        return SUCCESS;
    }

}

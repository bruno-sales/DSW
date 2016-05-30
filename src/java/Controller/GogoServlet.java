package Controller;

import Model.DAO.PersonagemDAO;
import Model.DAO.UsuarioDAO;
import Model.Personagem;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bruno
 */
public class GogoServlet extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String LOGOFF = "logoff";
    private static final String CADASTRARUSUARIO = "cadastrarUsuario";
    private static final String LISTARPERSONAGENS = "listarPersonagens";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String tarefa = request.getParameter("t");

            switch (tarefa) {
                case LOGIN:
                    validarLogin(request, response);
                    break;
                case LOGOFF:
                    break;
                case CADASTRARUSUARIO:
                    cadastrarUsuario(request, response);
                    break;
                case LISTARPERSONAGENS:
                    buscarPersonagens(request, response);
                    break;
                default:
                    response.sendRedirect("login.jsp");
                    break;
            }
        }

    }

    public void validarLogin(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        //Recuperar dados do formulario
        String login = request.getParameter("email");
        String senha = request.getParameter("senha");

        //se algum nulo, retorna erro
        if (login == null || senha == null) {
            response.sendRedirect("login.jsp?mensagem=*Login ou senha invalido!");
        } else {

            UsuarioDAO userDao = new UsuarioDAO();
            //Metodo interno de verificação de Login
            Usuario usuario = userDao.verificaLogin(login, senha);

            if (usuario == null) {
                response.sendRedirect("login.jsp?mensagem=*Login ou senha invalido!");
            } else {

                Cookie loginCookie = new Cookie("user", usuario.getNome());
                //cookie expirando em 30 mins                
                loginCookie.setMaxAge(30 * 60);

                //cria o cookie
                response.addCookie(loginCookie);

                //redireciona
                response.sendRedirect("listaPersonagens.jsp");
            }
        }
    }

    public void cadastrarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        UsuarioDAO uDao = new UsuarioDAO();

        Usuario novoUser = new Usuario();

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String senha = request.getParameter("senha");

        novoUser.setEmail(email);
        novoUser.setNome(nome);
        novoUser.setCpf(cpf);
        novoUser.setTelefone(telefone);
        novoUser.setSenha(senha);

        boolean retorno = uDao.inserir(novoUser);

        if (retorno == false) {
            response.sendRedirect("cadastroUsuario.jsp?mensagem=Ocorreu um erro! Tente novamente");
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    protected void buscarPersonagens(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        PersonagemDAO pDao = new PersonagemDAO();

        List<Personagem> lista = pDao.lista();

        request.setAttribute("personas", lista);

        // Redireciona
        RequestDispatcher rd = request.getRequestDispatcher("/listaPersonagens.jsp");
        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servle da aplicacao";
    }// </editor-fold>

}

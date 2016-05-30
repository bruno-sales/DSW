package Controller;

import Model.DAO.PersonagemDAO;
import Model.DAO.UsuarioDAO;
import Model.Personagem;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
                    fazerLogoff(request, response);
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
    
    //Método controlador de validação de login
    public void validarLogin(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        //Recuperar dados do formulario
        String login = request.getParameter("email");
        String senha = request.getParameter("senha");

        //se algum nulo, retorna erro
        if (login == null || senha == null) {
            response.sendRedirect("login.jsp?mensagem=*Login ou senha invalida!");
        } else {

            UsuarioDAO userDao = new UsuarioDAO();
            //Metodo interno de verificação de Login
            Usuario usuario = userDao.verificaLogin(login, senha);

            if (usuario == null) {
                response.sendRedirect("login.jsp?mensagem=*Preencha os campos para efetuar login!");
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
    
    //Método de controle para cadastro do usuário
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

        //Caso ocorra erro ao criar novo usuário, o retorno será falso
        boolean retorno = uDao.inserir(novoUser);

        if (retorno == false) {
            response.sendRedirect("cadastroUsuario.jsp?mensagem=Nao foi possivel efetuar o cadastro! Tente novamente");
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    //Método para listar todos os persoagens
    protected void buscarPersonagens(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        PersonagemDAO pDao = new PersonagemDAO();

        List<Personagem> lista = pDao.lista();

        request.setAttribute("personas", lista);

        // Redireciona
        RequestDispatcher rd = request.getRequestDispatcher("/listaPersonagens.jsp");
        rd.forward(request, response);

    }

    //Método para efetuar logoff
    public void fazerLogoff(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        //Recuperar cookie da requisição
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    loginCookie = cookie;
                    break;
                }
            }
        }
        if (loginCookie != null) {
            //Expirar cookie
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        
        //Redirecionar para página de login
        response.sendRedirect("login.jsp");
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

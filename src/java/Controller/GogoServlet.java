package Controller;

import Model.DAO.PersonagemDAO;
import Model.DAO.TokenDAO;
import Model.DAO.UsuarioDAO;
import Model.Personagem;
import Model.Token;
import Model.Usuario;
import Util.ServicoEmail;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * TODO Implementar as seguintes funcionalidades - TODA A PARAFERNALHA DAS
 * TRANSAÇÕES - Listar usuario, dar acesso apenas a um sysadmin - Lista de
 * ofertas abertas - Lista de ofertas: historico do usuario - Extrato da CC do
 * usuario - Cotação historica do personagem
 *
 */
@MultipartConfig(maxFileSize = 16177215)
public class GogoServlet extends HttpServlet {

    public static final int PAGESIZE = 5;
    private static final String LOGIN = "login";
    private static final String LOGOFF = "logoff";
    private static final String CADASTRARUSUARIO = "cadastrarUsuario";
    private static final String EDITARUSUARIO = "editarUsuario";
    private static final String CARREGARFOTOUSUARIO = "carregarFotoUsuario";
    private static final String LISTARPERSONAGENS = "listarPersonagens";
    private static final String VERIFICARTOKEN = "verificarToken";
    private static final String ENVIARTOKEN = "enviarToken";
    private static final String RECUPERARSENHA = "recuperarSenha";

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

        String tarefa = request.getParameter("t");

        /*
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
         }*/
        switch (tarefa) {
            case LOGOFF:
                fazerLogoff(request, response);
                break;
            case LISTARPERSONAGENS:
                buscarPersonagens(request, response);
                break;
            case LOGIN:
                validarLogin(request, response);
                break;
            case CADASTRARUSUARIO:
                cadastrarUsuario(request, response);
                break;
            case EDITARUSUARIO:
                editarUsuario(request, response);
                break;
            case CARREGARFOTOUSUARIO:
                carregarFotoUsuario(request, response);
                break;
            case VERIFICARTOKEN:
                verificarToken(request, response);
                break;
            case ENVIARTOKEN:
                enviarToken(request, response);
                break;
            case RECUPERARSENHA:
                recuperarSenha(request, response);
                break;
            default:
                response.sendRedirect("login.jsp");
                break;
        }

    }

// <editor-fold defaultstate="collapsed" desc="Região com os metodos de cadastro">
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
            response.sendRedirect("login.jsp?mensagem=Usuario cadastrado com sucesso");
        }

    }//</editor-fold>

// <editor-fold defaultstate="collapsed" desc="Região com os metodos de edição">
//Método de controle para editar o usuário
    public void editarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        UsuarioDAO uDao = new UsuarioDAO();

        Usuario usuario = new Usuario();

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");

        InputStream inputStream = null;

        Part foto = request.getPart("foto");

        if (foto != null) {
            // settar o fileUpload na variavel de stream
            inputStream = foto.getInputStream();
        }

        int idUsuario = 0;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    idUsuario = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        usuario.setIdUsuario(idUsuario);
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setTelefone(telefone);
        usuario.setFoto(inputStream);

        //Caso ocorra erro ao criar novo usuário, o retorno será falso
        boolean retorno = uDao.atualizar(usuario);

        if (retorno == false) {
            response.sendRedirect("editarUsuario.jsp?mensagem=Nao foi possivel concluir a edicao! Tente novamente");
        } else {
            response.sendRedirect("perfilUsuario.jsp");
        }
    }//</editor-fold>

// <editor-fold defaultstate="collapsed" desc="Região com os metodos de busca">
    //Método para listar todos os persoagens
    protected void buscarPersonagens(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        PersonagemDAO pDao = new PersonagemDAO();

        //Paginadores
        int page;
        int count = pDao.countItens();

        try { //Tenta converter o numero da pagina
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException nf) //Define como pagina 0, se não.
        {
            page = 0;
        }

        //Obter Lista
        List<Personagem> lista = pDao.lista(page, PAGESIZE);

        //Paginadores
        boolean hasNext = (count > (page + 1) * PAGESIZE);
        boolean hasPrior = (page > 0);

        //Guardar informações na memoria de requisição
        request.setAttribute("page", page);
        request.setAttribute("hasNextPage", hasNext);
        request.setAttribute("hasPriorPage", hasPrior);
        request.setAttribute("personas", lista);

        // Redireciona
        RequestDispatcher rd = request.getRequestDispatcher("/listaPersonagens.jsp");
        rd.forward(request, response);

    }

    private void carregarFotoUsuario(HttpServletRequest request, HttpServletResponse response) {

        try{            
            
        UsuarioDAO uDao = new UsuarioDAO();
        Usuario usuario;
        
        int userId = Integer.parseInt(request.getParameter("userId"));         
        
        usuario = uDao.getUsuarioPorId(userId);
        
        response.setContentType("image/jpeg");

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = usuario.getFoto().read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        response.getOutputStream().write(data);
        }catch(IOException io)
        {
            
        }

    }//</editor-fold>

// <editor-fold defaultstate="collapsed" desc="Região com os metodos de autenticação, login e logoff">
    //Método controlador de validação de login
    public void validarLogin(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        //Recuperar dados do formulario
        String login = request.getParameter("email");
        String senha = request.getParameter("senha");

        //se algum vazio, retorna erro
        if (login == null || senha == null) {
            response.sendRedirect("login.jsp?mensagem=*Preencha os campos para efetuar login!");
        } else {

            UsuarioDAO userDao = new UsuarioDAO();

            //Metodo interno de verificação de Validade de email            
            Usuario usuario = userDao.getUsuarioPorEmail(login);

            if (usuario == null) {
                response.sendRedirect("login.jsp?mensagem=*Login ou senha invalida!");
            } else {
                /*Se este usuario ultrapassou o limite de tentativas de login, 
                 Redirecionar a pagina de esqueci minha senha*/
                if (usuario.getNumeroLogins() >= 3) {
                    response.sendRedirect("recuperarSenha.jsp?mensagem=*Este usuario ultrapassou o "
                            + "numero de tentativas de login, favor trocar senha");

                } else {

                    //Metodo interno para verificação de autenticação             
                    boolean autenticado = userDao.verificaLogin(usuario.getIdUsuario(), senha);

                    if (autenticado) //Autenticação validada
                    {
                        Cookie loginCookie = new Cookie("user", usuario.getNome());
                        Cookie userIdCookie = new Cookie("userId", "" + usuario.getIdUsuario());

                        //cookie expirando em 30 mins                
                        loginCookie.setMaxAge(30 * 60);
                        userIdCookie.setMaxAge(30 * 60);

                        //cria o cookie
                        response.addCookie(loginCookie);
                        response.addCookie(userIdCookie);

                        //redireciona
                        response.sendRedirect("listaPersonagens.jsp");
                    } else //Autenticação invalida
                    {
                        response.sendRedirect("login.jsp?mensagem=*Login ou senha invalida!");
                    }
                }
            }
        }
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

    //Método para efetuar o envio de um novo token ao email
    public void enviarToken(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter("email");

        if (email == null) {
            response.sendRedirect("recuperarSenha.jsp?mensagem=Insira o email");
        } else {
            UsuarioDAO userDao = new UsuarioDAO();
            Usuario usuario = userDao.getUsuarioPorEmail(email);

            if (usuario == null) {
                response.sendRedirect("recuperarSenha.jsp?mensagem=email invalido");
            } else {
                Token token = new Token(usuario.getIdUsuario());
                TokenDAO tkDao = new TokenDAO();

                if (tkDao.insere(token)) //Se a criação do token foi OK, pode enviar email
                {
                    //Monta o email
                    ServicoEmail mail = new ServicoEmail();
                    boolean emailEnviado = mail.EnviarEmail(email, "Bolsa Gogo - Recuperar Senha",
                            "Olá, você requisitou um token para trocar sua senha na Bolsa Gogo. "
                            + "Seu token é: " + token.getToken());

                    if (emailEnviado) {
                        request.setAttribute("mensagem", "Um token foi enviado a este email");
                    } else {
                        request.setAttribute("mensagem", "Ocorreu um erro ao enviar um token a este email");
                    }
                    // Redireciona
                    RequestDispatcher rd = request.getRequestDispatcher("/recuperarSenha.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }

    //Método verificar validade de um token
    public void verificarToken(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter("email");
        String token = request.getParameter("token");

        if (email == null || token == null) {
            response.sendRedirect("recuperarSenha.jsp?mensagem=Insira o email");
        } else {
            UsuarioDAO userDao = new UsuarioDAO();
            Usuario usuario = userDao.getUsuarioPorEmail(email);

            if (usuario == null) {
                response.sendRedirect("recuperarSenha.jsp?mensagem=Email invalido");
            } else {
                TokenDAO tkDao = new TokenDAO();

                //Se a validação do token foi OK, pode liberar campos para troca de senha
                if (tkDao.verficaValidadeToken(usuario.getIdUsuario(), token)) {
                    request.setAttribute("tokenValido", true);
                    request.setAttribute("usuarioId", usuario.getIdUsuario());
                } else {
                    request.setAttribute("tokenValido", false);
                    request.setAttribute("mensagem", "Token invalido ou expirado. Favor gerar um novo token");
                }
                // Redireciona
                RequestDispatcher rd = request.getRequestDispatcher("/recuperarSenha.jsp");
                rd.forward(request, response);
            }
        }
    }

    //Método verificar validade de um token
    public void recuperarSenha(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        String senha = request.getParameter("senha");

        if (senha == null) {
            response.sendRedirect("recuperarSenha.jsp?mensagem=Insira a nova senha");
        } else {
            String usuario = request.getParameter("usuarioId");
            int usuarioId = Integer.parseInt(usuario);

            if (usuarioId == 0) {
                response.sendRedirect("recuperarSenha.jsp?mensagem=Ocorreu um erro ao recuperar o usuario");
            } else {

                UsuarioDAO userDao = new UsuarioDAO();
                //Se a troca de senha foi OK, redireciona pra login
                if (userDao.TrocarSenha(usuarioId, senha)) {
                    response.sendRedirect("login.jsp?mensagem=Senha alterada com sucesso");
                } else {
                    response.sendRedirect("recuperarSenha.jsp?mensagem=Ocorreu um erro ao alterar a senha");
                }
            }
        }
    }// </editor-fold>

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

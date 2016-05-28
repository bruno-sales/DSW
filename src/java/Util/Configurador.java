package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configurador {

    private String url;
    private String driver;
    private String login;
    private String senha;
    private String banco;

    public Configurador() {
        url = "jdbc:mysql://localhost/bolsa_gogo";
        driver = "com.mysql.jdbc.Driver";
        login = "root";
        senha = "admin";
        banco = "bolsa_gogo";
    }

    /**
     * Cria uma conexão com o banco de dados
     *
     * @return Retorna objeto de Conexão
     */
    public Connection getConnection() {
        try {
            Class.forName(driver).newInstance();
            Connection conexao = DriverManager.getConnection(url, login, senha);
            conexao.setCatalog(banco);
            return conexao;

        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - erro de SQL");
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - driver não encontrado");
            return null;
        } catch (InstantiationException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - erro de instanciação do driver");
            return null;
        } catch (IllegalAccessException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - acesso ilegal no driver");
            return null;
        }
    }

    public static void log(String mensagem) {
        System.out.println(mensagem);
    }
}

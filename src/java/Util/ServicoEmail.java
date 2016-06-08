package Util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * Classe responsavel pelo envio de emails
 */
public class ServicoEmail {

    private final String sender = "bolsagogodsw@gmail.com";
    private final String pass = "servidorweb";

    private final String host = "smtp.gmail.com";
    private final String socketPort = "465";
    private final String socketClass = "javax.net.ssl.SSLSocketFactory";
    private final String port = "465";
    private final Properties props;

    public ServicoEmail() {
        props = new Properties();
        
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", socketPort);
        props.put("mail.smtp.socketFactory.class", socketClass);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

    }

    public boolean EnviarEmail(String destinatario, String titulo, String mensagem) {

        boolean enviado;
        
        try {

            // Cria sessão para o email
            Session sessionEmail = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(sender, pass);
                        }
                    });

            // Para quem o email se destina.
            String to = destinatario;

            // Cria MimeMessage.
            MimeMessage message = new MimeMessage(sessionEmail);

            // Cabeçalho: Quem enviou.
            message.setFrom(new InternetAddress(sender));

            // Cabeçalho: Quem receberá.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Cabeçalho: Titulo
            message.setSubject(titulo);

            // Corpo do email
            message.setText(mensagem);

            // Transportar mensagem, enviar.
            Transport.send(message);
            
            enviado = true;
        } catch (MessagingException mex) {
            enviado = false;
        }
        
        return enviado;
    }
}

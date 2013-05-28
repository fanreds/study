package pl.edu.pk.view;

import org.jboss.seam.international.status.Messages;
import pl.edu.pk.DAO.MessageDAO;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.Message;
import pl.edu.pk.security.SecurityGenerator;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kleks
 * Date: 3/11/13
 * Time: 3:30 PM
 */
@Named
@ViewScoped
public class SentboxView implements Serializable {
    @Inject
    private CurrentUserManager currentUserManager;
    @Inject
    private UserDAO userDAO;
    @Inject
    private Messages messages;
    @Inject
    private MessageDAO messageDAO;
    private Message message;
    private List<Message> messageList;
    private boolean renderMessage = false;

    public List<Message> getMessages() {
        if (messageList == null) {
            messageList = messageDAO.getSentboxMessages(currentUserManager.getUser());
            SecurityGenerator securityGenerator = new SecurityGenerator();
            securityGenerator.initCipherRSA();
            for (Message message1 : messageList) {
                if (message1.getRecipient() != null) {                                                 //only test case - not your key
                    message1.setContentString(new String(securityGenerator.getDecoded(message1.getContent(), message1.getRecipient().getPrivateKey())));
                }
            }
        }
        return messageList;
    }

    public boolean isRenderMessage() {
        return renderMessage;
    }

    /*
        * true - group Message
        * false - private Message*/
    public boolean isGroupMessage(Message message) {
        if ((message.isForAll() || message.getSpecialization() != null || message.getGroup() != null) && message.getRecipient() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void read(Message message) {
        this.message = message;
        if (!this.message.isRead()) {
            this.message.setRead(true);
            messageDAO.update(message);
        }
        renderMessage = true;
    }

    public Message getMessage() {
        return message;
    }

    public void close() {
        message = null;
        renderMessage = false;
    }

    public String recipient(Message message) {
        if (message.isForAll()) {
            return "All";
        } else if (message.getSpecialization() != null) {
            return "Spec.: " + message.getSpecialization().getName();
        } else if (message.getGroup() != null) {
            return "Spec.: " + message.getGroup().getSpecialization().getName() + " " + "Gr.:" + message.getGroup().getName();
        } else {
            return message.getRecipient().getName() + " " + message.getRecipient().getForename();
        }
    }
}

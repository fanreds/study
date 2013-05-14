package pl.edu.pk.view;

import org.apache.commons.codec.binary.Base64;
import org.jboss.seam.international.status.Messages;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.File;
import pl.edu.pk.web.BundleKeys;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
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
public class ChangePassView implements Serializable {
    @Inject
    private CurrentUserManager currentUserManager;
    @Inject
    private UserDAO userDAO;
    @Inject
    private Messages messages;

    private String oldPass;
    private String newPass;
    private String repeatPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRepeatPass() {
        return repeatPass;
    }

    public void setRepeatPass(String repeatPass) {
        this.repeatPass = repeatPass;
    }

    public void save() {

        if (newPass.equals(repeatPass)){
            userDAO.update(currentUserManager.getUser());
            messages.info(BundleKeys.PASSWORD_CHANGED);
        }
    }
}

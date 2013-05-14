package pl.edu.pk.view;

import org.apache.commons.codec.binary.Base64;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.File;
import pl.edu.pk.domain.Student;

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
public class HomeView implements Serializable {
    private static final String BASE64_PREFIX = "data:image/gif;base64,";
    @Inject
    private CurrentUserManager currentUserManager;
    @Inject
    private UserDAO userDAO;
    @Inject
    private ImageScaling imageScaling;

    public String imageToBase64(byte[] image) throws IOException {
        return BASE64_PREFIX + Base64.encodeBase64String(imageScaling.scale(image, 200, 200));
    }

    public List<File> getUserFiles() {
        if (currentUserManager.getUser() instanceof Student) {
            return userDAO.getSharedFilesForStudent(currentUserManager.getUser());
        } else {
            return userDAO.getSharedFilesForLecturer(currentUserManager.getUser());
        }
    }
}

package pl.edu.pk.view;

import org.apache.commons.codec.binary.Base64;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.File;
import pl.edu.pk.domain.FileAccess;

import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kleks
 * Date: 3/11/13
 * Time: 3:30 PM
 */
@Named
@ViewScoped
public class UploadView implements Serializable {
    private static final String BASE64_PREFIX = "data:image/gif;base64,";
    private ArrayList<UploadedFile> files = new ArrayList<UploadedFile>();
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private CurrentUserManager currentUserManager;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private Instance<UserDAO> userDAO;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private ImageScaling imageScaling;
    private File file;

    public void listener(FileUploadEvent event) {
        UploadedFile file = event.getUploadedFile();
        file.getData();

        setFile(new File());
        getFile().setFileName(file.getName());
        getFile().setContent(file.getData());
        getFile().setUser(currentUserManager.getUser());
        FileAccess fileAccess = new FileAccess();
        fileAccess.setShareAll(false);
        getFile().setFileAccess(fileAccess);
        currentUserManager.getUser().getFiles().add(getFile());
        userDAO.get().update(currentUserManager.getUser());
    }

    public String image(File file) throws IOException {
        if (file.getFileName().contains("jpg") || file.getFileName().contains("png") || file.getFileName().contains("gif")) {
            return BASE64_PREFIX + Base64.encodeBase64String(imageScaling.scale(file.getContent(), 200, 200));
        } else {
            return "/img/placeholder.png";
        }
    }

    public File getFile() {
        if (file == null) {
            file = new File();
        }
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<File> getUserFiles() {

        return currentUserManager.getUser().getFiles();
    }

    public void delete(File file) {
        currentUserManager.getUser().getFiles().remove(file);
        userDAO.get().update(currentUserManager.getUser());
    }
}

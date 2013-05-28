package pl.edu.pk.view;

import org.apache.commons.codec.binary.Base64;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.File;
import pl.edu.pk.domain.FileAccess;
import pl.edu.pk.security.SecurityGenerator;

import javax.enterprise.inject.Instance;
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
public class UploadView implements Serializable {
    private static final String BASE64_PREFIX = "data:image/gif;base64,";
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
    private SecurityGenerator securityGenerator;

    public UploadView() {
        securityGenerator = new SecurityGenerator();
    }

    public void listener(FileUploadEvent event) {
        UploadedFile file = event.getUploadedFile();
        byte[] fileRaw = file.getData();

        setFile(new File());
        getFile().setFileName(file.getName());
        getFile().setUser(currentUserManager.getUser());

        getFile().setContent(encodeFile(fileRaw));
        FileAccess fileAccess = new FileAccess();
        fileAccess.setShareAll(false);
        getFile().setFileAccess(fileAccess);
        currentUserManager.getUser().getFiles().add(getFile());
        userDAO.get().update(currentUserManager.getUser());
    }

    private byte[] encodeFile(byte[] inputFile) {
        securityGenerator.initDES();
        securityGenerator.initCipherDES();
        getFile().setSecretKey(securityGenerator.generateSecretKey());
        byte[] fileEncoded = securityGenerator.getEncodedDES(inputFile, getFile().getSecretKey());
        getFile().setIv(securityGenerator.getIV());
        return fileEncoded;
    }

    private byte[] decodeFile(File inputFile) {
        securityGenerator.initDES();
        securityGenerator.initCipherDES();
        byte[] fileDecoded = securityGenerator.getDecodedDES(inputFile.getContent(), inputFile.getSecretKey(), inputFile.getIv());
        return fileDecoded;
    }

    public String image(File file) throws IOException {
        if (file.getFileName().contains("jpg") || file.getFileName().contains("png") || file.getFileName().contains("gif")) {
            return BASE64_PREFIX + Base64.encodeBase64String(imageScaling.scale(decodeFile(file), 200, 200));
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

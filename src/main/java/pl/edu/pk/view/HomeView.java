package pl.edu.pk.view;

import org.apache.commons.codec.binary.Base64;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.File;
import pl.edu.pk.domain.Student;
import pl.edu.pk.security.SecurityGenerator;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    public String image(File file) throws IOException {
        if (file.getFileName().contains("jpg") || file.getFileName().contains("png") || file.getFileName().contains("gif")) {
            return BASE64_PREFIX + Base64.encodeBase64String(imageScaling.scale(decodeFile(file), 200, 200));
        } else {
            return "/img/placeholder.png";
        }
    }
    private byte[] decodeFile(File inputFile) {
        SecurityGenerator securityGenerator = new SecurityGenerator();
        securityGenerator.initDES();
        securityGenerator.initCipherDES();
        byte[] fileDecoded = securityGenerator.getDecodedDES(inputFile.getContent(), inputFile.getSecretKey(), inputFile.getIv());
        return fileDecoded;
    }

    public List<File> getUserFiles() {
        if (currentUserManager.getUser() instanceof Student) {
            return userDAO.getSharedFilesForStudent(currentUserManager.getUser());
        } else {
            return userDAO.getSharedFilesForLecturer(currentUserManager.getUser());
        }
    }

    public void download(File file) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context
                .getExternalContext().getResponse();
        byte[] decoded= decodeFile(file);
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Length", String.valueOf(decoded.length));
        response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getFileName() + "\"");
        response.getOutputStream().write(decoded);
        context.responseComplete();
    }
}

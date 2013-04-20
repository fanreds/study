package pl.edu.pk.view;

import org.jboss.seam.transaction.Transactional;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.business.EMProducer;
import pl.edu.pk.domain.File;
import pl.edu.pk.domain.FileAccess;

import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
public class HomeView implements Serializable {
    private ArrayList<UploadedFile> files = new ArrayList<UploadedFile>();
    //    @Inject
//    private Conversation conversation;
    @Inject
    private CurrentUserManager currentUserManager;
    @Inject
    private Instance<EMProducer> entityManagerInstance;
    @Inject
    private UserDAO userDAO;
    private File file;

//    @Transactional
    public void listener(FileUploadEvent event) {
        UploadedFile file = event.getUploadedFile();
        file.getData();

        setFile(new File());
        getFile().setFileName(file.getName());
        getFile().setContent(file.getData());
//        FileAccess fileAccess = new FileAccess();
//        fileAccess.setShareAll(false);
//        getFile().setFileAccess(fileAccess);
        currentUserManager.getUser().getFiles().add(getFile());
        entityManagerInstance.get().update(currentUserManager.getUser());
//        entityManagerInstance.get().getEntityManager().merge(currentUserManager.getUser());
//        entityManagerInstance.get().getEntityManager().flush();
//        files.add(file);
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

//    public void initConversation() {
//        if (conversation.isTransient()) {
//            conversation.begin();
//        }
//    }

    public void init() {
//        initConversation();
    }

    public void delete(File file) {
        currentUserManager.getUser().getFiles().remove(file);
        entityManagerInstance.get().update(currentUserManager.getUser());
        int k = 0;
    }
}

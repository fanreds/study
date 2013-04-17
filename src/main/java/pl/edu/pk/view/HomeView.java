package pl.edu.pk.view;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.EMProducer;
import pl.edu.pk.domain.File;
import pl.edu.pk.domain.User;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.OutputStream;
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
@ConversationScoped
public class HomeView implements Serializable {
    private ArrayList<UploadedFile> files = new ArrayList<UploadedFile>();
    @Inject
    private Conversation conversation;
    @Inject
    private CurrentUserManager currentUserManager;
    @Inject
    private Instance<EMProducer> entityManagerInstance;

    @Inject
    private UserDAO userDAO;

    private File file;

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
        stream.close();
    }

    public void listener(FileUploadEvent event) {
        UploadedFile file = event.getUploadedFile();
        file.getData();

        getFile().setFileName(file.getName());
        getFile().setContent(file.getData());
        currentUserManager.getUser().getFiles().add(getFile());
        entityManagerInstance.get().getEntityManager().merge(currentUserManager.getUser());
        entityManagerInstance.get().getEntityManager().flush();
//        files.add(file);
    }

    public String clearUploadData() {
        files.clear();
        return null;
    }

    public Integer getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }

    public Long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public ArrayList<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<UploadedFile> files) {
        this.files = files;
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

    public void initConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void init() {
        initConversation();
    }
}

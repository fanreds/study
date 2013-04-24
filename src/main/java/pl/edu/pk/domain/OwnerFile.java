package pl.edu.pk.domain;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/24/13
 * Time: 11:04 AM
 */
public class OwnerFile {

    private User user;

    private File file;

    public OwnerFile(User user, File file) {
        this.user = user;
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

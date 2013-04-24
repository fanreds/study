package pl.edu.pk.view;

import org.jboss.seam.international.status.Messages;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.*;
import pl.edu.pk.web.BundleKeys;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/17/13
 * Time: 9:10 PM
 */
@Named
@ViewScoped
public class FileAccessView implements Serializable {

    Converter specializationConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            for (Specialization specialization : allSpecializations) {
                if (specialization.getName().equals(value)) {
                    return specialization;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return "Not Share";
            }
            return ((Specialization) value).getName();
        }
    };
    Converter studentConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            for (Student student : allStudents) {
                if (student.getName().equals(value)) {
                    return student;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            return ((Student) value).getName();
        }
    };
    Converter lecturerConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            for (Lecturer lecturer : allLecturers) {
                if (lecturer.getName().equals(value)) {
                    return lecturer;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            return ((Lecturer) value).getName();
        }
    };
    Converter userConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            for (User user : selectedFile.getFileAccess().getUsers()) {
                if (user.getName().equals(value)) {
                    return user;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            return ((User) value).getName();
        }
    };
    Converter groupConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            for (Group group : allGroups) {
                if (group.getName().equals(value)) {
                    return group;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return "Not Share";
            }
            return ((Group) value).getName();
        }
    };
    Converter groupConverterForMySpec = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            for (Group group : allGroupsForMySpec) {
                if (group.getName().equals(value)) {
                    return group;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return "Not Share";
            }
            return ((Group) value).getName();
        }
    };
    private List<Specialization> allSpecializations;
    private List<Group> allGroups;
    private List<Group> allGroupsForMySpec;
    private List<Student> allStudents;
    private List<Lecturer> allLecturers;
    private Specialization selectedSpecialization;
    private Group selectedGroup;
    private boolean selectedShareAll;
    private Student selectedStudent;
    private User selectedUserWithAccess;
    private Lecturer selectedLecturer;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private UserDAO userDAO;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private CurrentUserManager currentUserManager;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private Messages messages;
    private boolean renderUser = false;
    private boolean renderGroup = false;
    private boolean renderSpecialization = false;
    private boolean renderAll = false;
    private File selectedFile;

    public Converter getLecturerConverter() {
        return lecturerConverter;
    }

    public Converter getUserConverter() {
        return userConverter;
    }

    public void setUserConverter(Converter userConverter) {
        this.userConverter = userConverter;
    }

    public Converter getGroupConverterForMySpec() {
        return groupConverterForMySpec;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public User getSelectedUserWithAccess() {
        return selectedUserWithAccess;
    }

    public void setSelectedUserWithAccess(User selectedUserWithAccess) {
        this.selectedUserWithAccess = selectedUserWithAccess;
    }

    public Converter getGroupConverter() {
        return groupConverter;
    }

    public Converter getStudentConverter() {
        return studentConverter;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public List<Lecturer> getAllLecturers() {
        allLecturers = userDAO.getAllLecturers();

        if (currentUserManager.getUser() instanceof Lecturer) {
            allLecturers.remove(currentUserManager.getUser());
        }
        if (selectedFile.getFileAccess().getUsers() != null && !selectedFile.getFileAccess().getUsers().isEmpty()) {
            for (User user : selectedFile.getFileAccess().getUsers()) {
                if (allLecturers.contains(user)) {
                    allLecturers.remove(user);
                }
            }
        }
        return allLecturers;
    }

    public void setAllLecturers(List<Lecturer> allLecturers) {
        this.allLecturers = allLecturers;
    }

    public Lecturer getSelectedLecturer() {
        return selectedLecturer;
    }

    public void setSelectedLecturer(Lecturer selectedLecturer) {
        this.selectedLecturer = selectedLecturer;
    }

    public Specialization getSelectedSpecialization() {
        return selectedSpecialization;
    }

    public void setSelectedSpecialization(Specialization selectedSpecialization) {
        this.selectedSpecialization = selectedSpecialization;
    }

    public List<File> getUserFiles() {

        return currentUserManager.getUser().getFiles();
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Group selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public List<Specialization> getAllSpecializations() {

        if (allSpecializations == null) {
//            allSpecializations = new ArrayList<Specialization>();
            if (currentUserManager.getUser() instanceof Student) {
                allSpecializations = userDAO.getAllSpecializations(((Student) currentUserManager.getUser()).getGroup().getSpecialization().getAcademy());
            } else {
                allSpecializations = userDAO.getAllSpecializations(((Lecturer) currentUserManager.getUser()).getAcademy());
            }
        }
        return allSpecializations;
    }

    public void setAllSpecializations(List<Specialization> allSpecializations) {
        this.allSpecializations = allSpecializations;
    }

    public List<Group> getAllGroupsForMySpec() {
        if (allGroupsForMySpec == null) {
            allGroupsForMySpec = userDAO.getAllGroups(((Student) currentUserManager.getUser()).getGroup().getSpecialization());
        }
        return allGroupsForMySpec;
    }

    public List<Group> getAllGroups() {
        if (allGroups == null || selectedSpecialization == null) {
            allGroups = new ArrayList<Group>();
//            allGroups = userDAO.getAllGroups(selectedSpecialization);
        } else {
            allGroups = selectedSpecialization.getGroups();
        }
        return allGroups;
    }

    public void setAllGroups(List<Group> allGroups) {
        this.allGroups = allGroups;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public List<Student> getAllStudents() {
        if (allStudents == null && selectedGroup == null) {
            allStudents = new ArrayList<Student>();
        } else {
            allStudents = userDAO.getAllStudents(selectedGroup);

            if (currentUserManager.getUser() instanceof Student && ((Student) currentUserManager.getUser()).getGroup().equals(selectedGroup)) {
                allStudents.remove(currentUserManager.getUser());
            }
            if (selectedFile.getFileAccess().getUsers() != null && !selectedFile.getFileAccess().getUsers().isEmpty()) {
                for (User user : selectedFile.getFileAccess().getUsers()) {
                    if (allStudents.contains(user)) {
                        allStudents.remove(user);
                    }
                }
            }
        }
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public Converter getSpecializationConverter() {
        return specializationConverter;
    }

    public void clear() {
        allStudents = null;
        selectedGroup = null;
    }

    public boolean isRenderUser() {
        return renderUser;
    }

    public void setRenderUser(File file) {
        renderAll = false;
        renderGroup = false;
        renderSpecialization = false;
        if (renderUser && selectedFile != null && selectedFile.getId().equals(file.getId())) {
            renderUser = false;
        } else {
            renderUser = true;
        }

        selectedGroup = null;
        selectedSpecialization = null;
        selectedFile = file;
    }

    public boolean isRenderGroup() {
        return renderGroup;
    }

    public void setRenderGroup(File file) {
        renderAll = false;
        renderUser = false;
        renderSpecialization = false;
        if (renderGroup && selectedFile != null && selectedFile.getId().equals(file.getId())) {
            renderGroup = false;
        } else {
            renderGroup = true;
        }

        selectedFile = file;
        selectedGroup = selectedFile.getFileAccess().getGroup();
    }

    public boolean isRenderSpecialization() {
        return renderSpecialization;
    }

    public void setRenderSpecialization(File file) {
        renderAll = false;
        renderUser = false;
        renderGroup = false;
        if (renderSpecialization && selectedFile != null && selectedFile.getId().equals(file.getId())) {
            renderSpecialization = false;
        } else {
            renderSpecialization = true;
        }

        selectedFile = file;
        selectedSpecialization = selectedFile.getFileAccess().getSpecialization();
    }

    public boolean isRenderAll() {
        return renderAll;
    }

    public void setRenderAll(File file) {
        renderUser = false;
        renderGroup = false;
        renderSpecialization = false;
        if (renderAll && selectedFile != null && selectedFile.getId().equals(file.getId())) {
            renderAll = false;
        } else {
            renderAll = true;
        }

        selectedFile = file;
        selectedShareAll = selectedFile.getFileAccess().getShareAll();
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void saveAll() {
        boolean stateShareAll = selectedFile.getFileAccess().getShareAll();
        if (stateShareAll != selectedShareAll) {
            userDAO.update(selectedFile);
            messages.info(BundleKeys.SAVE_ACCESS_CONFIRMATION);
        }
    }

    public void saveSpecialization() {
        Specialization stateSpecialization = selectedFile.getFileAccess().getSpecialization();
        if (stateSpecialization != selectedSpecialization) {
            userDAO.update(selectedFile);
            messages.info(BundleKeys.SAVE_ACCESS_CONFIRMATION);
        }
    }

    public void saveGroup() {
        Group stateGroup = selectedFile.getFileAccess().getGroup();
        if (stateGroup != selectedGroup) {
            userDAO.update(selectedFile);
            messages.info(BundleKeys.SAVE_ACCESS_CONFIRMATION);
        }
    }

    public void saveUsers() {
        List<User> users = selectedFile.getFileAccess().getUsers();
        if (!users.isEmpty()) {
            userDAO.update(selectedFile);
            messages.info(BundleKeys.SAVE_ACCESS_CONFIRMATION);
        }
    }

    public void addAccessForStudent() {
        if (selectedStudent != null) {
            selectedFile.getFileAccess().getUsers().add(selectedStudent);
            getAllStudents().remove(selectedStudent);
            if (!isOnlyOneElementForStudents()) {
                selectedStudent = null;
            }
        }
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public void addAccessForLecturer() {
        if (selectedLecturer != null) {
            selectedFile.getFileAccess().getUsers().add(selectedLecturer);
            getAllStudents().remove(selectedLecturer);
            if (!isOnlyOneElementForLecturers()) {
                selectedLecturer = null;
            }
        }
    }

    public void removeAccessForStudent() {
        if (selectedUserWithAccess != null) {
            selectedFile.getFileAccess().getUsers().remove(selectedUserWithAccess);
            selectedUserWithAccess = null;
        }
    }

    public boolean isOnlyOneElementForUsers() {
        if (selectedFile.getFileAccess().getUsers().size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOnlyOneElementForStudents() {
        if (allStudents.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOnlyOneElementForLecturers() {
        if (allLecturers.size() == 1) {
            return true;
        } else {
            return false;
        }
    }
}

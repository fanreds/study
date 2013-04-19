package pl.edu.pk.view;

import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.domain.*;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return ((Group) value).getName();
        }
    };
    private List<Specialization> allSpecializations;
    private List<Group> allGroups;
    private List<Group> allGroupsForMySpec;
    private List<Student> allStudents;
    private Specialization selectedSpecialization;
    private Group selectedGroup;
    private List<Student> selectedStudents;
    private List<Group> selectedGroups;
    private List<Specialization> selectedSpecializations;
    private Map<File, Boolean> shareAll;
    private boolean selectedShareAll;
    @Inject
    private UserDAO userDAO;
    @Inject
    private CurrentUserManager currentUserManager;
    private boolean renderUser = false;
    private boolean renderGroup = false;
    private boolean renderSpecialization = false;
    private boolean renderAll = false;
    private File selectedFile;

    public Converter getGroupConverter() {
        return groupConverter;
    }

    public Converter getStudentConverter() {
        return studentConverter;
    }

    public Specialization getSelectedSpecialization() {
        return selectedSpecialization;
    }
    public List<File> getUserFiles() {

        return currentUserManager.getUser().getFiles();
    }

    public void setSelectedSpecialization(Specialization selectedSpecialization) {
        this.selectedSpecialization = selectedSpecialization;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Group selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public Map<File, Boolean> getShareAll() {
        if (shareAll == null) {
            shareAll = new HashMap<File, Boolean>();
            for (File file:getUserFiles()){
                shareAll.put(file,false);
            }
        }
        return shareAll;
    }

    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public List<Group> getSelectedGroups() {
        return selectedGroups;
    }

    public void setSelectedGroups(List<Group> selectedGroups) {
        this.selectedGroups = selectedGroups;
    }

    public List<Specialization> getSelectedSpecializations() {
        return selectedSpecializations;
    }

    public void setSelectedSpecializations(List<Specialization> selectedSpecializations) {
        this.selectedSpecializations = selectedSpecializations;
    }

    public boolean isSelectedShareAll() {
        return selectedShareAll;
    }

    public void setSelectedShareAll(boolean selectedShareAll) {
        this.selectedShareAll = selectedShareAll;
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

    public List<Student> getAllStudents() {
        if (allStudents == null && selectedGroup == null) {
            allStudents = new ArrayList<Student>();
        } else {
            assert allStudents != null;
            if (allStudents.isEmpty()) {
                allStudents = userDAO.getAllStudents(selectedGroup);
                //            allStudents = selectedGroup.getStudents();
            } else if (!allStudents.get(0).getGroup().equals(selectedGroup)) {
                allStudents = userDAO.getAllStudents(selectedGroup);

            }
            if (currentUserManager.getUser() instanceof Student && ((Student) currentUserManager.getUser()).getGroup().equals(selectedGroup)) {
                allStudents.remove(currentUserManager.getUser());
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
        selectedStudents = null;
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
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public String save() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

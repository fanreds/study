package pl.edu.pk.view;

import org.jboss.seam.international.status.Messages;
import pl.edu.pk.DAO.MessageDAO;
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
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kleks
 * Date: 3/11/13
 * Time: 3:30 PM
 */
@Named
@ViewScoped
public class MessageView implements Serializable {
    Converter studentConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            for (Student student : allStudents) {
                if (value.equals(student.getName() + " " + student.getForename())) {
                    return student;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return "Students:";
            }
            return ((Student) value).getName() + " " + ((Student) value).getForename();
        }
    };
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
                return "Choose";
            }
            return ((Specialization) value).getName();
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
            if (value == null) {
                return "Lecturers:";
            }
            return ((Lecturer) value).getName();
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
                return "Choose";
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
    OPTION selectedOption;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private CurrentUserManager currentUserManager;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private MessageDAO messageDAO;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private UserDAO userDAO;
    @SuppressWarnings("UnusedDeclaration")
    @Inject
    private Messages messages;
    private Message message;
    private boolean renderStudent = false;
    private boolean renderLecturer = false;
    private boolean renderGroup = false;
    private boolean renderGroupForStudent = false;
    private boolean renderSpecialization = false;
    private boolean renderAll = false;
    private List<Specialization> allSpecializations;
    private List<Group> allGroups;
    private List<Group> allGroupsForMySpec;
    private List<Student> allStudents;
    private List<Lecturer> allLecturers;
    private Specialization selectedSpecialization;
    private Group selectedGroup;

    public boolean isRenderLecturer() {
        return renderLecturer;
    }

    public Converter getGroupConverterForMySpec() {
        return groupConverterForMySpec;
    }

    public Converter getGroupConverter() {
        return groupConverter;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Group selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public Specialization getSelectedSpecialization() {

        return selectedSpecialization;
    }

    public void setSelectedSpecialization(Specialization selectedSpecialization) {
        this.selectedSpecialization = selectedSpecialization;
    }

    public List<Group> getAllGroupsForMySpec() {
        if (allGroupsForMySpec == null) {
            allGroupsForMySpec = userDAO.getAllGroups(((Student) currentUserManager.getUser()).getGroup().getSpecialization());
        }
        return allGroupsForMySpec;
    }

    public Message getMessage() {
        if (message == null) {
            message = new Message();
        }
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }


    @SuppressWarnings("SuspiciousMethodCalls")
    public List<Lecturer> getAllLecturers() {
        allLecturers = userDAO.getAllLecturers();

        if (currentUserManager.getUser() instanceof Lecturer) {
            allLecturers.remove(currentUserManager.getUser());
        }
        return allLecturers;
    }

    public void setAllLecturers(List<Lecturer> allLecturers) {
        this.allLecturers = allLecturers;
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

    @SuppressWarnings("SuspiciousMethodCalls")
    public List<Student> getAllStudents() {
        if (allStudents == null && selectedGroup == null) {
            allStudents = new ArrayList<Student>();
        } else {
            allStudents = userDAO.getAllStudents(selectedGroup);

            if (currentUserManager.getUser() instanceof Student && ((Student) currentUserManager.getUser()).getGroup().equals(selectedGroup)) {
                allStudents.remove(currentUserManager.getUser());
            }
        }
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public void clear() {
        allStudents = null;
        selectedGroup = null;
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

    public Converter getStudentConverter() {
        return studentConverter;
    }

    public Converter getSpecializationConverter() {
        return specializationConverter;
    }

    public Converter getLecturerConverter() {
        return lecturerConverter;
    }

    public void save() {
        if (!getMessage().isForAll() && getMessage().getSpecialization()==null && getMessage().getGroup()==null && getMessage().getRecipient()==null){
            messages.error(BundleKeys.MESSAGE_RECIPIENT_NOT_SELECTED);
            return;
        }
        getMessage().setSender(currentUserManager.getUser());
        getMessage().setSentDate(new Date());
        messageDAO.persist(getMessage());
        setMessage(new Message());
        messages.info(BundleKeys.MESSAGE_SENDED);
    }

    public boolean isRenderStudent() {
        return renderStudent;
    }

    public void setRenderStudent() {
        renderAll = false;
        renderGroup = false;
        renderGroupForStudent = false;
        renderSpecialization = false;
        renderLecturer = false;
        renderStudent = true;
        selectedGroup = null;
        selectedSpecialization = null;
    }

    public void setRenderLecturer() {
        renderAll = false;
        renderGroup = false;
        renderGroupForStudent = false;
        renderSpecialization = false;
        renderStudent = false;
        renderLecturer = true;
        selectedGroup = null;
        selectedSpecialization = null;
    }

    public boolean isRenderGroupForStudent() {
        return renderGroupForStudent;
    }

    public void setRenderGroupForStudent() {
        renderAll = false;
        renderStudent = false;
        renderGroup = false;
        renderSpecialization = false;
        renderLecturer = false;
        renderGroupForStudent = true;
    }

    public boolean isRenderGroup() {
        return renderGroup;
    }

    public void setRenderGroup() {
        renderAll = false;
        renderStudent = false;
        renderSpecialization = false;
        renderGroupForStudent = false;
        renderLecturer = false;
        renderGroup = true;
    }

    public boolean isRenderSpecialization() {
        return renderSpecialization;
    }

    public void setRenderSpecialization() {
        renderAll = false;
        renderStudent = false;
        renderGroup = false;
        renderGroupForStudent = false;
        renderLecturer = false;
        renderSpecialization = true;
    }

    public boolean isRenderAll() {
        return renderAll;
    }

    public void setRenderAll() {
        renderStudent = false;
        renderGroup = false;
        renderGroupForStudent = false;
        renderSpecialization = false;
        renderLecturer = false;
        renderAll = true;
    }

    public void renderer() {
        if (selectedOption == OPTION.ALL) {
            setRenderAll();
        } else if (selectedOption == OPTION.SPECIALIZATION) {
            setRenderSpecialization();
        } else if (selectedOption == OPTION.GROUP) {
            if (currentUserManager.getUser() instanceof Student) {
                setRenderGroupForStudent();
            } else if (currentUserManager.getUser() instanceof Lecturer) {
                setRenderGroup();
            }
        } else if (selectedOption == OPTION.STUDENT) {
            setRenderStudent();
        } else if (selectedOption == OPTION.LECTURER) {
            setRenderLecturer();
        }
    }

    public OPTION getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(OPTION selectedOption) {
        this.selectedOption = selectedOption;
    }

    public static enum OPTION {
        ALL,
        SPECIALIZATION,
        GROUP,
        STUDENT,
        LECTURER
    }
}

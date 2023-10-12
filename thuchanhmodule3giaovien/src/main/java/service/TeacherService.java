package service;

import dao.TeacherDAO;
import model.Teacher;

import java.util.List;
import java.util.Optional;

public class TeacherService {
    private List<Teacher> teachers;
    private static final TeacherService teacherService = new TeacherService();
    private final TeacherDAO teacherDAO = new TeacherDAO();
    public List<Teacher> findAll() {
        return teacherDAO.findAll();
    }

    public void create(Teacher teacher) {
        teacherDAO.insertTeacher(teacher);
    }

    public void update(Teacher teacher, int id) {
        teacherDAO.updateTeacher(teacher, id);
    }

    public Teacher findById(int id) {
        return teacherDAO.findById(id);
    }

    public void delete(int id) {
        teacherDAO.deleteById(id);
    }
}

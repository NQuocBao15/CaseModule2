package controller;

import model.Degree;
import model.Teacher;
import model.enums.EGender;
import service.DegreeService;
import service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

@WebServlet(name = "teacherController", urlPatterns = "/teachers")
public class TeacherController extends HttpServlet {

    private TeacherService teacherService;
    private DegreeService degreeService;

    @Override
    public void init() {
        teacherService = new TeacherService();
        degreeService = new DegreeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (Objects.equals(action, "create")) {
            showCreate(req, resp);
            return;
        }
        if (Objects.equals(action, "edit")) {
            showEdit(req, resp);
            return;
        }
        if (Objects.equals(action, "delete")) {
            delete(req, resp);
            return;
        }
        showList(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        teacherService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/teachers");
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teacher", teacherService.findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("genders", EGender.values());
        req.setAttribute("degrees", degreeService.getDegrees());
        req.getRequestDispatcher("teacher/edit.jsp").forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teacher", new Teacher());
        req.setAttribute("genders", EGender.values());
        req.setAttribute("degrees", degreeService.getDegrees());
        req.getRequestDispatcher("teacher/create.jsp").forward(req,resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teachers", teacherService.findAll());
        req.setAttribute("genders", EGender.values());
        req.setAttribute("degrees", degreeService.getDegrees());
        req.getRequestDispatcher("teacher/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (Objects.equals(action, "create")) {
            create(req, resp);
            return;
        }
        if (Objects.equals(action, "edit")) {
            edit(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        teacherService.update(getTeacherByRequest(req), Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/teachers");
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        teacherService.create(getTeacherByRequest(req));
        resp.sendRedirect("/teachers");
    }


    private Teacher getTeacherByRequest(HttpServletRequest req) {
        String name = req.getParameter("name");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String hobbie = req.getParameter("hobbie");
        EGender gender = EGender.valueOf(req.getParameter("gender"));

        int degreeId = Integer.parseInt(req.getParameter("degree"));
        Degree degree = new Degree(degreeId);

        return new Teacher(name,dob,hobbie,gender,degree);
    }
}

package com.example.crudtest.controller;

import com.example.crudtest.model.Contact;
import com.example.crudtest.model.Group;
import com.example.crudtest.service.ContactService;
import com.example.crudtest.service.GroupService;
import com.example.crudtest.service.dto.PageableRequest;
import com.example.crudtest.util.AppConstant;
import com.example.crudtest.util.AppUtil;
import com.example.crudtest.util.RunnableCustom;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
@WebServlet(name = "ContactController", urlPatterns = "/contacts")
public class ContactController extends HttpServlet {
    private final String PAGE = "contacts";
    private Map<String, RunnableCustom> validators;
    private final Map<String, String> errors = new HashMap<>();

    @Override
    public void init() {
        validators = new HashMap<>();
//        validators.put("phone", new RunnableWithRegex("[0-9]{10}", "phone", errors));
//        validators.put("name", new RunnableWithRegex("^[A-Za-z ]{6,20}","name",errors));
//        validators.put("price", new RunnableWithRegex("^[1-9][0-9]*$","price",errors));
//        validators.put("quantity", new RunnableWithRegex("^[1-9][0-9]*$","quantity",errors));
//        validators.put("description", new RunnableWithRegex("^.{1,100}$","description",errors));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(AppConstant.ACTION);

        if (Objects.equals(action, AppConstant.INFO)) {
            showInfo(req,resp);
            return;
        }
        if(Objects.equals(action, AppConstant.EDIT)){
            showEdit(req,resp);
            return;
        }
        if (Objects.equals(action, AppConstant.CREATE)) {
            showCreate(req, resp);
            return;
        }
        if (Objects.equals(action, AppConstant.DELETE)) {
            delete(req, resp);
            return;
        }
        showList(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors.clear();
        String action = req.getParameter(AppConstant.ACTION);
        log(String.valueOf(req));
        if (Objects.equals(action, AppConstant.CREATE)) {
            create(req, resp);
            return;
        }
        if (Objects.equals(action, AppConstant.EDIT)) {
            edit(req, resp);
            return;
        }
        showList(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PageableRequest request = new PageableRequest(
                req.getParameter("search")
        );

        req.setAttribute("pageable", request);
        req.setAttribute("contacts", ContactService.getContactService().getContacts(request));
        req.setAttribute("contactsJSON", new ObjectMapper().writeValueAsString(ContactService.getContactService().getContacts(request)));
        req.setAttribute("groupsJSON", new ObjectMapper().writeValueAsString(GroupService.getGroup()));
        req.getRequestDispatcher(PAGE + AppConstant.HOME_PAGE).forward(req,resp);
    }

    private void showInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        if(checkIdNotFound(resp, id)) return;
        req.setAttribute("contact", ContactService.getContactService().findById(id));
        req.setAttribute("contactJSON", new ObjectMapper().writeValueAsString(ContactService.getContactService().findById(id)));
        req.setAttribute("groupJSON", new ObjectMapper().writeValueAsString(GroupService.getGroup()));
        req.getRequestDispatcher(PAGE + AppConstant.INFO_PAGE).forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("contactJSON", new ObjectMapper().writeValueAsString(new Contact()));
        req.setAttribute("groupJSON", new ObjectMapper().writeValueAsString(GroupService.getGroup()));
        req.setAttribute("groups", GroupService.getGroup());
        req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE).forward(req,resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if(checkIdNotFound(resp, id)) return;
        req.setAttribute("contact", ContactService.getContactService().findById(id));
        req.setAttribute("contactJSON", new ObjectMapper().writeValueAsString(ContactService.getContactService().findById(id)));
        req.setAttribute("groups", GroupService.getGroup());
        req.getRequestDispatcher(PAGE + AppConstant.EDIT_PAGE).forward(req,resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Contact contact = getValidContact(req,resp);
        String pathServerImage = getServletContext().getRealPath("/") + "avatars";
        String pathProjectImage  = "D:\\CodeGym\\choderac\\CRUD-Test\\src\\main\\webapp\\avatars";

        String dbImageUrl = null;

        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);

            if(!fileName.isEmpty()){
                fileName = new File(fileName).getName();

                if(part.getContentType().equals("image/jpeg")){
                    part.write(pathProjectImage + File.separator + fileName);
                    dbImageUrl = File.separator + fileName;
                    dbImageUrl = dbImageUrl.replace("\\","/");
                    part.write(pathServerImage + File.separator + fileName);
                }
            }
        }
        if (dbImageUrl == null) {
            req.setAttribute("errorImage", "File ảnh không được để trống!");
        } else {
            contact.setAvatar(dbImageUrl);
        }
        if(errors.isEmpty()){
            ContactService.getContactService().create(contact);
            resp.sendRedirect("/contacts");
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Contact contact = getValidContact(req,resp);
        if(errors.isEmpty()){
            ContactService.getContactService().edit(contact);
            resp.sendRedirect("/contacts");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if(checkIdNotFound(resp, id)) return;
        ContactService.getContactService().delete(id);
        resp.sendRedirect(PAGE);
    }

    private Contact getValidContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Contact contact = (Contact) AppUtil.getObjectWithValidation(req, Contact.class,  validators);
        assert contact != null;
        contact.setGroups(new Group(Integer.parseInt(req.getParameter("group_id"))));

        if(!errors.isEmpty()){
            req.setAttribute("contactJSON", contact);
            req.setAttribute("groups", GroupService.getGroup());
            req.setAttribute("message","Something was wrong");
            req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE).forward(req,resp);
        }
        return contact;
    }

    private boolean checkIdNotFound(HttpServletResponse resp, int id) throws IOException{
        if(!ContactService.getContactService().existById(id)){
            resp.sendRedirect(PAGE + "?message=Id not found");
            return true;
        }
        return false;
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}

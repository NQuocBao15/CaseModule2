package com.example.crudtest.service;

import com.example.crudtest.dao.ContactDAO;
import com.example.crudtest.model.Contact;
import com.example.crudtest.service.dto.PageableRequest;
import com.example.crudtest.util.AppConstant;

import java.util.List;

public class ContactService {
    private static List<Contact> contacts;
    private static final ContactService contactService = new ContactService();
    private ContactDAO contactDAO = new ContactDAO();

    private ContactService() {

    }
    public static ContactService getContactService() {
        return contactService;
    }

    public Contact findById(int id) {
        return contactDAO.findById(id).orElseThrow(() -> new RuntimeException(String.format(AppConstant.ID_NOT_FOUND, "contact")));
    }

    public void create(Contact contact) {
        contactDAO.insertContact(contact);
    }

    public void edit(Contact contact) {
        contactDAO.updateContact(contact);
    }

    public List<Contact> getContacts(PageableRequest request) {
        return contactDAO.findAll(request);
    }


    public boolean existById(int id) {
        return contactDAO.findById(id).orElse(null) != null;
    }

    public void delete(int id) {
        contactDAO.deleteById(id);
    }
    public List<Contact> getContacts() {
        return contactDAO.getContact();
    }
}

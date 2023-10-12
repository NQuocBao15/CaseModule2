package service;

import dao.DegreeDAO;
import model.Degree;

import java.util.List;

public class DegreeService {
    public List<Degree> getDegrees() {
        return new DegreeDAO().findAll();
    }
    public Degree findById(long id) {
        return new DegreeDAO().findById(id);
    }
}

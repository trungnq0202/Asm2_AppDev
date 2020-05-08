package service;

import entity.Staff;

import java.util.List;

public interface StaffService {
    public List<Staff> findAll();
    public Staff findById(int id);
    public List<Staff> findByName(String name);
    public Staff save(Staff staff);
    public Staff update(Staff staff);
    public int delete(int id);
}

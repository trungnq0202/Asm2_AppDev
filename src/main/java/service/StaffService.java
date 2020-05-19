package service;

import model.Staff;
import model.PaginatedList;

public interface StaffService {
    public PaginatedList<Staff> findAll(int pageIndex, int pageSize);
    public Staff findById(int id);
    public PaginatedList<Staff> findByName(String name, int pageIndex, int pageSize);
    public PaginatedList<Staff> findByAddress(String address, int pageIndex, int pageSize);
    public PaginatedList<Staff> findByPhone(String phone, int pageIndex, int pageSize);
    public Staff save(Staff staff);
    public Staff update(Staff staff);
    public int delete(int id);
}

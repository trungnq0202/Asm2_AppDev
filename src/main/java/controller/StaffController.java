package controller;

import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StaffService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(path = "staffs/all", method = RequestMethod.GET)
    public List<Staff> getAllStaffs(){
        return staffService.findAll();
    }

    @RequestMapping(path = "staffs/{id}", method = RequestMethod.GET)
    public Staff getStaffById(@PathVariable int id){
        return staffService.findById(id);
    }

    @RequestMapping(path = "staffs", method = RequestMethod.GET)
    public List<Staff> getStaffByName(@RequestParam String name){
        return staffService.findByName(name);
    }

    @RequestMapping(path = "staffs", method = RequestMethod.POST)
    public Staff addStaff(@RequestBody Staff staff){
        return staffService.save(staff);
    }

    @RequestMapping(path = "staffs", method = RequestMethod.PUT)
    public Staff updateStaff(@RequestBody Staff staff){
        return staffService.update(staff);
    }

    @RequestMapping(path = "staffs/{id}", method = RequestMethod.DELETE)
    public int deleteStaff(@PathVariable int id){
        return staffService.delete(id);
    }

}

package controller;

import entity.Staff;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StaffService;

@RestController
@RequestMapping(path = "/")
public class StaffController {

    @Autowired
    private StaffService staffService;

    //Get paginated list of all Staffs
    @RequestMapping(path = "staffs/all", method = RequestMethod.GET)
    public PaginatedList<Staff> getAllStaffs(
            @RequestParam int pageIndex, @RequestParam int pageSize
    ){
        System.out.println(staffService);
        return staffService.findAll(pageIndex, pageSize);
    }

//    @RequestMapping(path = "staffs/{id}", method = RequestMethod.GET)
//    public Staff getStaffById(@PathVariable int id){
//        return staffService.findById(id);
//    }

    @RequestMapping(path = "staffs/by_name", method = RequestMethod.GET)
    public PaginatedList<Staff> getStaffByName(@RequestParam String name,
        @RequestParam int pageIndex, @RequestParam int pageSize
    ){
        return staffService.findByName(name, pageIndex, pageSize);
    }

    @RequestMapping(path = "staffs/by_phone", method = RequestMethod.GET)
    public PaginatedList<Staff> getStaffByPhone(@RequestParam String phone
            , @RequestParam int pageIndex, @RequestParam int pageSize){
        return staffService.findByPhone(phone, pageIndex, pageSize);
    }

    @RequestMapping(path = "staffs/by_address", method = RequestMethod.GET)
    public PaginatedList<Staff> getStaffByAddress(@RequestParam String address
            , @RequestParam int pageIndex, @RequestParam int pageSize){
        return staffService.findByAddress(address, pageIndex, pageSize);
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

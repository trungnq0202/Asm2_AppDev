package controller;

import entity.Provider;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProviderService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping(path = "providers/all", method = RequestMethod.GET)
    public PaginatedList<Provider> getAllProviders(@RequestParam int pageIndex, @RequestParam int pageSize){
        return providerService.findAll(pageIndex, pageSize);
    }

//    @RequestMapping(path = "providers/{id}", method = RequestMethod.GET)
//    public Provider getProviderById(@PathVariable int id){
//        return providerService.findById(id);
//    }

    @RequestMapping(path = "providers/by_name", method = RequestMethod.GET)
    public PaginatedList<Provider> getProvidersByName(@RequestParam String name, @RequestParam int pageIndex, @RequestParam int pageSize){
        return providerService.findByName(name, pageIndex, pageSize);
    }

    @RequestMapping(path = "providers/by_phone", method = RequestMethod.GET)
    public PaginatedList<Provider> getProvidersByPhone(@RequestParam String phone, @RequestParam int pageIndex, @RequestParam int pageSize){
        return providerService.findByPhone(phone, pageIndex, pageSize);
    }

    @RequestMapping(path = "providers/by_address", method = RequestMethod.GET)
    public PaginatedList<Provider> getProvidersByAddress(@RequestParam String address, @RequestParam int pageIndex, @RequestParam int pageSize){
        return providerService.findByAddress(address, pageIndex, pageSize);
    }

    @RequestMapping(path = "providers", method = RequestMethod.POST)
    public Provider addProvider(@RequestBody Provider provider){
        return providerService.save(provider);
    }

    @RequestMapping(path = "providers", method = RequestMethod.PUT)
    public Provider updateProvider(@RequestBody Provider provider){
        return providerService.update(provider);
    }

    @RequestMapping(path = "providers/{id}", method = RequestMethod.DELETE)
    public int deleteProvider(@PathVariable int id){
        return providerService.delete(id);
    }

}

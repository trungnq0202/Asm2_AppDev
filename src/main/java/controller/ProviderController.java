package controller;

import entity.Provider;
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
    public List<Provider> getAllProviders(){
        return providerService.findAll();
    }

    @RequestMapping(path = "providers/{id}", method = RequestMethod.GET)
    public Provider getProviderById(@PathVariable int id){
        return providerService.findById(id);
    }

    @RequestMapping(path = "providers", method = RequestMethod.GET)
    public List<Provider> getProvidersByName(@RequestParam String name){
        return providerService.findByName(name);
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

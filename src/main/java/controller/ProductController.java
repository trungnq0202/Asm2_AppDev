package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "products/all", method = RequestMethod.GET)
    public List<Product> getAllStaffs(){
        return productService.findAll();
    }

    @RequestMapping(path = "products/{id}", method = RequestMethod.GET)
    public Product getStaffById(@PathVariable int id){
        return productService.findById(id);
    }

    @RequestMapping(path = "products", method = RequestMethod.GET)
    public List<Product> getStaffByName(@RequestParam String name){
        return productService.findByName(name);
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public Product addStaff(@RequestBody Product product){
        return productService.save(product);
    }

    @RequestMapping(path = "products", method = RequestMethod.PUT)
    public Product updateStaff(@RequestBody Product product){
        return productService.update(product);
    }

    @RequestMapping(path = "products/{id}", method = RequestMethod.DELETE)
    public int deleteStaff(@PathVariable int id){
        return productService.delete(id);
    }

}

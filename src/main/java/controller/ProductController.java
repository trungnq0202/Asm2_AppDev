package controller;

import model.Product;
import model.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

@RestController
@RequestMapping(path = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "products/all", method = RequestMethod.GET)
    public PaginatedList<Product> getAllProducts( @RequestParam int pageIndex, @RequestParam int pageSize){
        return productService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "products/by_name", method = RequestMethod.GET)
    public PaginatedList<Product> getProductByName(@RequestParam String name, @RequestParam int pageIndex, @RequestParam int pageSize){
        return productService.findByName(name, pageIndex, pageSize);
    }

    @RequestMapping(path = "products/by_brand", method = RequestMethod.GET)
    public PaginatedList<Product> getProductByBrand(@RequestParam String brand, @RequestParam int pageIndex, @RequestParam int pageSize){
        return productService.findByBrand(brand, pageIndex, pageSize);
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @RequestMapping(path = "products", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @RequestMapping(path = "products/{id}", method = RequestMethod.DELETE)
    public int deleteProduct(@PathVariable int id){
        return productService.delete(id);
    }

}

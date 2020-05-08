package controller;

import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "categories/all", method = RequestMethod.GET)
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @RequestMapping(path = "categories/{id}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable int id){
        return categoryService.findById(id);
    }

    @RequestMapping(path = "categories", method = RequestMethod.GET)
    public List<Category> getCategoriesByName(@RequestParam String name){
        return categoryService.findByName(name);
    }

    @RequestMapping(path = "categories", method = RequestMethod.POST)
    public Category addCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @RequestMapping(path = "categories", method = RequestMethod.PUT)
    public Category updateCategory(@RequestBody Category category){
        return categoryService.update(category);
    }

    @RequestMapping(path = "categories/{id}", method = RequestMethod.DELETE)
    public int deleteCategory(@PathVariable int id){
        return categoryService.delete(id);
    }
}

package controller;

import entity.Category;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

@RestController
@RequestMapping(path = "/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "categories/all", method = RequestMethod.GET)
    public PaginatedList<Category> getAllCategories(
            @RequestParam int pageIndex, @RequestParam int pageSize
    ){
        return categoryService.findAll(pageIndex, pageSize);
    }

//    @RequestMapping(path = "categories/{id}", method = RequestMethod.GET)
//    public Category getCategoryById(@PathVariable int id){
//        return categoryService.findById(id);
//    }

    @RequestMapping(path = "categories/by_name", method = RequestMethod.GET)
    public PaginatedList<Category> getCategoriesByName(@RequestParam String name
        , @RequestParam int pageIndex, @RequestParam int pageSize){
        return categoryService.findByName(name, pageIndex, pageSize);
    }

    @RequestMapping(path = "categories", method = RequestMethod.POST)
    public Category addCategory(@RequestBody Category category){
        System.out.println("cate control" + category.getId());
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

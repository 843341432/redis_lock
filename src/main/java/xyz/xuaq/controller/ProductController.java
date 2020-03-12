package xyz.xuaq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.xuaq.service.ProductService;

/**
 * @author x
 */
@Controller
public class ProductController {
    @Autowired
    ProductService service;

    @RequestMapping("/update")
    public String update(){
        return service.update();
    }
}

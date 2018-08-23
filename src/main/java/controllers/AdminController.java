package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class AdminController {
    public AdminController() {
        setupEndpints();
    }

    private static void setupEndpints(){
        get("/admin", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}

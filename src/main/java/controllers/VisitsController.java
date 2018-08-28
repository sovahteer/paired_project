package controllers;
import db.DBHelper;
import models.visitors.Visit;
import models.visitors.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class VisitsController {
    public VisitsController(){ setupEndpoints();}

    private static void setupEndpoints() {
        get("/visits", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/admin/visits/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



    }
}

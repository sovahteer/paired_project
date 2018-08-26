package controllers;
import db.DBHelper;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;;
public class VisitorsController {

    public VisitorsController() {
        setupEndpoints();
    }

    public static void setupEndpoints(){
        get("/visitors", (req, res) -> {
            List<Visitor> visitors = DBHelper.getAll(Visitor.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/visitors/index.vtl");
            model.put("visitors", "visitors");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/visitors/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/visitors/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}

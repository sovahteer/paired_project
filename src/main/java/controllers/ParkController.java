package controllers;
import db.DBHelper;
import models.visitors.Visit;
import models.visitors.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
public class ParkController {

    public ParkController() {
        startEndpoints();
    }

    private static void startEndpoints() {


        get("/park", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

//            model.put("paddocks", paddocks);
            model.put("template", "templates/park/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

//        get("/park/:id/paddocks/:paddock_id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("template", "templates/park/index.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());

    }
}
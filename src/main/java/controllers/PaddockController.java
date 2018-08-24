package controllers;

import db.DBHelper;
import models.enums.DinosaurType;
import models.paddocks.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class PaddockController {
    public PaddockController(){ setupEndpoints();}

    private static void setupEndpoints() {
        get("/paddocks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("template", "templates/paddocks/inex.vtl");
            model.put("paddocks", paddocks);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/paddocks/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<DinosaurType> dinosaurTypes = DBHelper.getAll(DinosaurType.class);
            model.put("DinoType", dinosaurTypes);
            model.put("templates", "template/layout.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }



}

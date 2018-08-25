package controllers;

import db.DBHelper;
import models.dinosaurs.Dinosaur;
import models.enums.DietryType;
import models.enums.DinosaurType;
import models.paddocks.Paddock;
import models.parks.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class PaddockController {
    public PaddockController(){ setupEndpoints();}

    private static void setupEndpoints() {
        get("/paddocks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("paddocks", paddocks);
            model.put("template", "templates/paddocks/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/paddocks/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park park = parks.get(0);
            DietryType[] dietryTypes = DietryType.values();
            model.put("park", park);
            model.put("dietryTypes", dietryTypes);
            model.put("template", "templates/paddocks/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/paddocks/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock= DBHelper.find(intId, Paddock.class);
            DietryType[] dietryTypes = DietryType.values();

            Map<String, Object> model = new HashMap<>();
            model.put("dietryTypes", dietryTypes);
            model.put("template", "templates/paddocks/edit.vtl");
            model.put("paddock", paddock);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/paddocks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int parkId = Integer.parseInt(req.queryParams("park"));
            Park park = DBHelper.find(parkId, Park.class);
            DietryType dietryType = DietryType.valueOf(req.queryParams("dietryType"));
            String name = req.queryParams("name");
            Paddock newPaddock = new Paddock(name, park, dietryType);
            DBHelper.save(newPaddock);

            res.redirect("/paddocks");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }



}

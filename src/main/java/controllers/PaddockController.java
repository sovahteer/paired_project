package controllers;

import db.DBHelper;
import models.enums.DietaryType;
import models.paddocks.Paddock;
import models.parks.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
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
            DietaryType[] dietaryTypes = DietaryType.values();
            model.put("park", park);
            model.put("dietryTypes", dietaryTypes);
            model.put("template", "templates/paddocks/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/paddocks/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(intId, Paddock.class);

            Map<String, Object> model = new HashMap<>();

            model.put("paddock", paddock);
            model.put("template", "templates/paddocks/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/paddocks/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock= DBHelper.find(intId, Paddock.class);
            List<Park> parks = DBHelper.getAll(Park.class);
            Park park = parks.get(0);
            DietaryType[] dietryTypes = DietaryType.values();

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
            DietaryType dietryType = DietaryType.valueOf(req.queryParams("dietryType"));
            String name = req.queryParams("name");
            Paddock newPaddock = new Paddock(name, park, dietryType);
            DBHelper.save(newPaddock);

            res.redirect("/paddocks");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/paddocks/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(intId, Paddock.class);
            DietaryType dietryType = DietaryType.valueOf(req.queryParams("dietryType"));
            String name = req.queryParams("name");

            paddock.setName(name);
            paddock.setDietaryType(dietryType);


            DBHelper.update(paddock);
            res.redirect("/paddocks");
            return null;
        }, new VelocityTemplateEngine());

        post ("/paddocks/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Paddock paddockToDelete = DBHelper.find(id, Paddock.class);
            DBHelper.delete(paddockToDelete);
            res.redirect("/paddocks");
            return null;
        }, new VelocityTemplateEngine());
    }



}

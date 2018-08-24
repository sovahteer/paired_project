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

        post("/paddocks", (req, res) -> {
            int parkId = Integer.parseInt(req.queryParams("park"));
            DietryType dietryType = DietryType.valueOf(req.queryParams("diet_type"));
            Park park = DBHelper.find(parkId, Park.class);
            String name = req.queryParams("name");
            Paddock paddock = new Paddock(name, park, dietryType);
            DBHelper.save(paddock);
            res.redirect("/paddocks");
            return null;
        }, new VelocityTemplateEngine());
    }



}

package controllers;

import db.DBHelper;
import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import models.enums.FoodType;
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
import static spark.Spark.redirect;

public class DinosaursController {

    public DinosaursController() {
        setupEndpoint();
    }

    private static void setupEndpoint(){
        get("/dinosaurs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Dinosaur> dinosaurs = DBHelper.getAll(Dinosaur.class);
            model.put("template", "templates/dinosaurs/index.vtl");
            model.put("dinosaurs", dinosaurs);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/dinosaurs/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            DinosaurType[] dinosaurTypes = DinosaurType.values();
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            Park park = new Park();
            model.put("dinosaurTypes", dinosaurTypes);
            model.put("paddocks", paddocks);
            model.put("template", "templates/dinosaurs/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/dinosaurs/:id/feed", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Dinosaur dinosaur = DBHelper.find(intId, Dinosaur.class);
            FoodType[] foodTypes = FoodType.values();
            model.put("dinosaur", dinosaur);
            model.put("foodTypes", foodTypes);
            model.put("template", "templates/dinosaurs/feed.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/dinosaurs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            DinosaurType species = DinosaurType.valueOf(req.queryParams("species"));
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            Dinosaur newDino = new Dinosaur(species);
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            newDino.addPaddockToDinosaur(paddock);
            DBHelper.update(paddock);
            DBHelper.save(newDino);

            res.redirect("/dinosaurs");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/dinosaur/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int dinosaurId = Integer.parseInt(req.queryParams("dinosaur"));
            Dinosaur dinosaur = DBHelper.find(dinosaurId, Dinosaur.class);
            FoodType foodType = FoodType.valueOf(req.queryParams("foodType"));
            dinosaur.eat(foodType);
            res.redirect("/dinosaurs/:id");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



    }
}

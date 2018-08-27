package controllers;

import db.DBDinosaur;
import db.DBHelper;
import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import models.enums.FoodType;
import models.paddocks.Paddock;
import models.parks.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;
import static spark.Spark.post;


public class DinosaursController {

    public DinosaursController() {
        setupEndpoint();
    }

    private static void setupEndpoint(){

        get("/dinosaurs/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int dinosaurId = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaurToEdit = DBHelper.find(dinosaurId, Dinosaur.class);
//            DinosaurType[] dinosaurTypes = DinosaurType.values();
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
//            model.put("dinosaurTypes", dinosaurTypes);
            model.put("paddocks", paddocks);
            model.put("dinosaurToEdit", dinosaurToEdit);
            model.put("template", "templates/dinosaurs/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

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

        get("/dinosaurs/invalid_paddock", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/dinosaurs/invalid_paddock.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/dinosaurs/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int dinosaurId = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaur = DBHelper.find(dinosaurId, Dinosaur.class);
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);

            //assign paddock.dinoType if not Herbivore
            if (paddock.checkIfDinosaurTypeAssigned()) {
                if(dinosaur.checkIfCompatible(paddock)){
                    DBHelper.update(dinosaur);
                    DBDinosaur.addPaddockToDinosaur(dinosaur, paddock);
                    DBHelper.update(paddock);

                    res.redirect("/dinosaurs");
                } else {
                    model.put("dinosaur", dinosaur);
                    model.put("template", "templates/dinosaurs/invalid_paddock.vtl");
                }
            } else {
                if (dinosaur.checkIfCompatible(paddock)) {
                    DBDinosaur.addPaddockToDinosaur(dinosaur, paddock);
                    DBHelper.update(paddock);
                    DBHelper.update(dinosaur);
                    res.redirect("/dinosaurs");
                } else {
                    model.put("dinosaur", dinosaur);
                    model.put("template", "templates/dinosaurs/invalid_paddock.vtl");
                }

            }


            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/dinosaurs/:id/delete", (req, res) -> {
            int dinosaurId = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaur = DBHelper.find(dinosaurId, Dinosaur.class);
            DBHelper.delete(dinosaur);
            res.redirect("/dinosaurs");
            return null;
        }, new VelocityTemplateEngine());

        post("/dinosaurs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            DinosaurType species = DinosaurType.valueOf(req.queryParams("species"));
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            Dinosaur newDino = new Dinosaur(species);
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            newDino.addPaddockToDinosaur(paddock);
            //paddock not assigned if not compatible types, update and save only if assigned
            if (newDino.checkIfPaddockAssigned() == true) {
                DBHelper.update(paddock);
                DBHelper.save(newDino);
                res.redirect("/dinosaurs");
            } else {
                res.redirect("/dinosaurs/invalid_paddock");
            }
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/dinosaurs/:id/feed", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int dinosaurId = Integer.parseInt(req.params(":id"));
//            int paddockId = Integer.parseInt(req.queryParams("paddock"));
//            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            Dinosaur dinosaur = DBHelper.find(dinosaurId, Dinosaur.class);
            int paddockId = dinosaur.getPaddock().getId();
            FoodType foodType = FoodType.valueOf(req.queryParams("foodType"));
            dinosaur.eat(foodType);
            DBHelper.update(dinosaur);
            res.redirect("/paddocks/"+paddockId);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}

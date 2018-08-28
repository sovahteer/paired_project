package controllers;

import db.DBDinosaur;
import db.DBHelper;
import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.enums.FoodType;
import models.paddocks.Paddock;
import models.visitors.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static spark.Spark.get;
import static spark.Spark.post;


public class AdminController {
    public AdminController() {
        setupEndpoints();
    }

    private static void setupEndpoints(){
        get("/admin", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/:id/system_check", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            paddock.rampageCheck();
            int numberOfVisitors = DBHelper.getAll(Visitor.class).size();
            Random random = new Random();
            int randomNumber = random.nextInt(numberOfVisitors + 1);
            model.put("paddock", paddock);
            model.put("randomNumber", randomNumber);
            model.put("template", "templates/admin/system_check.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/admin/:id/restore_order", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            paddock.restoreOrder();
            DBHelper.update(paddock);
            res.redirect("/admin/paddocks/" + paddockId);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/admin/:id/approve", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            paddock.makeAvailableForVisits();
            DBHelper.update(paddock);
            res.redirect("/admin/paddocks/" + paddockId);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/admin/:id/no_entry", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            paddock.makeNotAvailableForVisit();
            DBHelper.update(paddock);
            res.redirect("/admin/paddocks/" + paddockId);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/dinosaurs/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int dinosaurId = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaurToEdit = DBHelper.find(dinosaurId, Dinosaur.class);
//            DinosaurType[] dinosaurTypes = DinosaurType.values();
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
//            model.put("dinosaurTypes", dinosaurTypes);
            model.put("paddocks", paddocks);
            model.put("dinosaurToEdit", dinosaurToEdit);
            model.put("template", "templates/admin/dinosaurs/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/dinosaurs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Dinosaur> dinosaurs = DBHelper.getAll(Dinosaur.class);
            model.put("template", "templates/admin/dinosaurs/index.vtl");
            model.put("dinosaurs", dinosaurs);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/dinosaurs/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            DinosaurType[] dinosaurTypes = DinosaurType.values();
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("dinosaurTypes", dinosaurTypes);
            model.put("paddocks", paddocks);
            model.put("template", "templates/admin/dinosaurs/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/dinosaurs/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Dinosaur dinosaur = DBHelper.find(intId, Dinosaur.class);
            model.put("dinosaur", dinosaur);
            model.put("template", "templates/admin/dinosaurs/show.vtl");
            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/dinosaurs/:id/feed", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Dinosaur dinosaur = DBHelper.find(intId, Dinosaur.class);
            FoodType[] foodTypes = FoodType.values();
            model.put("dinosaur", dinosaur);
            model.put("foodTypes", foodTypes);
            model.put("template", "templates/admin/dinosaurs/feed.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/admin/dinosaurs/invalid_paddock", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/dinosaurs/invalid_paddock.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/admin/dinosaurs/:id", (req, res) -> {
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

                    res.redirect("/admin/dinosaurs");
                } else {
                    model.put("dinosaur", dinosaur);
                    model.put("template", "templates/admin/dinosaurs/invalid_paddock.vtl");
                }
            } else {
                if (dinosaur.checkIfCompatible(paddock)) {
                    DBDinosaur.addPaddockToDinosaur(dinosaur, paddock);
                    DBHelper.update(paddock);
                    DBHelper.update(dinosaur);
                    res.redirect("/admin/dinosaurs");
                } else {
                    model.put("dinosaur", dinosaur);
                    model.put("template", "templates/admin/dinosaurs/invalid_paddock.vtl");
                }

            }


            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/admin/dinosaurs/:id/delete", (req, res) -> {
            int dinosaurId = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaur = DBHelper.find(dinosaurId, Dinosaur.class);
            DBHelper.delete(dinosaur);
            res.redirect("/admin/dinosaurs");
            return null;
        }, new VelocityTemplateEngine());

        post("/admin/dinosaurs", (req, res) -> {
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
                res.redirect("/admin/dinosaurs");
            } else {
                res.redirect("/admin/dinosaurs/invalid_paddock");
            }
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/admin/dinosaurs/:id/feed", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int dinosaurId = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaur = DBHelper.find(dinosaurId, Dinosaur.class);
            int paddockId = dinosaur.getPaddock().getId();
            FoodType foodType = FoodType.valueOf(req.queryParams("foodType"));
            dinosaur.eat(foodType);
            DBHelper.update(dinosaur);
            res.redirect("/admin/paddocks/"+paddockId);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/admin/paddocks/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock= DBHelper.find(intId, Paddock.class);
            DietaryType[] dietaryTypes = DietaryType.values();

            Map<String, Object> model = new HashMap<>();
            model.put("dietaryTypes", dietaryTypes);
            model.put("template", "templates/admin/paddocks/edit.vtl");
            model.put("paddock", paddock);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/admin/paddocks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("paddocks", paddocks);
            model.put("template", "templates/admin/paddocks/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/paddocks/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            DietaryType[] dietaryTypes = DietaryType.values();
            model.put("dietaryTypes", dietaryTypes);
            model.put("template", "templates/admin/paddocks/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin/paddocks/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(intId, Paddock.class);
            List<Dinosaur> dinosaurs = DBDinosaur.getAllDinoForPaddock(paddock);
            Map<String, Object> model = new HashMap<>();
            model.put("dinosaurs", dinosaurs);
            model.put("paddock", paddock);
            model.put("template", "templates/admin/paddocks/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



        post("/admin/paddocks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int parkId = Integer.parseInt(req.queryParams("park"));
            DietaryType dietaryType = DietaryType.valueOf(req.queryParams("dietaryType"));
            String name = req.queryParams("name");
            Paddock newPaddock = new Paddock(name, dietaryType);
            DBHelper.save(newPaddock);

            res.redirect("/admin/paddocks");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/admin/paddocks/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(intId, Paddock.class);
            DietaryType dietaryType = DietaryType.valueOf(req.queryParams("dietaryType"));
            String name = req.queryParams("name");
            paddock.setName(name);
            paddock.setDietaryType(dietaryType);
            DBHelper.update(paddock);
            res.redirect("/admin/paddocks");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/admin/paddocks/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Paddock paddockToDelete = DBHelper.find(id, Paddock.class);
            DBHelper.delete(paddockToDelete);
            res.redirect("/admin/paddocks");
            return null;
        }, new VelocityTemplateEngine());
    }


}

package controllers;

import db.DBHelper;
import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;

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
            model.put("dinosaurTypes", dinosaurTypes);
            model.put("template", "templates/dinosaurs/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}

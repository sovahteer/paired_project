package controllers;

import db.DBHelper;
import models.enums.DietaryType;
import models.paddocks.Paddock;
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
        get("/park/paddocks/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            DietaryType herbivore = DietaryType.HERBIVORE;
            model.put("herbivore", herbivore);
            model.put("paddock", paddock);
            model.put("template", "templates/park/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
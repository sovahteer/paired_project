package controllers;

import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
public class MainController {

    public static void main(String[] args) {
        Seeds.seedData();
        AdminController adminController = new AdminController();
        DinosaursController dinosaursController = new DinosaursController();
        PaddockController paddockController = new PaddockController();
        ParkController parkController = new ParkController();
        VisitorsController visitorsController = new VisitorsController();

        
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/home/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}

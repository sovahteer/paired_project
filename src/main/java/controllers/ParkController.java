package controllers;

import db.DBDinosaur;
import db.DBHelper;
import db.DBPaddock;
import db.DBVisit;
import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.information.Info;
import models.paddocks.Paddock;
import models.visitors.Visit;
import models.visitors.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static spark.Spark.get;
public class ParkController {

    public ParkController() {
        startEndpoints();
    }

    private static void startEndpoints() {
        get("/park/visits/:visitor_id/paddocks/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int visitorId = Integer.parseInt(req.params(":visitor_id"));
            Visitor visitor = DBHelper.find(visitorId, Visitor.class);
            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(paddockId, Paddock.class);
            paddock.rampageCheck();
            DietaryType herbivore = DietaryType.HERBIVORE;
            String herbivoreString = herbivore.getHumanReadable().toLowerCase();
            if(paddock.getDietaryType() == DietaryType.HERBIVORE){
                Info randomInfoHerbivore = Info.getRandomInfoOnHerbivore();
                model.put("randomInfoHerbivore", randomInfoHerbivore);
            } else {
                Info randomInfoOnSpecies = Info.getRandomInfoOfSpecies(paddock.getDinosaurType());
                model.put("randomInfoOnSpecies", randomInfoOnSpecies);
            }
            int numberOfVisitors = DBHelper.getAll(Visitor.class).size();
            Random random = new Random();
            int randomNumber = random.nextInt(numberOfVisitors + 1);

            List<Dinosaur> dinosaurs = DBDinosaur.getAllDinoForPaddock(paddock);
            Visit visit = DBVisit.getMostRecentVisit(visitor);
            visit.passageOfTime();
            model.put("randomNumber", randomNumber);
            model.put("dinosaurs", dinosaurs);
            model.put("visit", visit);
            model.put("visitor", visitor);
            model.put("herbivoreString", herbivoreString);
            model.put("herbivore", herbivore);
            model.put("paddock", paddock);
            model.put("template", "templates/park/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
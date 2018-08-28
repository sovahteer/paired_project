package controllers;
import db.DBPaddock;
import models.paddocks.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
public class ParkController {

    public ParkController() {
        startEndpoints();
    }

    private static void startEndpoints() {
        get("/park", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Paddock> allowedToVisit = DBPaddock.filterByCanVisit();
            Collections.shuffle(allowedToVisit);
            List<Paddock> paddocks =  listOfVisitablePaddocks();
            model.put("paddocks", paddocks);
            model.put("template", "templates/park/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

    private static List<Paddock> listOfVisitablePaddocks(){
        List<Paddock> allowedToVisit = DBPaddock.filterByCanVisit();
        Collections.shuffle(allowedToVisit);
        List<Paddock> paddocks;
        if (allowedToVisit.size() < 8){
            paddocks = allowedToVisit.subList(0, allowedToVisit.size());
        } else {
            paddocks = allowedToVisit.subList(0, 8);
        }
        return paddocks;
    }
}
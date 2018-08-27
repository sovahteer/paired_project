package controllers;

import db.DBDinosaur;
import db.DBHelper;
import db.DBPark;
import models.dinosaurs.Dinosaur;
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


    }



}

package controllers;
import db.DBHelper;
import models.visitors.Visit;
import models.visitors.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;


public class VisitorsController {

    public VisitorsController() {
        setupEndpoints();
    }

    public static void setupEndpoints(){
        get("/visitors", (req, res) -> {
            List<Visitor> visitors = DBHelper.getAll(Visitor.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/visitors/index.vtl");
            model.put("visitors", "visitors");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/visitors/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/visitors/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/visitors/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/visitors/login.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //show
        get("visitors/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int visitorId = Integer.parseInt(req.params(":id"));
            Visitor visitor = DBHelper.find(visitorId, Visitor.class);
            model.put("visitor", visitor);
            model.put("template", "templates/visitors/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
        //create
        post("/visitors", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String username = req.queryParams("username");
            Visitor visitor = new Visitor(name, username);
            DBHelper.save(visitor);
            res.redirect("/visitors/login");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    }
}

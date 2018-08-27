package controllers;

import db.DBHelper;
import models.paddocks.Paddock;

import models.visitors.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
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
            res.redirect("/paddocks/" + paddockId);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }


}

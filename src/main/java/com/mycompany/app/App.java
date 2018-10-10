package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    public static String BugFight(ArrayList<Integer> bugTeam1, ArrayList<Integer> bugTeam2, int powerUp1Index,
            int PowerUp2Index) {
        int team1Point = 0;
        int team2Point = 0;

        if (bugTeam1 == null || bugTeam2 == null) {
            return "Bug Team 1 formu ya da Bug Team 2 formu null";
        }
        if (bugTeam1.isEmpty() || bugTeam2.isEmpty()) {
            return "Bug Team 1 formu ya da Bug Team 2 formu bos";
        }
        if (bugTeam1.size() != bugTeam2.size()) {
            return "Bug Team 1 ve Bug Team 2 bocek sayilari esit degil";
        }

        powerUp1Index--;
        PowerUp2Index--;
        bugTeam1.set(powerUp1Index, bugTeam1.get(powerUp1Index) + 1);
        bugTeam2.set(PowerUp2Index, bugTeam1.get(PowerUp2Index) + 1);

        for (int i = 0; i < bugTeam1.size(); i++) {
            if (bugTeam1.get(i) > bugTeam2.get(i)) {
                team1Point++;
            } else if (bugTeam1.get(i) == bugTeam2.get(i)) {
                team1Point++;
                team2Point++;
            } else {
                team2Point++;
            }
        }

        if (team1Point > team2Point) {
            return "Bug Team 1 Kazandı!!!";
        } else if (team1Point == team2Point) {
            return "Berabere";
        } else {
            return "Bug Team 2 Kazandı!!!";
        }
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
            // System.out.println(req.queryParams("input1"));
            // System.out.println(req.queryParams("input2"));

            String input1 = req.queryParams("input1");
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while (sc1.hasNext()) {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
                inputList.add(value);
            }
            System.out.println(inputList);

            String input2 = req.queryParams("input2");
            java.util.Scanner sc2 = new java.util.Scanner(input2);
            sc2.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
            while (sc2.hasNext()) {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s", ""));
                inputList2.add(value);
            }
            System.out.println(inputList2);

            String powerUp1 = req.queryParams("powerUp1").replaceAll("\\s", "");
            int powerUp1AsInt = Integer.parseInt(powerUp1);

            String powerUp2 = req.queryParams("powerUp2").replaceAll("\\s", "");
            int powerUp2AsInt = Integer.parseInt(powerUp2);

            String result = App.BugFight(inputList, inputList2, powerUp1AsInt, powerUp2AsInt);

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());

        get("/compute", (rq, rs) -> {
            Map map = new HashMap();
            map.put("result", "not computed yet!");
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}

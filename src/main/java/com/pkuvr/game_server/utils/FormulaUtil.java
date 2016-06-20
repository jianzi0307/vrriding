package com.pkuvr.game_server.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FormulaUtil {

    public static void main(String[] args) throws ScriptException {

        long now = System.currentTimeMillis();

        String formula = "x + 1";
        Map<String, String> prarms = new HashMap<String, String>();
        prarms.put("x", "10");

        Double result = Double.valueOf(getResult(formula, prarms));

        long end = System.currentTimeMillis();

        if (result != null) {
            System.out.println(result);

            System.out.println(end - now);
        }

    }

    /**
     * 获取结果
     *
     * @param formula
     * @param prarms
     * @return
     * @throws ScriptException
     */
    public static String getResult(String formula, Map<String, String> prarms) throws ScriptException {


        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engineByName = sem.getEngineByName("js");

        String tf = new String(formula);

        for (Entry<String, String> entry : prarms.entrySet()) {
            tf = tf.replace(entry.getKey(), entry.getValue());
        }

        return engineByName.eval(tf).toString();
    }

}

package com.zjzcn.util;

import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;

public class GroovyRunner {

	private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");

	public static Map<String, Object> runScript(String script, Map<String, Object> context) {
		Bindings bindings = new SimpleBindings(context);
		try {
			engine.eval(script, bindings);
		} catch (Exception e) {
			throw new RuntimeException("Error running groovy, context: " + context + ", script: " + script, e);
		}

		return new HashMap<String, Object>(bindings);
	}

	public static void main(String[] args) {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("a", 1);
		System.out.println(runScript("println 'hehe'+a; b=a; c=2;", context));
	}

}

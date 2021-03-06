package com.ramblingwood.minecraft.jsonapi.streams;

import org.json.simpleForBukkit.JSONObject;

public class ConsoleMessage extends JSONAPIStream {

	public ConsoleMessage(String line) {
		super("", line);
		setTime();
	}
	
	public JSONObject toJSONObject () {
		JSONObject o = new JSONObject();
		o.put("time", getTime());
		o.put("line", getMessage());
		
		return o;		
	}

	@Override
	public String getSourceName() {
		return "console";
	}
}

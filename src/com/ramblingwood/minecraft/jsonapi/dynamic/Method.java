package com.ramblingwood.minecraft.jsonapi.dynamic;

import java.util.ArrayList;

import org.json.simpleForBukkit.JSONArray;
import org.json.simpleForBukkit.JSONObject;

public class Method {
	private String name = "Method name";
	private String desc = "Method description";
	private Class<?> returnValue = void.class;
	private String returnDesc = "Method return desc";
	private ArgumentList args = new ArgumentList();
	private ArrayList<String> flags = new ArrayList<String>();
	public Call call;
	
	public Method (JSONObject o) {
		setName((String)o.get("name"));
		setDesc((String)o.get("desc"));
		
		if(o.get("returns") != null && o.get("returns") instanceof JSONArray) {
			Class<?> c = Argument.getClassFromName((String)((JSONArray)o.get("returns")).get(0));
			if(c != null) {
				setReturnValue(c);
				setReturnDesc((String)((JSONArray)o.get("returns")).get(1));
			}
		}
		
		if(o.get("args") != null && o.get("args") instanceof JSONArray) {
			JSONArray thisargs = (JSONArray)o.get("args");
			
			for(Object obj : thisargs) {
				if(obj instanceof JSONArray) {
					args.add(new Argument((JSONArray) obj));
				}
			}
		}
		
		if(o.get("flags") != null && o.get("flags") instanceof JSONArray) {
			JSONArray flags = (JSONArray)o.get("flags");
			
			for(Object flag : flags) {
				this.flags.add(flag.toString());
			}
		}
		
		call = new Call(o.get("call").toString(), args, this.flags);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setReturnValue(Class<?> returnValue) {
		this.returnValue = returnValue;
	}

	public Class<?> getReturnValue() {
		return returnValue;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	public String getReturnDesc() {
		return returnDesc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public Call getCall() {
		return call;
	}
}


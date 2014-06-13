package com.runtimeverification.rvmonitor.java.rvj.output;

import com.runtimeverification.rvmonitor.java.rvj.Main;

public class SystemAspect {
	private final String name;

	public SystemAspect(String name) {
		this.name = name + "SystemAspect";
	}

	public String getSystemAspectName() {
		return name;
	}

	public String toString() {
		String ret = "";

		ret += "aspect " + name + " implements com.runtimeverification.rvmonitor.java.rt.RVMObject {\n";
		ret += "public static final ThreadLocal t_version = new ThreadLocal(){\n";
		ret += "protected Object initialValue() {\n";
		ret += "return new int[1000000];\n";
		ret += "}\n";
		ret += "};\n\n";
		
		ret += "public static final ThreadLocal t_global_depth = new ThreadLocal(){\n";
		ret += "protected Object initialValue() {\n";
		ret += "return new int[1];\n";
		ret += "}\n";
		ret += "};\n\n";
		
		
		
		ret += "pointcut sysbegin() : execution(* *(..)) && ";
		if(Main.dacapo){
			ret += "!within(com.runtimeverification.rvmonitor.java.rt.RVMObject+) && !adviceexecution() && BaseAspect.notwithin();\n";
		} else {
			ret += "!within(com.runtimeverification.rvmonitor.java.rt.RVMObject+) && !adviceexecution();\n";
		}
		ret += "before () : sysbegin() {\n";
		ret += "((int[])t_version.get())[++((int[])t_global_depth.get())[0]]++;\n";
		ret += "}\n";
		ret += "}\n\n";
		
		ret += "aspect " + name + "2 implements com.runtimeverification.rvmonitor.java.rt.RVMObject {\n";
		ret += "pointcut sysend() : execution(* *(..)) && ";
		if(Main.dacapo){
			ret += "!within(com.runtimeverification.rvmonitor.java.rt.RVMObject+) && !adviceexecution() && BaseAspect.notwithin();\n";
		} else {
			ret += "!within(com.runtimeverification.rvmonitor.java.rt.RVMObject+) && !adviceexecution();\n";
		}
		ret += "after () : sysend() {\n";
		ret += "((int[])" + name + ".t_global_depth.get())[0]--;\n";
		ret += "}\n";

		ret += "}";

		return ret;
	}
}

package rvmonitor.output;

import rvmonitor.parser.ast.PackageDeclaration;

public class Util {

	public static String defaultLocation = "Thread.currentThread()" +
			".getStackTrace()[4].toString()";

  public static String packageAndNameToUrl(PackageDeclaration packageDeclaration, String name){
    return "http://fsl.cs.uiuc.edu/annotated-java/__properties/html/" +
            packageToUrlFragment(packageDeclaration) + "/" + name + ".html"; 
  }

  public static String packageToUrlFragment(PackageDeclaration packageDeclaration){
    if(packageDeclaration == null) return "";
    return packageDeclaration.toString().replaceAll("[.]","/").replaceAll("(package\\s*)|;|\\s*","");
  }
}

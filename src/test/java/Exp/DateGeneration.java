package Exp;

import java.util.Date;

public class DateGeneration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DateGeneration dateGeneration=new DateGeneration();
		//Method chaining concept dateText.replace(" ","_").replace(":","_")
		System.out.println(dateGeneration.toString().replace(" ","_").replace(":","_"));
	}

}

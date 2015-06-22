package com.appazal.quizzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Config {

	protected static final String TAG = "Quizzle :";
	public static String RANDOM_QUESTION = "A class member declared protected becomes member of subclass of which type?";
	public static String QUESTION_PALETTE = "#444444";
	
	public static int options_size = 4;

	private static String[] options = {"Public", "Private", "Protected", "Static", "Default"};
	public static List<String> OptionList = new ArrayList<String>(Arrays.asList(options));

	private static String[] COLORS = {"#AA3333", "#33AA33", "#4444AA", "#777733"};
//	private static String[] COLORS = {"#FFFB67", "#D4D26A", "#807D15", "#555300"};
	public static List<String> OPTIONS_PALETTE = new ArrayList<String>(Arrays.asList(COLORS));

	static {
		Collections.shuffle(OptionList);
		Collections.shuffle(OPTIONS_PALETTE);
	}
	
	public static int getRand(){
		return (int)(Math.random()*100) % 100;
	}
}

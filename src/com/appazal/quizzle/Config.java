package com.appazal.quizzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.appazal.quizzle.model.option.Option;
import com.appazal.quizzle.model.option.TextOption;
import com.appazal.quizzle.model.question.ImageQuestion;
import com.appazal.quizzle.model.question.Question;
import com.appazal.quizzle.model.question.TextQuestion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config {

	protected static final String TAG = "Quizzle :";
	public static String RANDOM_QUESTION = "A class member declared protected becomes member of subclass of which type?";

	public static int options_size = 4;

	private static String[] options = {"Public", "Private", "Protected", "Static", "Default"};
	public static List<String> OptionList = new ArrayList<String>(Arrays.asList(options));

	private static String[] COLORS = {"#AA3333", "#33AA33", "#4444AA", "#777733"};
	public static List<String> OPTIONS_PALETTE = new ArrayList<String>(Arrays.asList(COLORS));

	static {
		Collections.shuffle(OptionList);
		Collections.shuffle(OPTIONS_PALETTE);
	}

	public static int getRand() {
		return (int)(Math.random()*100) % 100;
	}

	public static Gson gsonSerialize = new Gson();
	public static Gson gsonDeserialize = getGsonDeserializer();

	private static Gson getGsonDeserializer() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		RuntimeTypeAdapterFactory<Question> questionTypeAdapter = RuntimeTypeAdapterFactory.of(Question.class)
				.registerSubtype(TextQuestion.class)
				.registerSubtype(ImageQuestion.class);

		RuntimeTypeAdapterFactory<Option> optionTypeAdapter = RuntimeTypeAdapterFactory.of(Option.class)
				.registerSubtype(TextOption.class);

		gsonBuilder.registerTypeAdapterFactory(questionTypeAdapter);
		gsonBuilder.registerTypeAdapterFactory(optionTypeAdapter);

		//gsonBuilder.setExclusionStrategies((new ExclusionStrategy() {
		//	@Override
		//	public boolean shouldSkipField(FieldAttributes arg) {
		//		return arg.getDeclaringClass().equals(RealmObject.class);
		//	}
		//
		//	@Override
		//	public boolean shouldSkipClass(Class<?> arg0) {
		//		return false;
		//	}
		//}));

		return gsonBuilder.create();
	}
}

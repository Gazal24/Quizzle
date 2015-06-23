package com.appazal.quizzle;

import com.appazal.quizzle.option.Option;
import com.appazal.quizzle.option.TextOption;
import com.appazal.quizzle.question.Question;
import com.appazal.quizzle.question.TextQuestion;

public class MockImplementations {

	public static Puzzle[] getPuzzles(int num) {

		int count = 0;
		Puzzle[] puzzles = new Puzzle[num];
		while(count < puzzles.length) {
			Puzzle p = new Puzzle();

			TextQuestion tQues = new TextQuestion("Random Question " + Config.getRand(), count);
			p.setQuestion(tQues);

			Option[] options = new Option[4];
			options[0] = new TextOption("Random Option " + Config.getRand());
			options[1] = new TextOption("Random Option " + Config.getRand());
			options[2] = new TextOption("Random Option " + Config.getRand());
			options[3] = new TextOption("Random Option " + Config.getRand());
			p.setOptions(options);

			puzzles[count++] = p;
		}
		return puzzles;
	}
}

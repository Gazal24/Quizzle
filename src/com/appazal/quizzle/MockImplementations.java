package com.appazal.quizzle;

import com.appazal.quizzle.option.Option;
import com.appazal.quizzle.option.TextOption;
import com.appazal.quizzle.question.TextQuestion;

public class MockImplementations {

	public static Puzzle[] getPuzzles(int num) {

		int index = 0;
		Puzzle[] puzzles = new Puzzle[num];
		while(index < puzzles.length) {
			Puzzle p = new Puzzle();

			TextQuestion tQues = new TextQuestion("Random Question " + Config.getRand(), index);
			p.setQuestion(tQues);

			Option[] options = new Option[4];
			options[0] = new TextOption(index, "Random Option " + Config.getRand());
			options[1] = new TextOption(index, "Random Option " + Config.getRand(), true);
			options[2] = new TextOption(index, "Random Option " + Config.getRand());
			options[3] = new TextOption(index, "Random Option " + Config.getRand());
			p.setOptions(options);

			puzzles[index++] = p;
		}
		return puzzles;
	}
	
	public static Puzzle createPuzzle() {
		Puzzle puzzle = new Puzzle();
		puzzle.setQuestion(new TextQuestion(Config.RANDOM_QUESTION));
		Option[] options = new Option[4];
		int i = 0;
		while(i<4) {
			options[i] = new TextOption(0, Config.OptionList.get(i), i == 1 ? true : false);
			i++;
		}
		puzzle.setOptions(options);
		
		return puzzle;
	}
}

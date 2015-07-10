package com.appazal.quizzle;

import com.appazal.quizzle.model.Puzzle;
import com.appazal.quizzle.model.option.Option;
import com.appazal.quizzle.model.option.TextOption;
import com.appazal.quizzle.model.question.TextQuestion;

public class MockImplementations {

	public static Puzzle[] getPuzzles(int num) {
		int index = 0;
		Puzzle[] puzzles = new Puzzle[num];
		while(index < puzzles.length) {
			Puzzle p = new Puzzle();

			TextQuestion tQues = new TextQuestion(index, "Random Question " + Config.getRand());
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

	private static String mockJson="[{'question':{'imagePath':'/res/foo.png','id':100,'type':'ImageQuestion'},'options':[{'text':'Gazal','questionId':1,'isCorrect':true,'type':'TextOption'},{'text':'Maria','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pooja','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pradeep','questionId':1,'isCorrect':false,'type':'TextOption'}]},{'question':{'text':'What is your name','id':2,'type':'TextQuestion'},'options':[{'text':'Gazal','questionId':1,'isCorrect':true,'type':'TextOption'},{'text':'Maria','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pooja','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pradeep','questionId':1,'isCorrect':false,'type':'TextOption'}]}]";
	public static String getPuzzleJson(){
		return mockJson;
	}
}

package com.appazal.test;

import com.appazal.quizzle.model.Puzzle;
import com.appazal.quizzle.model.option.Option;
import com.appazal.quizzle.model.option.TextOption;
import com.appazal.quizzle.model.question.ImageQuestion;
import com.appazal.quizzle.model.question.TextQuestion;
import com.appazal.quizzle.service.PuzzleService;

public class TestPuzzle {

	public static void main(String[] arguments) throws Exception {
		Puzzle[] p = new Puzzle[2];
		p[0] = new Puzzle();
		p[1] = new Puzzle();
		p[0].setQuestion(new ImageQuestion(1, "/res/foo.png"));
		p[1].setQuestion(new TextQuestion(22, "What is your name"));
		Option[] options = new Option[4];
		options[0] = new TextOption(1, "Gazal", true);
		options[1] = new TextOption(1, "Maria");
		options[2] = new TextOption(1, "Pooja");
		options[3] = new TextOption(1, "Pradeep");
		p[0].setOptions(options);
		p[1].setOptions(options);
		
		System.out.println("My Class is " + p[0].getQuestion().getClass());
		System.out.println("The type is " + p[0].getQuestion().getType());
		System.out.println(PuzzleService.convertPuzzleToJson(p));
		
		String puzzleJson1 = "{'question':{'text':'What is your name','id':23,'type':'TextQuestion'},'options':[{'text':'Gazal','questionId':1,'isCorrect':true,'type':'TextOption'},{'text':'Maria','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pooja','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pradeep','questionId':1,'isCorrect':false,'type':'TextOption'}]}";
		String puzzleJson2 = "[{'question':{'imagePath':'/res/foo.png','id':100,'type':'ImageQuestion'},'options':[{'text':'Gazal','questionId':1,'isCorrect':true,'type':'TextOption'},{'text':'Maria','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pooja','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pradeep','questionId':1,'isCorrect':false,'type':'TextOption'}]},{'question':{'text':'What is your name','id':2,'type':'TextQuestion'},'options':[{'text':'Gazal','questionId':1,'isCorrect':true,'type':'TextOption'},{'text':'Maria','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pooja','questionId':1,'isCorrect':false,'type':'TextOption'},{'text':'Pradeep','questionId':1,'isCorrect':false,'type':'TextOption'}]}]";
		
		Puzzle[] pJsons = PuzzleService.convertJsonToPuzzle(puzzleJson2);
		System.out.println(pJsons[1].getQuestion().getId());
		
	}
}

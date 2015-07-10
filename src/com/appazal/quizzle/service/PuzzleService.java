package com.appazal.quizzle.service;

import org.json.JSONException;

import com.appazal.quizzle.Config;
import com.appazal.quizzle.model.Puzzle;
import com.appazal.quizzle.model.option.Option;

public class PuzzleService {

	private Puzzle puzzle;

	public Option getOptionAt(int index) {
		return puzzle.getOptions()[index];
	}

//	public static Puzzle readPuzzleDb(int id) {
//		RealmQuery<TextQuestion> qQuery = mRealm.where(TextQuestion.class);
//		qQuery.equalTo("id", id);
//		TextQuestion ques = qQuery.findFirst();
//
//		RealmQuery<TextOption> oQuery = mRealm.where(TextOption.class);
//		oQuery.equalTo("quesId", id);
//		RealmResults<TextOption> optionList = oQuery.findAll();
//
//		Puzzle puzzle = new Puzzle();
//		Option[] options = new Option[optionList.size()];
//		puzzle.setQuestion(ques);
//		puzzle.setOptions(optionList.toArray(options));
//
//		return puzzle;
//	}
//
//	public static void savePuzzlesDb(Realm mRealm, Puzzle[] puzzles) {
//		for(Puzzle p : puzzles)
//			storePuzzleInDb(mRealm, p);
//	}
//
//	public static void storePuzzleInDb(Realm mRealm, Puzzle puzzle) {
//		// TODO: Inefficient, too many transaction begin-commits for a list of puzzles.
//		// TODO: Remove Try/Catch. Handle duplicate entry case in a diff way.
//		try {
//			mRealm.beginTransaction();
//
//			mRealm.copyToRealm((TextQuestion)(puzzle.getQuestion()));
//			for(Option o : puzzle.getOptions())
//				mRealm.copyToRealm((TextOption)o);
//
//			mRealm.commitTransaction();
//		} catch (RealmException re) {
//			mRealm.cancelTransaction();
//			re.printStackTrace();
//		}
//	}

	public static Puzzle[] convertJsonToPuzzle(String puzzleJson) throws JSONException {
		return Config.gsonDeserialize.fromJson(puzzleJson, Puzzle[].class);
	}

	public static String convertPuzzleToJson(Puzzle[] puzzles){
		return Config.gsonSerialize.toJson(puzzles);
	}
}

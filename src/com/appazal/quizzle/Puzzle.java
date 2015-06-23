package com.appazal.quizzle;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

import com.appazal.quizzle.option.Option;
import com.appazal.quizzle.option.TextOption;
import com.appazal.quizzle.question.Question;
import com.appazal.quizzle.question.TextQuestion;

public class Puzzle {

	private Question question;
	private Option[] options = new Option[Config.options_size];
	
	public Puzzle() {}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public void setOptions(Option[] options) {
		this.options = options;
	}
	
	public Option[] getOptions() {
		return this.options;
	}
	
	public Option getOption(int index) {
		return this.options[index];
	}
	
   public static Puzzle readPuzzleDb(Realm mRealm, int id) {
	   RealmQuery<TextQuestion> qQuery = mRealm.where(TextQuestion.class);
       qQuery.equalTo("id", id);
	   TextQuestion ques = qQuery.findFirst();
	   
	   RealmQuery<TextOption> oQuery = mRealm.where(TextOption.class);
	   oQuery.equalTo("quesId", id);
	   RealmResults<TextOption> optionList = oQuery.findAll();
	   
	   Puzzle puzzle = new Puzzle();
	   Option[] options = new Option[optionList.size()];
	   puzzle.setQuestion(ques);
	   puzzle.setOptions(optionList.toArray(options));
	
	   return puzzle;
   }
   
   public static void savePuzzlesDb(Realm mRealm, Puzzle[] puzzles) {
	   for(Puzzle p : puzzles)
		   savePuzzleDb(mRealm, p);
   }
   
   public static void savePuzzleDb(Realm mRealm, Puzzle puzzle) {
	   // TODO: Inefficient, too many transaction begin-commits for a list of puzzles.
	   // TODO: Remove Try/Catch. Handle duplicate entry case in a diff way.
	   try {
		   mRealm.beginTransaction();

		   mRealm.copyToRealm((TextQuestion)(puzzle.getQuestion()));
		   for(Option o : puzzle.getOptions())
			   mRealm.copyToRealm((TextOption)o);
		   
		   mRealm.commitTransaction();
	   } catch (RealmException re) {
		   mRealm.cancelTransaction();
		   re.printStackTrace();
	   }
   }
}


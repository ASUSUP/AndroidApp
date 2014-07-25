package com.example.availablecourses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;


public class QuizWindow extends Activity {

	class QuestionAndAnswer{
		public List<String> allAnswers;
		public String answer;
		public String question;
		public String selectedAnswer;
		public int selectedId = -1;


		public QuestionAndAnswer(String question, String answer, List<String> distractors) {
			this.question = question;
			this.answer = answer;
			allAnswers = new ArrayList<String> (distractors);

			allAnswers.add(answer);
			Collections.shuffle(allAnswers);
		}

		public boolean isCorrect(){
			return answer.equals(selectedAnswer);
		}
	}

	RadioGroup answerRadioGroup;
	int currentQuestion = 0;
	TextView questionTextView;
	TextView score;
	Button submitButton;
	ArrayList<QuestionAndAnswer> quiz = new ArrayList<QuestionAndAnswer>();
	private int x=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz);

		questionTextView = (TextView) findViewById(R.id.questionTextView);
		answerRadioGroup = (RadioGroup) findViewById(R.id.answerRadioGroup);
		score = (TextView) findViewById(R.id.score);
		submitButton = (Button) findViewById(R.id.submitButton);

		answerRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group, int checkedId){
				if (checkedId > -1){
					QuestionAndAnswer qna = quiz.get(currentQuestion);
					qna.selectedAnswer = ((RadioButton) group.findViewById(checkedId)).getText().toString();
					qna.selectedId = checkedId;
				}
			}
		});



		final String[] question = {"what is your name?" , "how old are you?" , "what is your birth day?" , "how tall are you?" , "what is your favorite subject?"};
		final String[] answer = {"name" , "age" , "date" , "tall" , "subject"};
		String[] distractor = {"ques1a","ques1b","ques1c","ques2a","ques2b","ques2c","ques3a","ques3b","ques3c","ques4a","ques4b","ques4c","ques5a","ques5b","ques5c"};

		final ArrayList<String> distractorList = new ArrayList<String>();
		distractorList.addAll(Arrays.asList(distractor));
		int length = question.length;
		for (int i=0; i<length; i++){
			quiz.add(new QuestionAndAnswer(question[i] , answer[i] , distractorList.subList(i*3, (i+1)*3) ));
		}
		fillInQuestion(quiz);


		submitButton.setOnClickListener( new OnClickListener(){
			@Override
			public void onClick(View v) {
				if (x<5)
				{
					fillInQuestion(quiz);
				} else {
					Intent i = new Intent(getApplicationContext(), Score.class);
					startActivity(i);
				}
			}
		});
	}

	public void fillInQuestion(ArrayList<QuestionAndAnswer> quiz){
		QuestionAndAnswer qna = quiz.get(x++);

		questionTextView.setText(qna.question);

		int count = answerRadioGroup.getChildCount();
		for (int i=0; i<count; i++){
			((RadioButton) answerRadioGroup.getChildAt(i)).setText(qna.allAnswers.get(i));
		}
		if(qna.selectedId > -1)
			answerRadioGroup.check(qna.selectedId);
		else
			answerRadioGroup.clearCheck();

		{
			int correct = 0;
			for(QuestionAndAnswer question :quiz){
				if(question.isCorrect())
					correct++;
				score.setText("Your Score is " + correct);
			}
		}
	}
}
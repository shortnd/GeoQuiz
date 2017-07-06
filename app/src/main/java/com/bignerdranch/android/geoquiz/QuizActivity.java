package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    /**
     * Variables for the buttons
     */
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;

    /**
     * Variable for the question
     */
    private TextView mQuestionTextView;

    /**
     * Array of questions
     */
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_ocean, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Sets QuestionTextView to the CurrentIndex
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[ mCurrentIndex ].getTextResId();
        mQuestionTextView.setText(question);


        // Storing the reference of the buttons in xml to the variables to be used in java
        mTrueButton = (Button) findViewById(R.id.true_button);
        // Setting a listener for the True Button
        // Using an anonymous inner class
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            // Has to be overridden to use anonymous inner class
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }

        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        // Setting a listener for the False Button
        // Using an anonymous inner class
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        // Setting a listener for the Next Button
        // Using an anonymous inner class
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    // Updates the question to the next one in the Array
    private void updateQuestion() {
        int question = mQuestionBank[ mCurrentIndex ].getTextResId();
        mQuestionTextView.setText(question);
    }

    // Checks what button the user pressed and then checks to see if it is correct or incorrect
    // and then will display the proper Toast message
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[ mCurrentIndex ].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}

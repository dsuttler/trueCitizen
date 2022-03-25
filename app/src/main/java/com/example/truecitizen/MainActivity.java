package com.example.truecitizen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truecitizen.databinding.ActivityMainBinding;
import com.example.truecitizen.databinding.ActivityMainBindingImpl;
import com.example.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestion = 0;

    // create question bank
    private Question[] questionBank = new Question[] {
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TextView questionTextView = binding.questionTextView;
        Button nextButton = binding.nextButton;
        Button prevButton = binding.prevButton;
        Button trueButton = binding.trueButton;
        Button falseButton = binding.falseButton;

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        questionTextView.setText(questionBank[currentQuestion].getAnswerResId());

        // next button behaviors
        nextButton.setOnClickListener(view -> {
            currentQuestion = (currentQuestion == questionBank.length - 1)
                    ? 0
                    : currentQuestion + 1;

            questionTextView.setText(questionBank[currentQuestion].getAnswerResId());
            setActionBarColor(actionBar, "#FF6200EE");
        });

        // prev button behaviors
        prevButton.setOnClickListener(view -> {
            currentQuestion = (currentQuestion == 0)
                    ? questionBank.length - 1
                    : currentQuestion - 1;

            questionTextView.setText(questionBank[currentQuestion].getAnswerResId());
            setActionBarColor(actionBar, "#FF6200EE");
        });

        // true button behaviors
        trueButton.setOnClickListener(view -> {
            if(questionBank[currentQuestion].isAnswerTrue()) {
                Toast.makeText(
                        MainActivity.this,
                        R.string.correct_answer,
                        Toast.LENGTH_SHORT
                ).show();

                setActionBarColor(actionBar, "#32CD32");
            }
            else {
                Toast.makeText(
                        MainActivity.this,
                        R.string.wrong_answer,
                        Toast.LENGTH_SHORT
                ).show();

                setActionBarColor(actionBar, "#FF0000");
            }
        });

        // false button behaviors
        falseButton.setOnClickListener(view -> {
            if(!(questionBank[currentQuestion].isAnswerTrue())) {
                Toast.makeText(
                        MainActivity.this,
                        R.string.correct_answer,
                        Toast.LENGTH_SHORT
                ).show();

                setActionBarColor(actionBar, "#32CD32");
            }
            else {
                Toast.makeText(
                        MainActivity.this,
                        R.string.wrong_answer,
                        Toast.LENGTH_SHORT
                ).show();

                setActionBarColor(actionBar, "#FF0000");
            }
        });


    }

    private void setActionBarColor(ActionBar actionBar, String color) {
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(color));

        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);
    }
}
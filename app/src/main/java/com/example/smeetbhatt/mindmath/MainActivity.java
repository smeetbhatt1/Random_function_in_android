package com.example.smeetbhatt.mindmath;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionTextView,resultTextView,timerTextView,scoreTextView,welcomeTextView;
    Button option0,option1,option2,option3,playAgain,goButton;
    int correctAns,noOfQuestions=0,correctAttempts=0;
    ArrayList<Integer> answersList;
    RelativeLayout gameLayout;


    public void questionGenerator(){
        Random random=new Random();
        noOfQuestions++;
        int a,b;
        a=random.nextInt(10);
        b=random.nextInt(10);
        correctAns=a + b;
        questionTextView.setText(String.valueOf(a) +" + "+ String.valueOf(b));

        answersList=new ArrayList<Integer>();
        int correctAnsLoc = random.nextInt(4);
        for(int i=0;i<4;i++){

            if(i == correctAnsLoc){
                answersList.add(correctAns);
            }
            int inCorrectAns=random.nextInt(20);
            while (correctAns==inCorrectAns){
                    inCorrectAns=random.nextInt(20);
            }
            answersList.add(inCorrectAns);
        }
        option0.setText(String.valueOf(answersList.get(0)));
        option1.setText(String.valueOf(answersList.get(1)));
        option2.setText(String.valueOf(answersList.get(2)));
        option3.setText(String.valueOf(answersList.get(3)));
    }

    public void AnsClicked(View view){

        int optionId=Integer.parseInt(view.getTag().toString());
        if(correctAns== answersList.get(optionId)){

            resultTextView.setText("Correct");
            correctAttempts++;
        }
        else{

            resultTextView.setText("Wrong");
        }
        scoreTextView.setText(String.valueOf(correctAttempts)+" / "+String.valueOf(noOfQuestions));

        questionGenerator();
    }

    public void questionTimer(){
        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0");
                resultTextView.setText("Total Score: "+String.valueOf(correctAttempts));
                option0.setClickable(false);
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                playAgain.setVisibility(View.VISIBLE);


            }
        }.start();
    }

    public void setPlayAgain(View view){
        playAgain.setVisibility(View.INVISIBLE);
        noOfQuestions=0;
        correctAttempts=0;
        scoreTextView.setText("0 / 0");
        resultTextView.setText("");
        option0.setClickable(true);
        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        questionGenerator();
        questionTimer();
    }


    public void setGoButton(View v){

        goButton.setVisibility(View.INVISIBLE);
        welcomeTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);
        questionGenerator();
        questionTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        option0 = (Button) findViewById(R.id.option0);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        goButton = (Button) findViewById(R.id.goButton);
        playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);

        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
    }
}

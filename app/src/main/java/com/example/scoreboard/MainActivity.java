package com.example.scoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button startGame ,reset ,points1,points2,points3,plus1,plus2,plus3,endGame,
            whatsapp,gmail,facebook;
    TextView mainTitle, playersSummary,shareResults,pl1 , f1, pl2 ,f2 ,pl3,f3,pl4,f4,
            pl5,f5,pl6,f6,pl7,f7,pl8,f8,
            pl9,f9,pl10,f10, teamAScores , teamBscores;
    LinearLayout player1,teamAplayersheet,teamBplayersheet,btns,teamsSheet ,
            playersSheet, lastPanel;

    int  scoreA = 0, scoreB =0 , pl1fouls= 0 ,pl2fouls,pl3fouls,pl4fouls,
            pl5fouls,pl6fouls,pl7fouls,pl8fouls,pl9fouls,pl10fouls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//get team player sheet by id
        teamAplayersheet = findViewById(R.id.teamAplayersheet);
        teamBplayersheet = findViewById(R.id.teamBplayersheet);
        playersSummary = findViewById(R.id.playerSummary);
        mainTitle = findViewById(R.id.mainTitle);
        teamsSheet = findViewById(R.id.teamsPanel);
        playersSheet = findViewById(R.id.playersPanel);
        lastPanel = findViewById(R.id.last);
        shareResults = (TextView) findViewById(R.id.shareResults);
        btns = (LinearLayout) findViewById(R.id.btns);
        reset = (Button) findViewById(R.id.reset);
        startGame = (Button)findViewById(R.id.startGame);
        endGame = findViewById(R.id.endGame);

        //Results sharing
        whatsapp = findViewById(R.id.whatsapp);
        facebook = findViewById(R.id.facebook);
        gmail = (Button) findViewById(R.id.gmail);

        //get Team A players by id
        player1 = findViewById(R.id.player1);
        pl1 = findViewById(R.id.pl1);
        pl2 = findViewById(R.id.pl2);
        pl3 = findViewById(R.id.pl3);
        pl4 = findViewById(R.id.pl4);
        pl5 = findViewById(R.id.pl5);

       // getting Team A player fouls by id
        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);

        //getting Team B players by id
        pl6 = findViewById(R.id.pl6);
        pl7 = findViewById(R.id.pl7);
        pl8 = findViewById(R.id.pl8);
        pl9 = findViewById(R.id.pl9);
        pl10 = findViewById(R.id.pl10);


        // getting Team A player fouls by id
        f6 = findViewById(R.id.f6);
        f7 = findViewById(R.id.f7);
        f8 = findViewById(R.id.f8);
        f9 = findViewById(R.id.f9);
        f10 = findViewById(R.id.f10);

        //Both teams scores
        teamAScores = findViewById(R.id.teamAScores);
        teamBscores = findViewById(R.id.teamBScores);

        //Team A point increment 3,2,1
        plus1 = findViewById(R.id.plus1);
        plus2 = findViewById(R.id.plus2);
        plus3 = findViewById(R.id.plus3);

        //Team B point increment 3,2,1
        points1 = findViewById(R.id.points1);
        points2= findViewById(R.id.points2);
        points3 = findViewById(R.id.points3);
        endGame.setVisibility(View.GONE);
        reset.setVisibility(View.GONE);


        //Team B point increment
        // adding 3 points
        points3.setOnClickListener(view -> {
            scoreB = Integer.parseInt((String)teamBscores.getText());
            scoreB+=threePoints();
            teamBscores.setText(""+scoreB);

        });
        // adding 2 points
        points2.setOnClickListener(view -> {
            scoreB = Integer.parseInt((String)teamBscores.getText());
            scoreB+=twoPoints();
            teamBscores.setText(""+scoreB);

        });
        // adding 1 point
        points1.setOnClickListener(view -> {
            scoreB = Integer.parseInt((String)teamBscores.getText());
            scoreB+=onePoint();

            teamBscores.setText(""+scoreB);

        });

        //Team A point increment
        // adding 1 point
        plus1.setOnClickListener(view -> {
            scoreA = Integer.parseInt((String)teamAScores.getText());
            scoreA+=onePoint();
            teamAScores.setText(""+scoreA);

        });
        // adding 2 points
        plus2.setOnClickListener(view -> {
            scoreA = Integer.parseInt((String)teamAScores.getText());
            scoreA+=twoPoints();
            teamAScores.setText(""+scoreA);

        });
        // Adding 3 points
        plus3.setOnClickListener(view -> {
            scoreA = Integer.parseInt((String)teamAScores.getText());
            scoreA+=threePoints();
            teamAScores.setText(""+scoreA);

        });
        //onclick listenners for both team's players
        pl1.setOnClickListener(view -> pl1Fouls());
        pl2.setOnClickListener(view -> pl2Fouls());
        pl3.setOnClickListener(view ->pl3Fouls());
        pl4.setOnClickListener( view -> pl4Fouls());
        pl5.setOnClickListener(view -> pl5Fouls());
        pl6.setOnClickListener(view -> pl6Fouls());
        pl7.setOnClickListener(view -> pl7Fouls());
        pl8.setOnClickListener(view -> pl8Fouls());
        pl9.setOnClickListener(view -> pl9Fouls());
        pl10.setOnClickListener(view -> pl10Fouls());

        final String num = "+254745006734";
        final String text = message() + "\n fouls \n"+playerSummary();

//whatsapp sharing
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean installed = isAppInstalled("com.whatsapp");

                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + num + "&text=" + text));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Whatsapp is not installed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
//facebook sharing
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean installed = isAppInstalled("com.facebook");

                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.facebook.com/send?phone=" + num + "&text=" + text));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Whatsapp is not installed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean isAppInstalled(String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;

        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = false;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = true;
            e.printStackTrace();
        }
        return is_installed;
    }

    public String winningTeam(){
        if (Integer.parseInt((String) teamBscores.getText()) > Integer.parseInt((String) teamAScores.getText())){return "TeamB";}
        else if(Integer.parseInt((String) teamBscores.getText()) < Integer.parseInt((String) teamAScores.getText())) {return "TeamA";}
        else {return "Draw";}
    }


    public void emailling(String message){
        String fullMessage = message() + "\n fouls \n" + playerSummary();
        String[] emails = {"ray@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("'/'");
        intent.putExtra(intent.EXTRA_EMAIL, emails);
        intent.putExtra(intent.EXTRA_SUBJECT, "Match results summary");
        intent.putExtra(intent.EXTRA_TEXT, fullMessage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);}
    }
    // Methods to limit number of fouls for team B players to be 5 or less

    public void pl1Fouls(){
        int numberOfFouls = Integer.parseInt((String) f1.getText());
        if (numberOfFouls < 5){
            pl1fouls++;
            f1.setText(""+pl1fouls);
        }else {
            pl1.setVisibility(View.GONE);
            f1.setText("Disqualified");
        }
    }

    public void pl2Fouls(){
        int numberOfFouls = Integer.parseInt((String) f2.getText());
        if (numberOfFouls < 5){
            pl2fouls++;
            f2.setText(""+pl2fouls);
        }else {
            pl2.setVisibility(View.GONE);
            f2.setText("Disqualified");
        }
    }

    public void pl3Fouls(){
        int numberOfFouls = Integer.parseInt((String) f3.getText());
        if (numberOfFouls < 5){
            pl3fouls++;
            f3.setText(""+pl3fouls);
        }else {
            pl3.setVisibility(View.GONE);
            f3.setText("Disqualified");
        }
    }

    public void pl4Fouls(){
        int numberOfFouls = Integer.parseInt((String) f4.getText());
        if (numberOfFouls < 5){
            pl4fouls++;
            f4.setText(""+pl4fouls);
        }else {
            pl4.setVisibility(View.GONE);
            f4.setText("Disqualified");
        }
    }

    public void pl5Fouls(){
        int numberOfFouls = Integer.parseInt((String) f5.getText());
        if (numberOfFouls < 5){
            pl5fouls++;
            f5.setText(""+pl5fouls);
        }else {
            pl5.setVisibility(View.GONE);
            f5.setText("Disqualified");
        }
    }

    // Methods to limit number of fouls for team B players to be 5 or less
    public void pl6Fouls(){
        int numberOfFouls = Integer.parseInt((String) f6.getText());
        if (numberOfFouls < 5){
            pl6fouls++;
            f6.setText(""+pl6fouls);
        }else {
            pl6.setVisibility(View.GONE);
            f6.setText("Disqualified");
        }
    }

    public void pl7Fouls(){
        int numberOfFouls = Integer.parseInt((String) f7.getText());
        if (numberOfFouls < 5){
            pl7fouls++;
            f7.setText(""+pl7fouls);
        }else {
            pl7.setVisibility(View.GONE);
            f7.setText("Disqualified");
        }
    }

    public void pl8Fouls(){
        int numberOfFouls = Integer.parseInt((String) f8.getText());
        if (numberOfFouls < 5){
            pl8fouls++;
            f8.setText(""+pl8fouls);
        }else {
            pl8.setVisibility(View.GONE);
            f8.setText("Disqualified");
        }
    }

    public void pl9Fouls(){
        int numberOfFouls = Integer.parseInt((String) f9.getText());
        if (numberOfFouls < 5){
            pl9fouls++;
            f9.setText(""+pl9fouls);
        }else {
            pl9.setVisibility(View.GONE);
            f9.setText("Disqualified");
        }
    }

    public void pl10Fouls(){
        int numberOfFouls = Integer.parseInt((String) f10.getText());
        if (numberOfFouls < 5){
            pl10fouls++;
            f10.setText(""+pl10fouls);
        }else {
            pl10.setVisibility(View.GONE);
            f10.setText("Disqualified");
        }
    }

    public int threePoints(){return 3;}
    public  int twoPoints(){return 2;}
    public  int onePoint(){return 1;}


    //email sharing
    public void gmailBtn(View view) {
        String message = "Game results";
        emailling(message);
    }
    public String message(){
        int teamBpoints = Integer.parseInt((String) teamBscores.getText());
        int teamApoint = Integer.parseInt((String) teamAScores.getText());
        String messageText= " Team Won : "+winningTeam()+"!!! \n points \n   TeamA: " +  teamApoint+"  Team:B  " + teamBpoints ;
        return  messageText;
    }
// reset method for team scores and player fouls
    public void reset(View view) {
        shareResults.setText("--Start a new Game---");
        teamAScores.setText(""+0);teamBscores.setText(""+0);
        pl1fouls = 0;f1.setText("" + 0);pl1.setVisibility(View.VISIBLE);
        pl2fouls = 0;f2.setText("" + 0);pl2.setVisibility(View.VISIBLE);
        pl3fouls = 0;f3.setText("" + 0);pl3.setVisibility(View.VISIBLE);
        pl4fouls = 0;f4.setText("" + 0);pl4.setVisibility(View.VISIBLE);
        pl5fouls = 0;f5.setText("" + 0);pl5.setVisibility(View.VISIBLE);
        pl6fouls = 0;f6.setText("" + 0);pl6.setVisibility(View.VISIBLE);
        pl7fouls = 0;f7.setText("" + 0);pl7.setVisibility(View.VISIBLE);
        pl8fouls = 0;f8.setText("" + 0);pl8.setVisibility(View.VISIBLE);
        pl9fouls = 0;f9.setText("" + 0);pl9.setVisibility(View.VISIBLE);
        pl10fouls = 0;f10.setText("" + 0);pl10.setVisibility(View.VISIBLE);
        btns.setVisibility(View.GONE);
        startGame.setVisibility(View.VISIBLE);
        reset.setVisibility(View.GONE);
        endGame.setVisibility(View.GONE);
        teamAplayersheet.setVisibility(View.VISIBLE);
        teamBplayersheet.setVisibility(View.VISIBLE);
    }
    public void Start(View view) {shareResults.setText("************Game ON************");
        reset.setVisibility(View.VISIBLE);
        playersSummary.setVisibility(View.GONE);
        btns.setVisibility(View.GONE);
        startGame.setVisibility(View.GONE);
        endGame.setVisibility(View.VISIBLE);

        display();
    }


    public  void onEndGame(View view){
        btns.setVisibility(View.VISIBLE);shareResults.setText("Full Time Results: \n" +message());
        startGame.setVisibility(View.GONE);
        endGame.setVisibility(View.GONE);
        reset.setVisibility(View.VISIBLE);
        onFT();

    }
    public void onFT(){
        teamAplayersheet.setVisibility(View.GONE);
        teamBplayersheet.setVisibility(View.GONE);
        teamsSheet.setVisibility(View.GONE);
        playersSummary.setVisibility(View.VISIBLE);
        playersSummary.setText(playerSummary());
    }

    public void display(){
        mainTitle.setVisibility(View.VISIBLE);
        teamsSheet.setVisibility(View.VISIBLE);
        playersSheet.setVisibility(View.VISIBLE);
    }

    public String playerSummary(){
        String message = "  player name   " + " no. of Fouls \n "+
                         "Team A \n"+
                       pl1.getText()+"             "+f1.getText()+"\n"+
                       pl2.getText()+"             "+f2.getText()+"\n"+
                       pl3.getText()+"             "+f3.getText()+"\n"+
                       pl4.getText()+"             "+f4.getText()+"\n"+
                       pl5.getText()+"             "+f5.getText()+"\n"+
                       "Team B\n"+
                       pl6.getText()+"              "+f6.getText()+"\n"+
                       pl7.getText()+"              "+f7.getText()+"\n"+
                       pl8.getText()+"              "+f8.getText()+"\n"+
                       pl9.getText()+"              "+f9.getText()+"\n"+
                       pl5.getText()+"              "+f10.getText()+"\n"
                ;
        return message;
    }

}
package com.example.openmath.openmath.controleur;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.LecteurAudio;
import com.example.openmath.openmath.modele.Question;
import com.example.openmath.openmath.modele.QuestionBanque;
import com.example.openmath.openmath.modele.Session;


import java.util.Locale;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MultiplesChoixActivite extends OpenMathActivite implements View.OnClickListener
{

    private TextView partie;
    private ImageView horloge ;
    private Question questionCourante;
    private TextView phraseDeBienvenue;
    private Button boutonReponseChoix1;
    private Button boutonReponseChoix2;
    private Button boutonReponseChoix3;
    private Button boutonReponseChoix4;
    private Button boutonPrecedent;
    private Button boutonTest;
    private Button MenuA ;
    private TextView numeroQuestion;
    private TextView tempsTimer ;
    private CountDownTimer timer;
    private ImageButton imageVolumeUp ;
    private boolean enableTouchEvents = true;
    private TextToSpeech textToSpeech;
    private Vibrator vibration ;
    private int codeActivite = QuestionBanque.getCodeActivite();
    private int totalQuestion = (QuestionBanque.getListQuestion() == null)?0: QuestionBanque.getListQuestion().size();

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité MultiplesChoixActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(Session.getUser()!= null || ConfigurationTest.fakeConnecter){

            getMenuInflater().inflate(R.menu.menu_main,menu); }
        else {
            getMenuInflater().inflate(R.menu.menu_cnx,menu);
        }
        return true;
    }

    /**
     * @atuhor Oualid BENAZZOUZ
     * Cette méthodes liste les choix atteignable depuis le menu déroulant dans l'activité apropro.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.acceuil:
                intent = new Intent(MultiplesChoixActivite.this, MenuActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.signalement:
                intent = new Intent(MultiplesChoixActivite.this, SignalementActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.scoreCourant:
                intent = new Intent(MultiplesChoixActivite.this, ScoreActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.settings:
                intent = new Intent(MultiplesChoixActivite.this, SettingsActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(MultiplesChoixActivite.this, AProposActivite.class);
                textToSpeech.stop();
                break ;

            case R.id.menufeedback:
                intent = new Intent(MultiplesChoixActivite.this, FeedbackActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.deconnection:
                intent = new Intent(MultiplesChoixActivite.this, BienvenueActivite.class);
                textToSpeech.stop();
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(MultiplesChoixActivite.this,IdentificationActivite.class);
                textToSpeech.stop();
                break ;


             /*   if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                }
                else{
                    findViewById(R.id.groupmenu).setVisibility(View.GONE);
                } */

        }
        if (intent != null)                 startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
    /**
     * @author alexandre lallis
     * Cette méthode créer l'activité MultipleChoixActivite à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multipleschoix_activite);


        //On relie les widgets des activité au code java.
        phraseDeBienvenue = findViewById(R.id.texteQuestion);
        boutonReponseChoix1 = findViewById(R.id.boutonReponseChoix1);
        boutonReponseChoix2 = findViewById(R.id.boutonReponseChoix2);
        boutonReponseChoix3 = findViewById(R.id.boutonReponseChoix3);
        boutonReponseChoix4 = findViewById(R.id.boutonReponseChoix4);
        tempsTimer = findViewById(R.id.tempsTimer);
        boutonPrecedent = findViewById(R.id.boutonPrecedent);
        boutonTest = findViewById(R.id.boutonTest);
        numeroQuestion = findViewById(R.id.numeroQuestion);
        imageVolumeUp = findViewById(R.id.imageVolumeUp);
        horloge = findViewById(R.id.horlogeimg);
        partie = findViewById(R.id.textEntraineToi);
        MenuA = findViewById(R.id.MenuA) ;


        /*
        Les tags permettent de différencier les boutons étant donné qu'il à été choisi de ne redéfinir qu'une fois
        la méthode onClick.
         */
        boutonReponseChoix1.setTag(1);
        boutonReponseChoix2.setTag(2);
        boutonReponseChoix3.setTag(3);
        boutonReponseChoix4.setTag(4);
        boutonPrecedent.setTag(5);
        boutonTest.setTag(6);

        /*
        Ajout des listeners sur les bouton afin d'utiliser la méthode onClick
        de l'interface View.OnClickListener qui permet de ne pas redéfinir pour chaque bouton une methode onClick.
        */

        boutonReponseChoix1.setOnClickListener(this);
        boutonReponseChoix2.setOnClickListener(this);
        boutonReponseChoix3.setOnClickListener(this);
        boutonReponseChoix4.setOnClickListener(this);
        boutonPrecedent.setOnClickListener(this);
        boutonTest.setOnClickListener(this);
        vibration = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if(!ConfigurationTest.isInstrumenteTest)
        {
            questionCourante = QuestionBanque.getQuestion();
            QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1);
            afficherQuestion(questionCourante);
        }



        if(QuestionBanque.isModeDefi())
        {
            if(QuestionBanque.getIsPremierDefi())
            {
                startTimerDefi(60);
                QuestionBanque.setIsPremierDefi(false);
            }
            else
            {
                startTimerDefi(QuestionBanque.getValeurTimerDefi());
            }
        }


        initialisationTextToSpeech();


        MenuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiplesChoixActivite.this,MenuActivite.class) ;
                startActivity(intent);
            }
        });

    }

    /**
     * @author Alexandre Lallis
     * Initialise le text to speech.
     */
    public void initialisationTextToSpeech()
    {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.FRANCE);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("textToSpeach", "Language not supported (FRENCH ONLY)");
                    }
                    else {
                        imageVolumeUp.setEnabled(true);
                    }
                } else {
                    Log.e("textToSpeach", "Echec de l'inilisation");
                }
            }
        });

        imageVolumeUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                speak();
            }
        });
    }

    /**
     * @author Alexandre Lallis
     * Cette fonctione parle le texte avec un text to speach.
     */
    private void speak() {
        String text = questionCourante.getEnonce() ;
        if(boutonReponseChoix1.getText() != "")
        {
            text += " " + boutonReponseChoix1.getText() ;
        }
        if(boutonReponseChoix2.getText() != "")
        {
            text += " " + boutonReponseChoix2.getText();
        }
        if(boutonReponseChoix3.getText() != "")
        {
            text += " " + boutonReponseChoix3.getText();
        }
        if(boutonReponseChoix4.getText() != "")
        {
            text += " " + boutonReponseChoix4.getText();
        }
        float volume = (float) 1 ;
        float vitesse = (float) 0.5 ;

        textToSpeech.setPitch(volume);
        textToSpeech.setSpeechRate(vitesse);
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    /**
     * @author Alexandre Lallis
     * Cette fonction est appelé à la destruction de l'activité. Et le textToSpeech est fermé ici.
     */
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }



    /**
     * @autor alexandre lallis
     * Cette methode est appeler en fin de jeu (mode test uniquement), et affiche le score de l'utilisateur sur le jeu joué dans une petite fênetre
     * puis renvoie au niveaux.
     * @param : rien
     * @return : rien
     */
    public void finDefi()
    {
        QuestionBanque.setIsPremierDefi(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Le defi est terminé!")
                .setMessage("Votre score est de :  " + QuestionBanque.getScoreMaximalDefi())
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        reininitialisationParametres();
                        Intent niveauxIntent = new Intent(MultiplesChoixActivite.this,DefiActivite.class) ;
                        startActivity(niveauxIntent);
                    }

                })
                .setCancelable(false)
                .create()
                .show();
    }


    /**
     * @author alexandre lallis
     * Cette fonction réinitialise les paramètres de questionBanque et arrete le timer.
     */
    public void reininitialisationParametres()
    {
        QuestionBanque.setScoreMaximalDefi(0);
        QuestionBanque.setModeTest(false);
        QuestionBanque.setModeDefi(false);
        QuestionBanque.setCompteurDefi(0);
        QuestionBanque.setIsPremierDefi(true);
        textToSpeech.stop();
        stopTimer();
    }

    /**
     * @autor alexandre lallis
     * Cette methode est appeler en fin de jeu (mode test uniquement), et affiche le score de l'utilisateur sur le jeu joué dans une petite fênetre
     * puis renvoie au niveaux.
     * @param : rien
     * @return : rien
    */
   public void finTest()
   {
       /*
         On arrete le timer, on remet le mode test à false et on affiche le score avant de revenir
        aux choix des niveaux (methode finTest() ).
        */
        QuestionBanque.setModeTest(false);
        stopTimer();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Enregistrer le score
        QuestionBanque.attribuerScore();
        saveCurrentScores();
        builder.setTitle("Yeah vous avez fini un niveau!")
                .setMessage("Votre score est de :  " + QuestionBanque.getScoreCourant() + "/" + totalQuestion*QuestionBanque.getDifficulteCourante())
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent niveauxIntent = new Intent(MultiplesChoixActivite.this,NiveauxActivite.class) ;

                        if(codeActivite <= 10)
                        {
                            niveauxIntent.putExtra("CodeThemes",1);
                        }
                        else if(codeActivite >= 11 && codeActivite <= 20)
                        {
                            niveauxIntent.putExtra("CodeThemes",11);
                        }
                        else if(codeActivite >= 21 && codeActivite <= 30)
                        {
                            niveauxIntent.putExtra("CodeThemes",21);
                        }
                        else
                        {
                            niveauxIntent.putExtra("CodeThemes",31);
                        }

                        startActivity(niveauxIntent);
                    }

                })
                .setCancelable(false)
                .create()
                .show();
    }


    /**
     * @autor alexandre lallis
    Cette fonction est appeler automatiquement lors d'un click sur un bouton par l'utilisateur. Les boutons sont
    différencier par leur tags. Le bouton precedent renvoie en arrière, le bouton test activite le mode test où les
    question sont minuté et le score comptabilisé avec un nombre de question donné. Le reste des boutons sont les boutons
    correspondant aux réponse de la question, lors de l'appui sur un de ces boutons on colorie le ou les boutons, en
    rouge ou vert indiquant une bonne ou mauvaise réponse. Par défaut il n'y a pas de fin au jeu, c'est un entrainement,
    il faut activer le mode test et la méthode finTest() est appelé si le nombre de questions à été épuisé.
    @param : un objet view.
    @return : rien
    */
    @Override
    public void onClick(View view)
    {

        int reponseIndex = (int) view.getTag();


        //Si appuie sur le bouton Precedent
        if (reponseIndex == 5)
        {
            reininitialisationParametres();
            finish();
        }

        //Si appuie sur le bouton test
        else if (reponseIndex == 6)
        {
            activationDuModeTest();
        }
        //Si appuie sur l'un des bouton comportant une reponse.
        else
        {
            textToSpeech.stop();
            if (reponseIndex == questionCourante.getReponseIndex())
            {
                bonneReponse();
            }
            else
            {
                mauvaiseReponse(reponseIndex);
            }

            //On désactive le tactile avant le handler de 2sec.
            enableTouchEvents = false;
            /*
            Cette handler dure 2 sec et permet à l'utilisateur d'observer le résultat(bonne ou mauvaise réponse) ainsi
            que la correction. Pendant c'est 2 secondes le tactile est désactivité (ergonomie).
            */
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    enableTouchEvents = true;

                    //Si l'ensemble des question générer on été joué.
                    if (QuestionBanque.getQuestionSuivanteIndex() == QuestionBanque.getListQuestion().size())
                    {
                        //Si le mode test est actif.
                        if (QuestionBanque.isModeTest())
                        {
                            finTest();
                        }
                        else if(QuestionBanque.isModeDefi())
                        {
                            switch (QuestionBanque.getCodeDefi())
                            {
                                case 1: QuestionBanque.changementJeuDefi1();
                                    break;
                                case 2: QuestionBanque.changementJeuDefi2();
                                    break;
                                case 3: QuestionBanque.changementJeuDefi3();
                                    break;
                                case 4: QuestionBanque.changementJeuDefi4();
                                    break;

                            }
                            QuestionBanque.setScoreMaximalDefi(QuestionBanque.getScoreCourant() + QuestionBanque.getScoreMaximalDefi());
                            boucleJeu();
                        }
                        //Si le mode test n'est pas actif et qu'il n'y à plus de question à jouer, on boucle.
                        else
                        {
                           boucleJeu();
                        }
                    }
                    //Si il reste des questions à jouer
                    else
                    {
                        jouerQuestion();
                    }
                }
            }, 2000); // 2 sec

        }
    }

    /**
     * @author alexandre lallis
     * Cette fonction récupére la question courante et l'affichage.
     */
    public void jouerQuestion()
    {
        questionCourante = QuestionBanque.getQuestion();
                        /*
                           On vérifie que la question est bien au format multiples réponse (soit avec plusieurs bouton, sinon on va dans
                          l'activité aproprié.
                         */
        if (questionCourante.getFormat() == 2)
        {
            Intent SaisieReponseActiviteIntent = new Intent(MultiplesChoixActivite.this, SaisieReponseActivite.class);
            QuestionBanque.setValeurTimerDefi(Integer.parseInt(tempsTimer.getText().toString()));
            startActivity(SaisieReponseActiviteIntent);
        }
        else
        {
                            /*
                             On incrémente l'index de la question courante, on enleve le coloriage des bouton (rouge et vert suite au
                             bonne ou mauvaise réponse) puis on affiche la nouvelle question.
                             */
            QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1);
            resetColoriage();
            if(QuestionBanque.isModeTest())
            {
                //On arrete le timer avant dans relancer un nouveau.
                stopTimer();
                startTimerTest(30);
            }
            afficherQuestion(questionCourante);
        }


    }

    /**
        Cette fonction active le mode test.
     */
    public void activationDuModeTest()
    {
        QuestionBanque.setModeTest(true);
        QuestionBanque.initialisationQuestionBanque(QuestionBanque.getDifficulteCourante());
        questionCourante = QuestionBanque.getQuestion();
        if (questionCourante.getFormat() == 2)
        {
            Intent SaisieReponseActiviteIntent = new Intent(MultiplesChoixActivite.this, SaisieReponseActivite.class);
            QuestionBanque.setValeurTimerDefi(Integer.parseInt(tempsTimer.getText().toString()));
            startActivity(SaisieReponseActiviteIntent);
        }
        else
        {
            QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1);
            resetColoriage();
            startTimerTest(30);
            afficherQuestion(questionCourante);
            boutonTest.setEnabled(false);
            boutonTest.setVisibility(View.GONE);
        }
    }

    /**
     * @author alexandre lallis
     * Cette fonction confirme une bonne réponse en jouant un son et mettant en vert la bonne réponse.
     */
    public void bonneReponse() {
        /*
            On colorie la bonne réponse en vert et on incrémente le score.
            De plus qu'on joue un le son pour la bonne réponse
            */
        Log.e("MuliplesChoix", "wow i click right response");
        colorierBoutonBonneReponse(questionCourante.getReponseIndex());
        LecteurAudio.jouerBruitage(this, R.raw.bonne_reponse);
        QuestionBanque.setScoreCourant(QuestionBanque.getScoreCourant() + 1*QuestionBanque.getDifficulteCourante());
        if (QuestionBanque.isModeTest() || QuestionBanque.isModeDefi()) {
            Intent intent = this.getIntent();
            // recuperer la catogory de question depuis Intent reçu
            if (intent.hasExtra(IntentKeys.CURRENT_QUESTION_CATEGORY)) {
                String currentQuestionCategory = intent.getStringExtra(IntentKeys.CURRENT_QUESTION_CATEGORY);

                int currentScoreCategory = intent.getIntExtra("qc" + currentQuestionCategory, 0);
                Log.e("multipleChoix", currentQuestionCategory);
                //Session.addScore(currentQuestionCategory, 1);
                intent.putExtra("qc" + currentQuestionCategory, currentScoreCategory + 1);
                //Session.addScore(currentQuestionCategory, currentScoreCategory +1);


            } else if(QuestionBanque.isModeDefi()) {
                intent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY, "Defi");
                int currentScore = intent.getIntExtra("qcDefi", 0);
                intent.putExtra("qcDefi", currentScore+1);
                Session.addScore("Defi",currentScore+1);
                saveCurrentScores();
            }else {
                Log.e("MultipleChoix", "intent does not have extra");
            }
        }
    }

    /**
     * @author alexandre lallis
     * Cette fonctione permet de regénéré de nouvelle question, une fois les questions généré de base épuisé.
     */
    public void boucleJeu()
    {
         /*
           Lorsqu'on atteint la fin du nombre de question générer,
           on génére de nouvelles questions et on continue le jeu en boucle jusqu'a que l'utilisateur revienne en
           arrière ou active le mode test.
         */
        QuestionBanque.initialisationQuestionBanque(QuestionBanque.getDifficulteCourante());
        questionCourante = QuestionBanque.getQuestion();
          /*
          On vérifie que la question est bien au format multiples réponse (soit avec plusieurs bouton, sinon on va dans
          l'activité aproprié.
          */
        if (questionCourante.getFormat() == 2)
        {
            Intent SaisieReponseActiviteIntent = new Intent(MultiplesChoixActivite.this, SaisieReponseActivite.class);
            QuestionBanque.setValeurTimerDefi(Integer.parseInt(tempsTimer.getText().toString()));
            startActivity(SaisieReponseActiviteIntent);
        }
        else
        {

            /*
           On incrémente l'index de la question courante, on enleve le coloriage des bouton (rouge et vert suite au
          bonne ou mauvaise réponse) puis on affiche la nouvelle question.
          */
            QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1);
            resetColoriage();
            afficherQuestion(questionCourante);
        }
    }

    /**
     * @author alexandre lallis
     * Cette fonction met en évidance une mauvaise réponse par l'affichage de la bonne réponse en vert et de la
     * mauvaise en rouge. Et une légére vibration avec un son;
     * @param reponseIndex
     */
    public void mauvaiseReponse(int reponseIndex)
    {
        /*
          On colorie en rouge la mauvaise réponse et la bonne réponse en vert.
          Et on joue le son pour une mauvaise réponse avec une petite vibration.
          */
        colorierBoutonBonneReponse(questionCourante.getReponseIndex());
        colorierBoutonMauvaiseReponse(reponseIndex);
        LecteurAudio.jouerBruitage(this,R.raw.mauvaise_reponse);
        if(SettingsActivite.isVibration)
        {
            vibration.vibrate(50);
        }
    }

    /**
     * @author alexandre lallis
     * Lance le timer defi. A la fin du timer defi, le score est affiché et l'utilisateur est renvoyé au defi.
     * @param s
     */
    private void startTimerDefi(int s)
    {
        timer = new CountDownTimer(s * 1000,1000)
        {
            /**
             * @author alexandre lallis
             * Cette méthode actualise l'affichage du timer toutes les secondes.
             * @param l
             */
            @Override
            public void onTick(long l)
            {
                tempsTimer.setText("" + (l / 1000));
            }

            @Override
            public void onFinish()
            {
                if(textToSpeech != null) {
                    textToSpeech.stop();
                }
                finDefi();
            }
        };

        timer.start();
    }

    /**
     * @author alexandre lallis
     * Cette méthode lance un timer de s secondes.
     * @param s
     */
    private void startTimerTest(int s)
    {
        tempsTimer.setText("30");
        tempsTimer.setTextColor(Color.WHITE);
        timer = new CountDownTimer(s * 1000,1000)
       {
           /**
            * @author alexandre lallis
            * Cette méthode actualise l'affichage du timer toutes les secondes.
            * @param l
            */
            @Override
            public void onTick(long l)
            {
             tempsTimer.setText("" + (l / 1000));
            }

           /**
            * @author alexandre lallis
            * Cette méthode est appelé à la fin du timer. La fin du timer correspont à une mauvaise réponse, on colorie
            * alors la bonne réponse, on met le timer en rouge , on joue le son de mauvaise réponse etc.. puis on passe à
            * la question suivante en réinitialisant le timer. Sauf dans le cas où c'est la dernier question la méthode
            * finTest() est appelé.
            */
           @Override
            public void onFinish()
            {
                enableTouchEvents = false;
                textToSpeech.stop();
                tempsTimer.setTextColor(Color.RED);
                if(SettingsActivite.isVibration)
                {
                    vibration.vibrate(50);
                }
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        colorierBoutonBonneReponse(questionCourante.getReponseIndex());
                        if(QuestionBanque.getQuestionSuivanteIndex()==QuestionBanque.getListQuestion().size())
                        {
                            QuestionBanque.setModeTest(false);
                            finTest();
                        }
                        else
                        {
                            jouerQuestion();
                        }
                        enableTouchEvents = true;

                    }
                }, 2000); // 2 sec

            }
        };

       timer.start();
    }

    /**
     * @author alexandre lallis
     * Cette méthode permet de stopper le timer, il est nécessaire de stopper le timer car celui utilise
     * les threads et ne s'arrete pas avec la fin de l'activité ou lorsque que l'on relance un
     * nouveau timer.
     */
    private void stopTimer()
    {
        if(timer != null)
        {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * @autor alexandre lallis
     * Cette méthode active ou désactive le tactile en fonction de la valeur de enableTouchEvents.
     * Cette méthode est ici utilisé pour désactive le tactile pendant le handler de 2sec qui permet
     * d'afficher la bonne ou mauvaise réponse.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if (enableTouchEvents)
            return super.dispatchTouchEvent(ev);
        return true;
    }


    /**
     * @autor alexandre lallis
     * Cette fonction change la couleur du bouton appuyer en vert.(Appeler en cas de bonne réponse)
     */
    private void colorierBoutonBonneReponse(int tag)
    {
        switch (tag)
        {
            case 1 :
                boutonReponseChoix1.setBackgroundColor(Color.GREEN);
                break;
            case 2 :
                boutonReponseChoix2.setBackgroundColor(Color.GREEN);
                break;
            case 3 :
                boutonReponseChoix3.setBackgroundColor(Color.GREEN);
                break;
            case 4 :
                boutonReponseChoix4.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    /**
     * @autor alexandre lallis
     * Cette fonction change la couleur du bouton appuyer en rouge.(Appeler en cas de mauvaise réponse)
     */
    private void colorierBoutonMauvaiseReponse(int tag)
    {
        switch(tag)
        {
            case 1 :
                boutonReponseChoix1.setBackgroundColor(Color.RED);
                break;
            case 2 :
                boutonReponseChoix2.setBackgroundColor(Color.RED);
                break;
            case 3 :
                boutonReponseChoix3.setBackgroundColor(Color.RED);
                break;
            case 4 :
                boutonReponseChoix4.setBackgroundColor(Color.RED);
                break;
        }
    }

    /**
     * @autor alexandre lallis
     * Cette fonction enleve background.color qui à pu être mis lors de
     * bonne ou mauvaise réponse. Plus précisément en cas de bonne réponse affichage vert
     * , mauvaise réponse affiche rouge et cette fonctionne enleve les couleurs.
     */
    private void resetColoriage()
    {
        boutonReponseChoix1.setBackgroundColor(Color.WHITE);
        boutonReponseChoix2.setBackgroundColor(Color.WHITE);
        boutonReponseChoix3.setBackgroundColor(Color.WHITE);
        boutonReponseChoix4.setBackgroundColor(Color.WHITE);
    }

    /**
     * @autor alexandre lallis
     * Cette fonction modifie le texte de base présent sur les boutons pour afficher celui qui correspond
     * aux réponses de la question courante. Si un bouton réponse n'est pas utilisé sont texte est changé
     * en une chaine vide par le développeur .Ainsi le bouton réponse non utilisé sera désactivité
     * et rendu invisible grace à la methode cacherWidget().
     * //@param Cette fonctione ne prends aucun paramètre.
     * @return Ne renvoie rien
    */
    private void afficherQuestion(final Question question)
    {
        phraseDeBienvenue.setText(question.getEnonce());
        boutonReponseChoix1.setText(question.getlistChoixPossibles().get(0));
        boutonReponseChoix2.setText(question.getlistChoixPossibles().get(1));
        boutonReponseChoix3.setText(question.getlistChoixPossibles().get(2));
        boutonReponseChoix4.setText(question.getlistChoixPossibles().get(3));
        String text = QuestionBanque.getQuestionSuivanteIndex() + "/" + totalQuestion;
        numeroQuestion.setText(text);
        this.cacherWidget();
    }



    /**
     * @autor alexandre lallis
     *  Cette fonction permet de cacher et de désactiver les boutons réponses qui ne sont pas utilisé.Ainsi que le
     *  textView utilisé uniquement pendant le mode Test.
     *  Un bouton réponse non utilisé correspond à un texte vide. (Par défaut on propose 4 réponses mais parfois
     *  seulement 3.)
     *  Cette méthode permet aussi de cache le bouton test lorsqu'il est activité et de rendre visible le timer pendant
     *  le mode test.
     *  //@param Cette fonctione ne prends aucun paramètre.
     *  @return Ne renvoie rien
     */
    private void cacherWidget()
    {

        if (phraseDeBienvenue.getText().length() == 0)
        {
            phraseDeBienvenue.setVisibility(INVISIBLE);
        }
        if (boutonReponseChoix1.getText().length() == 0)
        {
            boutonReponseChoix1.setVisibility(View.GONE);
        }
        if (boutonReponseChoix2.getText().length() == 0)
        {
            boutonReponseChoix2.setVisibility(View.GONE);
        }
        if (boutonReponseChoix3.getText().length() == 0)
        {
            boutonReponseChoix3.setVisibility(View.GONE);
        }
        if (boutonReponseChoix4.getText().length() == 0)
        {
            boutonReponseChoix4.setVisibility(View.GONE);
        }
        if(QuestionBanque.isModeTest())
        {
            numeroQuestion.setVisibility(VISIBLE);
            boutonTest.setVisibility(View.GONE);
            tempsTimer.setVisibility(VISIBLE);
            horloge.setVisibility(VISIBLE);
            partie.setText("TEST");
        }
        else if(QuestionBanque.isModeDefi())
        {
            boutonTest.setVisibility(View.GONE);
            numeroQuestion.setVisibility(VISIBLE);
            tempsTimer.setVisibility(VISIBLE);
            partie.setText("DÉFI");
        }
        else
        {
            numeroQuestion.setVisibility(INVISIBLE);
            tempsTimer.setVisibility(INVISIBLE);
            horloge.setVisibility(View.GONE);
            partie.setText("Entraine toi");

        }


    }







}




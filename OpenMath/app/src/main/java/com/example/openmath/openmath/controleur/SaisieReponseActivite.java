package com.example.openmath.openmath.controleur;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import android.view.KeyEvent;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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


public class SaisieReponseActivite extends OpenMathActivite
{

    private TextView partie;
    private ImageView horloge ;
    private TextView enonce ;
    private EditText champReponse ;
    private Button boutonValidation;
    private Button boutonPrecedent ;
    private Button boutonMenuA ;
    private Question questionCourante;
    private Button boutonTest;
    private TextView numeroQuestion ;
    private TextView correctionReponse ;
    private TextView tempsTimer;
    private CountDownTimer timer;
    private Context context = this;
    private boolean enableTouchEvents = true;
    private ImageButton imageVolumeUp ;
    private TextToSpeech textToSpeech ;
    private Vibrator vibration ;
    private int codeActivite = QuestionBanque.getCodeActivite();
    private int totalQuestion = (QuestionBanque.getListQuestion() == null)?0: QuestionBanque.getListQuestion().size();

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité saisieReponseActivite.
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
                intent = new Intent(SaisieReponseActivite.this, MenuActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.signalement:
                intent = new Intent(SaisieReponseActivite.this, SignalementActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.scoreCourant:
                intent = new Intent(SaisieReponseActivite.this, ScoreActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.settings:
                intent = new Intent(SaisieReponseActivite.this, SettingsActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(SaisieReponseActivite.this, AProposActivite.class);
                textToSpeech.stop();
                break ;

            case R.id.menufeedback:
                intent = new Intent(SaisieReponseActivite.this, FeedbackActivite.class);
                textToSpeech.stop();
                break ;


            case R.id.deconnection:
                intent = new Intent(SaisieReponseActivite.this, BienvenueActivite.class);

                saveCurrentScores();

                textToSpeech.stop();
                OpenMathActivite.deconnecter();

                break ;


            case R.id.boutonPrecedent:
                this.finish();
                textToSpeech.stop();
                break ;


            case R.id.seconnecter:
                intent = new Intent(SaisieReponseActivite.this,IdentificationActivite.class);
                textToSpeech.stop();
                break ;

               /* if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                    System.out.print("Pas co");
                }
                else{
                    System.out.print("Pas co");
                    findViewById(R.id.groupmenu).setVisibility(View.GONE);
                } */

        }
        if (intent != null)                 startActivity(intent) ;

        return super.onOptionsItemSelected(item);
    }



    /**
     * Cette méthode créer l'activité saisie réponse activité à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saisie_reponse_activite);

        //On relie les widgets des activité au code java.
        enonce = findViewById(R.id.enonce);
        champReponse = findViewById(R.id.champReponse);
        boutonValidation = findViewById(R.id.boutonValidation);
        boutonPrecedent = findViewById(R.id.boutonPrecedent);
        boutonMenuA = findViewById(R.id.boutonMenuA) ;
        boutonTest = findViewById(R.id.boutonTest);
        numeroQuestion = findViewById(R.id.numeroQuestion);
        correctionReponse = findViewById(R.id.correctionReponse);
        tempsTimer = findViewById(R.id.tempsTimer);
        imageVolumeUp = findViewById(R.id.imageVolumeUp);
        horloge = findViewById(R.id.horlogeimg);
        partie = findViewById(R.id.textEntraineToi);
        final Context context = this;
        vibration = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        /*
        Cette méthode est appelé lors de l'edition du champ texte où l'utilisateur rentre sa réponse.
        Je m'assure que lorsque l'utilisateur valide sa saisie, le clavier se ferme.
        source: https://stackoverflow.com/questions/2342620/how-to-hide-keyboard-after-typing-in-edittext-in-android
         */
        champReponse.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    // do something, e.g. set your TextView here via .setText()
                    InputMethodManager imm = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        /*
        Cette méthode est appelé lors de l'appuie sur le bouton precedent.
        Lors de l'appuie sur le bouton précédent on renvoie l'utilisateur aux niveaux.
         */
        boutonPrecedent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                reininitialisationParametres();
                finish();
            }
        });


        boutonMenuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaisieReponseActivite.this,MenuActivite.class) ;
                startActivity(intent);
            }
        });


        /*
        Cette méthode est appelé lors de l'appuie sur le bouton validation.
        Lors de l'appuie sur le bouton validation on verifie si la réponse est correct au quel cas on affichera
        la réponse en rouge ou vert et dans le cas d'une mauvaise réponse la correction. Et pour finir on affiche
        la prochaine question.
         */
        boutonValidation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Je désactive le tactile pendant le handler de 2sec (ergonomie).
                enableTouchEvents = false;
                //Je regarde si la réponse est la bonne.

                if(questionCourante.getReponseSaisie().equals(champReponse.getText().toString()))
                {
                    bonneReponse();
                }
                else
                {
                    mauvaiseReponse();
                }
                new Handler().postDelayed(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        //Je réactive le tactile
                        enableTouchEvents = true;
                        //Je regarde si le nombre de question généré à été épuisé ou pas.
                        if (QuestionBanque.getQuestionSuivanteIndex() == QuestionBanque.getListQuestion().size())
                        {

                            //Je regarde si je suis en mode test , entrainement ou defi
                            if(QuestionBanque.isModeTest())
                            {
                                /*
                                Le nombre de question à été épuisé et je me trouve dans le le mode test.
                                Je met le mode test à false, stop le timer, affiche le score puis renvoie
                                l'utilisateur aux niveaux.
                                 */
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
                            //Si le mode test n'est pas actif
                            else
                            {
                                boucleJeu();
                            }
                        }
                        //Si le nombre de question généré n'a pas été épuise
                        else
                        {
                            jouerQuestion();
                        }
                    }
                }, 2000); // 2 sec

            }
        });

        //Lors de la l'appuie sur le le bouton test.
        boutonTest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*
                Je met le modeTest à true et je génére de nouvelle question pour l'évaluation.
                 */
                QuestionBanque.setModeTest(true) ;
                QuestionBanque.initialisationQuestionBanque(QuestionBanque.getDifficulteCourante());
                questionCourante = QuestionBanque.getQuestion();
                //Je vérifie que le format de la question courante soit la bon format (soit saisie réponse).
                if(questionCourante.getFormat() == 1)
                {
                    Intent SaisieReponseActiviteIntent = new Intent(SaisieReponseActivite.this, MultiplesChoixActivite.class);
                    QuestionBanque.setValeurTimerDefi(Integer.parseInt(tempsTimer.getText().toString()));
                    startActivity(SaisieReponseActiviteIntent);
                }
                else
                {

                    //J'incrémente l'index de la question courante, affiche la question courante et lance le timer.
                    QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1) ;
                    afficherQuestion(questionCourante);
                    startTimerTest(30);

                }



            }
        });

        /*
        Au lancement de l'activité je récupére la question courante généré précédament dans NiveauxActivité (via la fct initialiser banque).
        Incrémente l'index et affiche la question courante.
         */

        if(!ConfigurationTest.isInstrumenteTest)
        {
            questionCourante = QuestionBanque.getQuestion();
            QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1) ;
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

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.FRANCE);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("textToSpeach", "Language not supported (FRENCH ONLY)");
                    } else {
                        imageVolumeUp.setEnabled(true);
                    }
                }
                else {
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
    private void speak()
    {
        String text = questionCourante.getEnonce() ;
        float volume = (float) 1 ;
        float vitesse = (float) 0.5 ;

        textToSpeech.setPitch(volume);
        textToSpeech.setSpeechRate(vitesse);
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    /**
     * @author alexandre lallis
     * Cette fonction met en évidance une mauvaise réponse par l'affichage de la bonne réponse en vert et de la
     * mauvaise en rouge. Et une légére vibration avec un son;
     */
    private void mauvaiseReponse()
    {
        //La réponse est fausse, je colorie la mauvaise réponse en rouge et je rend visible la correction.
        champReponse.setTextColor(Color.RED);
        LecteurAudio.jouerBruitage(context,R.raw.mauvaise_reponse);
        if(SettingsActivite.isVibration)
        {
            vibration.vibrate(50);
        }
        correctionReponse.setVisibility(VISIBLE);
    }

    /**
     * @author alexandre lallis
     * Cette fonction confirme une bonne réponse en jouant un son et mettant en vert la bonne réponse.
     */
    private void bonneReponse()
    {
        //La réponse est bonne, je colorie la bonne réponse en vert et incrémente le score.
        //La réponse est bonne, je colorie la bonne réponse en vert et incrémente le score.
        champReponse.setTextColor(Color.GREEN);
        LecteurAudio.jouerBruitage(context,R.raw.bonne_reponse);
        QuestionBanque.setScoreCourant(QuestionBanque.getScoreCourant() + 1*QuestionBanque.getDifficulteCourante());
        Intent intent = SaisieReponseActivite.this.getIntent();
        String currentQuestionCategory = intent.getStringExtra(IntentKeys.CURRENT_QUESTION_CATEGORY);
        if(QuestionBanque.isModeDefi()) {
            if (currentQuestionCategory != null) {
                if(QuestionBanque.isModeDefi())
                Session.addScore(currentQuestionCategory, 1);
                saveCurrentScores();
                Log.e("saisieReponse", currentQuestionCategory);
                //String uc = intent.getStringExtra(IntentKeys.CURRENT_USER);
                int sc = Session.getScores().get(currentQuestionCategory);
                //setUtilisateurConnecte(uc);
                //String currentQuestionCategory = intent.getIntExtra("qc"+currentQuest,"");
                //Log.e("SaisieResponse",currentScoreCategory + "x");
                intent.putExtra("qc" + currentQuestionCategory, sc);
                intent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY, currentQuestionCategory);
                //Log.e("moi",getUtilisateurConnecte());

            } else {
                Log.e("SaisieRepActi", "bonneReponse : currentQuestionCategory null");
            }
        }
    }


    private void jouerQuestion()
    {
        //Je récupére la prochaine question
        questionCourante = QuestionBanque.getQuestion();
        //Je vérifie qu'il sagit bien d'une question au format saisieréponse sinon je bascule vers le format correspondant à la question.
        if(questionCourante.getFormat() == 1)
        {
            Intent SaisieReponseActiviteIntent = new Intent(SaisieReponseActivite.this, MultiplesChoixActivite.class);
            QuestionBanque.setValeurTimerDefi(Integer.parseInt(tempsTimer.getText().toString()));
            startActivity(SaisieReponseActiviteIntent);
        }
        else
        {
            QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1) ;
            //Je regarde si je suis dans le mode test pour savoir si je dois relancer un nouveau timer.
            if(QuestionBanque.isModeTest())
            {
                                    /*
                                    Je réinitialise le timer
                                     */
                stopTimer();
                startTimerTest(30);
            }
            //affiche la question courante.
            afficherQuestion(questionCourante);
        }

    }

    /**
     * @author alexandre lallis
     * Cette fonctione permet de regénéré de nouvelle question, une fois les questions généré de base épuisé.
     */
    private void boucleJeu()
    {
        /*
        Je ne suis pas dans le mode test, alors je génére de nouvelles questions.
        Et continue le jeu jusqu'à que l'utilisateur active le mode test ou utilise le bouton
        précédent.
        */
        QuestionBanque.initialisationQuestionBanque(QuestionBanque.getDifficulteCourante());
        questionCourante = QuestionBanque.getQuestion();
        //Je vérifie qu'il sagit bien d'une question au format saisieréponse sinon je bascule vers le format correspondant à la question.
        if(questionCourante.getFormat() == 1)
        {
            Intent SaisieReponseActiviteIntent = new Intent(SaisieReponseActivite.this, MultiplesChoixActivite.class);
            QuestionBanque.setValeurTimerDefi(Integer.parseInt(tempsTimer.getText().toString()));
            startActivity(SaisieReponseActiviteIntent);
        }
        else
        {
            //Le format est correct j'incrémente l'index qui cible la question courante et affiche la nouvelle question.
            QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1);
            afficherQuestion(questionCourante);
        }
    }

    /**
     * @author Alexandre Lallis
     * Cette fonction est appelé à la destruction de l'activité. Et le textToSpeech est fermé ici.
     */
    @Override
    protected void onDestroy()
    {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }

    /**
     * @autor alexandre lallis
     * Désactive le tactile pendant le handler de 2sec qui permet d'afficher la bonne ou mauvaise réponse.
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
                        Intent niveauxIntent = new Intent(SaisieReponseActivite.this,DefiActivite.class) ;
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
                if(textToSpeech != null){
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
             * Cette méthode est appelé lorsque le timer atteint 0s.
             */
            @Override
            public void onFinish()
            {
                /*
                Je met le champRéponse et le timer en rouge, affiche la correction et désactive le tactile
                pour le handler de 2sec qui permet à l'utilisateur de voir la correction (ergonomie).
                 */
                textToSpeech.stop();
                champReponse.setTextColor(Color.RED);
                correctionReponse.setVisibility(VISIBLE);
                tempsTimer.setTextColor(Color.RED);
                if(SettingsActivite.isVibration)
                {
                    vibration.vibrate(50);
                }
                enableTouchEvents = false;
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        // Je réactive le tactile
                        enableTouchEvents = true;

                        //Je vérifie si le nombre de question généré à été épuisé.
                        if (QuestionBanque.getQuestionSuivanteIndex() == QuestionBanque.getListQuestion().size())
                        {
                            /*
                            Le nombre de question à été épuisé et on est forcément dans le mode test puisque le timer
                            à c'est terminé et n'es utilisé que pour le mode test.
                            Je stop le timer et appel la méthode finTest().
                             */
                            QuestionBanque.setModeTest(false);
                            stopTimer();
                            finTest();
                        }
                        else
                        {

                            questionCourante = QuestionBanque.getQuestion();
                            //Je vérifie que le format de jeu soit correcte sinon je passe sur le bon format de jeu.
                            if(questionCourante.getFormat() == 1)
                            {
                                Intent SaisieReponseActiviteIntent = new Intent(SaisieReponseActivite.this, MultiplesChoixActivite.class);
                                QuestionBanque.setValeurTimerDefi(Integer.parseInt(tempsTimer.getText().toString()));
                                startActivity(SaisieReponseActiviteIntent);
                            }
                            else
                            {
                                /*
                                je passe à la question suivante, en réinitialisant le timer, incrémente l'index de la questionCourante
                                 et en récupérant la nouvelle question avant de l'afficher.
                                 */

                                QuestionBanque.setQuestionSuivanteIndex(QuestionBanque.getQuestionSuivanteIndex() + 1) ;
                                stopTimer();
                                startTimerTest(30);
                                afficherQuestion(questionCourante);
                            }


                        }
                    }
                }, 2000); // 2 sec

            }
        };
        //Je lance le timer.
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
     * Cette methode est appeler en fin de jeu (mode test uniquement), et affiche le score de l'utilisateur sur le jeu joué dans une petite fênetre
     * puis renvoie au niveaux.
     * @param : rien
     * @return : rien
     */
    private void finTest()
    {
        QuestionBanque.setModeTest(false);
        stopTimer();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        QuestionBanque.attribuerScore();
        saveCurrentScores();
        builder.setTitle("Yeah vous avez fini un niveau!")
                .setMessage("Votre score est de :  " + QuestionBanque.getScoreCourant() + "/" + totalQuestion*QuestionBanque.getDifficulteCourante())
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent niveauxIntent = new Intent(SaisieReponseActivite.this,NiveauxActivite.class) ;

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
     * Cette methode est met à jour l'affichage des questions , du champ texte et des textView utilisé.
     * @param : rien
     * @return : rien
     */
    private void afficherQuestion(final Question question)
    {
        enonce.setText(question.getEnonce());
        champReponse.setText("");
        champReponse.setTextColor(Color.WHITE);
        String text = QuestionBanque.getQuestionSuivanteIndex() + "/" + totalQuestion;
        numeroQuestion.setText(text);
        text = "La Bonne réponse était :  " + questionCourante.getReponseSaisie() ;
        correctionReponse.setText(text);
        correctionReponse.setVisibility(INVISIBLE);
        tempsTimer.setTextColor(Color.WHITE);
        cacherWidget();
    }



    /**
     * @autor alexandre lallis
     *  Cette fonction permet de cacher et de désactiver les boutons réponses qui ne sont pas utilisé.Ainsi que le
     *  textView utilisé uniquement pendant le mode Test.
     *  Un bouton réponse non utilisé correspond à un texte vide. (Par défaut on propose 4 réponses mais parfois
     *  seulement 3.)
     *  //@param Cette fonctione ne prends aucun paramètre.
     *  @return Ne renvoie rien
     */
    private void cacherWidget()
    {
        if(QuestionBanque.isModeTest())
        {
            numeroQuestion.setVisibility(VISIBLE);
            boutonTest.setEnabled(false);
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
        if(QuestionBanque.isModeDefi())
        {
            boutonTest.setVisibility(View.GONE);
        }

    }

}

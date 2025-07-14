package csc1035.project2;

import csc1035.project2.DBController.QuestionController;
import csc1035.project2.DBController.QuizController;
import csc1035.project2.DBController.Randomgenerate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.loader.plan.spi.QuerySpaceUidNotRegisteredException;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.*;

public class Testing {

    public static void main(String[] args) {
        // initialize a MCQuestion
        Question question1 = new Question();
        // create a question text
        question1.SetQuestion("What's the capital of England?");
        // create the topic of the question
        question1.SetTopic("General Knowledge");
        // assign a mark to the question
        question1.SetMarks(1);
        // create an Array list of options
        Option testOption1 = new Option("London");
        Option testOption2 = new Option("Brazil");
        Option testOption3 = new Option("Leeds");
        // create a new ArrayList of options
        ArrayList<Option> testOptions = new ArrayList<>(Arrays.asList(testOption1, testOption2, testOption3));
        // set the options of the example class to our list
        question1.SetOptions(testOptions);
        // print out the question
        System.out.println(question1.GetQuestion());
        // print out the options
        System.out.println(question1.GetOptions());
        // add an extra option
        Option testOption = new Option();
        testOption.SetOption("Newcastle");
        question1.AddOption(testOption);
        // print out the updated list
        System.out.println("Updated answer list: " + question1.GetOptions());
        // set the answer of the example question
        question1.SetAnswer("London");
        // check if the answered entered is correct
        String answerEntered = "London";
        System.out.println("Your answer " + answerEntered + " is: " + question1.CheckAnswer(answerEntered));
        // set the type of the question
        question1.SetType("MCQ");
        // print the type of the example question
        System.out.println("The type of the question is: " + question1.GetType());
        // print the topic of the example question
        System.out.println("The topic of the question is: " + question1.GetTopic());
        // print the mark of the question
        System.out.println("This question is worth: " + question1.GetMarks() + " points.");
        // print the question ID
        System.out.println("Question ID: " + question1.GetQuestionID());
        // update check answer with the answer entered
        question1.updateCheckAnswer(answerEntered);

        System.out.println("\n");


        // initialize a SAQuestion
        Question question2 = new Question();
        // create a question text
        question2.SetQuestion("Who's the prime minister of England?");
        // create the topic of the question
        question2.SetTopic("General Knowledge");
        // assign a mark to the question
        question2.SetMarks(5);
        // set the answer of the question
        question2.SetAnswer("Boris Johnson");
        // print the question
        System.out.println(question2.GetQuestion());
        // print the answer
        System.out.println(question2.GetAnswer());
        // check if the answered entered is correct
        String answer = "Boris Johnson";
        System.out.println("Your answer " + answer + " is: " + question2.CheckAnswer(answer));
        // enter an incorrect variable
        answer = "Trump";
        System.out.println("Your answer " + answer + " is: " + question2.CheckAnswer(answer));
        // print the topic of the example question
        System.out.println("The topic of the question is: " + question2.GetTopic());
        // print the mark of the question
        System.out.println("This question is worth: " + question2.GetMarks() + " points.");
        // set the type of the question
        question2.SetType("SAQ");
        // print out the type of the question
        System.out.println("The type of the question is: " + question2.GetType());
        // print the question ID
        System.out.println("Question ID: " + question2.GetQuestionID());
        // print out the question
        System.out.println(question2);
        // update check answer with the entered answer
        question2.updateCheckAnswer(answer);

        System.out.println("\n");
        // Create new question
        Question question3 = new Question();
        question3.SetQuestion("What is 5 x5?");
        question3.SetAnswer("25");
        question3.SetTopic("Maths");
        question3.SetType("SAQ");
        question3.SetMarks(1);
        String userAnswer = "25";
        question3.updateCheckAnswer(userAnswer);

        Quiz quiz1 = new Quiz();
        Quiz quiz2 = new Quiz();
        Quiz quiz3 = new Quiz();
        // Set the quiz topic
        quiz1.setqTopic("General Knowledge");
        quiz2.setqTopic("Maths");
        // link quiz to its questions
        quiz1.getQuestions().add(question1);
        quiz1.getQuestions().add(question2);
        quiz2.getQuestions().add(question3);
        // link options to questions
        testOption1.setQuestion(question1);
        testOption2.setQuestion(question1);
        testOption3.setQuestion(question1);
        // calculate and set the total marks of the quizzes
        quiz1.setTotalMarks(quiz1.getTotalMarks());
        quiz2.setTotalMarks(quiz2.getTotalMarks());

        // Test Create Question  method
        try {
            // initiate question controller
            QuestionController qController = new QuestionController();
            // create three questions in the database
            qController.Questioncreat(question1);
            qController.Questioncreat(question2);
            qController.Questioncreat(question3);
            // create options for the questions
            qController.Optioncreat(testOption1);
            qController.Optioncreat(testOption2);
            qController.Optioncreat(testOption3);
            // delete question with the ID 31
            qController.Questiondelete(31);
            // update question with the ID 4
            qController.questionupdate(4, "What's the capital of Brazil?","General Knowledge", 1, "Brazil");
        } catch (Exception e) {
            System.out.println("CreateQuestion failed, please try again...");
        }

        // Test create Quiz  method
        try {
            // initiate quiz controller
            QuizController quizC = new QuizController();
            // create the quizzes in the database
            quizC.Quizcreat(quiz1);
            quizC.Quizcreat(quiz2);
            quizC.Quizcreat(quiz3);
            // delete a quiz
            quizC.Quizdelete(10);
            // update a quiz
            quizC.Quizupdate(7, "General Knowledge");

        } catch (Exception e) {
            System.out.println("CreateQuiz failed, please try again...");

        }

        try{
            Randomgenerate r = new Randomgenerate();
            r.RandomIO();
        }catch (Exception e){
            System.out.println("Random generate failed, try again...");
        }


    }
}
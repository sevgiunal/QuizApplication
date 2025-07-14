package csc1035.project2;

import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity (name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int questionID;

    @Column
    private int marks;

    @Column
    private String question;

    @Column
    private String topic;

    @Column
    private String type;

    @Column
    private String answer;

    @Column
    private int CheckAnswer;

    /**
     * Returns the Failed? status of question.
     * @return the Failed? status.
     */
    public int getCheckAnswer() {
        return CheckAnswer;
    }

    /**
     * Sets the Failed? status of the question.
     * @param checkAnswer the new Failed? status.
     */
    public void setCheckAnswer(int checkAnswer) {
        CheckAnswer = checkAnswer;
    }

    /**
     * Returns the list of quizzes that this question is present in.
     * @return
     */
    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    @ManyToMany(mappedBy = "questions")
    private Set<Quiz> quizzes = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private List<Option> optionList;

    /**
     * Argument constructor method.
     * @param marks is the marks the question is worth.
     * @param question is the question string.
     * @param topic is the topic of the question.
     * @param type is the type of question.
     * @param answer is the answer string.
     * @param options is the array of option strings.
     */
    public Question(int marks, String question, String topic, String type, String answer, String[] options) {
        SetMarks(marks);
        SetQuestion(question);
        SetTopic(topic);
        SetType(type);
        SetAnswer(answer);
        if (GetType()=="MCQ") {
            ArrayList<Option> newOptions = new ArrayList<Option>();
            for (String i : options) {
                newOptions.add(new Option(i));
            }
            optionList = newOptions;
        }
    }

    /**
     * No arg constructor method.
     */
    public Question() {
    }

    /**
     * Checks if the given string contains the keyword.
     * @param answer is the answer attempt.
     * @return true if answer contains the keyword.
     */
    public boolean CheckAnswer(String answer) {
        if (answer.contains(GetAnswer())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds an option to the list of multiple choices.
     * @param option the new option/choice.
     * @return false if a method is encountered or duplicate is attempted, otherwise true.
     */
    public boolean AddOption(Option option) {
        boolean success = true;
        try {
            if (!optionList.contains(option)) {
                optionList.add(option);
            }
            else {
                System.out.println("AddOption failed, cannot add an option that is already present...");
                success = false;
            }
        }
        catch (Exception e) {
            System.out.println("AddOption encountered an error...");
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    //Getters & Setters
    /**
     * Returns the question answer.
     * @return the answer.
     */
    public String GetAnswer() {
        return this.answer;
    }

    /**
     * Sets the question answer.
     * @param answer the answer.
     * @return false if the method fails, true otherwise.
     */
    public boolean SetAnswer(String answer) {
        boolean success = true;
        try {
            this.answer = answer;
        }
        catch (Exception e) {
            System.out.println("SetAnswer encountered an error...");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    /**
     * Returns the QuestionID.
     * @return the QuestionID.
     */
    public int GetQuestionID() {
        return questionID;
    }

    /**
     * Returns the question type as a string.
     * @return the question type.
     */
    public String GetType() {
        return type;
    }

    /**
     * Returns the marks this question is worth.
     * @return the marks.
     */
    public int GetMarks() {
        return marks;
    }

    /**
     * Sets the marks this question is worth.
     * @param marks the marks.
     * @return false if the method fails, otherwise true.
     */
    public boolean SetMarks(int marks) {
        boolean success = true;
        try {
            this.marks = marks;
        }
        catch (Exception e) {
            System.out.println("SetMarks encountered an error...");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    /**
     * Returns the topic of the question.
     * @return the topic.
     */
    public String GetTopic() {
        return topic;
    }

    /**
     * Sets the topic of the question.
     * @param topic the topic.
     * @return false if the method fails, true otherwise.
     */
    public boolean SetTopic(String topic) {
        boolean success = true;
        try {
            this.topic = topic;
        }
        catch (Exception e) {
            System.out.println("SetTopic encountered an error...");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    /**
     * Returns the question string.
     * @return the question.
     */
    public String GetQuestion() {
        return question;
    }

    /**
     * Sets the question string.
     * @param question the question string.
     * @return false if the method fails, true otherwise.
     */
    public boolean SetQuestion(String question) {
        boolean success = true;
        try {
            this.question = question;
        }
        catch (Exception e) {
            System.out.println("SetQuestion encountered an error...");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    /**
     * Sets the type of the question.
     * @param type the type of the question.
     * @return false if the method fails, otherwise true.
     */
    protected boolean SetType(String type) {
        boolean success = true;
        try {
            if (this.type == null) {
                this.type = type;
            } else {
                System.out.println("SetType failed, cannot change an existing value...");
                success = false;
            }
        }
        catch (Exception e)
        {
            System.out.println("SetType encountered an error...");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    /**
     * Returns the question options.
     * @return the ArrayList of options.
     */
    public ArrayList<Option> GetOptions() {
        ArrayList<Option> optionArrayList = new ArrayList<Option>();
        optionArrayList.addAll(optionList);
        return optionArrayList;
    }

    /**
     * Sets the question options.
     * @param options is the ArrayList of question options.
     * @return false if an error occurred when setting, true otherwise.
     */
    public boolean SetOptions(ArrayList<Option> options) {
        boolean success = true;
        try {
            this.optionList = options;
        }
        catch (Exception e) {
            System.out.println("SetOptions encountered an error...");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    /**
     * updates Check answer whenever the user takes the quiz
     * @param answer the answer the user entered
     */
    public void updateCheckAnswer(String answer){
        //  uses the method check answer to check if the answer is true
        if (CheckAnswer(answer)){
            setCheckAnswer(1);
        }
        else {
            setCheckAnswer(0);
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", marks=" + marks +
                ", question='" + question + '\'' +
                ", topic='" + topic + '\'' +
                ", type='" + type + '\'' +
                ", answer='" + answer + '\'' +
                ", CheckAnswer=" + CheckAnswer +
                ", quizzes=" + quizzes +
                ", optionList=" + optionList +
                '}';
    }
}

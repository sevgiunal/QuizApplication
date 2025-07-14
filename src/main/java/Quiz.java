package csc1035.project2;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int QuizID;

    @Column
    private String qTopic;

    @Column
    private int totalMarks;

    @Column
    private int marksScored;

    @ManyToMany
    @JoinTable(
                name = "QUIZ_QUESTION",
                joinColumns = {@JoinColumn(name = "QuizID")},
                inverseJoinColumns = {@JoinColumn(name = "questionID")})
    private Set<Question> questions = new HashSet<>();

    /**
     * Sets the topic of the quiz.
     * @param qTopic is the topic.
     */
    public Quiz(String qTopic) {
        this.qTopic = qTopic;
    }

    /**
     * Sets the ID of the quiz.
     * @param quizID is the new ID value.
     */
    public void setQuizID(int quizID) {
        QuizID = quizID;
    }

    /**
     * The constructor method.
     */
    public Quiz() {
    }

    /**
     * Gets the question list
     * @return a list of questions
     */

    public Set<Question> getQuestions() {
        return questions;
    }

    /**
     * Returns the ID of the quiz.
     * @return the quiz ID.
     */
    public int getQuizID() {
        return this.QuizID;
    }

    /**
     * Returns the topic of the quiz.
     * @return the topic.
     */
    public String getqTopic() {
        return qTopic;
    }

    /**
     * Sets the topic of the quiz.
     * @param qTopic the topic.
     */
    public void setqTopic(String qTopic) {
        this.qTopic = qTopic;
    }

    /**
     * Returns the number of marks a user has scored.
     * @return the total marks scored.
     */
    public int getMarksScored() {
        return marksScored;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "QuizID=" + QuizID +
                ", qTopic='" + qTopic + '\'' +
                ", totalMarks=" + totalMarks +
                ", marksScored=" + marksScored +
                ", questions=" + questions +
                '}';
    }

    /**
     * Sets the total marks scored.
     * @param marksScored the total marks scored.
     */
    public void setMarksScored(int marksScored) {
        this.marksScored = marksScored;
    }

    /**
     * returns the total marks the quiz is worth.
     * @return the total marks.
     */
    public int getTotalMarks(){
        int i = 0;
        for (Question q: getQuestions())
            if(q.GetMarks()> 0){
                i++;
            }
        return i;
    }

    /**
     * Sets the total marks the quiz is worth.
     * @param totalmarks is the new total marks value.
     */
    public void setTotalMarks(int totalmarks){
        this.totalMarks = totalmarks;
    }
}

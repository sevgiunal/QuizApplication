package csc1035.project2;

import javax.persistence.*;

@Entity (name = "Option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int optionID;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Column
    private String option;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Question question;

    @Override
    public String toString() {
        return "Option{" +
                "option='" + option + '\'' +
                '}';
    }

    /**
     * The constructor method.
     */
    public Option() {
    }

    Option(String optionString) {
        SetOption(optionString);
    }

    /**
     * Returns the option string.
     * @return the option string.
     */
    public String GetOption() {
        return this.option;
    }

    /**
     * Sets the option string.
     * @param inOption the option string.
     * @return false if the method fails, true otherwise.
     */
    public boolean SetOption(String inOption) {
        boolean success = true;
        try {
            this.option = inOption;
        }
        catch (Exception e) {
            System.out.println("SetOption encountered an error");
            e.printStackTrace();
            success = false;
        }
        return success;
    }
}

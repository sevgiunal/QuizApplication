package csc1035.project2;

import java.util.*;
import csc1035.project2.DBController.QuestionController;
import javax.persistence.*;

import static java.lang.System.exit;

    public class IO {

        /**
         * Takes a collection of Strings and prints them line by line.
         * @param options
         */
        public static void Menu(String[] options) {
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("Choose an option from the menu : ");
        }

        ///////////////////////////////////////  Question Management ///////////////////////////////////////////////////
        /**
         * Creates a new question from user inputs, then adds it to the database.
         */
        private static boolean CreateQuestionIO() { //Create new question
            String qType;
            String[] qOptions = new String[3];
            String qQuestion;
            String qAnswer;
            String qTopic;
            System.out.println("Allowing user to create new questions");
            Scanner s = new Scanner(System.in);
            System.out.println("What is the question?:");
            qQuestion = s.nextLine();
            System.out.println("What is the type of question? (MCQ or SAQ): ");
            qType = s.nextLine().toUpperCase();
            if (qType.equals("MCQ")) {
                for (int i = 0; i < 3; i++) { //3 options per MCQ by design
                    System.out.println("What is option " + i + "?:");
                    qOptions[i] = s.nextLine();
                }
                System.out.println("What is the answer? (1/2/3):");
            } else {
                System.out.println("What is the answer?:");
            }
            qAnswer = s.nextLine().toUpperCase();
            System.out.println("What is the question topic?:");
            qTopic = s.nextLine().toUpperCase();
            boolean success = true;
            try {
                QuestionController qController = new QuestionController();
                Question newQuestion = new Question(1, qQuestion, qTopic, qType, qAnswer, qOptions);
                qController.Questioncreat(newQuestion);
            }
            catch (Exception e) {
                System.out.println("Question creation failed, please try again...");
                success = false;
            }
            return success;
        }

        /**
         * Deletes a question from the database.
         * @param qID the id of the question to be deleted.
         * @return false if the method fails/errors, true otherwise.
         */
        private static boolean DeleteQuestion(String qID) {
            boolean success = true;
            try {
                QuestionController qController = new QuestionController();
                qController.Questiondelete(Integer.parseInt(qID));
            }
            catch (Exception e) {
                System.out.println("Question deletion failed, please try again...");
                success = false;
            }
            return success;
        }

        /**
         * Allows a user to edit an attribute of a Question.
         * @param qID the id of the question to be edited.
         * @return false if the method fails/errors, true otherwise.
         */
        private static boolean EditQuestionIO(String qID) {
            boolean success = true;
            boolean valid = false;
            QuestionController qController = new QuestionController();
            Question sQuestion = qController.GetQuestion(qID);
            Scanner s = new Scanner(System.in);
            System.out.println("Selected question: ");
            qController.readQuestion(qID);
            while (valid = false) {
                System.out.println("1- Change Question");
                System.out.println("2- Change Answer");
                System.out.println("3- Edit Options");
                System.out.println("4- Change Topic");
                System.out.println("5- Exit Menu");
                System.out.println("Enter your choice here (1-4): ");
                String userChoice = s.nextLine();
                String editInfo;
                switch (userChoice) {
                    case "1":
                        System.out.println("Change Question selected...");
                        System.out.println("Enter the new question here: ");
                        editInfo = s.nextLine();
                        qController.questionupdate(Integer.parseInt(qID), editInfo,sQuestion.GetTopic(),1,sQuestion.GetAnswer());
                        valid = true;
                        break;
                    case "2":
                        System.out.println("Change Answer selected...");
                        System.out.println("Enter the new answer here: ");
                        editInfo = s.nextLine();
                        qController.questionupdate(Integer.parseInt(qID), sQuestion.GetQuestion(),sQuestion.GetTopic(),1,editInfo);
                        valid = true;
                        break;
                    case "3":
                        int optionChoice = 0;
                        System.out.println("Edit Options selected...");
                        while (optionChoice < 1 && optionChoice > 3) {
                            try {
                                System.out.println("Which option would you like to edit? (1-3): ");
                                optionChoice = s.nextInt();
                            }
                            catch (Exception e) {
                                System.out.println("That isn't a valid option, please try again...");
                            }
                        } //Doesn't allow user to enter invalid options
                        System.out.println("Enter the new option here: ");
                        editInfo = s.nextLine();
                        qController.questionupdate(Integer.parseInt(qID), sQuestion.GetQuestion(),sQuestion.GetTopic(),1,sQuestion.GetAnswer());
                        valid = true;
                        break;
                    case "4":
                        System.out.println("Change topic selected...");
                        System.out.println("Enter the new topic here: ");
                        editInfo = s.nextLine();
                        qController.questionupdate(Integer.parseInt(qID), sQuestion.GetQuestion(),editInfo,1,sQuestion.GetAnswer());
                        valid = true;
                        break;
                    case "5":
                        System.out.println("Exit selected...");
                        valid = true;
                        exit(0);
                    default:
                        System.out.println("That isn't a valid input, please try again...");
                        break;
                }
            }
            return success;
        } //UNFINISHED
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////  Quiz Management  /////////////////////////////////////////////////////
        public static boolean CreateQuizIO() {
            boolean success = true;
            return success;
        } //UNFINISHED

        public static boolean DeleteQuizIO(int qID) {
            boolean success = true;
            return success;
        } //UNFINISHED

        /**
         * Allows a user to edit an aspect of a quiz.
         * @param qID is the id of the quiz to be edited.
         * @return false if the method fails/errors, true otherwise.
         */
        public static boolean EditQuizIO(int qID) {
            boolean success = true;
            return success;
        } //UNFINISHED
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////  Other Main  ///////////////////////////////////////////////////////////
        /**
         * Allows a user to play through a selected quiz.
         * @param quizID the id of the quiz to be played.
         */
        public static void PlayQuiz(int quizID) {

        } //UNFINISHED

        public static void PrintStats() {

        } //UNFINISHED
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////  Main Menu Options  ////////////////////////////////////////////////////
        public void option1() { //Multiple users no longer planned
            Scanner Username = new Scanner(System.in);
            System.out.print("What is your username? \n");
            String getName = Username.nextLine();
            System.out.print(getName);
            @Entity
            @Access(AccessType.FIELD)
            class User<U> {
                @Transient
                private U value;

                @Access(AccessType.PROPERTY)
                private Username getValue() {
                    return (Username) value;
                }

                private void setValue(Username u) {
                    this.value = (U) u;
                    String U = String.valueOf(u);
                    if (getName.equals(U)) {
                        System.out.println("Your are logged in");
                    } else {
                        System.out.println("Username is not in the database");
                    }
                }

                @Embeddable
                class Username {
                }
            }
        }
        private static void option1New() {
            System.out.println("Running the quiz...");
            //PlayQuiz();
        } //UNFINISHED

        /**
         * Provides a CLI menu and takes user inputs and prints stats from the database to the commandline.
         */
        private static void option2() {
            System.out.println("Showing stats of the user:");
            Scanner s = new Scanner(System.in);
            boolean valid = false;
            while (valid == false) {
                System.out.println("1- Show total stats ");
                System.out.println("2- Find average mark");
                String option;
                try {option = s.nextLine();} //Protect against fatal input
                catch (Exception e) {
                    System.out.println("That is not a valid input, please try again...");
                    option = "invalid";}
                switch (option) {
                    case "1":
                        System.out.println("Showing your total score...");
                        PrintStats();
                        valid = true;
                        break;
                    case "2": {
                        System.out.println("Work out your average mark...");
                        class AverageMarks {
                            public void main(String args[]) {
                                int i;

                                System.out.println("Enter the number of questions you have answered: ");

                                Scanner sc = new Scanner(System.in);

                                int n = sc.nextInt();

                                int[] a = new int[n];

                                System.out.println("Enter the amount of marks you got for each question ");

                                for (i = 0; i < n; i++) {
                                    a[i] = sc.nextInt();
                                }

                                double avg = averageCalculation(a);

                                System.out.print("Your have an average score of  (");

                                for (i = 0; i < n - 1; i++) {
                                    System.out.print(a[i] + ",");
                                }
                                System.out.println(a[i] + ") = " + avg / n);

                            }
        /**
         * Returns the sum of the average mark
         * @return the final sum of marks
         */
                            double averageCalculation(int a[]) {
                                double sum = 0;

                                for (int i = 0; i < a.length; i++) {
                                    sum = sum + a[i];
                                }
                                return sum;

                            }

                        }

                    }
                }

                break;
                }
        }

        /**
         * Provides a CLI menu and takes user inputs, calling on other methods for management of quizzes in the database.
         */
        private static void option3() {
            System.out.println("Manage Quizzes selected...");
            Scanner s = new Scanner(System.in);
            boolean valid = false;
            while (valid == false) {
                System.out.println("1- Create New Quiz");
                System.out.println("2- Edit a Quiz");
                System.out.println("3- Delete a Quiz");
                System.out.println("4- Exit to Main Menu");
                System.out.println("Enter selection here (1-4):");
                int qID; //For Update/Delete options
                String option;
                try {option = s.nextLine();} //Protect against fatal input
                catch (Exception e) {
                    System.out.println("That is not a valid input, please try again...");
                    option = "invalid";}
                switch (option) {
                    case "1":
                        System.out.println("Create New Quiz selected...");
                        CreateQuizIO();
                        valid = true;
                        break;
                    case "2":
                        System.out.println("Edit a Quiz selected...");
                        System.out.println("What quiz would you like to edit? Enter its ID here:");
                        qID = s.nextInt();
                        EditQuizIO(qID);
                        valid = true;
                        break;
                    case "3":
                        System.out.println("Delete a Quiz selected...");
                        System.out.println("What quiz would you like to edit? Enter its ID here:");
                        qID = s.nextInt();
                        DeleteQuizIO(qID);
                        valid = true;
                        break;
                    case "4":
                        System.out.println("Exit to Main Menu selected...");
                        valid = true;
                        exit(0);
                    default:
                        System.out.println("That is not a valid input, please try again...");
                        break;
                }
            }
        }

        /**
         * Provides a CLI menu and takes user inputs, calling on other methods for management of questions in the database.
         */
        private static void option4() {
            System.out.println("Manage Questions selected...");
            Scanner s = new Scanner(System.in);
            boolean valid = false;
            while (valid == false) {
                System.out.println("1- Create New Question");
                System.out.println("2- Edit a Question");
                System.out.println("3- Delete a Question");
                System.out.println("4- Exit to Main Menu");
                System.out.println("Enter selection here (1-4):");
                String qID; //For Update/Delete options
                String option;
                try {option = s.nextLine();} //Protect against fatal input
                catch (Exception e) {
                    System.out.println("That is not a valid input, please try again...");
                    option = "invalid";}
                switch (option) {
                    case "1":
                        System.out.println("Create New Question selected...");
                        CreateQuestionIO();
                        valid = true;
                        break;
                    case "2":
                        System.out.println("Edit a Question selected...");
                        System.out.println("What question would you like to edit? Enter its ID here:");
                        qID = s.nextLine();
                        EditQuestionIO(qID);
                        valid = true;
                        break;
                    case "3":
                        System.out.println("Delete a Question selected...");
                        System.out.println("What question would you like to edit? Enter its ID here:");
                        qID = s.nextLine();
                        DeleteQuestion(qID);
                        valid = true;
                        break;
                    case "4":
                        System.out.println("Exit to Main Menu selected...");
                        valid = true;
                        exit(0);
                    default:
                        System.out.println("That is not a valid input, please try again...");
                        break;
                }
            }
        }

        /**
         * Provides a CLI menu and takes user input to print questions from the database.
         */
        private static void option5() {
            String qStatus;
            String qTopic;
            String qType;
            QuestionController qController = new QuestionController();
            Scanner s = new Scanner(System.in);
            System.out.println("See questions selected...");
            System.out.println("Would you like to see only questions you have answered incorrectly? (Y/N)");
            qStatus = s.nextLine().toUpperCase();
            if (qStatus.equals("Y")) {
                qController.readQuestionStatus("0");
            }
            else {
                System.out.println("What topic of questions would you like to see? (ALL to see all topics):");
                qTopic = s.nextLine().toUpperCase();
                if (!qTopic.equals("ALL")) {
                    qController.readQuestionTopics(qTopic);
                }
                else {
                    System.out.println("What type of questions would you like to see? (MCQ/SAQ/ALL):");
                    qType = s.nextLine().toUpperCase();
                    if (!qType.equals("ALL")) {
                        qController.readQuestionTypes(qType);
                    }
                    else {
                        qController.readQuestions();
                    }
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////  MAIN  ///////////////////////////////////////////////////////////
        /**
         * Displays a menu with options that the user can pick with integer inputs
         * @param args
         */
        public static void main(String[] args) {
            String[] options = {"" +
                    "\n1- Play Quiz",
                    "2- See Stats",
                    "3- Manage Quizzes",
                    "4- Manage Questions",
                    "5- See Questions",
                    "6- Exit",
            };
            Scanner scanner = new Scanner(System.in);
            String option = "0";
            while (option != "7") {
                Menu(options);
                try {
                    option = scanner.nextLine();
                    switch (option) {
                        case "1": //Play Quiz
                            option1New();
                            break;
                        case "2": //See User Statistics
                            option2();
                            Quiz totalMarks = new Quiz();
                            System.out.println(totalMarks.getTotalMarks());
                            break;
                        case "3": //Quiz Management
                            option3();
                            break;
                        case "4": //Question Management
                            option4();
                            break;
                        case "5": //See Questions
                            option5();
                            break;
                        case "6": //Exit
                            exit(0);
                    }
                } catch (Exception ex) {
                    System.out.println("An error was encountered...");
                    System.out.println("Please enter an integer value between 1 and " + options.length);
                    //scanner.next();
                }
            }
        }
    }
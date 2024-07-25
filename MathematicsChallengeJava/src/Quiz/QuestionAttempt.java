package Quiz;

/**
 * Represents an attempt at answering a question in the quiz.
 */
public class QuestionAttempt {
    private String questionText;   // The text of the question
    private String givenAnswer;    // The answer provided by the participant
    private String correctAnswer;  // The correct answer to the question
    private int score;             // The score awarded for this attempt (positive or negative)
    private boolean isCorrect;     // Whether the given answer is correct or not
    private long timeTaken;        // The time taken to answer the question, in milliseconds
    private String username;       // The username of the participant

    /**
     * Constructs a QuestionAttempt with the specified details.
     *
     * @param questionText   The text of the question.
     * @param givenAnswer    The answer provided by the participant.
     * @param correctAnswer  The correct answer to the question.
     * @param score          The score awarded for this attempt.
     * @param isCorrect      Whether the given answer is correct.
     * @param timeTaken      The time taken to answer the question, in milliseconds.
     * @param username       The username of the participant.
     */
    public QuestionAttempt(String questionText, String givenAnswer, String correctAnswer, int score, boolean isCorrect, long timeTaken, String username) {
        this.questionText = questionText;
        this.givenAnswer = givenAnswer;
        this.correctAnswer = correctAnswer;
        this.score = score;
        this.isCorrect = isCorrect;
        this.timeTaken = timeTaken;
        this.username = username;
    }

    // Getter methods

    /**
     * Returns the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Returns the answer provided by the participant.
     *
     * @return The given answer.
     */
    public String getGivenAnswer() {
        return givenAnswer;
    }

    /**
     * Returns the correct answer to the question.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the score awarded for this attempt.
     *
     * @return The score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns whether the given answer is correct.
     *
     * @return True if the answer is correct; false otherwise.
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     * Returns the time taken to answer the question, in milliseconds.
     *
     * @return The time taken.
     */
    public long getTimeTaken() {
        return timeTaken;
    }

    /**
     * Returns the username of the participant.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a string representation of the result of this attempt.
     *
     * @return "Correct" if the answer was correct; "Incorrect" otherwise.
     */
    public String getResult() {
        if (isCorrect) {
            return "Correct";
        } else {
            return "Incorrect";
        }
    }

    @Override
    public String toString() {
        return "QuestionAttempt{" +
                "questionText='" + questionText + '\'' +
                ", givenAnswer='" + givenAnswer + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", score=" + score +
                ", isCorrect=" + isCorrect +
                ", timeTaken=" + timeTaken +
                ", username='" + username + '\'' +
                '}';
    }
}

package Quiz;

public class QuestionAttempt {
    private String questionText;
    private String userAnswer;
    private boolean isCorrect;
    private int marksAwarded;

    public QuestionAttempt(String questionText, String userAnswer, boolean isCorrect, int marksAwarded) {
        this.questionText = questionText;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
        this.marksAwarded = marksAwarded;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getMarksAwarded() {
        return marksAwarded;
    }
}

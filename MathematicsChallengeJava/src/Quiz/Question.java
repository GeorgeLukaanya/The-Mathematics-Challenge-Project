package Quiz;

public class Question {
    private String questionText;
    private String correctAnswer;
    private int marks;

    public Question(String questionText, String correctAnswer, int marks) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getMarks() {
        return marks;
    }
}

package Quiz;

public class Answer {
    private int id;
    private String answerText;

    public Answer(int id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }
}
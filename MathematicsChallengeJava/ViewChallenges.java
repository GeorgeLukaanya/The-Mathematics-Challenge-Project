import java.util.*;

public class ViewChallenges {

    private List<Challenge> challenges;

    public ViewChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public void displayChallenges() {
        System.out.println("Available Challenges:");
        for (Challenge challenge : challenges) {
            if (challenge.isActive()) {
                System.out.println("Challenge ID: " + challenge.getId());
                System.out.println("Title: " + challenge.getTitle());
                System.out.println("Description: " + challenge.getDescription());
                System.out.println("Start Date: " + challenge.getStartDate());
                System.out.println("End Date: " + challenge.getEndDate());
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            }
        }
    }

    public Challenge selectChallenge(long id) {
        for (Challenge challenge : challenges) {
            if (challenge.getId() == id && challenge.isActive()) {
                return challenge;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge(1L, "Math Challenge 1", "Solve basic math problems with Muwonge.", new Date(), new Date(System.currentTimeMillis() + 86400000), true, Arrays.asList(
                new Question(1L, "What is 2 + 2?", "4", 10),
                new Question(2L, "What is 3 + 5?", "8", 10)
        )));
        challenges.add(new Challenge(2L, "Math Challenge 2", "Advanced math problems with Nicholas.", new Date(), new Date(System.currentTimeMillis() + 172800000), true, Arrays.asList(
                new Question(3L, "What is 12 * 12?", "144", 10),
                new Question(4L, "What is 25 / 5?", "5", 10)
        )));

        ViewChallenges viewChallenges = new ViewChallenges(challenges);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("MENU:");
            System.out.println("1. View Challenges");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewChallenges.displayChallenges();
                    System.out.print("Enter Challenge ID to attempt: ");
                    long challengeId = scanner.nextLong();
                    Challenge selectedChallenge = viewChallenges.selectChallenge(challengeId);
                    if (selectedChallenge != null) {
                        selectedChallenge.attemptChallenge(scanner);
                    } else {
                        System.out.println("Invalid Challenge ID or Challenge is not active.");
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class Challenge {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean active;
    private List<Question> questions;

    public Challenge(Long id, String title, String description, Date startDate, Date endDate, boolean active, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void attemptChallenge(Scanner scanner) {
        int totalMarks = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            System.out.print("Your answer: ");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                totalMarks += question.getMarks();
                System.out.println("Correct! You earned " + question.getMarks() + " marks.");
            } else {
                System.out.println("Incorrect! The correct answer is " + question.getCorrectAnswer() + ".");
            }
        }
        System.out.println("You scored " + totalMarks + " out of " + (questions.size() * 10) + " marks.");
    }
}

class Question {
    private Long id;
    private String questionText;
    private String correctAnswer;
    private int marks;

    public Question(Long id, String questionText, String correctAnswer, int marks) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    public Long getId() {
        return id;
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

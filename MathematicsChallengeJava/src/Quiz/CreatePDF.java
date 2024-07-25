package Quiz;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreatePDF {

    /**
     * Creates a PDF report of the quiz results.
     *
     * @param filePath the file path to save the PDF
     * @param questionAttempts a list of QuestionAttempt objects containing quiz details
     */
    public static void createReportPdf(String filePath, List<QuestionAttempt> questionAttempts) {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font questionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Font answerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);

            // Add title
            Paragraph title = new Paragraph("Challenge Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Add empty line

            // Iterate through the questions and answers
            for (int i = 0; i < questionAttempts.size(); i++) {
                QuestionAttempt attempt = questionAttempts.get(i);

                Paragraph questionParagraph = new Paragraph("Question " + (i + 1) + ": " + attempt.getQuestionText(), questionFont);
                document.add(questionParagraph);

                Paragraph yourAnswerParagraph = new Paragraph("Your Answer: " + (attempt.getGivenAnswer() != null ? attempt.getGivenAnswer() : ""), answerFont);
                document.add(yourAnswerParagraph);

                Paragraph correctAnswerParagraph = new Paragraph("Correct Answer: " + attempt.getCorrectAnswer(), answerFont);
                document.add(correctAnswerParagraph);

                document.add(new Paragraph(" ")); // Add empty line
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}

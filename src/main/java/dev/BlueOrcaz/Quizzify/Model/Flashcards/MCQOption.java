package dev.BlueOrcaz.Quizzify.Model.Flashcards;

public class MCQOption {
    // mcq option object consisting of an optionid, the option itself, and whether or not it is the correct answer or not.
    private int optionId;
    private String option;
    private boolean correctAnswer;


    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

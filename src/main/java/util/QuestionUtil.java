package util;

import model.Question;

import java.util.ArrayList;

public class QuestionUtil {
    public static ArrayList<Question> initQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("The Sun is planet", false));
        questions.add(new Question("The Earth is planet", true));
        questions.add(new Question("Venus is the hottest planet in the Solar System", true));
        questions.add(new Question("Astronaut experience zero gravity in space", true));
        questions.add(new Question("The Moon is a star", false));
        questions.add(new Question("The Earth is flat", false));
        questions.add(new Question("The Earth is the third planet from the Sun", true));
        questions.add(new Question("The Earth is the only planet that has life", true));
        questions.add(new Question("The Earth is the largest planet in the Solar System", false));
        questions.add(new Question("Mars has liquid water on its surface", false));
        return questions;
    }
}

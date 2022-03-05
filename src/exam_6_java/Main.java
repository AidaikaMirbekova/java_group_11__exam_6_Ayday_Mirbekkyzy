package exam_6_java;




import exam_6_java.exam.Exam_6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Exam_6("localhost", 9889).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

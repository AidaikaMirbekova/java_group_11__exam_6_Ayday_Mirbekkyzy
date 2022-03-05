package exam_6_java.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelDay {
    int days;
    private String date;
//    String toDays = null;
    private List<ModelNote.Note> notes = new ArrayList<>();
    private Random r = new Random();
//    private List<ModelDay> dayList = new ArrayList<>(30);

//    public ModelDay(int days, String toDays) {
//        this.days = days;
//        this.toDays = toDays;
//    }

//    public ModelDay(){}

    public ModelDay (int days){
        this.days = days;
        int rand = r.nextInt(4)+1;
        for (int i = 0; i <rand; i++) {
            notes.add(new ModelNote.Note());
        }
    }
    private  List<ModelDay> makeDay(List<ModelDay>dayList){
        int days = 0;
        int rand = r.nextInt(4)+1;
        for (int i = 0; i <rand; i++) {
            notes.add(new ModelNote.Note());
        }
        return dayList;
    }

    public int getModelDay() {
        return days;
    }

    public void setModelDay(int days) {
        this.days = days;
    }

//    public String getToDays() {
//        return toDays;
//    }
//
//    public void setToDays(int days) {
//        this.toDays = toDays;
//    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public List<ModelNote.Note> getNotes() {
        return notes;
    }

    public void setNotes(List<ModelNote.Note> notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

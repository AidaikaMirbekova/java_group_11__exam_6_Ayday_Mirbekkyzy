package exam_6_java.exam;

import java.util.List;

public class ModelCalendar {
    List<ModelDay>dayList = getDays();
    List<ModelNote.Note> noteList;

    public ModelCalendar(){}

    public ModelCalendar(List<ModelDay> dayList) {
        this.dayList = dayList;
    }

    public List<ModelDay> getDayList() {
        return dayList;
    }

    public void setDayList(List<ModelDay> dayList) {
        this.dayList = dayList;
    }
    private List<ModelDay> getDays() {
        return dayList;
    }

    public List<ModelNote.Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<ModelNote.Note> noteList) {
        this.noteList = noteList;
    }
}

package exam_6_java.exam;

import exam_6_java.exam_server.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ModelNote {
      private List<Note> notes = new ArrayList<>();
      Note note = new Note();

      public List<Note> getNotes() {
            return notes;
      }


      public ModelNote(){
            notes.add(new Note(note.getName(), note.getCategory(),note.getDescription(),note.getPhoto()));
      }
      public static class Note{
            private String name;
            private Category category;
            private  String description;
            private String photo;

            public Note(String name, Category category) {
                  this.name = name;
                  this.category = category;
                  this.description = Generator.makeDescription();
                  this.photo = getPhoto();
            }

            public Note() {
                  name = Generator.makeName();
                  category = getRndCategory();
                  description = Generator.makeDescription();
            }

            public Note(String name, String description, String type) {
                  this.name = name;
                  getTypeByName(type);
                  this.description = description;
            }

            public Note(String name, Category category, String description, String photo) {
                  this.name = name;
                  this.category = category;
                  this.description = Generator.makeDescription();
                  this.photo = photo;
            }




            public static Note makeNotes(String name,  String description){
            Note n = new Note();
            n.name = name;
            n.description = description;
            return n;
      }

            private void getTypeByName(String name) {
                  if (name.equals("URGENT")) {
                        photo = "images/urgent.png";
                        category = Category.URGENT;
                  } else if (name.equals("SHOPPING")) {
                        photo = "images/shop.png";
                        category = Category.SHOPPING;
                  } else if (name.equals("WORK")) {
                        photo = "images/work.png";
                        category = Category.WORK;
                  } else if (name.equals("GENERAL")) {
                        photo = "images/gen.png";
                        category = Category.GENERAL;
                  } else {
                        photo = "images/oth.png";
                        category = Category.OTHERS;
                  }
            }

            private Category getRndCategory() {
                  Random random = new Random();
                  int rnd = random.nextInt(5) + 1;
                  if (rnd == 1) {
                        photo = "images/work.png";
                        return Category.WORK;
                  } else if (rnd == 2) {
                        photo = "images/shop.png";
                        return Category.SHOPPING;
                  } else if (rnd == 3) {
                        photo = "images/gen.png";
                        return Category.GENERAL;
                  } else if (rnd == 4) {
                        photo = "images/urgent.png";
                        return Category.URGENT;
                  } else {
                        photo = "images/oth.png";
                        return Category.OTHERS;
                  }
            }

            public String getName() {
                  return name;
            }

            public void setName(String name) {
                  this.name = name;
            }

            public Category getCategory() {
                  return category;
            }

            public void setCategory(Category category) {
                  this.category = category;
            }

            public String getDescription() {
                  return description;
            }

            public String getPhoto() {
                  return photo;
            }

            public void setPhoto(String photo) {
                  this.photo = photo;
            }

      }
}

package Code.Backend;

import java.util.ArrayList;

public class Company {
    private String name;
    private int id;
    private int count = 0;
    private ArrayList<String> studentNames = new ArrayList<>();

    public String getString(){
        return "Id: " + this.id + ", Name: " + this.name + ", Anzahl: " + this.count + ", Schülernamen: " + this.studentNames;
    }
    private void addStudentName(String studentName, String klasse){
        this.studentNames.add(studentName + " " + klasse);
    }

    public void addStudent(String studentName, String klasse){
        this.addStudentName(studentName, klasse);
        this.count++;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public int getCount(){
        return this.count;
    }

    public ArrayList<String> getStudentsName(){
        return this.studentNames;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setStudentNames(ArrayList<String> studentNames){
        this.studentNames = studentNames;
    }
}

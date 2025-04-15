
import java.io.*;
import java.util.*;
import java.security.PublicKey;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.io.FileWriter;
class Student{
    public String name;
    public int roll;
    public String dept;

    public Student(String Name,int Roll,String Dept){
        this.name = Name;
        this.roll = Roll;
        this.dept = Dept;
        //System.out.println(name+" "+roll+" "+dept);
    }
    public void PrintInfo(){
        System.out.println("Name : " + this.name);
        System.out.println("Roll : " + this.roll);
        System.out.println("Department : " + this.dept+'\n');
    }

}
class Result{
    public int roll;
    public String subject;
    public float CG;
    public float credit;

    public Result(int roll,String subject,float CG,float credit){
        this.roll = roll;
        this.subject = subject;
        this.CG = CG;
        this.credit = credit;
    }
    public void PrintResult(){
        System.out.println("Roll : " + this.roll);
        System.out.println("Subject : "+ this.subject);
        System.out.println("CGPA : "+this.CG);
        System.out.println("Credit : "+this.credit+'\n');
    }
}
class Calculating_CG{
    public String name;
    public int roll;
    public float CG;

    Calculating_CG(String name,int roll,float CG){
        this.name = name;
        this.roll = roll;
        this.CG = CG;
    }
}
class cmp implements Comparator<Calculating_CG> {
    public int compare(Calculating_CG c1, Calculating_CG c2) {
        if (c1.CG == c2.CG) {
            if (c1.roll == c2.roll) {
                if (c1.name == c2.name) {
                    return 0;
                } else return c1.name.compareTo(c2.name);
            } else if (c1.roll > c2.roll) {
                return 1;
            } else return 0;
        } else if (c1.CG < c2.CG) {
            return 1;
        } else return -1;
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("======================");
        String oldName = "files123.txt";
        String newName = oldName.replaceAll("\\d*$", "").trim();
        System.out.println("New Name: " + newName);
        System.out.println("======================");


        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Result> results = new ArrayList<Result>();
        try{
            File f = new File("Student.txt");
            Scanner st = new Scanner(f);

            while (st.hasNextLine()) {
                String tmp="";
                String name="",roll="",dept="";
                int comma = 0;
                String data = st.nextLine();
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == ',') {
                        if (comma == 0) {
                            name = name + tmp;
                            comma++;
                            tmp="";
                        }
                        else if(comma==1){
                            roll = roll + tmp;
                            comma++;
                            tmp="";
                        }
                   }
                    else{
                        tmp = tmp + data.charAt(i);
                    }
                }
                if(tmp.length()!=0){
                    dept = dept + tmp;
                }
                int num = 0;
                num = Integer.parseInt(roll);
                students.add(new Student(name,num,dept));
            }
            st.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i<students.size();i++){
            students.get(i).PrintInfo();
        }

        try{
            File f = new File("result.txt");
            Scanner st = new Scanner(f);
            while(st.hasNextLine()){
                String data = st.nextLine();
                int comma = 0;
                int roll=0;
                String sub = "";
                float cg = 0.0f;
                float cre = 0.0f;
                String tmp = "";
                for(int i=0;i<data.length();i++){
                    if(data.charAt(i)==','){
                        if(comma==0){
                            roll = Integer.parseInt(tmp);
                            tmp="";
                            comma++;
                        }
                        else if(comma==1){
                            sub = sub + tmp;
                            tmp="";
                            comma++;
                        }
                        else if(comma == 2){
                            cg = Float.parseFloat(tmp);
                            tmp = "";
                            comma++;
                        }
                    }
                    else{
                        tmp = tmp+data.charAt(i);
                    }
                }
                if (tmp.length()!=0) {
                   cre = Float.parseFloat(tmp);
                }
                //System.out.println(roll+ " " + sub+" "+cg+" "+cre);
                results.add(new Result(roll,sub,cg,cre));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<results.size();i++){
            results.get(i).PrintResult();
        }

        // Calculating CG of Student
        ArrayList<Calculating_CG> calculating_cgs = new ArrayList<Calculating_CG>();
        for(int i=0;i<students.size();i++){
            float total_credit = 0.0f;
            float totalcg = 0.0f;
            float res = 0.0f;
            for(int j=0;j<results.size();j++){
                if(students.get(i).roll == results.get(j).roll){
                    totalcg = totalcg + (results.get(j).credit * results.get(j).CG);
                    total_credit = total_credit + results.get(j).credit;
                }
            }
            //System.out.println(totalcg+" "+total_credit+'\n');
            res = totalcg/total_credit;
            calculating_cgs.add(new Calculating_CG(students.get(i).name,students.get(i).roll,res));
        }
        Collections.sort(calculating_cgs,new cmp());
        //Collections.sort(calculating_cgs, Collections.reverseOrder(Comparator.comparing(CG::Calculating_CG).thenComparing(CG::getRoll).thenComparing(CG::getName)) );
        for(Calculating_CG cgs : calculating_cgs){
            System.out.println(cgs.name+" "+cgs.roll+" "+String.format("%.2f",cgs.CG)+'\n');
        }
        // sort by CG
        try {
            FileWriter out1 = new FileWriter("Sort.txt");
            for(Calculating_CG cgs : calculating_cgs){
                out1.write(cgs.name+" "+cgs.roll+" "+cgs.CG+'\n');
                //System.out.println(cgs.name+" "+cgs.roll+" "+String.format("%.2f",cgs.CG)+'\n');
            }
            out1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

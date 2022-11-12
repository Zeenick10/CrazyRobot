
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CrazyRobot {

    public static final Scanner SCANNER=new Scanner(System.in);

    CrazyRobot(ArrayList<StoreDictionary> arrayList){
        loopProcess(arrayList);
    }


    public static void main(String[] args) {
        ArrayList<StoreDictionary> arrayList = new ArrayList<>();
        arrayList.add(new StoreDictionary("What is your name?","Crazy Robot"));
        CrazyRobot crazyRobot1 = new CrazyRobot(arrayList);
    }

    private void loopProcess(ArrayList<StoreDictionary> arrayList){
        System.out.println("Do you want to search or not?(y,n)");
        String check = SCANNER.nextLine();
        try{
            if(check.equalsIgnoreCase("y") || check.equalsIgnoreCase("yes")){
                arrayList=checkProcess(arrayList);
                loopProcess(arrayList);
            } else if (check.equalsIgnoreCase("n") || check.equalsIgnoreCase("no")) {
                System.out.println("Process End");
            }else {
                throw new InputMismatchException("Please Type valid words");

            }

        }catch (InputMismatchException e){
            System.out.println(e);
            loopProcess(arrayList);
        }
    }

    private ArrayList<StoreDictionary> checkProcess(ArrayList<StoreDictionary> arrayList){
        System.out.println("Question");
        String question = SCANNER.nextLine();
        boolean found = false;
        try{
            for(StoreDictionary arrayList1:arrayList){
                if(questionProcess(question,arrayList)){
                    found =true;
                    System.out.println("Question : "+question+"?");
                    System.out.println("Answer : "+answerProcess(question,arrayList1.getAnswer(),arrayList));
                    break;
                }else{
                    found = false;
                }
            }
            throw new CustomInputMissMatchException("This is the new question,Please tell the answer?");
        } catch (CustomInputMissMatchException e) {
            if(found==false){
                System.out.println(e);
            }else{

            }
        } finally {
            if(found==false){
                String answer= SCANNER.nextLine();
                arrayList.add(new StoreDictionary(question,answer));
            }
        }


        return arrayList;
    }

    private boolean questionProcess(String question,ArrayList<StoreDictionary> arrayList){
        boolean flag = false;
        for(StoreDictionary s:arrayList){
            if(question.equalsIgnoreCase(s.getQuestion())){
                flag = true;
            }
        }
        return flag;
    }

    private String answerProcess(String question,String answer,ArrayList<StoreDictionary> arrayList){
        answer = null;
        for(StoreDictionary s : arrayList){
            if(question.equalsIgnoreCase(s.getQuestion())){
                answer = s.getAnswer();
            }
        }
        return answer;
    }
}

class StoreDictionary{
    private String question,answer;

    public StoreDictionary(String question,String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

class CustomInputMissMatchException extends RuntimeException{
    public CustomInputMissMatchException(String s){
        super(s);
    }
}

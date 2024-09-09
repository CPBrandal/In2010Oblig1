import java.util.ArrayList;
import java.io.*;

public class Teque2 {
    ArrayList<Integer> liste_f = new ArrayList<Integer>();  
    ArrayList<Integer> liste_b = new ArrayList<Integer>();
    public static void main(String[] args) throws IOException {
        Teque2 oblig = new Teque2();
        InputStreamReader l = new InputStreamReader(System.in);
        BufferedReader leser = new BufferedReader(l);
        int lengde = Integer.parseInt(leser.readLine());
        String les;
        while((les = leser.readLine()) != null && lengde != 1){
            String[] liste = les.split(" ");
            if(liste[0].equals("push_back")){
                oblig.push_back(Integer.parseInt(liste[1]));
                lengde--;
                oblig.balanserLister();
            }
            else if(liste[0].equals("push_front")){
                oblig.push_front(Integer.parseInt(liste[1]));
                lengde--;
                oblig.balanserLister();
            }
            else if(liste[0].equals("push_middle")){
                oblig.push_middle(Integer.parseInt(liste[1]));
                lengde--;
                oblig.balanserLister();
            }
            else if(liste[0].equals("get")){
                oblig.get(Integer.parseInt(liste[1]));
                lengde--;
            }
        } leser.close();
    }

    public void push_back(int i){
        liste_b.add(i);
    }
    public void push_front(int i){
        liste_f.add(0, i);
    }
    public void push_middle(int i){

    }
    public void get(int i){
        
    }
    public void balanserLister(){
        if(liste_f.size() == 1 && liste_b.size() == 0){
            return;
        } 
        if(liste_f.size() == 0 && liste_b.size() == 1){
            return;
        }
        int diff = liste_f.size() - liste_b.size();
        

    }
}

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

class Teque{
    public Node forste;
    public Node siste;
    public Node mid;
    public int teller = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        Teque oblig = new Teque();
        BufferedReader leser = new BufferedReader(new FileReader("test.txt"));
        String les;
        while((les = leser.readLine()) != null){
            String[] liste = les.split(" ");
            if(liste[0].equals("push_back")){
                oblig.push_back(Integer.parseInt(liste[1]));
            }
            else if(liste[0].equals("push_front")){
                oblig.push_front(Integer.parseInt(liste[1]));
            }
            else if(liste[0].equals("push_middle")){
                oblig.push_middle(Integer.parseInt(liste[1]));
            }
            else if(liste[0].equals("get")){
                oblig.get(Integer.parseInt(liste[1]));
            }
        } leser.close();
    } 

    public void push_back(int i){
        Node n = new Node(i);
        if(forste == null){
            forste = n;
            siste = n;
            mid = n;
            teller++;
            return;
        }
        if(forste == siste){
            forste.neste = n;
            n.forrige = forste;
            mid = n;
            siste = n;
            teller++;
            return;
        }
        else{
            if(teller%2 == 0){
                siste.neste = n;
                n.forrige = siste;
                siste = n;
                teller++;  
            } else {
                siste.neste = n;
                n.forrige = siste;
                siste = n;
                mid = mid.neste;
                teller++;
            }
        }
    }

    public void push_front(int i){
        Node n = new Node(i);
        if(forste == null){
            forste = n;
            siste = n;
            mid = n;
            teller++;
            return;
        }
        if(forste == siste){
            n.neste = forste;
            forste.forrige = n;
            forste = n;
            teller++;
            return;
        }
        else{
            if(teller%2 == 0){ 
                n.neste = forste;
                forste.forrige = n;
                forste = n;
                mid = mid.forrige;
                teller++;
            } else {
                n.neste = forste;
                forste.forrige = n;
                forste = n;
                teller++;
            }
        }
    }

    public void push_middle(int i){
        Node n = new Node(i);
        if(forste == null){
            forste = n;
            siste = n;
            mid = n;
            teller++;
            return;
        }
        if(forste == siste){
            forste.neste = n;
            n.forrige = forste;
            mid = n;
            siste = n;
            teller++;
        } else {
            if(teller%2 == 0){
                mid.forrige.neste = n;
                n.forrige = mid.forrige;
                n.neste = mid;
                mid = n;
                teller++;
            } else {
                n.neste = mid.neste;
                n.forrige = mid;
                mid.neste = n;
                mid = n;
                teller++;
            }
        }
    }

    public void get(int i){
        Node nesteNode = mid;
        int midIndex;
        if(teller%2 == 0){
            midIndex = (teller) / 2;
        } else {
            midIndex = (teller - 1) / 2;
        }
        if(i == 0){
            System.out.println(forste.data);
            return;
        }
        if(i == midIndex){
            System.out.println(nesteNode.data);
        } else if(i < midIndex){
            for(int x = 0; x < midIndex-i; x++){
                nesteNode = nesteNode.forrige;
            }
            System.out.println(nesteNode.data);
        } else {
            for(int x = 0; x < i - midIndex; x++){
                nesteNode = nesteNode.neste;
            }
            System.out.println(nesteNode.data);
        }
    }

    public class Node {
        Node neste;
        Node forrige;
        int data;

        public Node (int i){
            data = i;
        }

        public void settNeste(Node n){
            this.neste = n;
        }
    }
}
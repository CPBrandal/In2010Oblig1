import java.io.*;

class Teque1{
    public Node forste;
    public Node siste;
    public Node mid;
    public int teller = 0;
    public static int lengde;

    public static void main(String[] args) throws NumberFormatException, IOException {
        Teque1 oblig = new Teque1();
        InputStreamReader l = new InputStreamReader(System.in);
        BufferedReader leser = new BufferedReader(l);
        lengde = Integer.parseInt(leser.readLine());
        String les;
        while((les = leser.readLine()) != null && lengde >= 1){
            String[] liste = les.split(" ");
            if(liste[0].equals("push_back")){
                oblig.push_back(Integer.parseInt(liste[1]));
                lengde--;
                oblig.settMid();
                if(lengde == 0){
                    break;
                }
            }
            else if(liste[0].equals("push_front")){
                oblig.push_front(Integer.parseInt(liste[1]));
                lengde--;
                oblig.settMid();
                if(lengde == 0){
                    break;
                }
            }
            else if(liste[0].equals("push_middle")){
                oblig.push_middle(Integer.parseInt(liste[1]));
                lengde--;
                oblig.settMid();
                if(lengde == 0){
                    break;
                }
            }
            else if(liste[0].equals("get")){
                oblig.get(Integer.parseInt(liste[1]));
                lengde--;
                if(lengde == 0){
                    break;
                }
            }
        } leser.close();
        //oblig.print();
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
            siste = n;
            teller++;
        }
        else{
            siste.neste = n;
            n.forrige = siste;
            siste = n;
            teller++;
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
        }
        else{
            n.neste = forste;
            forste.forrige = n;
            forste = n;
            teller++;
        }
    }

    public void push_middle(int i){
        Node n = new Node(i);
        Node peker = forste;
        int l = (teller+1)/2;
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
            siste = n;
            teller++;
        } else {
            for(int x = 0; x < l; x++){
                peker = peker.neste;
            }
            n.neste = peker;
            n.forrige = peker.forrige;
            peker.forrige.neste = n;
            peker.forrige = n;
            teller++;
        } 
    }

    public void get(int i){
        Node nesteNode = mid;
        int midIndex = (teller+1)/2;
        if(i == 0){
            System.out.println(forste.data);
            return;
        }
        if(i == teller){
            System.out.println(siste.data);
            return;
        }
        if(i == midIndex){
        
        } else if(i < midIndex){
            for(int x = 0; x < midIndex-i; x++){
                nesteNode = nesteNode.forrige;
            }
        } else {
            for(int x = 0; x < i - midIndex; x++){
                nesteNode = nesteNode.neste;
            }
        }
        System.out.println(nesteNode.data);
    }

    public void settMid(){
        Node peker = forste;
        int midten = (teller+1)/2;
        for(int x = 0; x < midten; x++){
            peker = peker.neste;
        }
        mid = peker;
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

    public void print(){
        Node peker = forste;
        for(int x = 0; x < teller; x++){
            System.out.println(peker.data);
            peker = peker.neste;
        }
    }
}
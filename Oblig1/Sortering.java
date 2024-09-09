import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Antar slik som det står i oppgaveteksten: . Input skal "leses fra fil, hvor filnavnet blir gitt som kommandolinjeargument." at filnavn er antall elementer i listen. Her blir det lest som f.eks xxxxx_xxxxx_1000.txt
// Jeg har sammarbeidet med Ola Skeie i deler av koden.

public class Sortering {
    static int statistikk[][];
    static int liste[];

    static int antSamIns = 0; // Insertion
    static int antBytIns = 0;
    static long tidIns;

    static int antSamMer = 0; // Merge
    static int antBytMer = 0;
    static long tidMer;

    public static void main(String[] args) throws IOException {
        BufferedReader leser = new BufferedReader(new FileReader(args[0] + ".txt"));
        String str = args[0];
        String[] streng = args[0].split("_");
        int antElementer = Integer.parseInt(streng[streng.length - 1]);
        liste = new int[antElementer];

        statistikk = new int[6][antElementer+1];
        int plass = 0;
        String les;
        Sortering s = new Sortering();
        while((les = leser.readLine()) != null){
            liste[plass] = Integer.parseInt(les);
            plass++;
        }
        for(int x = 0; x < liste.length+1; x++){ //Lager n+1 lister for insertion og merge
            int ins[] = new int[x];
            int mer[] = new int[x];
            if(x != 0){
                for(int y = 0; y < ins.length; y++){
                    ins[y] = liste[y];
                    mer[y] = liste[y];
            }
            }
            s.instertionSort(ins); // Insertion
            statistikk[0][x] = antSamIns;
            statistikk[1][x] = antBytIns;
            statistikk[2][x] = (int) tidIns;
            s.insClear();

            long t1 = System.nanoTime(); // Merge
            s.mergeSort(mer);
            tidMer = (System.nanoTime()-t1)/1000;
            statistikk[3][x] = antSamMer;
            statistikk[4][x] = antBytMer;
            statistikk[5][x] = (int) tidMer;
            s.merClear(); 
        }

        leser.close();
        try {
            FileWriter myWriter = new FileWriter(str + "insertion_result.out");
            s.instertionSort(liste);
            for(int i : liste){
                myWriter.write(String.valueOf(i));
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {   
            FileWriter myWriter2 = new FileWriter(str + "_statistics.csv");
            myWriter2.write("n, alg1_cmp, alg1_swaps, alg1_time, alg2_cmp, alg2_swaps, alg2_time");
            myWriter2.write("\n");
            for(int x = 0; x < antElementer+1; x++){
                myWriter2.write(""+x);
                for(int y = 0; y < 6; y++){
                    myWriter2.write(",       " + statistikk[y][x]);
                }
                myWriter2.write("\n");
            }
            myWriter2.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
  }

    public void instertionSort(int[] l){
        long t = System.nanoTime();

        int storrelse = l.length;
        for(int x = 1; x < storrelse; x++){ //Starter på tallet på plass 1
            int tall = l[x];
            int index = x - 1;
            while(index >= 0 && tall < l[index]){
                l[index + 1] = l[index];
                index--;
                insBytte();
                insSammenligning();
            }

            l[index + 1] = tall;
            if(index>=0){
                insSammenligning();      
        }
    }
        long time = (System.nanoTime()-t)/1000;
        tidIns = time;
/*         System.out.println("Mikrosekunder sorteringstid: " + time);
        System.out.println("Antall bytter: " + antBytIns);
        System.out.println("Antall sammenligninger: " + antSamIns); */
}

    public void insBytte(){
        antBytIns++;
    }

    public void insSammenligning(){
        antSamIns++;
    }
    public void merBytte(){
        antBytMer++;
    }

    public void merSammenligning(){
        antSamMer++;
    }

    public void insClear(){
        antBytIns = 0;
        antSamIns = 0;
        tidIns = 0;
    }

    public void merClear(){
        antBytMer = 0;
        antSamMer = 0;
        tidMer = 0;
    }

    public void mergeSort(int[] l){
        int lengde = l.length;
        if(lengde <= 1){
            return;
        }
        int midten = (lengde/2);
        int LV[] = new int[midten]; 
        int LH[] = new int[lengde - midten]; 

        for(int x = 0; x < midten; x++){
            LV[x] = l[x];
        }
        for(int y = midten; y < lengde; y++){
            LH[y-midten] = l[y];
        }
        mergeSort(LV);
        mergeSort(LH);

        Merge(l, LV, LH);
    }

        public void Merge(int[] l, int[] LV, int[] LH){
            int v = 0;
            int h = 0;
            while(v < LV.length && h < LH.length){
                if(LV[v] <= LH[h]){
                    l[v+h] = LV[v];
                    v++;
                    merSammenligning();
                } else {
                    merBytte();
                    merSammenligning();
                    l[v+h] = LH[h];
                    h++;
              }
            }
            while(v<LV.length){
                l[v+h] = LV[v];
                v++;
                if(h != LH.length){
                    merSammenligning();
                }
            }
            while(h<LH.length){
                l[v+h] = LH[h];
                h++;
            }
        }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Antar slik som det står i oppgaveteksten: . Input skal "leses fra fil, hvor filnavnet blir gitt som kommandolinjeargument." at filnavn er antall elementer i listen.
// Jeg har sammarbeidet med Ola Skeie i deler av koden.

public class AlgoritmeTest {
    static long statistikk[];
    static int liste[];

    static long antSamIns = 0; // Insertion
    static long antBytIns = 0;
    static long tidIns;

    static long antSamMer = 0; // Merge
    static long antBytMer = 0;
    static long tidMer;

    public static void main(String[] args) throws IOException {
        BufferedReader leser = new BufferedReader(new FileReader(args[0] + ".txt"));
        String str = args[0];
        String[] streng = args[0].split("_");
        int antElementer = Integer.parseInt(streng[streng.length - 1]);
        liste = new int[antElementer];

        statistikk = new long[6];
        int plass = 0;
        String les;
        AlgoritmeTest aT = new AlgoritmeTest();
        while((les = leser.readLine()) != null){
            liste[plass] = Integer.parseInt(les);
            plass++;
        }
        leser.close();
        int[] ins = new int[antElementer];
        int[] mer = new int[antElementer];
        for(int x = 0; x < antElementer; x++){
            ins[x] = liste[x];
            mer[x] = liste[x];
        }
        long t = System.nanoTime();
        aT.instertionSort(ins);
        tidIns = (System.nanoTime()-t)/1000;

        statistikk[0] = antSamIns;
        statistikk[1] = antBytIns;
        statistikk[2] = tidIns;

        long t1 = System.nanoTime(); // Merge
        aT.mergeSort(mer);
        tidMer = (System.nanoTime()-t1)/1000;
        
        statistikk[3] = antSamMer;
        statistikk[4] = antBytMer;
        statistikk[5] = tidMer;

        try {   
            FileWriter myWriter2 = new FileWriter(str + "_statistic_1test.csv");
            myWriter2.write("n, alg1_cmp, alg1_swaps, alg1_time, alg2_cmp, alg2_swaps, alg2_time");
            myWriter2.write("\n");
            myWriter2.write(""+antElementer);
            for(int x = 0; x < 6; x++){
                myWriter2.write(",     " + statistikk[x]);
            }
            myWriter2.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
  }

    public void instertionSort(int[] l){

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
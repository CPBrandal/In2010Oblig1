import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeAlgo {
    static int liste[];
    public static void main(String[] args) throws IOException {
        BufferedReader leser = new BufferedReader(new FileReader(args[0] + ".txt"));
        String str = args[0];
        String[] streng = args[0].split("_");
        int antElementer = Integer.parseInt(streng[streng.length - 1]);
        liste = new int[antElementer];
        MergeAlgo m = new MergeAlgo();
        int plass = 0;
        String les;
        while((les = leser.readLine()) != null){
            liste[plass] = Integer.parseInt(les);
            plass++;
        }
        leser.close();
        m.MergeSort(liste);
        for(int i : liste){
            System.out.println(i);
        }
    }

    public void MergeSort(int[] l){
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
        MergeSort(LV);
        MergeSort(LH);

        Merge(l, LV, LH);
    }

        public void Merge(int[] l, int[] LV, int[] LH){
            int v = 0;
            int h = 0;
            while(v < LV.length && h < LH.length){
                if(LV[v] <= LH[h]){
                    l[v+h] = LV[v];
                    v++;
                } else {
                    l[v+h] = LH[h];
                    h++;
                }
            }
            while(v<LV.length){
                l[v+h] = LV[v];
                v++;
            }
            while(h<LH.length){
                l[v+h] = LH[h];
                h++;
            }
    }
}
package sortalgo;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.scene.text.*;

public class Shellsort {
    public SequentialTransition shellsort(int[] arr, ArrayList<StackPane> list, double speed) {
        Swap sw = new Swap();
        SequentialTransition st = new SequentialTransition();
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                    temp = arr[j];
                    arr[j] = arr[j - gap];
                    arr[j - gap] = temp;
                    st.getChildren().add(sw.farswap(list.get(j - gap), list.get(j), list, speed));
                    j -= gap;
                }
            }
        }
        return st;
    }


    private void claim(boolean b){
        if(!b) throw new Error("sortalgo.Shellsort Test"+testNumber+"fails");
        testNumber++;
    }
    private int testNumber = 1;

    // testing 1-10
    private void run(){
        Shellsort testshs = new Shellsort();
        int[] testarr = new int[]{0, 3, 0, 5, 8, 1, 7, 14, 11, 15};
        ArrayList<Text> testtext = new ArrayList<Text>(10);
        ArrayList<Rectangle> testrec = new ArrayList<Rectangle>(10);
        StackPane testsp = new StackPane();
        ArrayList<StackPane> testlist = new ArrayList<StackPane>();
        for(int i=0; i < testarr.length; i++) {
            testsp.setId(String.valueOf(testarr[i]));
            testtext.add(i,new Text(String.valueOf(testarr[i])));
            testrec.add(i,new Rectangle());
            testsp.getChildren().addAll(testrec.get(i), testtext.get(i));
            testlist.add(testsp);
        }
        double testspeed = 400;
        testshs.shellsort(testarr,testlist,testspeed);
        claim(testarr[0] == 0);
        claim(testarr[1] == 0);
        claim(testarr[2] == 1);
        claim(testarr[3] == 3);
        claim(testarr[4] == 5);
        claim(testarr[5] == 7);
        claim(testarr[6] == 8);
        claim(testarr[7] == 11);
        claim(testarr[8] == 14);
        claim(testarr[9] == 15);
        System.out.print("Sorted Array: ");
        for(int j=0; j < testarr.length; j++) {
            System.out.print(testarr[j]+" ");
        }
        System.out.println("\nAll sortalgo.Shellsort Tests Passed!");
    }

    public static void main(String[] args){
        Shellsort mshs = new Shellsort();
        mshs.run();
    }
}

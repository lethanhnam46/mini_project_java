package sortalgo;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.scene.text.*;

public class Insertionsort{
    public SequentialTransition insertionsort(int[] arr, ArrayList<StackPane> list, double speed){
        Swap sw = new Swap();
        SequentialTransition st = new SequentialTransition();
        int temp;
        for(int i = 1; i < arr.length; i++){
            for(int j = i; j > 0; j--){
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    st.getChildren().add(sw.swap(list.get(j - 1), list.get(j), list, speed));
                }
            }
        }
        return st;
    }

    private void claim(boolean b){
        if(!b) throw new Error("sortalgo.Insertionsort Test"+testNumber+"fails");
        testNumber++;
    }
    private int testNumber = 1;

    // testing 1-10
    private void run(){
        Insertionsort testis = new Insertionsort();
        int[] testarr = new int[]{4, 3, 6, 2, 1, 2, 12, 11, 10,7};
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
        testis.insertionsort(testarr,testlist,testspeed);
        claim(testarr[0] == 1);
        claim(testarr[1] == 2);
        claim(testarr[2] == 2);
        claim(testarr[3] == 3);
        claim(testarr[4] == 4);
        claim(testarr[5] == 6);
        claim(testarr[6] == 7);
        claim(testarr[7] == 10);
        claim(testarr[8] == 11);
        claim(testarr[9] == 12);
        System.out.print("Sorted Array: ");
        for(int j=0; j < testarr.length; j++) {
            System.out.print(testarr[j]+" ");
        }
        System.out.println("\nAll sortalgo.Insertionsort Tests Passed!");
    }

    public static void main(String[] args){
        Insertionsort mis = new Insertionsort();
        mis.run();
    }
}

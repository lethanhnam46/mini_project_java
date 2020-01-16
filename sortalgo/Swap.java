/*
Two kinds of swaps
 */
package sortalgo;

import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.*;
import javafx.util.Duration;

public class Swap{
    // swap two adjacent numbers
    ParallelTransition swap(StackPane sp1, StackPane sp2, ArrayList<StackPane> list, double speed){
        TranslateTransition tt1 = new TranslateTransition();
        TranslateTransition tt2 = new TranslateTransition();
        tt1.setDuration(Duration.millis(speed));
        tt2.setDuration(Duration.millis(speed));
        ParallelTransition pt = new ParallelTransition();
        tt1.setNode(sp1);
        tt2.setNode(sp2);
        tt1.setByX(60);
        tt2.setByX(-60);
        pt.getChildren().addAll(tt1, tt2);
        Collections.swap(list, list.indexOf(sp1), list.indexOf(sp2));
        return pt;
    }

    // swap two numbers that are not adjacent
    ParallelTransition farswap(StackPane sp1, StackPane sp2, ArrayList<StackPane> list, double speed){
        int num;
        TranslateTransition tt1 = new TranslateTransition();
        TranslateTransition tt2 = new TranslateTransition();
        ParallelTransition pt = new ParallelTransition();
        tt1.setNode(sp1);
        tt2.setNode(sp2);
        tt1.setDuration(Duration.millis(speed));
        tt2.setDuration(Duration.millis(speed));
        int indexsp1 = list.indexOf(sp1);
        int indexsp2 = list.indexOf(sp2);
        num = indexsp2 - indexsp1;
        num*=60;
        tt1.setByX(num);
        tt2.setByX(-num);
        Collections.swap(list, list.indexOf(sp1), list.indexOf(sp2));
        pt.getChildren().addAll(tt1, tt2);
        return pt;
    }
}

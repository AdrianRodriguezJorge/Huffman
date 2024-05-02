import java.awt.EventQueue;

import inter.*;

public class Main {
     public static void main(String[] args) {
          EventQueue.invokeLater(new Runnable() {
               public void run() {
                    try {
                         HWindow frame = new HWindow();
                         frame.setVisible(true);
                    } catch (Exception e) {
                         e.printStackTrace();
                    }
               }
          });
     }
}

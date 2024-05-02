import java.awt.EventQueue;

import inter.Principal;

public class Main {
     public static void main(String[] args) {
          EventQueue.invokeLater(new Runnable() {
               public void run() {
                    try {
                         Principal frame = new Principal();
                         frame.setVisible(true);
                    } catch (Exception e) {
                         e.printStackTrace();
                    }
               }
          });
     }
}

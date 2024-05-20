import javax.swing.*;

public class TimeThread implements Runnable{
    private int seconds, minutes;
    private final JLabel label;
    public static boolean isThread;

    TimeThread(JLabel label){
        this.label = label;
        seconds = 0;
        minutes = 0;
        isThread = true;
    }

    @Override
    public void run() {
        String txt = "0:00";
        label.setText(txt);
        while (isThread) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            seconds++;
            if(seconds>=60){
                minutes = seconds/60;
            }
            if(seconds - minutes*60 < 10)
                txt = minutes + ":0" + (seconds - minutes*60);
            else txt = minutes + ":" + (seconds - minutes*60);
            label.setText(txt);
        }

    }
    public void reset(){
        seconds = 0;
    }
}

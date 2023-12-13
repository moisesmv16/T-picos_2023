package Components;

import javafx.scene.control.ProgressBar;

public class Hilo extends Thread{
    private ProgressBar pgbCorredor;
    public  Hilo(String nombre,ProgressBar pgb){
        //super(nombre);
        this.setName(nombre);
        this.pgbCorredor = pgb;
    }

    @Override
    public void run() {
        super.run();
        try {
            double avance =0;
            while (avance <= 1){
                sleep((long) (Math.random() * 1500));
                avance += Math.random()/10;
                pgbCorredor.setProgress(avance);
                //System.out.println("Corredor " + this.getName() + " LLego al kilimetro " + i);
            }
            //System.out.println("Corredor " + this.getName() + " Llego a la meta ");
        }catch (Exception e){
        }
    }
}

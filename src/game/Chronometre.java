package game;

import java.util.concurrent.TimeUnit;

public class Chronometre {
    private long begin;
    private long end;
    private long current;
    private int limite; // 

    public Chronometre(int limite) {
        //intialisation
        this.limite = limite;
        
    }
    
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
    //gives the accurate actual time since the start of the chrono till this second , time is given in seconds
    public int getActualTime(){ 
        return (int)((System.currentTimeMillis()-this.begin)/1000.0);
    }
 
    public long getTime() {
        return end-begin;
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 
    public int getSeconds() {
        return (int) ((end - begin) / 1000.0);
    }
 
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }
 
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
    
    /**
    * Method to know if it remains time.
    */
    public boolean remainsTime() {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((current -this.begin)/1000);
       return timeSpent<this.limite;
    }
    
     // Optionnel : 
    public boolean remains15sec(){
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((limite - (current-this.begin)/1000));
       return timeSpent<15;
    }
    
    public static void main(String[] args) throws InterruptedException{
       Chronometre chrono = new Chronometre(20); 
       chrono.start();
       TimeUnit.SECONDS.sleep(2);
       System.out.println(""+chrono.remains15sec());
       TimeUnit.SECONDS.sleep(2);
       TimeUnit.SECONDS.sleep(2);
       System.out.println(""+chrono.remains15sec());

       TimeUnit.SECONDS.sleep(2);
       chrono.stop();
       System.out.println(""+chrono.getTime());
       System.out.println(""+chrono.getHours()+":"+chrono.getMinutes()+":"+chrono.getSeconds());

       System.out.println(""+chrono.remains15sec());
       TimeUnit.SECONDS.sleep(2);
       
       TimeUnit.SECONDS.sleep(2);
       System.out.println(""+chrono.remains15sec());

       
       
    }
     
}

/**
This class the is driver class that runs the whole project.
Running this class calls a new instance of Kiosk(), the user
facing interface of the project.
@author Craig Li, Prerak Patel
*/

public class RunProject1 {

    /**
    Calls a new Kiosk to infterface with the user.
    @param args arguments taken from console
     */
    public static void main(String[] args) {
        new Kiosk().run();
        
    }

}
/**
Driver method that runs the Payroll Processing class.
Running this class calls a new instance of PayrollProcessing, the user 
interface of this project.
@author Craig Li, Prerak Patel
 */
public class RunProject2{
    /**
    Calls a new Payroll Process to infterface with the user.
    @param args    arguments taken from console
    */
    public static void main(String[] args){
        new PayrollProcessing().run();
    }
}
package gr.aueb.cf.schoolpro.service.Exceptions;

public class NoRecordsFoundException extends Exception{
    public static final long serialVersionUID = 1;

    public NoRecordsFoundException(){
        super("The system couldn't find the requested data");
    }
}

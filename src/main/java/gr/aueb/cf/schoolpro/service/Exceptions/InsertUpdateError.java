package gr.aueb.cf.schoolpro.service.Exceptions;

public class InsertUpdateError extends Exception {
    public static final long serialVersionUID = 1;

    public InsertUpdateError(){
         super("Error in storing the new data");
    }
}

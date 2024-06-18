package mk.finki.ukim.mk.lab.model.exception;

public class StoreNotFoundException extends Exception{
    public StoreNotFoundException(Long Id) {
        super("Store with Id " + Id + " not found");
    }
}

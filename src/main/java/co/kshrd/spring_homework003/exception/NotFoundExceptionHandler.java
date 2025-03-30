package co.kshrd.spring_homework003.exception;

public class NotFoundExceptionHandler extends RuntimeException {
    public NotFoundExceptionHandler(String message)
    {
        super(message);
    }
}

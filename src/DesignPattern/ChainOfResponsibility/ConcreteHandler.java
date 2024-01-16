package DesignPattern.ChainOfResponsibility;

public class ConcreteHandler extends Handler{
    private int threshold;

    public ConcreteHandler(int threshold){
        this.threshold = threshold;
    }

    @Override
    public void handleRequest(int request) {
        if(request<=threshold){
            System.out.println("Request " + request + " handled by ConcreteHandler");
        }else if(nextHandler!=null){
            nextHandler.handleRequest(request);
        }else{
            System.out.println("No handler available for request " + request + ".");
        }
    }
}

package adapter;

public class CreditCardPayment implements PaymentSystem {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment was received from credit card in amount: " + amount/475 + " USD");
    }
}

public class SavingAccount extends BankAccount {
    private float interestRate; // ex: 0.03 pour 3 %

    public SavingAccount(float interestRate) {
        super();
        this.interestRate = interestRate;
    }

    public SavingAccount(float initialAmount, float interestRate) {
        super(initialAmount);
        this.interestRate = interestRate;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public void addPeriodicInterest() {
        float interest = balance * interestRate;
        if (interest > 0) {
            balance += interest;
            addOperation(new Operation("INTEREST", interest));
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" rate=%.2f%%", interestRate * 100);
    }
}

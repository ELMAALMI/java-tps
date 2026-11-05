public class CheckingAccount extends BankAccount {
    public static final int FREE_TRANSACTIONS = 3;
    public static final float TRANSACTION_FEE = 0.50f;

    private int transactionCount;

    public CheckingAccount() {
        super();
    }

    public CheckingAccount(float initialAmount) {
        super(initialAmount);
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public void deposit(float amount) {
        super.deposit(amount);
        transactionCount++;
    }

    @Override
    public void withdraw(float amount) {
        super.withdraw(amount);
        transactionCount++;
    }

    /**
     * A appeler en fin de mois : deduit les frais des transactions au-dela du quota
     * gratuit.
     */
    public void deductFees() {
        int extra = transactionCount - FREE_TRANSACTIONS;
        if (extra > 0) {
            float fees = extra * TRANSACTION_FEE;
            balance -= fees;
            addOperation(new Operation("FEES", -fees));
        }
        transactionCount = 0;
    }

    @Override
    public String toString() {
        return super.toString() + " transactions=" + transactionCount;
    }
}

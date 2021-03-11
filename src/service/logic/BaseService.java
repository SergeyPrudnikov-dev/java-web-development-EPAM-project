package by.epam.jvd.vitebsk.task4.service.logic;

import by.epam.jvd.vitebsk.task4.service.Transaction;

public class BaseService {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}

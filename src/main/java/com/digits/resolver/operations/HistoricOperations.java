package com.digits.resolver.operations;

import java.util.ArrayList;
import java.util.List;

public class HistoricOperations {

    private final List<List<Operation>> historicOperationList;

    public HistoricOperations() {
        List<List<Operation>> historicOperationList = new ArrayList<>();
        historicOperationList.add(new ArrayList<>());
        this.historicOperationList = historicOperationList;
    }

    public List<List<Operation>> getHistoricOperationList() {
        return historicOperationList;
    }

    /**
     * Add operation to the last List element of the historicOperationList.
     *
     * @param operation : Operation to add.
     */
    public void addOperationToCurrentPath(Operation operation) {
        historicOperationList.get(historicOperationList.size() - 1).add(operation);
    }

    /**
     * Add a new List element to the historicOperationList with the common path of the previous list.
     */
    public void addNewPath() {
        List<Operation> newOperations = new ArrayList<>(historicOperationList.get(historicOperationList.size() - 1));
        // Remove last operation from next historic operations
        newOperations.remove(newOperations.size() - 1);
        historicOperationList.add(newOperations);
    }




}

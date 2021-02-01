package Memory.operator;

import marsVM.MarsVM;

public abstract class _AOperator implements _IOperator {

    protected MarsVM executer;

    public void setExecuter(MarsVM vm) {
        executer = vm;
    }
}

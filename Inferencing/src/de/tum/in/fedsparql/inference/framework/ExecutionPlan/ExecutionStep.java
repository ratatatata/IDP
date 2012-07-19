package de.tum.in.fedsparql.inference.framework.ExecutionPlan;

import de.tum.in.fedsparql.inference.framework.ExecutionPlanDispatcher.Scheduler;

/**
 * general execution step
 */
public abstract class ExecutionStep {

	public ExecutionStep(Object ID) {
		_ID = ID;
	}
	protected Object _ID=null;

	public Object getID() {
		return _ID;
	}
	@Override
	public String toString() {
		return _ID+"#"+this.getClass().getSimpleName();
	}

	abstract void execute(Scheduler scheduler) throws Exception;

}

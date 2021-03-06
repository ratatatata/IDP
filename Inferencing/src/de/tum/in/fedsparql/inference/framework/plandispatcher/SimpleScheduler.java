package de.tum.in.fedsparql.inference.framework.plandispatcher;

import de.tum.in.fedsparql.inference.framework.graph.DependencyGraph;
import de.tum.in.fedsparql.inference.io.Dispatcher;
import de.tum.in.fedsparql.inference.io.IO;
import de.tum.in.fedsparql.inference.io.Monitoring;

public class SimpleScheduler extends Scheduler {

	public SimpleScheduler(DependencyGraph collection, IO io, Monitoring monitoring, Dispatcher dispatcher) {
		super(collection, io, monitoring, dispatcher);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void schedule(ThreadInfo threadInfo) {
		threadInfo.startExecution(getRandomNode());
	}

}

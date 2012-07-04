
import de.tum.in.fedsparql.inference.dummy.JenaDatabase;
import de.tum.in.fedsparql.inference.framework.Script;
import de.tum.in.fedsparql.inference.framework.ScriptCollection;
import de.tum.in.fedsparql.inference.framework.ExecutionPlan.ExecutionPlan;
import de.tum.in.fedsparql.inference.framework.ExecutionPlan.ExecutionStep;
import de.tum.in.fedsparql.inference.framework.exceptions.CircularDependencyException;


public class Main3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		// set up scripts
		Script r1 = new Script(
				"r1",
				new JenaDatabase[]{new JenaDatabase("a")},
				new JenaDatabase[]{new JenaDatabase("b")},
				".."
				);
		Script r2 = new Script(
				"r2",
				new JenaDatabase[]{new JenaDatabase("b")},
				new JenaDatabase[]{new JenaDatabase("c")},
				".."
				);
		Script r3 = new Script(
				"r3",
				new JenaDatabase[]{new JenaDatabase("a")},
				new JenaDatabase[]{new JenaDatabase("d")},
				".."
				);
		Script r4 = new Script(
				"r4",
				new JenaDatabase[]{new JenaDatabase("c"),new JenaDatabase("d")},
				new JenaDatabase[]{new JenaDatabase("e")},
				".."
				);
		Script r5 = new Script(
				"r5",
				new JenaDatabase[]{new JenaDatabase("e")},
				new JenaDatabase[]{new JenaDatabase("e")},
				".."
				);
		Script r6 = new Script(
				"r6",
				new JenaDatabase[]{new JenaDatabase("c")},
				new JenaDatabase[]{new JenaDatabase("e")},
				".."
				);

		ScriptCollection scripts;
		try {
			// create script collection
			scripts = new ScriptCollection(new Script[]{
					r1,
					r2,
					r3,
					r4,
					r5,
					r6
			});

			scripts.printScripts();
			System.out.println();
			scripts.printDirectDependencies();
			System.out.println();
			scripts.printInheritedDependencies();
			System.out.println();
			//			scripts.printIndependentlyProcessableScripts();
			System.out.println();



			// create execution plan
			System.out.println("EXECUTION PLAN:");
			ExecutionPlan p = new ExecutionPlan(scripts);
			for (ExecutionStep step: p.getSteps()) {
				System.out.println(step);
			}

			System.out.println();
			System.out.println();
			System.out.println();

			// execute execution plan
			System.out.println("EXECUTING:");
			p.execute();
		} catch (CircularDependencyException e) {
			e.printStackTrace();
		}


	}

}
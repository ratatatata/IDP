package de.tum.in.fedsparql.inference.framework;


/**
 * Database Identifier
 */
public class DatabaseID {

	/* constructors */
	/**
	 * Creates a Database-Identifier with the given name.
	 * Assumes isFresh=false.
	 * 
	 * @param name Name of the Database
	 */
	public DatabaseID(String name) {
		this(name, false);
	}
	/**
	 * Creates a Database-Identifier with the given name.
	 * 
	 * @param name Name of the Database
	 * @param isFresh true: Database needs to be created || false: Database already exists.
	 * @see #isFresh()
	 */
	public DatabaseID(String name, boolean isFresh) {
		_name = name;
		_isFresh = isFresh;
	}


	/* public getters */
	/**
	 * @return this Database's name
	 */
	public String getName() {
		return _name;
	}
	/**
	 * States whether this Database needs be created or already exists.<br/>
	 * If a Output-Database is marked as "fresh" in a specific Script, this Script needs to be executed before any other Script that reads from this DB
	 * 
	 * @return true: Database needs to be created || false: Database already exists
	 */
	public boolean isFresh() {
		return _isFresh;
	}


	/* overriden methods */
	@Override
	public String toString() {
		return this.getName() + (this.isFresh()?"*":"");
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DatabaseID) {
			DatabaseID db = (DatabaseID) obj;

			return this.getName() != null ? this.getName().equals(db.getName()) : db.getName() == null;
		}

		return false;
	}


	/* protected member */
	protected String _name;
	protected boolean _isFresh;
}

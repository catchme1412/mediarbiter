package quickdt;

import java.io.Serializable;
import java.util.Set;

public final class NominalBranch extends Branch {
	private static final long serialVersionUID = -1723969623146234761L;
	public final Set<Serializable> inSet;

	public NominalBranch(final String attribute, final Set<Serializable> inSet) {
		super(attribute);
		this.inSet = inSet;

	}

	@Override
	protected boolean decide(final Attributes attributes) {
		Serializable o = attributes.get(attribute);
		return inSet.contains(o);
	}

	@Override
	public String toString() {
		return attribute + " in " + inSet;
	}

	@Override
	public String toNotString() {
		return attribute + " not in " + inSet;
	}
}

package com.raj.youtube.classifier;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Qualifier implements Serializable {

	private static final long serialVersionUID = 1L;

	private Serializable value;

	private String label;

	private Qualifier(String label) {
		this.label = label;
	}

	public static Serializable greateThan(Serializable i) {
		Qualifier q = new Qualifier("greaterThan " + i);
		q.value = i;
		return q;
	}

	public static Serializable lessThan(Serializable i) {
		Qualifier q = new Qualifier("lessThan " + i);
		q.value = i;
		return q;
	}

	public static Serializable containsText(String text) {
		Qualifier q = new Qualifier("containsText " + text);
		q.value = text;
		return q;
	}

	public boolean isContainsText(String text) {
		return text.contains(value.toString());
	}

	public boolean isGreaterThan(Serializable v) {
		return invokeCompareTo(v) >= 1;
	}

	public boolean isLessThan(Serializable v) {
		return invokeCompareTo(v) <= -1;
	}

	public boolean isEqualTo(Serializable v) {
		return invokeCompareTo(v) == 0;
	}

	private int invokeCompareTo(Serializable v) {
		Class<?> c = v.getClass();
		try {
			Method main = c.getMethod("compareTo", Object.class);
			Object r = main.invoke(v, value);
			return (Integer) r;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}

	public static void main(String[] args) {
		Qualifier q = (Qualifier) Qualifier.greateThan(10.5);
		System.out.println(q.isGreaterThan(1.5));
		System.out.println(q.isEqualTo(10.5));
		System.out.println(q.isLessThan(10.0));
		Serializable f = Qualifier.containsText("movie");
		System.out.println(((Qualifier)f).isContainsText("full movie"));
	}

	@Override
	public String toString() {
		return label + value;
	}

	@Override
	public boolean equals(Object obj) {
		return value.equals(obj);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}


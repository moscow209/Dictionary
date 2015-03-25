package com.example.common;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class MyArrayList<T> extends AbstractList<T> implements List<T> {

	private Object[] data;
	private int size;

	public MyArrayList(int initialCapacity) {
		super();
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: "
					+ initialCapacity);
		data = new Object[initialCapacity];
	}

	public MyArrayList() {
		this(10);
	}

	public void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			@SuppressWarnings("unused")
			Object oldData[] = data;
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean add(T t) {
		ensureCapacity(size + 1);
		data[size] = t;
		size = size + 1;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index);
		return (T) data[index];
	}

	public T remove(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index);
		@SuppressWarnings("unchecked")
		T oldValue = (T) data[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(data, index + 1, data, index, numMoved);
		size = size - 1;
		data[size] = null;
		return oldValue;
	}

	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	public T set(int index, T element) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index);
		@SuppressWarnings("unchecked")
		T oldValue = (T) data[index];
		data[index] = element;
		return oldValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sort(List<T> list, Comparator<? super T> c) {
		Object[] a = list.toArray();
		Arrays.sort(a, (Comparator) c);
		for (int i = 0; i < a.length; i++)
			list.set(i, (T) a[i]);
	}

}

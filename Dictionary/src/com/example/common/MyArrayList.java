package com.example.common;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class MyArrayList<E> extends AbstractList<E> implements List<E> {

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

	public boolean add(E e) {
		ensureCapacity(size + 1);
		data[size] = e;
		size = size + 1;
		return true;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index);
		return (E) data[index];
	}

	public E remove(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index);
		E oldValue = (E) data[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(data, index + 1, data, index, numMoved);
		size = size - 1;
		data[size] = null;
		return oldValue;
	}

}

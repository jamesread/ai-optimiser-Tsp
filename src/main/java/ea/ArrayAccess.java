package ea;

public interface ArrayAccess<T> {
	public T get(int index);

	public int length();

	public void set(int index, T t);
}

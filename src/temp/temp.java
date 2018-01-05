package temp;

abstract class A<T> {
    public abstract T getValue(String content);
}

class B extends A<String> {
    public String getValue(String content) {
		return content; }
}

class C extends A<Integer> {
    public Integer getValue(String content) {
		return null; }
}

class D extends A<Boolean> {
    public Boolean getValue(String content) {
		return null; }
}
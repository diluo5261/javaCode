class Person{
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

public class HashBuck<K,V> {

    static class Node<K,V>{
        public K key;
        public V val;
        public Node<K,V> next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public Node <K,V>[] arrays = (Node<K,V>[])new Node[10];
    public int usedSize;

    public void put(K key,V val){
        //1、通过hsahCode定位数组的下标
        //key是一个引用类型，不能直接%长度
        int hash = key.hashCode();
        int index = hash % arrays.length;//hashCode是一个很大的值，不能直接作为下标，要进行取模操作
        Node<K,V> cur = arrays[index];
        while(cur != null){
            if(cur.key.equals(key)){
                cur.val = val;
            }
        }

        Node<K,V> node = new Node<>(key,val);
        node.next = arrays[index];
        arrays[index] = node;
    }

    public V get(K key){
        int hash = key.hashCode();
        int index = hash % arrays.length;

        Node<K,V> cur = arrays[index];
        while(cur != null){
            if(cur.key.equals(key)){
                return cur.val;
            }
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Person person1 = new Person("12345");
        Person person2 = new Person("12345");
        System.out.println(person1);
        System.out.println(person1.equals(person2));
        System.out.println("===================");
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }


}

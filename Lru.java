
import java.util.Collection;
import java.util.HashMap;


public class Lru {
//    定义节点
    public static class Node<K,V> {
    public K key;
    public V value;
    public Node<K, V> last;
    public Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

//    双向链表，所有的节点都是从head到tail串好的
    public static class NodeDoubleLinkedList<K,V>{
        private Node<K,V> head;
        private Node<K,V> tail;

    public NodeDoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

//    如果是一个新的节点，从尾部插入；
    public void addNode(Node<K,V> newNode){
        if (newNode == null){
            return;
        }
//        如果链表为空
        if (head == null){
            head=newNode;
            tail=newNode;
        }else {//双向链表中有节点；
            tail.next = newNode;
            newNode.last = tail;
            tail = newNode;
        }
    }
//    调用此方法，潜台词是确定该节点已经存在链表中
    public void updateNode(Node<K,V> oldNode){
//        如果该节点已经是尾结点，直接返回
        if (tail == oldNode) {
            return;
        }
//        如果该节点是头节点,那么头结点指向下一个节点
        if (head == oldNode) {
            head = head.next;
            head.last = null;
        }else {//如果是中间任意节点，那么将前后节点连接
            oldNode.last.next = oldNode.next;
            oldNode.next.last = oldNode.last;
        }
            tail.next = oldNode;
            oldNode.last = tail;
            oldNode.next = null;
            tail = oldNode;

    }
//删除头部节点,并返回
    public Node<K,V> removeHead(){
        if(this.head == null){
            return null;
        }
        Node<K,V> res = this.head;
//        如果只有一个节点，则将头尾节点重置；
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        }else {
            this.head = this.head.next;
            this.head.last = null;
            res.next = null;
        }
        return res;
    }
    }

    public static class MyCach<K,V>{
        private HashMap<K,Node<K,V>> keyNodeMap;
        private NodeDoubleLinkedList<K,V> doubleLinkedList;
        private final int capacity;

        public MyCach(int capacity) {
            if(capacity<1){
                throw new RuntimeException("初始容量必须大于1");
            }
            this.capacity = capacity;
            keyNodeMap = new HashMap<K, Node<K,V>>();
            doubleLinkedList = new NodeDoubleLinkedList<K, V>();
        }

        public V get(K key){
            if (keyNodeMap.containsKey(key)){
                Node<K,V> res = keyNodeMap.get(key);
                doubleLinkedList.updateNode(res);
                return res.value;
            }
            return null;
        }

        public void set(K key,V value){
            if (keyNodeMap.containsKey(key)){//如果存在此节点，则替换value
                Node<K,V> node = keyNodeMap.get(key);
                node.value = value;
                doubleLinkedList.updateNode(node);
            }else{
                if(keyNodeMap.size() == capacity){
                    removeMostUnusedCach();
                }
                Node<K,V> node = new Node<K, V>(key,value);
                keyNodeMap.put(key,node);
                doubleLinkedList.addNode(node);
            }
        }

        private void removeMostUnusedCach() {
            Node<K, V> node = doubleLinkedList.removeHead();
            keyNodeMap.remove(node.key);
        }
//        获取最近一次操作的数据
        public V getRecentUsed(){
            if(keyNodeMap == null){
                return null;
            }
            Node<K,V> node = doubleLinkedList.tail;
            return node.value;
        }
    }

    public static void main(String[] args) {
        MyCach<String,String> cach = new MyCach<String, String>(4);
        cach.set("first","第一");
        cach.set("second","第二");
        cach.set("third","第三");
        cach.set("ddd","44");
        getCach(cach);
        cach.get("second");
        cach.get("first");
        cach.get("ddd");
        cach.set("fff","555");
        System.out.println("______________________");
        getCach(cach);
        System.out.println(cach.getRecentUsed());
    }

    private static void getCach(MyCach<String, String> cach) {
        HashMap<String,Node<String,String>> map = cach.keyNodeMap;
        Collection<Node<String, String>> values = map.values();
        for(Node<String,String> node:values){
            System.out.println(node.key+"="+node.value);
        }
    }
}

package com.yh.math.skiptable;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

/**
 * 跳表
 * https://juejin.cn/post/6844903924789575694#comment
 */
public class SkipListMap<K extends Comparable, V> implements Map<K, V> {

    /**
     * 单个节点信息
     */
    static class Node<K extends Comparable, V> {
        /**
         * key
         */
        final K key;
        /**
         * value
         */
        volatile Object value;
        /**
         * 下一个节点
         */
        volatile Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node(K key, Object value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * 返回是否是“链表最左下角的 BASE_HEADER 节点”
         */
        boolean isBaseHeader() {
            return value == BASE_HEADER;
        }

        /**
         * 返回是否是数据节点
         */
        V getValidValue() {
            Object v = value;
            if (v == this || this.isBaseHeader()) {
                return null;
            }

            return (V) v;
        }

        /**
         * 跟另一个节点比较KEY的大小
         *
         * @param o 另一个节点
         * @return int
         */
        int compareKeyTo(Node<K, V> o) {
            return this.key.compareTo(o.key);
        }

        /**
         * 当前节点KEY跟另一个节点的KEY比较
         *
         * @param key 另一个节点的KEY
         * @return int
         */
        int compareKeyTo(K key) {
            return this.key.compareTo(key);
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return SkipListMap.equals(key, node.key) &&
                    SkipListMap.equals(value, node.value);
        }

        /**
         * 取 key 和 value 的 hashCode 的异或
         */
        @Override
        public final int hashCode() {
            return SkipListMap.hashCode(key) ^ SkipListMap.hashCode(value);
        }
    }

    /**
     * 索引节点
     */
    static class Index<K extends Comparable, V> {
        /**
         * 索引指向的最底层的数据节点
         */
        final Node<K, V> node;

        /**
         * 下边的索引节点
         */
        final Index<K, V> down;

        /**
         * 右边的索引节点
         */
        volatile Index<K, V> right;

        public Index(Node<K, V> node, Index<K, V> down) {
            this(node, down, null);
        }

        public Index(Node<K, V> node, Index<K, V> down, Index<K, V> right) {
            this.node = node;
            this.down = down;
            this.right = right;
        }
    }

    /**
     * 头部索引节点
     */
    static class HeadIndex<K extends Comparable, V> extends Index<K, V> {
        /**
         * 用于标识当前索引的层级
         */
        int level;

        public HeadIndex(Node<K, V> node, Index<K, V> down, Index<K, V> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }

    /* ---------------- Fields -------------- */

    /**
     * 初始首节点
     */
    private static final Object BASE_HEADER = new Object();

    /**
     * 默认第一层的level
     */
    public static final int DEFAULT_LEVEL = 1;

    /**
     * 整个跳跃表的最顶层的头节点
     */
    private transient volatile HeadIndex<K, V> head;

    /**
     * 索引的level
     */
    private volatile int level;

    public SkipListMap() {
        this.initialize();
    }

    public static int hashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    @Override
    public int size() {
        int count = 0;

        for (Node<K, V> temp = this.findFirst(); temp != null; temp = temp.next) {
            if (temp.getValidValue() != null) {
                count++;
            }
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return this.findFirst() == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    //@Override
    public boolean containsKey(K key) {
        return this.getNode(key) != null;
    }

    //@Override
    public V get(K key) {
        Node<K, V> temp;
        return (temp = this.getNode(key)) != null ? (V) temp.value : null;
    }

    @Override
    public V put(K key, V value) {
        this.putVal(key, value);
        return value;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    //@Override
    public void remove(K key) {
        this.removeVal(key);
    }

    @Override
    public void clear() {
        this.initialize();
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {

    }

    /**
     * 格式化遍历输出
     */
    public void forEach() {
        HeadIndex<K, V> headIdx = this.head;

        //1. 获取索引节点的数组
        Index<?, ?>[] idxArr = new Index<?, ?>[this.level];
        while (headIdx != null) {
            idxArr[headIdx.level - 1] = headIdx.right;
            headIdx = (HeadIndex<K, V>) headIdx.down;
        }

        //2. 获取第一个数据节点
        Node<K, V> node = this.findFirst();

        //3. 遍历索引节点
        for (int i = this.level; i > 0; i--) {
            System.out.print(MessageFormat.format("HeadIdx[level={0}]", i));

            if (idxArr.length == this.level) {
                Index<?, ?> idx = idxArr[i - 1];
                Node<K, V> tmpNode = node;

                //以数据链表为基准遍历索引列表，如果某个数据节点不存在索引则用占位符替代
                while (tmpNode != null) {
                    if (idx != null && tmpNode.equals(idx.node)) {
                        System.out.print(MessageFormat.format(", Idx[key={0}]", tmpNode.key));
                        idx = idx.right;
                    } else {
                        System.out.print(", Idx[null]");
                    }

                    tmpNode = tmpNode.next;
                }
            }

            System.out.print("\n");
        }

        //4. 遍历数据节点
        System.out.print("HeadNode[BASE_HEADER]");
        while (node != null) {
            System.out.print(MessageFormat.format(", Node[key={0}, value={1}]", node.key, node.value));

            node = node.next;
        }
        System.out.print("\n");
    }


    /**
     * 初始化
     */
    private void initialize() {
        level = DEFAULT_LEVEL;
        head = new HeadIndex<K, V>(new Node<K, V>(null, BASE_HEADER, null),
                null, null, DEFAULT_LEVEL);
    }

    /**
     * 获取第一个数据节点
     */
    private Node<K, V> findFirst() {
        Node<K, V> preNode = this.head.node;
        //数据部分的节点
        Node<K, V> node = preNode.next;

        if (node != null) {
            return node;
        } else {
            return null;
        }
    }

    /**
     * 移除某个键值对
     */
    private void removeVal(K key) {
        //1. 删除数据节点
        //1.1 获取key的前置数据节点
        Node<K, V> preNode = this.findPreNode(key);
        //下一个数据节点
        Node<K, V> node = preNode.next;

        //1.2 判断是否是我们查找的那个节点
        if (node == null || node.key.compareTo(key) != 0) {
            return;
        }

        //1.3 将数据节点从链表上移除
        preNode.next = node.next;


        //2. 删除索引节点
        Index<K, V> preLevelIdx = this.findDownPreIdx(this.head, node);
        if (preLevelIdx != null) {
            //2.1 获取目标节点的索引节点
            Index<K, V> levelIdx = preLevelIdx.right;

            //2.2 重新关联索引链表
            preLevelIdx.right = levelIdx.right;

            //2.3 继续删除下层的索引节点
            while (preLevelIdx != null) {
                preLevelIdx = this.findDownPreIdx(preLevelIdx, node);

                if (preLevelIdx != null) {
                    levelIdx = preLevelIdx.right;
                    preLevelIdx.right = levelIdx.right;
                }
            }
        }
    }

    /**
     * 根据 key 查找对应数据节点
     */
    private Node<K, V> getNode(K key) {
        //1. 获取key的前置数据节点
        Node<K, V> preNode = this.findPreNode(key);
        //下一个数据节点
        Node<K, V> node = preNode.next;

        //2. 判断是否是我们查找的那个节点
        if (node != null && node.key.compareTo(key) == 0) {
            return node;
        } else {
            return null;
        }
    }


    /**
     * 存储某个键值对
     */
    private void putVal(K key, V value) {
        //1. 获取key的前置数据节点
        Node<K, V> preNode = this.findPreNode(key);
        //获取后置节点，作为新节点的后置节点
        Node<K, V> nextNode = preNode.next;

        //如果发现重复节点，则直接替换值并返回
        if (nextNode != null && nextNode.compareKeyTo(key) == 0) {
            nextNode.value = value;
            preNode.next = nextNode;
            return;
        }

        //2. 创建新的数据节点
        Node<K, V> newNode = new Node<>(key, value);

        //3. 将新节点挂载到链表
        newNode.next = nextNode;
        preNode.next = newNode;

        //4. 设置新节点的索引
        this.createNodeIndex(newNode);
    }

    /**
     * 设置新插入数据节点的索引情况
     *
     * @param newNode 新插入到链表的数据节点
     */
    private void createNodeIndex(Node<K, V> newNode) {
        //1. 生成一个随机整数
        int rnd = ThreadLocalRandom.current().nextInt();

        //2. 如果最高位和最低位都为1，则直接插入链表(1/4的概率)，其他的需要创建索引节点
        if ((rnd & 0x80000001) == 0) {
            HeadIndex<K, V> headIdx = this.head;
            Index<K, V> idx = null;

            //索引节点的层级
            int level = 1, maxLevel = headIdx.level;

            //2.1 判断应该建立几层的索引节点（从右边第2为开始计算连续为1的个数）
            while (((rnd = rnd >>> 1) & 1) == 1) {
                level++;
            }

            //2.2 创建对应的索引节点
            //如果计算出来的层级没有超过原来最大的层级，则直接循环建立索引节点，否则需要在顶层再新建一层
            if (level <= maxLevel) {
                for (int i = 1; i <= level; i++) {
                    idx = new Index<>(newNode, idx);
                }
            } else {
                //在顶层再新建一层索引节点
                level = maxLevel + 1;
                this.level = level;

                //2.2.1 创建索引节点
                for (int i = 1; i <= level; i++) {
                    idx = new Index<>(newNode, idx);
                }

                //2.2.2 重新设置各层级的头索引结点
                HeadIndex<K, V> newHeadIdx = new HeadIndex<>(headIdx.node, headIdx, null, level);
                HeadIndex<K, V> levelHeadIdx = newHeadIdx;
                for (int i = level; i >= 1; i--) {
                    levelHeadIdx.level = i;
                    levelHeadIdx = (HeadIndex<K, V>) levelHeadIdx.down;
                }

                //创建新的最顶层的头节点
                this.head = newHeadIdx;
                headIdx = newHeadIdx;
            }

            //2.3 将新创建的那一列索引节点跟原来的关联起来
            Index<K, V> levelIdx = idx;
            Index<K, V> preLevelIdx = this.findDownPreIdx(headIdx, headIdx.level, level, idx.node);
            //横向关联索引节点
            levelIdx.right = preLevelIdx.right;
            preLevelIdx.right = levelIdx;

            for (int i = level; i > 1; i--) {
                levelIdx = levelIdx.down;
                preLevelIdx = this.findDownPreIdx(preLevelIdx, i, (i - 1), levelIdx.node);

                //横向关联索引节点
                levelIdx.right = preLevelIdx.right;
                preLevelIdx.right = levelIdx;
            }
        }
    }

    /**
     * 查找底层的指定索引节点的前置索引节点，没有则返回空
     *
     * @param current      当前索引位置
     * @param expectedNode 目标节点
     * @return cn.zifangsky.skiplist.SkipListMap.Index<K, V>
     */
    private Index<K, V> findDownPreIdx(Index<K, V> current, Node<K, V> expectedNode) {
        Index<K, V> currentIdx = current;
        //右边索引节点
        Index<K, V> rightIdx;
        //右边数据节点
        Node<K, V> rightNode;

        //不断向右和向下搜索指定层级的前置索引节点
        while (currentIdx != null) {
            //1. 将索引向右移动
            while (true) {
                rightIdx = currentIdx.right;
                if (rightIdx == null) {
                    break;
                }

                rightNode = rightIdx.node;

                //如果右边索引节点还在目标节点的左边，则将索引向右移动
                if (rightNode.compareKeyTo(expectedNode) < 0) {
                    currentIdx = currentIdx.right;
                } else if (rightNode.compareKeyTo(expectedNode) == 0) {
                    return currentIdx;
                } else {
                    break;
                }
            }

            //2. 将索引向下移动一步
            currentIdx = currentIdx.down;
        }

        return null;
    }

    /**
     * 查找底层的指定层级的前置索引节点
     *
     * @param current       当前索引位置
     * @param currentLevel  当前层级
     * @param expectedLevel 待查找的层级
     * @param expectedNode  目标节点
     * @return cn.zifangsky.skiplist.SkipListMap.Index<K, V>
     */
    private Index<K, V> findDownPreIdx(Index<K, V> current, int currentLevel,
                                       int expectedLevel, Node<K, V> expectedNode) {
        Index<K, V> currentIdx = current;
        //右边索引节点
        Index<K, V> rightIdx;
        //右边数据节点
        Node<K, V> rightNode;

        //不断向右和向下搜索指定层级的前置索引节点
        while (currentLevel >= 1) {
            //1. 将索引向右移动
            while (true) {
                rightIdx = currentIdx.right;
                if (rightIdx == null) {
                    break;
                }

                rightNode = rightIdx.node;

                //如果右边索引节点还在目标节点的左边，则将索引向右移动
                if (rightNode.compareKeyTo(expectedNode) < 0) {
                    currentIdx = currentIdx.right;
                } else {
                    break;
                }
            }

            //2. 将索引向下移动一步
            if (currentLevel > expectedLevel) {
                currentLevel = (currentLevel - 1);
                currentIdx = currentIdx.down;
            } else {
                break;
            }
        }

        return currentIdx;
    }

    /**
     * 查找指定KEY的前置
     *
     * @param key KEY
     * @return cn.zifangsky.skiplist.SkipListMap.Node<K, V>
     */
    private Node<K, V> findPreNode(K key) {
        Index<K, V> currentIdx = head;
        //下边索引节点
        Index<K, V> downIdx;
        //右边索引节点
        Index<K, V> rightIdx;
        //右边数据节点
        Node<K, V> rightNode;

        //不断向右和向下搜索指定KEY的前置数据节点
        while (true) {
            //1. 将索引向右移动
            while (true) {
                rightIdx = currentIdx.right;
                if (rightIdx == null) {
                    break;
                }

                rightNode = rightIdx.node;

                //如果右边索引节点还在目标节点的左边，则将索引向右移动
                if (rightNode.compareKeyTo(key) < 0) {
                    currentIdx = currentIdx.right;
                } else {
                    break;
                }
            }

            downIdx = currentIdx.down;

            //2. 如果下边索引节点不为空，则将索引向下移动一步
            if (downIdx != null) {
                currentIdx = downIdx;
            } else {
                break;
            }
        }

        //3. 在数据链表上继续向右移动
        Node<K, V> idxNode = currentIdx.node;
        while (true) {
            rightNode = idxNode.next;

            //如果右边数据节点还在目标节点的左边，则将索引向右移动
            if (rightNode == null || rightNode.compareKeyTo(key) >= 0) {
                break;
            } else {
                idxNode = idxNode.next;
            }
        }

        return idxNode;
    }

}
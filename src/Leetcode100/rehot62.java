package Leetcode100;
//Todo：实现trie（前缀树,一种多叉树）
public class rehot62 {
    class Trie {
        //多叉树模型
        class TireNode {
            //标记是否是最后一个结点（区分search和startsWith）
            private boolean isEnd;
            //存储多叉树的子节点
            TireNode[] next;

            public TireNode() {
                isEnd = false;
                next = new TireNode[26];
            }
        }

        private TireNode root;

        public Trie() {
            root = new TireNode();
        }

        public void insert(String word) {
            TireNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TireNode();
                }
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TireNode node = root;
            for (char c : word.toCharArray()) {
                node = node.next[c - 'a'];
                if (node == null) {
                    return false;
                }
            }
            //如果遍历到的最后一个结点不是结尾，就说明这只是前缀而不是整个单词
            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            TireNode node = root;
            for (char c : prefix.toCharArray()) {
                node = node.next[c - 'a'];
                if (node == null) {
                    return false;
                }
            }
            //这里就不需要判断了
            return true;
        }
    }

    //答案的另一种写法，没有新建一个多叉树，直接用当前的trie类作为树的结点
    class Trie2 {
        //记录该字母的下一位所有可能的字母坐标
        private Trie2[] children;
        //该字母是否为最后一个字母
        private boolean isEnd;
        public Trie2() {
            //初始化26个字母
            children = new Trie2[26];
            //默认为不是最后一个字母
            isEnd = false;
        }
        public void insert(String word) {
            //得到字典树根节点
            Trie2 node = this;
            //去遍历待插入单词的字符集合
            for (char c : word.toCharArray()) {
                //得到该字符在数组中的坐标
                int index = c - 'a';
                //如果正在遍历的该字母在上一个节点的数组坐标中没有记录，就新建一个字母节点在字典树中
                if(node.children[index] == null){
                    node.children[index] = new Trie2();
                }
                //每一次生成字母都移动指针到下一个字母节点
                node = node.children[index];
            }
            //最后一个字母节点设置为最后一个字母
            node.isEnd = true;
        }
        public boolean search(String word) {
            //返回检索到的最后一个字母节点
            Trie2 node = searchPrefix(word);
            //只有当该单词在字典树中存在并且最后一个字母节点为最后一个字母，才返回true
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            //只要前缀匹配存在于字典树中就返回true
            return searchPrefix(prefix) != null;
        }
        //前缀搜索 还是 全文搜索都是调用此方法，区别在于前缀搜索只要前缀匹配就返回true，全文搜索则要匹配到最后一个字母才返回true，所以这里返回的是最后一个字母节点
        public Trie2 searchPrefix(String word){
            //得到字典树根节点
            Trie2 node = this;
            //开始验证字符串在字典树中是否存在
            for (char c : word.toCharArray()) {
                //得到该字符在数组中的坐标
                int index = c - 'a';
                //如果该字符在数组中存在，就移动指针到下一个字母节点，直至到达最后一个待搜索的最后一个字母节点
                if(node.children[index] != null){
                    node = node.children[index];
                }else{
                    //如果在此过程中没有找到待搜索的其中一个字母节点，就返回null，代表该字母不存在于字典树中
                    return null;
                }
            }
            //没有问题，那就是到达了最后一个待搜索的最后一个字母节点，返回该节点(节点可能是最后一个字母节点也可能不是)
            return node;
        }
    }
}

package com.scofen.util.tree;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 将扁平数据构造成树结构
 * 将树结构转为扁平结构
 *
 * @author: gaofeng
 * @date: 2021/1/4 16:49
 * @version: 1.0
 */
public class TreeUtils {

    public static final String DEFAULT_SPLITERATOR = "/";

    private TreeUtils() {
    }


    enum Model {
        TREE, PATH, TREE_AND_PATH
    }


    /**
     * 构造树的入口方法
     *
     * @param originData    数据列表
     * @param rootPredicate 根节点条件
     * @param <T>           implements IpaasTree
     * @param <K>           implements Serializable
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> buildTree(List<T> originData, Predicate<T> rootPredicate) {
        return baseBuild(Model.TREE, originData, null, rootPredicate);
    }


    /**
     * 构建树路径，不改变数据结构
     *
     * @param originData
     * @param rootPredicate
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> buildPath(List<T> originData, Predicate<T> rootPredicate) {
        return buildPath(originData, DEFAULT_SPLITERATOR, rootPredicate);
    }

    /**
     * 构建树路径，不改变数据结构
     *
     * @param originData
     * @param spliterator
     * @param rootPredicate
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> buildPath(List<T> originData, CharSequence spliterator, Predicate<T> rootPredicate) {
        return baseBuild(Model.PATH, originData, spliterator, rootPredicate);
    }

    /**
     * 构建树和路径
     *
     * @param originData
     * @param rootPredicate
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> buildTreeAndPath(List<T> originData, Predicate<T> rootPredicate) {
        return buildTreeAndPath(originData, DEFAULT_SPLITERATOR, rootPredicate);
    }

    /**
     * 构建树和路径
     *
     * @param originData
     * @param spliterator
     * @param rootPredicate
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> buildTreeAndPath(List<T> originData, CharSequence spliterator, Predicate<T> rootPredicate) {
        return baseBuild(Model.TREE_AND_PATH, originData, spliterator, rootPredicate);
    }


    /**
     * 树构建的基础方法
     *
     * @param model
     * @param originData
     * @param spliterator
     * @param rootPredicate
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> baseBuild(Model model,
                                                                                       List<T> originData,
                                                                                       CharSequence spliterator,
                                                                                       Predicate<T> rootPredicate) {
        if (Objects.isNull(model) || CollectionUtils.isEmpty(originData) || Objects.isNull(rootPredicate)) {
            return Collections.EMPTY_LIST;
        }

        List<T> result = new ArrayList<>(CollectionUtils.size(originData));

        List<T> roots = originData.stream().filter(rootPredicate).sorted(Comparator.comparing(T::index)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(roots)) {
            return Collections.EMPTY_LIST;
        }

        // 删除根节点，避免重复遍历
        originData.removeAll(roots);

        switch (model) {
            case TREE:
                roots.forEach(r -> result.add(buildTree(r, originData)));
                break;
            case PATH:
                roots.forEach(r -> result.addAll(buildPath(r, originData, spliterator)));
                break;
            case TREE_AND_PATH:
                roots.forEach(r -> result.add(buildTreeAndPath(r, originData, spliterator)));
                break;
            default:
                throw new RuntimeException();
        }
        return result;
    }


    /**
     * 构建子节点
     *
     * @param parentNode 父节点
     * @param originData 数据列表
     * @param <T>        implements IpaasTree
     * @param <K>        implements Serializable
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> T buildTree(T parentNode, List<T> originData) {
        List<T> childrenNode = new ArrayList<>();

        List<T> children = filterByParentId(parentNode.id(), originData);

        if (CollectionUtils.isNotEmpty(children)) {
            // 删除节点，避免重复遍历
            originData.removeAll(children);
        }
        for (T child : children) {
            child.parent(parentNode);
            childrenNode.add(buildTree(child, originData));
        }
        parentNode.children(childrenNode);
        return parentNode;
    }


    /**
     * 构建子节点路径
     *
     * @param parentNode
     * @param originData
     * @param spliterator
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> buildPath(T parentNode, List<T> originData, CharSequence spliterator) {
        List<T> result = new ArrayList<>();

        List<T> children = filterByParentId(parentNode.id(), originData);

        result.add(parentNode);

        if (CollectionUtils.isEmpty(children)) {
            return result;
        }

        // 删除节点，避免重复遍历
        originData.removeAll(children);

        for (T child : children) {
            child.parent(parentNode);
            child.path(parentNode.path() + spliterator + child.pathProperty());
            result.addAll(buildPath(child, originData, spliterator));
        }
        parentNode.children(children);
        return result;
    }

    /**
     * 构建树和路径
     *
     * @param parentNode
     * @param originData
     * @param spliterator
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> T buildTreeAndPath(T parentNode, List<T> originData, CharSequence spliterator) {
        List<T> children = filterByParentId(parentNode.id(), originData);

        if (CollectionUtils.isNotEmpty(children)) {
            originData.removeAll(children);
        }

        for (T child : children) {
            child.parent(parentNode);
            child.path(parentNode.path() + spliterator + child.path());
            buildTreeAndPath(child, originData, spliterator);
        }

        parentNode.children(children);
        return parentNode;

    }


    /**
     * @param parentId   父节点ID
     * @param originData 数据列表
     * @param <T>        implements IpaasTree
     * @param <K>        implements Serializable
     * @return
     */
    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> filterByParentId(K parentId, List<T> originData) {
        return originData.stream()
                .filter(i -> Objects.equals(parentId, i.parentId()))
                .sorted(Comparator.comparing(T::index))
                .collect(Collectors.toList());

    }


    public static <T extends TreeNode<T, K>, K extends Serializable> List<T> tree2list(List<T> tree) {
        List<T> list = new ArrayList<>();
        tree.forEach(i -> getChild(i, null, list));
        return list;
    }


    public static <T extends TreeNode<T, K>, K extends Serializable> void getChild(T root, K parentId, List<T> list) {
        root.parentId(parentId);
        root.id(root.id());
        list.add(root);
        if(CollectionUtils.isNotEmpty(root.children())) {
            root.children().forEach(children -> getChild(children, root.id(), list));
        }
        root.children(null);
    }


    @Getter
    public static class TestTreeNode implements TreeNode<TestTreeNode, Long>, Serializable {

        private static final long serialVersionUID = -9153069020164026942L;

        private Long id;

        private String name;

        private Long parentId;

        private Integer index;

        private List<TestTreeNode> children;

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public void setChildren(List<TestTreeNode> children) {
            this.children = children;
        }

        @Override
        
        public Long id() {
            return this.id;
        }

        @Override
        public Long parentId() {
            return this.parentId;
        }

        @Override
        public String path() {
            return this.name;
        }

        @Override
        public void path(String path) {
            this.name = path;
        }

        @Override
        public Integer index() {
            return this.index;
        }

        @Override
        public List<TestTreeNode> children() {
            return this.children;
        }

        @Override
        public void children(List<TestTreeNode> children) {
            this.children = children;
        }

        public TestTreeNode() {
        }

        public TestTreeNode(Long id, Long parentId, String name, Integer index) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        List<TestTreeNode> testTrees;
        System.out.println("=========================【buildTree】============================");
        testTrees = TreeUtils.buildTree(initData(), tree -> Objects.isNull(tree.parentId()));
        System.out.println(JSON.toJSONString(testTrees));
        System.out.println("=========================【buildPath】============================");
        testTrees = TreeUtils.buildPath(initData(), tree -> Objects.isNull(tree.parentId()));
        System.out.println(JSON.toJSONString(testTrees));
        System.out.println("======================【buildTreeAndPath】=========================");
        testTrees = TreeUtils.buildTreeAndPath(initData(), tree -> Objects.isNull(tree.parentId()));
        System.out.println(JSON.toJSONString(testTrees));

        testTrees = initData();
        System.out.println("======================【tree2list】=========================");
        System.out.println(JSON.toJSONString(TreeUtils.tree2list(testTrees)));

    }

    static List<TestTreeNode> initData() {

        return Lists.newArrayList(
                new TestTreeNode(1L, null, "一级-1", 1),
                new TestTreeNode(2L, null, "一级-2", 2),
                new TestTreeNode(3L, null, "一级-3", 3),

                new TestTreeNode(4L, 1L, "二级-1", 1),
                new TestTreeNode(5L, 1L, "二级-2", 2),
                new TestTreeNode(6L, 1L, "二级-3", 3),

                new TestTreeNode(7L, 4L, "三级-1", 1),
                new TestTreeNode(8L, 4L, "三级-2", 2),
                new TestTreeNode(9L, 4L, "三级-3", 3)
        );
    }


}



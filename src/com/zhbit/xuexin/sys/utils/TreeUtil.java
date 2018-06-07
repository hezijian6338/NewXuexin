package com.zhbit.xuexin.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.Authority;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 采用递归方式去构造树和生成json数组
 * 
 * @author 梁日宇
 */
public class TreeUtil {

    private List<Authority> list;

    TreeMap<String, List<Authority>> map;

    public TreeUtil() {
    }

    public TreeUtil(List<Authority> list) {
        super();
        this.list = list;
    }

    /**
     * 
     * 
     * @Title: doTreeMenu
     * @Description: TODO(构建菜单。)
     * @author 梁日宇
     * @date 2015-12-27 下午8:21:20
     * @return
     * @return JSONArray
     */
    public JSONArray getTreeMenu() {
        createAuthorityTreeMap();
        return treeMapToJSONArray(Const.defult_Pid);
    }

    /**
     * 
     * 
     * @Title: getAuthorityTree
     * @Description: TODO(构建权限树外调函数。)
     * @author 梁日宇
     * @date 2015-12-27 下午8:29:36
     * @return
     * @return JSONArray
     */
    public JSONArray getAuthorityTree() {
        createAuthorityTreeMap();
        return authorityTree(Const.defult_Pid);
    }

    /**
     * 
     * 
     * @Title: createAuthorityTreeMap
     * @Description: TODO(将权限信息构造成树形结构，保存在map。)
     * @author Administrator
     * @date 2015-12-27 下午8:31:11
     * @return void
     */
    public void createAuthorityTreeMap() {
        map = new TreeMap<String, List<Authority>>();
        List<Authority> tmproot = new ArrayList<Authority>();
        map.put(Const.defult_Pid, tmproot);
        for (Authority auth : list) {
            List<Authority> tmp = null;
            if ((tmp = map.get(auth.getParentId())) != null) {// 寻找auth的父节点Pid
                tmp.add(auth);
            }
            else {
                tmp = new ArrayList<Authority>();
                tmp.add(auth);
            }
            map.put(auth.getParentId(), tmp);
        }
    }

    /**
     * 
     * 
     * @Title: treeMapToJSONArray
     * @Description: TODO(将菜单权限树转换成json数组。)
     * @author 梁日宇
     * @date 2015-12-27 下午8:31:31
     * @param string
     * @return
     * @return JSONArray
     */
    public JSONArray treeMapToJSONArray(String string) {
        List<Authority> li = map.get(string);
        JSONArray ja = new JSONArray();
        if (li != null) {
            for (Authority auth : li) {
                JSONArray jsa;
                JSONObject jo = new JSONObject();// auth 的孩子打包 为jo，一层层封装
                if ((jsa = treeMapToJSONArray(auth.getAuthorityId())) != null) {// 孩子
                                                                           // 有自己的孩子，递归打包
                    jo.put("name", auth.getAuthorityName());
                    jo.put("submenu", jsa);
                }
                else {
                    jo.put("name", auth.getAuthorityName());
                    if (auth.getUrl() == null) {
                        jo.put("url", "");
                    }
                    else {
                        jo.put("url", auth.getUrl());
                    }
                }
                ja.add(jo);// 把这些打包 以后给上一层
            }
            return ja;
        }
        return null;
    }

    /**
     * 
     * 
     * @Title: authorityTree
     * @Description: TODO(构建权限树。)
     * @author 梁日宇
     * @date 2015-12-27 下午8:31:46
     * @param string
     * @return
     * @return JSONArray
     */
    public JSONArray authorityTree(String string) {
        List<Authority> li = map.get(string);
        JSONArray ja = new JSONArray();
        if (li != null) {
            for (Authority auth : li) {
                JSONArray jsa;
                JSONObject jo = new JSONObject();// auth 的孩子打包 为jo
                jo.put("id", auth.getAuthorityId());
                jo.put("text", auth.getAuthorityName());
                if ((jsa = authorityTree(auth.getAuthorityId())) != null) {// 孩子有自己的孩子
                    jo.put("children", jsa);
                }
                ja.add(jo);// 把这些打包 以后给上一层
            }
            return ja;
        }
        return null;
    }

  
    /**
     * @Title: linkAuthority
     * @Description: TODO(链接所对应的操作类型。)
     * @author 梁日宇
     * @date 2016-1-3 下午11:39:41
     * @return
     * @return Map<String,List<Integer>>
     */
    public Map<String, List<Integer>> linkAuthority() {
        createAuthorityTreeMap();
        Map<String, List<Integer>> linkAuthMap = new HashMap<String, List<Integer>>();
        if (list != null) {
            for (Authority auth : list) {
                List<Authority> childs = map.get(auth.getAuthorityId());
                if (childs != null) {
                    List<Integer> types = new ArrayList<Integer>();
                    for (Authority child : childs) {
                        types.add(child.getAuthorityType());
                    }
                    if (linkAuthMap.containsKey(auth.getUrl())) {// 若包含则叠加
                        types.addAll(linkAuthMap.get(auth.getUrl()));
                        linkAuthMap.remove(auth.getUrl());
                    }
                    linkAuthMap.put(auth.getUrl(), types);
                }
            }
        }
        return linkAuthMap;
    }

}

package com.zhbit.xuexin.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.Organization;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-2-20 下午7:40:01
 *@version 1.0
 */
public class OrgTreeUtil {

    private List<Organization> list;
    
    TreeMap<String, List<Organization>> map;

    public OrgTreeUtil(List<Organization> list) {
        super();
        this.list = list;
    }
    
    public void createTreeMap() {
        map = new TreeMap<String, List<Organization>>();
        List<Organization> tmproot = new ArrayList<Organization>();
        map.put(Const.defult_Pid, tmproot);
        for (Organization org : list) {
            List<Organization> tmp = null;
            if ((tmp = map.get(org.getParentId())) != null) {// 寻找父节点Pid
                tmp.add(org);
            }
            else {
                tmp = new ArrayList<Organization>();
                tmp.add(org);
            }
            map.put(org.getParentId(), tmp);
        }
    }
    
    /**
     * 
     * 
     * @Title: orgTree
     * @Description: TODO(构建树形组织机构列表。)
     * @author 梁日宇
     * @date 2015-12-27 下午8:31:46
     * @param orgId
     * @return
     * @return JSONArray
     */
    public JSONArray orgTree(String orgId) {
        List<Organization> li = map.get(orgId);
        JSONArray ja = new JSONArray();
        if (li != null) {
            for (Organization org : li) {
                JSONArray jsa;
                JSONObject jo = new JSONObject();// auth 的孩子打包 为jo
                jo.put("id", org.getOrgId());
                jo.put("text", org.getOrgName());
                if ((jsa = orgTree(org.getOrgId())) != null) {// 孩子有自己的孩子
                    jo.put("children", jsa);
                    //jo.put("state", "closed");
                }
                ja.add(jo);// 把这些打包 以后给上一层
            }
            return ja;
        }
        return null;
    }
    
    public JSONArray getOrgTree() {
        createTreeMap();
        return orgTree(Const.defult_Pid);
    }
    
}

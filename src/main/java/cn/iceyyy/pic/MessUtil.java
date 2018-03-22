package cn.iceyyy.pic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.SqlRunner;
import cn.hutool.db.ds.simple.SimpleDataSource;

public class MessUtil {

    public static String getKeyWord(String str) {
        str = str.trim();
        str = StrUtil.removeSuffix(str, "?");
        str = StrUtil.removeSuffix(str, "？");
        str = StrUtil.removeSuffix(str, ".");
        str = StrUtil.removeSuffix(str, "。");
        if (str.startsWith("有没有") && str.endsWith("工具类")) {
            str = StrUtil.unWrap(str, "有没有", "工具类");
            str = StrUtil.removeSuffix(str, "的");
            return str;
        }
        if (str.startsWith("有没有") && str.endsWith("方法")) {
            str = StrUtil.unWrap(str, "有没有", "方法");
            str = StrUtil.removeSuffix(str, "的");
            return str;
        }
        if (str.startsWith("有没有") && str.endsWith("函数")) {
            str = StrUtil.unWrap(str, "有没有", "函数");
            str = StrUtil.removeSuffix(str, "的");
            return str;
        }
        return str;
    }

    public static String find(String keyWord) {
        String url = "jdbc:mysql://rm-wz91d4g2u3o2zcwhvo.mysql.rds.aliyuncs.com:3306/hutool";
        String user = "hutool";
        String pass = "CNhutool4";
        List<ResultItem> list = find(url, pass, user, keyWord);
        list = CollUtil.distinct(list);
        if (list == null) {
            return keyWord;
        }
        if ("http".equalsIgnoreCase(keyWord) || "https".equalsIgnoreCase(keyWord)) {
            ResultItem get = new ResultItem();
            get.setClassName("cn.hutool.http.HttpUtil");
            get.setMethodName("get");
            get.setSummary("发送get请求");
            ResultItem post = new ResultItem();
            post.setClassName("cn.hutool.http.HttpUtil");
            post.setMethodName("post");
            post.setSummary("发送post请求");
            List<ResultItem> first = new ArrayList<ResultItem>();
            first.add(get);
            first.add(post);
            first.addAll(list);
            list = first;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            ResultItem entity = list.get(i);
            sb.append(entity);
            if (i >= 5) {
                break;
            }
        }
        return sb.toString();
    }

    private static List<ResultItem> find(String url, String pass, String user, String params) {
        SimpleDataSource ds = new SimpleDataSource(url, user, pass);
        params.replace("\t", " ");
        params = params.replace("'", "");
        params = params.replace("\"", "");
        params = params.replace(" or ", "");
        params = params.replace(" and ", "");
        SqlRunner sqlRunner = SqlRunner.create(ds);
        List<ResultItem> list = null;
        try {
            String sql = null;
            if ("http".equalsIgnoreCase(params) || "https".equalsIgnoreCase(params)) {
                sql = "select distinct * from t_hutool where summary like ? and summary not like '%http://%' and summary not like '%https://%' limit 1000";

            } else {
                sql = "select * from t_hutool where summary like ? limit 1000";
            }
            params = StrUtil.wrap(params, "%", "%");
            list = sqlRunner.query(sql, ResultItem.class, params);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

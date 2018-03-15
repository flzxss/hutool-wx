package cn.iceyyy.pic;

import java.sql.SQLException;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Entity;
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
        List<Entity> list = find(url, pass, user, keyWord);
        if (list == null) {
            return keyWord;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            String className = entity.getStr("className");
            String methodName = entity.getStr("methodName");
            String summary = entity.getStr("summary");
            sb.append("className=" + className);
            sb.append("\n");
            sb.append("methodName=" + methodName);
            sb.append("\n");
            sb.append("summary=\n" + summary);
            sb.append("\n");
            sb.append("----------------------------------------");
            sb.append("\n");
            if (i >= 5) {
                break;
            }
        }
        return sb.toString();
    }

    private static List<Entity> find(String url, String pass, String user, String params) {
        SimpleDataSource ds = new SimpleDataSource(url, user, pass);
        params.replace("\t", " ");
        params = params.replace("'", "");
        params = params.replace("\"", "");
        params = params.replace(" or ", "");
        params = params.replace(" and ", "");
        SqlRunner sqlRunner = SqlRunner.create(ds);
        String sql = "select * from t_hutool where summary like ? limit 10";
        List<Entity> list = null;
        try {
            params = StrUtil.wrap(params, "%", "%");
            list = sqlRunner.query(sql, params);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

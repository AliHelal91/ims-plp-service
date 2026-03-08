package com.channels.ims.plp.config;

import java.util.ArrayList;
import java.util.List;

public final class SqlContext {

    private static final ThreadLocal<List<String>> SQLS =
            ThreadLocal.withInitial(ArrayList::new);

    private SqlContext() {}

    public static void add(String sql) {
        SQLS.get().add(sql);
    }

    public static List<String> getAll() {
        return new ArrayList<>(SQLS.get());
    }

    public static void clear() {
        SQLS.remove();
    }
}

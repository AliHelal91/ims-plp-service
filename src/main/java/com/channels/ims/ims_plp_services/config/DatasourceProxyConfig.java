package com.channels.ims.ims_plp_services.config;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import net.ttddyy.dsproxy.proxy.ParameterSetOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatasourceProxyConfig {

    /**
     * Query Listener to fetch the Actual Query with Params
     * @return QueryExecutionListener
     */
    @Bean
    public QueryExecutionListener myLogDtoListener() {
        return new QueryExecutionListener() {
            @Override
            public void beforeQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
                // Not needed for logging
            }

            @Override
            public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
                for (QueryInfo queryInfo : queryInfoList) {

                    String sqlWithValues = buildQueryWithParameters(queryInfo);
                    SqlContext.add(sqlWithValues);
                }
            }
        };
    }

    /**
     * Method To rebuild the Actual DB Query with Parameter by replace the (?) with Actual Value
     *
     * @param queryInfo QueryInfo
     * @return String
     */
    public String buildQueryWithParameters(final QueryInfo queryInfo) {

        // Find The SQL Query with (?)
        String sql = queryInfo.getQuery();

        // Get The List of Query Parameters
        List<List<ParameterSetOperation>> batchParams = queryInfo.getParametersList();

        // If Params Empty return the SQL Query
        if (batchParams.isEmpty()) {
            return sql;
        }

        // Take first parameter set (usually batch size 1)
        List<ParameterSetOperation> params = batchParams.get(0);

        // Get The Actual Params Value and Replace with (?)
        for (ParameterSetOperation param : params) {

            String value = param.getArgs()[1] == null ? "NULL" : param.getArgs()[1].toString();
            sql = sql.replaceFirst("\\?", value);
        }
        return sql;
    }
}

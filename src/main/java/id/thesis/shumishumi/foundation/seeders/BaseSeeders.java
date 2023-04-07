package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.model.context.TracerContext;
import id.thesis.shumishumi.common.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

@Priority(1)
@Component
@Profile("!test")
public abstract class BaseSeeders {

    protected static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.SEEDERS_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String tableName;
    private String seedersName;

    abstract String setTableName();

    abstract String setSeedersName();

    abstract void seeds();

    private void deleteAllData() {
        String statement = new StatementBuilder(tableName, DatabaseConst.STATEMENT_DELETE)
                .buildStatement();

        LogUtil.info(LOGGER, String.format("%s: delete statement - %s", seedersName, statement));

        try {
            jdbcTemplate.update(statement);
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
        }
    }

    public void execute() {
        TracerContext.initialize();
        this.tableName = setTableName();
        this.seedersName = setSeedersName();

        LogUtil.info(LOGGER, String.format("%s: start seeding", seedersName));

        deleteAllData();

        seeds();

        TracerContext.removeTracer();
    }
}

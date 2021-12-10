package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TraderDao extends JdbcCrudDao<Trader> {

  private final String TABLE_NAME = "trader";
  private final String ID_COLUMN = "id";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleInsert;

  private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);

  @Autowired
  public TraderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
    logger.debug("TraderDao JDBC connection created.");
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<Trader> getEntityClass() {
    return Trader.class;
  }

  @Override
  public <S extends Trader> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public List<Trader> findAllById(Iterable<Integer> ids) {
    return null;
  }

  // Unimplemented methods

  @Override
  public int updateOne(Trader entity) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void delete(Trader trader) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void deleteAll(Iterable<? extends Trader> iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }
}

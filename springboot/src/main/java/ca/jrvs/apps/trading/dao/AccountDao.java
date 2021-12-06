package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

  private final String TABLE_NAME = "account";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  private final static Logger logger = LoggerFactory.getLogger(AccountDao.class);

  @Autowired
  public AccountDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
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
  Class getEntityClass() {
    return Account.class;
  }

  @Override
  public <S extends Account> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public List<Account> findAllById(Iterable<Integer> ids) {
    return null;
  }

  public Account findByTraderId(Integer traderId) {
    return findAll().stream().filter(account -> account.getTraderId() == traderId).collect(
        Collectors.toList()).get(0);
  }

  @Override
  public int updateOne(Account account) {
    String updateSql = "UPDATE " + TABLE_NAME + " SET trader_id=?, amount=? WHERE " + ID_COLUMN + "=?";
    return jdbcTemplate.update(updateSql, new Object[]{account.getTraderId(), account.getAmount(), account.getId()});
  }

  // Unimplemented methods

  public Object[] makeUpdateValues(Account account) {
    return new Object[]{account.getTraderId(), account.getAmount(), account.getId()};
  }

  @Override
  public void delete(Account account) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void deleteAll(Iterable<? extends Account> iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }
}

package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  abstract public JdbcTemplate getJdbcTemplate();

  abstract public SimpleJdbcInsert getSimpleJdbcInsert();

  abstract public String getTableName();

  abstract public String getIdColumnName();

  abstract Class<T> getEntityClass();

  @Override
  public <S extends T> S save(S entity) {
    if (existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update quote.");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  /**
   * Helper method that saves one entity.
   *
   * @param entity
   * @param <S>
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  abstract public int updateOne(T entity);

  @Override
  public boolean existsById(Integer id) {
    return findById(id).isPresent();
  }

  public Optional<T> findById(Integer id) {
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
    Optional<T> foundEntities = Optional.empty();
    try {
      foundEntities = Optional.ofNullable(getJdbcTemplate().queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (EmptyResultDataAccessException e) {
      logger.error(this.getClass() + " findById() caught exception.");
    }

    if (foundEntities.isPresent()) {
      return foundEntities;
    }

    return Optional.empty();
  }

  @Override
  public List<T> findAll() {
    String selectSql = "SELECT * FROM " + getTableName();
    List<T> foundEntities = getJdbcTemplate().query(selectSql,
        BeanPropertyRowMapper.newInstance(getEntityClass()));
    return foundEntities;
  }

  public abstract List<T> findAllById(Iterable<Integer> ids);

  @Override
  public void deleteById(Integer id) {
    if (id == null) {
      logger.error(this.getClass() + " deleteById() caught exception.");
      throw new IllegalArgumentException("ID cannot be null.");
    }
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
    getJdbcTemplate().update(deleteSql, id);
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(deleteSql);
  }

  @Override
  public long count() {
    String countSql = "SELECT count(*) FROM " + getTableName();
    return getJdbcTemplate().queryForObject(countSql, Long.class);
  }
}

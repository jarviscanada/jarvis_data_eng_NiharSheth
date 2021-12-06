package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {

  private final static Logger logger = LoggerFactory.getLogger(PositionDao.class);
  private final String TABLE_NAME = "position";
  private final String ID_COLUMN = "account_id";
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Optional<Position> findById(Integer id) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=?";
    Optional<Position> foundPositions = Optional.empty();
    try {
      foundPositions = Optional.ofNullable(
          jdbcTemplate.queryForObject(selectSql, BeanPropertyRowMapper.newInstance(Position.class),
              id));
    } catch (EmptyResultDataAccessException e) {

    }

    if (foundPositions.isPresent()) {
      return foundPositions;
    }

    return Optional.empty();
  }

  public List<Position> findAll() {
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    List<Position> foundPositions = jdbcTemplate.query(selectSql,
        BeanPropertyRowMapper.newInstance(Position.class));
    return foundPositions;
  }

  public long count() {
    String countSql = "SELECT count(*) FROM " + TABLE_NAME;
    return jdbcTemplate.queryForObject(countSql, Long.class);
  }

  public boolean existsById(Integer id) {
    return findById(id).isPresent();
  }
}

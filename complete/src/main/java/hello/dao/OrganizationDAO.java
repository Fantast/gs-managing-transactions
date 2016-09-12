package hello.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hello.dto.Organization;

@Component
@Transactional(propagation = Propagation.MANDATORY)
public class OrganizationDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIfApplicationWithNameExists(String name) throws InterruptedException {
//        return jdbcTemplate.queryForObject("SELECT  count(*)>0 FROM organization WHERE status!='removed' AND name=? LOCK IN SHARE MODE",
//        return jdbcTemplate.queryForObject("SELECT  count(*)>0 FROM organization WHERE status!='removed' AND name=? FOR UPDATE",
        return jdbcTemplate.queryForObject("SELECT  count(*)>0 FROM organization WHERE status!='removed' AND name=?",
                new Object[]{name}, Boolean.class);
    }

    public boolean checkIfApplicationWithCodeExists(String code) throws InterruptedException {
//        return jdbcTemplate.queryForObject("SELECT  count(*)>0 FROM organization WHERE status!='removed' AND code=? LOCK IN SHARE MODE",
//        return jdbcTemplate.queryForObject("SELECT  count(*)>0 FROM organization WHERE status!='removed' AND code=? FOR UPDATE",
        return jdbcTemplate.queryForObject("SELECT  count(*)>0 FROM organization WHERE status!='removed' AND code=?",
                new Object[]{code}, Boolean.class);
    }

    public void create(String code, String name) throws InterruptedException {
        jdbcTemplate.update("INSERT INTO organization(code, name) VALUES (?, ?)", code, name);
    }

    public List<Organization> list() {
        return jdbcTemplate.query("SELECT * FROM organization", BeanPropertyRowMapper.newInstance(Organization.class));
    }
}

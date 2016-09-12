package hello.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import hello.dao.OrganizationDAO;
import hello.dto.Organization;

@Service
public class OrganizationService {

    private final static Logger log = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationDAO dao;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(String code, String name) throws Exception {
//        if (dao.checkIfApplicationWithCodeExists(code)) {
//            throw new IllegalArgumentException("Already got the code: " + code);
//        }
        if (dao.checkIfApplicationWithNameExists(name)) {
            throw new IllegalArgumentException("Already got the name: " + name);
        }
        Thread.sleep(3000);
        log.info("EXEC    : [{}, {}]", code, name);
        dao.create(code, name);
        log.info("DONE    : [{}, {}]", code, name);
        Thread.sleep(3000);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Organization> list() {
        return dao.list();
    }
}

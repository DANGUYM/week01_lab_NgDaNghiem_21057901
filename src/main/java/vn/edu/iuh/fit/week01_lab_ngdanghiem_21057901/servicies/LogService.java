package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.servicies;

import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Log;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories.LogRepository;

import java.time.Instant;
import java.time.LocalDateTime;

public class LogService {
    private LogRepository logRepository = new LogRepository();

    public void recordLogin(String accountId) {
        Log log = new Log();
        log.setAccountId(accountId);
        log.setLoginTime(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC));
        logRepository.recordLog(log);
    }

    public void recordLogout(String accountId) {
        Log log = new Log();
        log.setAccountId(accountId);
        log.setLogoutTime(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC));
        logRepository.recordLog(log);

    }
}


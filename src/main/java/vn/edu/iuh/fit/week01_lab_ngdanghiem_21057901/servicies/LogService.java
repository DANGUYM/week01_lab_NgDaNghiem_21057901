package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.servicies;

import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Log;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories.LogRepository;

import java.time.LocalDateTime;

public class LogService {
    private LogRepository logRepository = new LogRepository();

    public void recordLogin(Log log) {
        logRepository.recordLog(log);
    }

    public void recordLogout(String accId) {
        Log recentLog = logRepository.findMostRecentLogByAccountId(accId);
        recentLog.setLogoutTime(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC));

        LocalDateTime loginTime = recentLog.getLoginTime().atZone(java.time.ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime logoutTime = recentLog.getLogoutTime().atZone(java.time.ZoneOffset.UTC).toLocalDateTime();
        long minutes = java.time.Duration.between(loginTime, logoutTime).toMinutes();
        recentLog.setNotes("Online " + minutes + " minutes");

        logRepository.recordLog(recentLog);
    }
}


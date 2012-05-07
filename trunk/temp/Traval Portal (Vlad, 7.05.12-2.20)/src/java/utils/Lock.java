/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import database.mapping.Employee;
import database.mapping.Trf;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Merle
 */
public class Lock {
    
    private static long timeout = 10000;
    private static Set<Trf> lockedTrfs = new HashSet<Trf>();
    private static Map<Trf, Employee> employeesWhoLocked = new HashMap<Trf, Employee>();
    private static Map<Trf, Date> timeOfLock = new HashMap<Trf, Date>();
    
    public static boolean lockTrf(Trf trf, Employee employee){
        if ((trf != null) & !lockedTrfs.contains(trf)) {
            lockedTrfs.add(trf);
            employeesWhoLocked.put(trf, employee);
            timeOfLock.put(trf, new Date());
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean unlockTrf(Trf trf){
        if ((trf != null) & lockedTrfs.contains(trf)) {
            lockedTrfs.remove(trf);
            employeesWhoLocked.remove(trf);
            timeOfLock.remove(trf);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isLockedTrf(Trf trf){
        if (lockedTrfs.contains(trf)){
            long difference = new Date().getTime() - timeOfLock.get(trf).getTime();
            if (difference > timeout){
                unlockTrf(trf);
                return true;
            }
            return true;
        } else {
            return false;
        }
    }
}

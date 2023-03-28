package dev.abdullo.roomreservation.listener;

import dev.abdullo.roomreservation.dao.ReservationDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@WebListener
public class Listener implements ServletContextListener, Job/*, HttpSessionListener, HttpSessionAttributeListener*/ {


    public Listener() {
    }

    private org.quartz.Scheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job = newJob(Listener.class)
                    .withIdentity("job1", "group1")
                    .build();
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
                    .withSchedule(dailyAtHourAndMinute(12, 0))
                    .build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(JobExecutionContext context) {
        ReservationDAO.getInstance().deleteExpiredReservations();
    }


}

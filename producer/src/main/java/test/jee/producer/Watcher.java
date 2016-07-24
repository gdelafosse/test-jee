package test.jee.producer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

@Singleton
@Startup
public class Watcher extends FileAlterationListenerAdaptor
{
    private final static Path messages = Paths.get("/messages");

    private final static Path errors = Paths.get(messages.toString(), ".errors");

    @EJB
    private Producer producer;

    private FileAlterationMonitor monitor;

    @PostConstruct
    private void start() throws Exception
    {
        createErrors();
        FileAlterationObserver fao = new FileAlterationObserver(messages.toFile());
        fao.addListener(this);
        monitor = new FileAlterationMonitor();
        monitor.addObserver(fao);
        System.out.println("Producer starts watching /messages");
        monitor.start();
    }

    @PreDestroy
    private void stop() throws Exception
    {
        monitor.stop();
    }

    private void createErrors() throws IOException
    {
        if (!errors.toFile().exists())
        {
            Files.createDirectories(errors);
        }
    }

    @Override
    public void onFileCreate(final File file)
    {
        try
        {
            System.out.println(String.format("File %s created", file.toString()));
            String message = new String(Files.readAllBytes(file.toPath()), "UTF-8");
            producer.sendMessage(message);
            file.delete();
            System.out.println(String.format("File %s sent", file.toString()));
        }
        catch (Exception e)
        {
            moveToErrors(file);
        }
    }

    private void moveToErrors(File file)
    {
        try
        {
            Files.move(file.toPath(), errors);
            System.out.println(String.format("File %s moved to error", file.toString()));
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }
}

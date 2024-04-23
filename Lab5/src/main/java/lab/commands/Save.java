package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.managers.DumpManager;

import java.io.IOException;

public class Save extends Command {
    private DumpManager dumpManager;
    public Save(DumpManager dumpManager){
        super("save","сохранить коллекцию в файл");
        this.dumpManager = dumpManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        dumpManager.dump();
    }
}

package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.ExitException;

import java.io.IOException;

public class Exit extends Command{

    public Exit(){
        super("exit","завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        throw new ExitException();
    }
}

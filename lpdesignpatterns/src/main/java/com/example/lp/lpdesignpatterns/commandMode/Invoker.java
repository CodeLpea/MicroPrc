package com.example.lp.lpdesignpatterns.commandMode;

import com.example.lp.lpdesignpatterns.commandMode.Command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求命令类
 */
public class Invoker {
        //开发过程中没有此属性，此处作为示例，用来便利和撤销操作
        private static List<Command> list = new ArrayList<Command>();
        private static List<Command> redoList = new ArrayList<Command>();

        public Invoker(Command command)
        {
            list.add(command);
        }

        public void action(Command command){
            command.excute();
        }

        public void removeAction(Command command){
            if(list.contains(command)){
                System.out.println(command.getClass().getName()+"被移除");
                list.remove(command);
                redoList.add(command);
            }
        }
        public void redoAction(){
            if(redoList.size()>0){
                Command redoCommand=redoList.get(redoList.size()-1);
                redoCommand.excute();
                System.out.println(redoCommand.getClass().getName()+"被恢复");
                redoList.remove(redoList.size()-1);
                list.add(redoCommand);
            }else {
                System.out.println("没有可以恢复的移除");
            }

        }

        public void allAction(){
            if(list.size()>0){
                for (Command mCommand : list)
                {
                    mCommand.excute();
                }
            }
        }


}

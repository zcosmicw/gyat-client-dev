package us.gyatdevs.commands.impl;

import us.gyatdevs.commands.Command;

public class AuthorsCommand implements Command {
    private static final String COMMAND_NAME = "authors";

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public void onCommand(String[] args) {
        msgHelper.sendSeparateLine();
        msgHelper.sendMessage("Authors of the client is: &f0gyatdevs &7and &fmadeq", true);
        msgHelper.sendSeparateLine();
    }

}

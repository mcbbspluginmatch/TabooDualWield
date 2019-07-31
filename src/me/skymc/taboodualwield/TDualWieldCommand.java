package me.skymc.taboodualwield;

import com.ilummc.tlib.resources.TLocale;
import me.skymc.taboolib.commands.builder.SimpleCommandBuilder;
import org.bukkit.Bukkit;

import static com.ilummc.tlib.resources.TLocale.Translate.setColored;

/**
 * @Author CziSKY
 * @Since 2019-06-19 14:44
 */
public class TDualWieldCommand{
    public TDualWieldCommand(){
        SimpleCommandBuilder.create("TDualWieldCommand", TabooDualWield.getInst())
                .permission("TDualWieldCommand.*")
                .permissionMessage(setColored("&8&l[&2&lTabooDualWield&8&l] &4你没有使用这条指令的权限!"))
                .description("The main command of TabooDualWield.")
                .aliases("twc", "tdualwield")
                .execute((sender, args) -> {
                    if (args.length == 0){
                        for (String CommandTitle : getCommandTitle()) {
                            sender.sendMessage(CommandTitle);
                        }
                        sender.sendMessage(setColored(" &f/twc reload &6- &e重载配置文件."));
                        sender.sendMessage(" ");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("reload")){
                        TabooDualWield.getConf().reload();
                        TLocale.reload();
                        sender.sendMessage(setColored("&8&l[&2&lTabooDualWield&8&l] &e重载成功!"));
                        return true;
                    }
                    return true;
                }).build();
    }

    private String[] getCommandTitle() {
        return new String[] { " ", setColored("&e&l----- &6&lTabooDualWield Commands &e&l-----"), " " };
    }

}

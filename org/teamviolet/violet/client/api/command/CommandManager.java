//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.command;

import net.minecraft.client.*;
import org.teamviolet.violet.client.impl.command.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.events.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import java.util.concurrent.atomic.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.handler.*;
import com.mojang.brigadier.suggestion.*;

public class CommandManager
{
    private static final String PREFIX = ";";
    private final CommandDispatcher<Object> dispatcher;
    private final Minecraft mc;
    
    public CommandManager() {
        this.dispatcher = (CommandDispatcher<Object>)new CommandDispatcher();
        this.mc = Minecraft.getMinecraft();
    }
    
    public void init() {
        new BindCommand().populate(this.dispatcher);
        new FakePlayerCommand().populate(this.dispatcher);
        new FriendCommand().populate(this.dispatcher);
        new HelpCommand().populate(this.dispatcher);
        new ModulesCommand().populate(this.dispatcher);
        new SettingCommand().populate(this.dispatcher);
        new SettingsCommand().populate(this.dispatcher);
        new ToggleCommand().populate(this.dispatcher);
        new NameCommand().populate(this.dispatcher);
        new VersionCommand().populate(this.dispatcher);
        new HClipCommand().populate(this.dispatcher);
        new TPCommand().populate(this.dispatcher);
        Violet.getViolet().getDispatcher().register(this);
    }
    
    @Listener
    public void listen(final ChatEvent.Outgoing event) {
        if (!event.getMessage().startsWith(";")) {
            return;
        }
        event.cancel();
        final ParseResults<Object> parse = (ParseResults<Object>)this.dispatcher.parse(event.getMessage().substring(1), (Object)this);
        int result = 0;
        try {
            result = this.dispatcher.execute((ParseResults)parse);
        }
        catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        if (result == 0) {
            final String commandName = event.getMessage().split(" ")[0];
            final AtomicReference<String> chatMessage = new AtomicReference<String>("Command " + commandName + " did not successfully execute.");
            String suggestion;
            final AtomicReference<String> atomicReference;
            this.dispatcher.getCompletionSuggestions((ParseResults)parse).thenAccept(suggestions -> {
                if (!suggestions.isEmpty()) {
                    suggestion = ((Suggestion)suggestions.getList().get(0)).apply(event.getMessage());
                    atomicReference.set(atomicReference.get() + " Did you mean '" + suggestion.substring(0, suggestion.length() - 1) + "'?");
                }
                return;
            });
            InfoUtil.chatError(chatMessage.get());
        }
        this.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
    }
}

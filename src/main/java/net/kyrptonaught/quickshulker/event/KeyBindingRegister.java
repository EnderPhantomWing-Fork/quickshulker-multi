package net.kyrptonaught.quickshulker.event;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.kyrptonaught.kyrptconfig.keybinding.DisplayOnlyKeyBind;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
//#if MC >= 1.21.10
//$$ import net.minecraft.client.option.KeyBinding;
//$$ import net.minecraft.util.Identifier;
//#else
//#endif

public class KeyBindingRegister {
    //#if MC >= 1.21.10
    //$$  public static final KeyBinding.Category MAIN = KeyBinding.Category.create(Identifier.of(QuickShulkerMod.MOD_ID));
    //
    //$$  public static void register(){
    //$$      KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
    //$$              "key.quickshulker.config.openSettingGui",
    //$$              MAIN,
    //$$              QuickShulkerMod.getConfig().openSettingGui,
    //$$              setKey -> QuickShulkerMod.config.save()
    //$$      ));
    //$$      KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
    //$$              "key.quickshulker.config.keybinding",
    //$$              MAIN,
    //$$              QuickShulkerMod.getConfig().keybinding,
    //$$              setKey -> QuickShulkerMod.config.save()
    //$$      ));
    //$$  }
    //#else
    public static final String MAIN = "key.categories.quickshulker";

    public static void register(){
        KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
                "key.quickshulker.config.openSettingGui",
                MAIN,
                QuickShulkerMod.getConfig().openSettingGui,
                setKey -> QuickShulkerMod.config.save()
        ));
        KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
                "key.quickshulker.config.keybinding",
                MAIN,
                QuickShulkerMod.getConfig().keybinding,
                setKey -> QuickShulkerMod.config.save()
        ));
    }
    //#endif
}

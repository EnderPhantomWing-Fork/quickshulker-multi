package net.kyrptonaught.kyrptconfig.keybinding;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
//#if MC >= 1.21.10
//$$ import net.minecraft.util.Identifier;
//#else
//#endif

import java.util.function.Consumer;

public class DisplayOnlyKeyBind extends KeyBinding {
    private CustomKeyBinding customKeyBinding;
    private final Consumer<InputUtil.Key> keySet;

    //#if MC >= 1.21.10
    //$$ public DisplayOnlyKeyBind(String translationKey, InputUtil.Type type, int code, KeyBinding.Category category) {
    //#else
    public DisplayOnlyKeyBind(String translationKey, InputUtil.Type type, int code, String category) {
    //#endif
        super(translationKey, type, code, category);
        keySet = (boundKey) -> {
        };
    }

    //#if MC >= 1.21.10
    //$$ public DisplayOnlyKeyBind(String translationKey, KeyBinding.Category category, CustomKeyBinding customKeyBinding, Consumer<InputUtil.Key> keySet) {
    //#else
    public DisplayOnlyKeyBind(String translationKey, String category, CustomKeyBinding customKeyBinding, Consumer<InputUtil.Key> keySet) {
    //#endif
        super(translationKey, customKeyBinding.getDefaultKey().getCategory(), customKeyBinding.getDefaultKey().getCode(), category);
        this.customKeyBinding = customKeyBinding;
        this.keySet = keySet;
        updateSetKey();
    }

    public void setBoundKey(InputUtil.Key boundKey) {
        super.setBoundKey(boundKey);
        if (customKeyBinding != null)
            customKeyBinding.setRaw(getBoundKeyTranslationKey());
        keySet.accept(boundKey);
    }

    public void updateSetKey() {
        super.setBoundKey(customKeyBinding.getKeybinding().orElse(InputUtil.UNKNOWN_KEY));
    }

    //#if MC >= 1.21.10
    //$$ @Override
    //$$ public KeyBinding.Category getCategory() {
    //$$     updateSetKey();
    //$$     return super.getCategory();
    //$$ }
    //#else
    @Override
    //#if MC >= 1.21.10
    //$$ public KeyBinding.Category getCategory() {
    //#else
    public String getCategory() {
    //#endif
        updateSetKey();
        return super.getCategory();
    }
    //#endif

    @Override
    //#if MC >= 1.21.10
    //$$ public String getId() {
    //#else
    public String getTranslationKey() {
    //#endif
        updateSetKey();
        //#if MC >= 1.21.10
        //$$ return super.getId();
        //#else
        return super.getTranslationKey();
        //#endif
    }

    @Override
    public InputUtil.Key getDefaultKey() {
        updateSetKey();
        return super.getDefaultKey();
    }
}

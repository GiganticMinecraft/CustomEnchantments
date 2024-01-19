package com.taiter.ce.Enchantments.Armor;

import com.taiter.ce.Enchantments.CEnchantment;
import com.taiter.ce.ReflectionHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Shielded extends CEnchantment {

  private static Method getAbsorptionHearts;
  private static Method setAbsorptionHearts;
  int baseStrength;
  int strengthPerLevel;
  long cooldown;

//    static {
//        try {
//            getAbsorptionHearts = ReflectionHelper.getNMSClass("EntityHuman").getDeclaredMethod("getAbsorptionHearts");
//            setAbsorptionHearts = ReflectionHelper.getNMSClass("EntityHuman").getDeclaredMethod("setAbsorptionHearts", float.class);
//        } catch (NoSuchMethodException | SecurityException e) {
//            e.printStackTrace();
//        }
//    }

  public Shielded(Application app) {
    super(app);
    configEntries.put("BaseStrength", 4);
    configEntries.put("StrengthPerLevel", 2);
    configEntries.put("Cooldown", 30);
    triggers.add(Trigger.DAMAGE_TAKEN);
  }

  @Override
  public void effect(Event e, ItemStack item, int level) {
    EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
    Player player = (Player) event.getEntity();
    if (getAbsorptionHearts(player) <= 0) {
      setAbsorptionHearts(player, baseStrength + level * strengthPerLevel);
      generateCooldown(player, cooldown);
    }
  }

  @Override
  public void initConfigEntries() {
    baseStrength = Integer.parseInt(
        getConfig().getString("Enchantments." + getOriginalName() + ".BaseStrength"));
    strengthPerLevel = Integer.parseInt(
        getConfig().getString("Enchantments." + getOriginalName() + ".StrengthPerLevel"));
    cooldown = Long.parseLong(
        getConfig().getString("Enchantments." + getOriginalName() + ".Cooldown"));
  }

  private float getAbsorptionHearts(Player player) {
    try {
      return (float) getAbsorptionHearts.invoke(ReflectionHelper.getEntityHandle(player),
          new Object[0]);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return 0;
  }

  private void setAbsorptionHearts(Player player, float newValue) {
    try {
      setAbsorptionHearts.invoke(ReflectionHelper.getEntityHandle(player), newValue);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

}

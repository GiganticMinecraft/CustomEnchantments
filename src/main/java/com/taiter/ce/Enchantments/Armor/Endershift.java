package com.taiter.ce.Enchantments.Armor;

/*
 * This file is part of Custom Enchantments
 * Copyright (C) Taiterio 2015
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


import com.taiter.ce.Enchantments.CEnchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Endershift extends CEnchantment {

  int duration;
  int strength;
  int trigger;
  int cooldown;

  public Endershift(Application app) {
    super(app);
    configEntries.put("Duration", 200);
    configEntries.put("Strength", 5);
    configEntries.put("HpToTrigger", 4);
    configEntries.put("Cooldown", 6000);
    triggers.add(Trigger.DAMAGE_TAKEN);
  }

  @Override
  public void effect(Event e, ItemStack item, int level) {
    EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
    Player player = (Player) event.getEntity();
    if (player.getHealth() <= trigger) {
      player.addPotionEffect(
          new PotionEffect(PotionEffectType.SPEED, duration * level, strength + level - 1));
      player.addPotionEffect(
          new PotionEffect(PotionEffectType.ABSORPTION, duration * level, strength + level - 1));
      player.sendMessage("You feel a rush of energy coming from your armor!");
      generateCooldown(player, cooldown);
    }
  }

  @Override
  public void initConfigEntries() {
    duration = Integer.parseInt(
        getConfig().getString("Enchantments." + getOriginalName() + ".Duration"));
    strength =
        Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Strength"))
            - 1;
    trigger = Integer.parseInt(
        getConfig().getString("Enchantments." + getOriginalName() + ".HpToTrigger"));
    cooldown = Integer.parseInt(
        getConfig().getString("Enchantments." + getOriginalName() + ".Cooldown"));
  }
}

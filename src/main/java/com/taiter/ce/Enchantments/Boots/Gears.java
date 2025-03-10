package com.taiter.ce.Enchantments.Boots;

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
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;


public class Gears extends CEnchantment {

  int strength;

  public Gears(Application app) {
    super(app);
    configEntries.put("SpeedBoost", 1);
    triggers.add(Trigger.WEAR_ITEM);
  }

  @Override
  public void effect(Event e, ItemStack item, int level) {
  }

  @Override
  public void initConfigEntries() {
    strength =
        Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".SpeedBoost"))
            - 1;
    this.getPotionEffectsOnWear().put(PotionEffectType.SPEED, strength);
  }
}

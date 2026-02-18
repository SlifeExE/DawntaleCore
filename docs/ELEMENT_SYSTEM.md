# Dawntale Element System


## Overview

The Element System is based on a **9-element cycle**, where each element dominates exactly one other and is dominated by exactly one other. This creates a perfectly balanced network.

Debuff stacks accumulate with each successful hit and trigger powerful effects at maximum stacks. Elements have specific cleanse mechanics and interactions, creating strategic depth in combat.

---

## Base Elements (9)

### Element Dominance Circle

🔴 **Miasma** → 💧 **Water** → 🔥 **Fire** → ❄️ **Ice** → 🌍 **Earth** → ⚡ **Lightning** → ✨ **Arcane** → 🌑 **Void** → ☀️ **Holy** → 🔴 **Miasma**

Each element beats the next and is beaten by the previous one.

---

## General Mechanics & Rules

### Stack Application
- **Debuff stacks are ONLY applied on successful hits**
- Dodge, Block, Parry, or other defense actions prevent stack application
- The attack must connect with the target to apply element stacks
- **Shield Spells:** While an active shield is present, elemental stacks are NOT applied
    - Shields block stack application as long as they are active/intact
    - Once shield expires or breaks, stacks can be applied again on subsequent hits

### Damage Reduction & Immunity

**Elemental Resistance (via Stats):**
- Elemental resistance reduces damage taken from element effects
- Each element has its own resistance stat
- **Resistance is CAPPED at 50% maximum** (via character stats alone)
- Higher resistance = lower damage taken from that element's effects (DoT, burns, etc.)
- Cannot achieve full immunity through stat resistance

**Resilience Stat (RES):**
- Leveling the **Resilience (RES)** stat grants additional elemental resistance to **ALL elements**
- Resilience bonus applies globally across all 9 elements simultaneously
- Helps with general elemental defense without specializing in specific resistances
- Works in combination with individual element resistances (additive)

**Full Element Immunity (100%):**
Complete immunity against an element can ONLY be achieved through special mechanics (not through stat resistance):

- **Temporary Buffs & Spells:** Protective spells that grant immunity to element effects
- **Special Immunity Conditions:** Environmental effects, status-based immunities, ability-based immunities
- **Armor/Equipment Effects:** Armor enchantments, legendary items, special perks

**Immunity Behavior:**
- While immune to an element: **elemental effects are negated, stacks are NOT applied**
- **Physical damage from attacks is STILL taken** (immunity only blocks elemental effects, not base damage)

- Immunity prevents ALL elemental effects of that element (DoT, stacks, debuffs)
- Example: If immune to Fire, cannot be burned AND cannot apply Fire stacks
- Immunity is visible/trackable on the entity for multiplayer awareness

---

## Stack Decay Behavior Types

### Decays Over Time
Effect stacks reduce automatically after duration expires. Each stack reduction resets the duration to match the new stack count. Process repeats until 0 stacks (continuous cascade).  
New Stacks can always be applied again while decaying, decay cascade starts with first stack

**Example:** Miasma at 5 stacks → 5 sec duration → reduces to 4 stacks → 4 sec duration → ... → 0 stacks

### Decays After Trigger
Effect stacks persist until a specific condition is met (e.g., next hit taken, ability used). Once triggered, stacks are consumed and effect activates.

**Stack Timeout:** If no new hits with this element are received for **15 seconds**, all stacks automatically decay to 0.

**Example:** Ice at max stacks → Next hit taken → guaranteed Crit + Frozen state → stacks reset

**Timeout Example:** Player has 7 Wet stacks, takes no further Water element hits for 15 seconds → stacks automatically reset to 0

---

## Debuff Names Reference

Quick lookup table for all Stack and Trigger debuff names:

| Element | Stack Debuff | Trigger Debuff | Max Stacks | Color |
|---------|--------------|----------------|------------|-------|
| 🔴 Miasma | Rot | Blight | 5 | Red |
| 💧 Water | Wet | Vulnerable | 15 | Blue |
| 🔥 Fire | Ignite | Burn | 8 | Orange |
| ❄️ Ice | Frostbite | Frozen | 12 | Cyan |
| 🌍 Earth | Grasp | Rooted | 10 | Green |
| ⚡ Lightning | Charged | Paralyzed | 20 | Yellow |
| ✨ Arcane | Distortion | Phase Shifted | 10 | Purple |
| 🌑 Void | Corruption | Soul Rend | 10 | Dark Purple |
| ☀️ Holy | Sanctify | Purified | 10 | Gold |

---

## Detailed Element Definitions

### 🔴 Miasma - Blight Decay System

**Core Mechanic:**
- Max Stacks: 5
- Attack Speed Slow: -20% (constant, non-degrading)
- DoT: Ticks every 0.5 seconds
- **DoT Damage Scaling:** 0.1% of max HP per stack per tick (1 stack = 0.1%, 5 stacks = 0.5% per tick)
- New Stacks can always be applied again while decaying, decay cascade starts with first stack
**Scenario: 5 Miasma Stacks Applied**

**Activation (5 Stacks Applied via Hits):**
- Applies: DoT tick every 0.5 seconds for **5 seconds** (1 sec per stack)
- **DoT Damage per tick:** 0.5% of max HP (5 stacks × 0.1% per stack)
- Applies: Attack Speed Slow (-20%) for entire effect duration
- Total DoT ticks: 10 (5 sec ÷ 0.5 sec intervals)
- **Total DoT Damage:** 10 ticks × 0.5% = **5% of max HP**

**After 5 Seconds (Decay Cascade Begins):**
- Stacks: 5 → 4
- Duration reset: **4 seconds** (matches stack count)
- **DoT Damage per tick:** 0.4% of max HP (4 stacks × 0.1% per stack)
- DoT tick interval: every 0.5 seconds for 4 seconds
- **Attack Speed Slow persists at -20% (DOES NOT DEGRADE)**
- Total DoT ticks: 8 (4 sec ÷ 0.5 sec intervals)
- **Total DoT Damage:** 8 ticks × 0.4% = **3.2% of max HP**

**Complete Decay Timeline:**
- 5 Stacks: 5 sec duration → 10 ticks → 0.5% per tick → **-20% AS (persistent)** → Total: 5% max HP
- 4 Stacks: 4 sec duration → 8 ticks → 0.4% per tick → **-20% AS (persistent)** → Total: 3.2% max HP
- 3 Stacks: 3 sec duration → 6 ticks → 0.3% per tick → **-20% AS (persistent)** → Total: 1.8% max HP
- 2 Stacks: 2 sec duration → 4 ticks → 0.2% per tick → **-20% AS (persistent)** → Total: 0.8% max HP
- 1 Stack: 1 sec duration → 2 ticks → 0.1% per tick → **-20% AS (persistent)** → Total: 0.2% max HP
- 0 Stacks: Effect ends

**Total Duration:** 5 + 4 + 3 + 2 + 1 = **15 seconds**
**Cumulative DoT Damage (5→0 cascade):** 5% + 3.2% + 1.8% + 0.8% + 0.2% = **11% of max HP total**

---

### 💧 Water - Vulnerability System

**Mechanic:**
- Each hit applies 1 Wet stack (cumulative)
- At 15 stacks: Entity becomes Vulnerable
- Vulnerable effect: All Defenses reduced by -20% for 15 seconds
- After duration expires: Stacks reset to 0

**Defense Reduction Applies To:**
- Armor/Physical Defense
- Magic Resistance
- All damage mitigation sources

**Cleanse Interaction:**
- **Cannot be cleansed** with standard Cleanse spells
- **Heat Spells only:** Heat spells cast on Vulnerable entity apply Heatshield buff
- **Heatshield Effect:** 10 second buff making entity resistant to Water/Vulnerability
- Entity resists further Wet stack application while Heatshield is active

**Max Stacks:** 15
**Vulnerability Duration:** 15 seconds
**Defense Reduction:** -20% (all defense types)

---

### 🔥 Fire - Burn System

**Mechanic:**
- Each hit applies 1 Ignited stack (no damage on apply)
- At 8 stacks: Entity becomes Burning
- Burning effect: DoT for 3 seconds (ticks every 0.25 seconds = 12 ticks total)
- **DoT Damage:** 2% of max HP per tick
- After duration expires: Stacks reset to 0

**Cleanse Interaction:**
- **Cannot be cleansed** with standard Cleanse spells
- **Water Spells only:** Water spells cast on Burning entity extinguish Fire (Cleaning)
- **Extinguish Effect:** Removes Burning status WITHOUT dealing damage (pure elemental interaction)
- Entity is NOT damaged by Water cleansing

**Max Stacks:** 8
**Burn Duration:** 3 seconds
**Tick Interval:** 0.25 seconds (12 ticks total)
**DoT Damage:** 2% of max HP per tick
**Total Burn Damage:** 12 ticks × 2% = **24% of max HP**

---

### ❄️ Ice - Freeze System

**Mechanic:**
- Each hit applies 1 Frostbite stack (cumulative)
- At 7 stacks: Movement Speed reduced by -25% (visible debuff)
- At 12 stacks: Entity becomes Frozen
- Frozen state: Cannot perform ANY actions (no movement, attacks, abilities, spells)
- Duration: 5 seconds OR until next hit taken OR Fire spell cast on frozen entity

**Shield Interaction:**
- Shield spells CAN be applied on frozen entities
- Entity remains Frozen while shield is active
- Entity CANNOT receive heals or buffs while frozen
- Once shield expires or breaks: Entity takes damage normally and Frostbite stacks can be applied again

**Fire Spell Interaction:**
- Fire spells cast on frozen entity = cleanses Frozen status WITHOUT dealing damage
- Resets stacks to 0
- Entity is NOT damaged by the cleansing fire spell
- **Heatshield Applied:** Entity gains Heatshield buff for 10 seconds after cleanse
- **Heatshield Effect:** Makes entity resistant to Frostbite and Frozen (cannot be stacked again during duration)

**Thaw Trigger - Enhanced Critical Hit:**
- Next hit on frozen entity = **Guaranteed Critical Hit**
- **Damage Calculation:** `max(200%, Entity's Crit DMG Value)`
    - Minimum 200% of normal damage (double damage)
    - If entity has Crit DMG stat > 200%, use that value instead
    - Example: Entity has 250% Crit DMG → next hit deals 250% damage
    - Example: Entity has 150% Crit DMG → next hit deals 200% damage (minimum)
- **Only applies to ONE damage hit** on frozen entity
- After hit is taken: Stacks reset to 0
- Duration expires = stacks reset to 0
- Frostbite Movement Speed reduction resets also

---

### 🌍 Earth - Root System

**Mechanic:**
- Each hit applies 1 Grasp stack (cumulative)
- At 10 stacks: Entity becomes Rooted
- Rooted effect: Cannot move, BUT can still use abilities/spells for 8 seconds OR until Roots are destroyed
- After duration OR root destruction: Stacks reset to 0

**Root Destruction:**
- **Standard Destruction:** 3 regular hits to break Root
- **Fire Spell Destruction:** 1 Fire spell hit destroys Root instantly (no damage from spell)
- **Immune:** To Lightning, Water & Holy Type Spells 

**Cleanse Interaction:**
- **Cannot be cleansed** with standard Cleanse spells
- **Only Fire destroys Roots** (Fire spells provide instant destruction)

**Root Properties:**
- Destroyable object with HP equivalent to 3 hits
- Can be targeted and attacked to break early
- Prevents movement (excludes teleport abilities)

---

### ⚡ Lightning - Paralysis System

**Mechanic:**
- Each hit applies 1 Shock stack (Charged)
- At 20 stacks: Paralyzed effect triggers
- Paralyzed effect: Target is stunned for 5 seconds, stacks reset to 0
- Damage does not cancel Paralysis

**Cleanse Interaction:**
- **Cleanse spells ONLY** can break Paralysis stun early
- Healing spells do NOT remove Paralysis (only pure cleanse works)
- Self-cleanse abilities (on the paralyzed entity) also work
- Cannot be healed/supported while Paralyzed

---

### ✨ Arcane - Phase Shift Teleport System

**Mechanic:**
- Each hit applies 1 Distortion stack
- Each stack = +2% Spell Cooldown Reduction (applied during spell cast)
- At 10 stacks: Phase Shifted effect active + Mana and Stamina regeneration reduced by 50%
- Phase Shift Trigger: **Every hit on entity with 10 stacks triggers teleport** (entity cannot go to 11 stacks)
- After teleport Stacks stay at 10 and slowly Decay, 1 Stack per 2 seconds (For Stack 1-9) When at full Stacks it takes 5 Seconds to decay to 9 Stacks
- New Stacks can always be applied again while decaying

**Teleport Parameters:**
- **Horizontal Range:** 3-10 blocks away in a circle around entity
- **Vertical Range:** 0-8 blocks UPWARD (also can teleport into air/void)
- Can teleport into air - entity may take **fall damage** depending on landing height
- **Always finds a valid spot** within range (algorithm always succeeds)
- Teleport happens instantly

**Teleport Mechanics:**
- Repeated application: Every time entity reaches 10 stacks + takes another hit → Phase Shift triggers
- Can chain multiple Phase Shifts if hits keep coming

**Example Scenario:**
- Entity at coordinates (100, 64, 100)
- 5 hits applied → 5 Distortion stacks
- 5 more hits → 10 stacks (Phase Shifted effect active)
- Next (11th) hit → Teleported to random location: (105-110, 64-72, 98-104)
- Teleports into air at Y=70 (6 blocks up) → takes fall damage when landing
- Stacks reset to 0
- If more hits continue: stacks build to 10 again → triggers Phase Shift again
- **Infinite loop possible** if attacker keeps hitting

---

### 🌑 Void - Soul Rend System

**Mechanic:**
- Each hit applies 1 Void stack (cumulative)
- Each stack = -6% Healing reduction (cumulative: 1 stack = -6%, 2 stacks = -12%, etc.)
- At 10 stacks: Soul Rend is triggered
- Soul Rend effect: Entity cannot receive ANY healing (-100%) for 10 seconds
- **Critical:** New stacks CANNOT be applied while Soul Rend is active (10 sec duration)
- After Soul Rend duration expires: Stacks reset to 0, entity becomes vulnerable to Void stacking again

**Healing Reduction Applies To:**
- Self-healing (potions, abilities)
- Ally healing from other players/entities
- Passive regeneration effects
- All healing sources are blocked equally

**Example Stack Progression:**
- 1 Stack: -6% Healing
- 5 Stacks: -30% Healing
- 10 Stacks: Soul Rend triggered (-100% Healing for 10 sec, no new stacks allowed)
- After 10 sec: Stacks reset to 0, can reapply

---

### ☀️ Holy - Purification System

**Mechanic:**
- Each hit applies 1 Sanctify stack (cumulative)
- At 10 stacks: Purified effect triggers
- Purified effect: Removes ALL buffs AND debuffs from entity, stacks reset to 0

**Purification Removes:**
- All positive buffs (damage boosts, speed buffs, shields, etc.)
- All negative debuffs (slows, DoTs, weakness effects, etc.)
- Both beneficial and harmful effects are stripped equally

**Strategic Consideration:**
- Can cleanse friendly buffs if used improperly on allies
- Can cleanse enemy debuffs if used on enemies (double-edged sword)
- Useful for resetting state before major encounters

---

## Cleanse & Element Interaction System

### Element Cleanse Compatibility Table

| Element | Cleanseable? | Cleanse Method | Special Interactions |
|---------|--------------|-----------------|----------------------|
| 🔴 Miasma | ✅ YES | Standard Cleanse | Cleanse removes Rot stacks/Blight |
| 💧 Water | ❌ NO | Heat Spells only | Heat Spells apply Heatshield (10 sec, blocks Water stacking) |
| 🔥 Fire | ❌ NO | Water Spells only | Water Spells extinguish Fire (Cleaning) without damage |
| ❄️ Ice | ✅ YES (Fire) | Fire Spells only | Fire Spells cleanse Frozen + apply Heatshield (10 sec, blocks Frostbite/Frozen) |
| 🌍 Earth | ❌ NO | Fire Spells only | Fire Spells destroy Root in 1 hit (alternative to 3 regular hits) |
| ⚡ Lightning | ✅ YES | Cleanse Spells ONLY | Healing/Support spells do NOT remove Paralyzed |
| ✨ Arcane | ✅ YES | Standard Cleanse | Cleanse removes Distortion stacks/Phase Shifted |
| 🌑 Void | ✅ YES | Standard Cleanse | Cleanse removes Corruption stacks/Soul Rend |
| ☀️ Holy | 🚫 UNCLEANSEABLE | Cannot be prevented | Purified effect always applies (cannot be blocked or prevented) |

---

## Special Hybrid Elements

### Rule
Only available for **Monsters/Bosses**. Players cannot have these on weapons/armor (for now).

**Combinations only allowed for elements that do NOT counter each other.**

### Hybrid List

| Composition | Name | Effect | Classification |
|---|---|---|---|
| Water + Fire | **Steam** | Blind / Reduced Sight Range | 2-Element |
| Ice + Earth | **Glacial Armor** | Reflects Damage, Defense ++ | 2-Element |
| Void + Lightning + Arcane | **Chaos** | Random Effects (Unpredictable) | 3-Element |
| Holy + Void + Arcane | **Singularity** | Time Warp, Instant-Kill mechanic | 3-Element |

---

## Open Questions (TODO)

### ✅ FINALIZED Mechanics (All 9 Elements - COMPLETE)

**🔴 Miasma (Rot/Blight)**
- [x] Max Stacks: 5
- [x] Attack Speed Slow: -20% (persistent)
- [x] DoT tick: every 0.5 sec

**💧 Water (Wet/Vulnerable)**
- [x] Max Stacks: 15
- [x] Defense Reduction: -20%
- [x] Duration: 15 seconds

**🔥 Fire (Ignite/Burn)**
- [x] Max Stacks: 8
- [x] Duration: 3 seconds
- [x] Tick Interval: 0.25 seconds

**❄️ Ice (Frostbite/Frozen)**
- [x] Max Stacks: 12
- [x] Freeze Duration: 5 seconds
- [x] MS reduction threshold: 7 stacks = -25%

**🌍 Earth (Grasp/Rooted)**
- [x] Max Stacks: 10
- [x] Root Duration: 8 seconds
- [x] Root HP: 3 hits

**⚡ Lightning (Charged/Paralyzed)**
- [x] Max Stacks: 20
- [x] Stun Duration: 5 seconds
- [x] Cleanse: ONLY Cleanse spells (no Healing/Support)

**✨ Arcane (Distortion/Phase Shifted)**
- [x] Max Stacks: 10
- [x] CDR per stack: +2%
- [x] Teleport Range: 3-10 blocks horizontal, 0-8 blocks vertical

**🌑 Void (Corruption/Soul Rend)**
- [x] Max Stacks: 10
- [x] Healing Reduction: -6% per stack
- [x] Soul Rend Duration: 10 seconds
- [x] Soul Rend blocks re-stacking

**☀️ Holy (Sanctify/Purified)**
- [x] Max Stacks: 10
- [x] Effect: Removes all buffs + debuffs

### Remaining Specifications
- [ ] All elements: Damage values and scaling factors
- [x] Miasma: DoT damage value per tick
- [ ] Fire: Burn damage per tick
- [ ] Detailed hybrid effect mechanics (e.g., exact Singularity behavior?)
- [ ] Balance testing & tuning for all stack-based triggers

---

## Implementation Notes

### For Java/Hytale Code:
- Enum for 9 Base Elements with color assignments
- Store element dominance in matrix or enum relationships
- Stack system: `Map<Element, Integer>` per entity
- Separate stack decay logic (Decays Over Time vs. Decays After Trigger)
- **Miasma special:** Cascade decay system with duration matching stack count
- **Arcane special:** Phase Shift triggers on EVERY hit at 10 stacks (teleport loop)
- **Fire:** Track burning state separately from Ignited stacks
- **Ice:** Movement speed reduction scaling at 7 stacks threshold, Freeze at 12 stacks
- Trigger logic for max stack activation (per element)
- Timestamp tracking for timed decay intervals

### For Monster Spawning:
- Hybrid elements only on specific boss spawns
- Keep hybrid element logic separate from base element logic
- Prevent player access to hybrid elements (validation layer)

### Element-Specific Implementation Details:
- **🔴 Miasma:** Max 5 stacks, Duration = Stack Count (1 sec per stack), AS slow = constant -20%
- **💧 Water:** Max 15 stacks, Vulnerability = -20% all defenses for 15 seconds
- **🔥 Fire:** Max 8 stacks, Burn lasts 3 seconds, ticks every 0.25 sec
- **❄️ Ice:** Max 12 stacks, Freeze lasts 5 seconds, MS reduction at 7+ stacks (-25%), Fire spells cleanse + apply Heatshield (10 sec, blocks Frostbite/Frozen)
- **🌍 Earth:** Max 10 stacks, Root lasts 8 seconds, Root HP = 3 hits (destroyable)
- **⚡ Lightning:** Max 20 stacks, Stun lasts 5 seconds, ONLY Cleanse spells break stun (no healing/support spells)
- **✨ Arcane:** Max 10 stacks, +2% CDR per stack, Phase Shift triggers EVERY HIT at 10 stacks, teleports 3-10 blocks horizontal, 0-8 blocks vertical (can teleport into air → fall damage), always finds valid spot
- **🌑 Void:** Max 10 stacks, -6% healing per stack, Soul Rend = -100% healing for 10 seconds, no re-stacking during Soul Rend
- **☀️ Holy:** Max 10 stacks, Purifies all buffs + debuffs equally
- **Fire on Ice:** Fire spells cleanse frozen status without dealing damage, applies Heatshield buff
- **Heatshield:** 10 second buff making entity resistant to Frostbite and Frozen
- Frozen entities cannot receive heals/buffs during freeze
- Root allows ability usage but blocks movement
- Arcane infinite loop possible if attacker maintains hit chain

---

*Last updated: 2026-02-18*
---
apply: always
---

# Junie AI Guidelines — Hytale Modding

These guidelines define the rules and practices for developing Hytale server plugins in the Dawntale project.

---

## 1. Hytale API Lookup Priority

When looking up Hytale functions, methods, classes, or interfaces, **always** follow this priority order:

### Priority 1: Decompiled Server Source Code

```
F:\Hytale_Dev\Mods_and_Plugins\Dawntale\Docs\decompiled
```

The most relevant package:

```
F:\Hytale_Dev\Mods_and_Plugins\Dawntale\Docs\decompiled\com\hypixel\hytale
```

This contains the original decompiled methods and classes of the Hytale server. Use this as the **primary source of truth** to verify method signatures, parameters, return types, and class hierarchies.

### Priority 2: Documentation Collection (Site Docs)

```
F:\Hytale_Dev\Mods_and_Plugins\Dawntale\Docs\site\content\docs\en
```

Contains explanatory documentation covering many decompiled elements, events, commands, and plugin systems.

### Priority 3: HytaleDocs Reference

```
F:\Hytale_Dev\Mods_and_Plugins\Dawntale\Docs\HytaleDocs\index.json
```

The `index.json` provides a high-level overview of the structure and where to find specific documentation. Each content folder typically contains an `overview` file that lists the subfolder structure — use these for navigation.

### Priority 4: UI Elements Reference

```
F:\Hytale_Dev\Mods_and_Plugins\Dawntale\Docs\UI-Elements-Reference.md
```

Based on decompiled code, this documents all available UI elements, their properties, styles, and behaviors for the Custom UI system.

### Important

- **Never** assume method signatures, parameters, or return types from memory. Always verify against the decompiled code.
- If a class or method cannot be found in the decompiled code, communicate this explicitly rather than guessing.
- Do not use Minecraft/Bukkit patterns or APIs — Hytale has its own API design.
- **Do not use deprecated methods** if newer replacements are available. Always check the decompiled code for the current API surface and prefer non-deprecated alternatives.

---

## 2. Project Structure and Conventions

### Maven Project Layout

Every Dawntale plugin follows standard Maven structure:

```
src/
├── main/
│   ├── java/
│   │   └── dev/dawntalemodding/<pluginname>/
│   │       ├── commands/
│   │       ├── database/
│   │       ├── events/
│   │       ├── listeners/
│   │       ├── models/
│   │       ├── ui/
│   │       ├── utils/
│   │       └── <PluginName>.java  # Main class (extends JavaPlugin)
│   └── resources/
│       └── manifest.json          # REQUIRED
└── pom.xml
```

### Build System

- Use **Maven** as the build system.
- Target **Java 25** (as required by Hytale).

### Plugin Manifest (manifest.json)

Every plugin **must** include a `manifest.json` in `src/main/resources/`.

```json
{
  "Group": "Dawntale",
  "Name": "<PluginName>",
  "Version": "1.0.0",
  "Description": "<Short description>",
  "Authors": [
    {
      "Name": "Slife",
      "Email": "",
      "Url": ""
    }
  ],
  "Website": "",
  "ServerVersion": "*",
  "Dependencies": {},
  "OptionalDependencies": {},
  "DisabledByDefault": false,
  "IncludesAssetPack": false,
  "Main": "dev.dawntalemodding.<pluginname>.<MainClassName>"
}
```

**Rules:**

- `Group` is always `"Dawntale"`.
- `Main` must point to the fully qualified main class that extends `JavaPlugin`.
- `Dependencies` should list required plugins (e.g., `"Dawntale:API": "*"`).
- `IncludesAssetPack` set to `true` if the plugin ships with an asset pack.

---

## 3. DawntaleAPI

DawntaleAPI is the central shared library for the Dawntale ecosystem. It provides common utilities like `Players`, `MessageUtils`, and `Colors` that all plugins share. Refer to the DawntaleAPI README and docs for details on available classes and usage.

**All Dawntale plugins must depend on it:**

manifest.json:
```json
"Dependencies": {
"Dawntale:API": "*"
}
```

pom.xml:
```xml
<dependency>
  <groupId>dev.dawntalemodding</groupId>
  <artifactId>DawntaleAPI</artifactId>
  <version>1.0.0</version>
  <scope>provided</scope>
</dependency>
```

**Key rules:**

- Never duplicate shared utilities in individual plugins — they belong in DawntaleAPI.
- Never use deprecated Hytale shortcuts (e.g., `Player.getPlayerRef()`) — use the DawntaleAPI replacement.
- If you need a new shared utility, add it to DawntaleAPI, not to a specific plugin.

---

## 4. UI Development

### UI File Location

All `.ui` layout files for **CustomHUD** and **CustomUIPages** must be placed in the shared Dawntale Asset Pack, **not** inside individual mod/plugin folders:

```
F:\Hytale_Dev\Mods_and_Plugins\Dawntale\Dawntale_Assets\Common\UI\Custom\DawntaleUI
```

### Style Imports and Component Priority

When building UI layouts, use the following import aliases:

```
$S = "Style.ui"
$C = "Common.ui"
```

**Priority order for pre-built components:**

1. **`Style.ui` (first priority)** — Always check `Style.ui` for available Dawntale-specific pre-built components, styles, and templates first.
2. **`Common.ui` (fallback)** — Only use components from `Common.ui` if nothing suitable is available in `Style.ui`.

### UI Technical Notes

- Use `InteractiveCustomUIPage` with the required constructor parameters: `PlayerRef`, `CustomPageLifetime`, `BuilderCodec`.
- Use typed `EventData` parameters instead of generic Maps.
- ScrollView elements require `ScrollbarStyle` instead of `Style` and must use `LayoutMode: TopScrolling`.
- Template parameters use `@` syntax, regular properties use colon assignment.

---

## 5. Hytale Plugin Patterns

### Command Creation

- Use the `AbstractCommand` hierarchy with `addSubCommand()`.
- Call `requirePermission()` in the constructor to override auto-generated permissions.
- Use `CompletableFuture<Void>` for asynchronous operations.

### Permission Schema

All permissions across the Dawntale ecosystem **must** follow a unified naming convention:

```
dawntale.<pluginname>.<command>
dawntale.<pluginname>.<command>.<subcommand>
```

**Examples:**

| Permission | Grants |
|---|---|
| `dawntale.utils.*` | All DawntaleUtils commands |
| `dawntale.economy.*` | All economy commands |
| `dawntale.sync.permissions.reload` | Only the permissions reload subcommand in Sync |
| `dawntale.utils.role.assign` | Only the role assign subcommand in Utils |

**Rules:**

- The root is always `dawntale`, never the auto-generated `<plugin.basepermission>.command.<n>` pattern.
- Plugin name must be lowercase (e.g., `utils`, `sync`, `economy`).
- Subcommands chain with dots: `dawntale.utils.role.assign`, `dawntale.utils.role.remove`.
- Wildcard `*` grants all permissions at that level and below.
- Every command and subcommand **must** have an explicit `requirePermission()` call — never rely on auto-generated permissions.

**Implementation:**

```java
public class RoleCommand extends AbstractCommandCollection {
  public RoleCommand() {
    super("role", "Manage roles");
    requirePermission("dawntale.utils.role");

    addSubCommand(new RoleAssignCommand());  // dawntale.utils.role.assign
    addSubCommand(new RoleRemoveCommand());  // dawntale.utils.role.remove
    addSubCommand(new RoleListCommand());    // dawntale.utils.role.list
  }
}

public class RoleAssignCommand extends AbstractAsyncPlayerCommand {
  public RoleAssignCommand() {
    super("assign", "Assign a role to a player");
    requirePermission("dawntale.utils.role.assign");
  }
}
```

### Database

- Use `DawntaleDB.getInstance()` for database access.
- Wrap asynchronous DB operations with `CompletableFuture`.
- Maintain cached copies of frequently queried data (e.g., role definitions) for synchronous access during performance-critical operations.

### Chat and Messaging

- Use `MessageUtils` and `Colors` from DawntaleAPI for text formatting.
- Display role prefixes in the format `[RoleName] Player: Message` with specific color codes.

### Events

- `PacketReceivedEvent` does not exist in the Hytale API — prefer command-based solutions over packet interception.
- Register event listeners through the plugin's `EventRegistry`.

---

## 6. Plugin Initialization and Structured Logging

### Logging System

Use Hytale's `HytaleLogger` (via `getLogger()` or `HytaleLogger.get()`) for all log output. **Never** use `System.out.println()` for operational logs.

### Module-Based Logging Convention

Every plugin must use **module tags** in log messages during initialization and runtime so that issues can be quickly traced to a specific subsystem. The server automatically prefixes log lines with the plugin name (e.g., `[Sync|P]`, `[Utils|P]`), so the module tag is the text **we** write in the log message itself.

#### Format

```
[Module] Description of what happened
```

- The module tag is always enclosed in **square brackets**.
- The module name describes the subsystem: `Main`, `DB`, `Permissions`, `Player Data`, `Config`, `UI`, `Commands`, etc.

#### Setup Method Structure

In `setup()`, log each subsystem as it initializes. Final message is always `[Main] Successfully initialized.`

```java
@Override
protected void setup() {
  getLogger().atInfo().log("[Config] Configuration loaded from: %s", configPath);
  getLogger().atInfo().log("[DB] Database connection established successfully.");
  getLogger().atInfo().log("[Permissions] Loaded %d roles, %d player assignments from DB.", roleCount, assignmentCount);
  getLogger().atInfo().log("[Commands] Registered %d commands.", commandCount);
  getLogger().atInfo().log("[Main] Successfully initialized.");
}
```

#### Console Output Example

```
[Sync|P] [Config] Configuration reloaded from: /hytale/mods/DawntaleSync/config.json
[Sync|P] [DB] Database connection established successfully.
[Sync|P] [Player Data] Found 2 JSON files in domain 'players'
[Sync|P] [Permissions] Loaded 8 roles, 1 player assignments from DB.
[Sync|P] [Main] Successfully initialized.
```

#### Rules

- **Every distinct subsystem** gets its own module tag.
- If a plugin has no additional subsystems, use `[Main]` for all messages.
- Final log in `setup()` must always be `[Main] Successfully initialized.`

---

## 7. Java Code Conventions

### Naming

- **Packages**: All lowercase, no underscores or special characters. Short, meaningful, domain-based.
- **Classes/Interfaces**: `PascalCase`, nouns or noun phrases.
- **Methods**: `camelCase`, verbs or verb phrases.
- **Variables**: `camelCase`, short and meaningful. No single-letter names except loop counters.
- **Constants**: `UPPER_SNAKE_CASE`.

### Code Layout

- Use 4 spaces for indentation, no tabs.
- Maximum line length of 120 characters.
- When wrapping lines, break after a comma or operator. Indent continuation lines with 8 spaces.

### Classes and Data Structures

- Prefer Java records for pure data holders.
- Prefer immutable classes whenever possible.
- Program to interfaces, not implementations.
- Use enums instead of string or integer constants.
- Utility classes must have a private constructor that throws `UnsupportedOperationException`.

### Exception Handling

- Catch specific exceptions instead of `Exception` or `Throwable`.
- Never silently ignore exceptions — either handle them or rethrow them.

### Concurrency

- Prefer `java.util.concurrent` utilities over `wait()` / `notify()`.
- Use `volatile` only for simple atomic operations. For more complex cases, use `java.util.concurrent.atomic` or locks.

### Optional

- Use `Optional` as a return type when a method might not return a value.
- Do not use `Optional` for class fields or method parameters.

### Stream API

- Avoid side effects in stream operations like `map()` and `filter()`.
- Prefer method references over lambdas when possible.

### Collections

- Choose the right collection for the use case: `List` for ordered collections, `Set` for unordered collections without duplicates, `Map` for key-value pairs.
- Use `isEmpty()` to check if a collection is empty.
- Methods that return collections should return empty collections instead of `null`.
- Use the diamond operator (`<>`) for generic type inference.
- Prefer the `for-each` loop for iteration.

### Date/Time and Strings

- Use the Java 8 Date-Time API (`java.time.*`) instead of `java.util.Date` / `java.util.Calendar`.
- Use multiline text blocks (`"""`) for multi-line string literals (SQL, JSON, XML) instead of concatenation or newline escapes.

---

## 8. Error Handling and User Feedback

- Implement consistent error handling and user feedback across all commands.
- Error messages should clearly describe what went wrong and what the user can do.
- Confirm successful actions with a brief confirmation message.

---

## 9. General Rules

- **No assumptions**: If it is unclear whether a Hytale method exists or how it works, look it up in the decompiled code.
- **No Bukkit/Spigot/Paper patterns**: Hytale is not a Minecraft fork. The API designs differ fundamentally.
- **No deprecated methods**: Always use the latest non-deprecated API when available.
- **No duplication**: Shared utilities belong in DawntaleAPI, not in individual plugins.
- **Caching**: Keep performance-critical data (roles, permissions) cached, but ensure synchronization with the database.
- **Async where needed**: Never execute database or network operations on the main thread.
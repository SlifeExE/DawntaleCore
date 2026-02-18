---
apply: always
---

# Docs Guidelines — Dawntale Projects

These guidelines define the standard structure for README files and documentation across all Dawntale plugins, mods, and libraries.

---

## Part 1: README.md Structure

Every Dawntale project must include a `README.md` following this exact section order:

### 1. Title & Description

```markdown
# Dawntale:<ProjectName>

Short one-to-two sentence description of what the project does.
```

---

### 2. License

Always placed directly after the description.

```markdown
---

### ⚖️ License

The content of this repository is proprietary. Use, distribution, or modification is prohibited without permission.
However, the documentation in the `docs/` folder may be freely used as a "HowTo" source.

See the [LICENSE](LICENSE) file for more details.
```

---

### 3. Features

Brief feature list. One sentence per feature, no detailed explanations.

```markdown
---

### ✨ Features

- **Feature Name**: One sentence description.
- **Feature Name**: One sentence description.
```

---

### 4. Dependencies

List all required dependencies with links (or URL placeholders if not yet available).

```markdown
---

### 📦 Dependencies

| Dependency | Version | Description |
|---|---|---|
| [Dawntale:API](URL) | `1.0.0+` | Core shared library |
```

---

### 5. Project Structure

Show the **complete folder tree** including all nested subdirectories — stop just before individual files (no `.java`, `.json`, etc.). Only include folders that actually exist.

```
src/main/
├── java/dev/dawntalemodding/<pluginname>/
│   ├── commands/              # All command classes
│   │   ├── admin/             # Server administration commands
│   │   ├── debug/             # Debug and diagnostic commands
│   │   └── player/            # Player-facing commands
│   ├── database/              # Database access and management
│   ├── listeners/             # Event listeners
│   └── utils/                 # Utility classes and helpers
└── resources/
    └── manifest.json
```

Every folder must have a short `# description` comment. Descriptions should be one short phrase — no full sentences.

---

### 6. Documentation

Link to all docs in the `docs/` folder. Only list docs that actually exist. **API documentation belongs in DawntaleAPI's own repo, not in individual plugins.**

```markdown
---

### 📚 Documentation

Detailed information can be found in the following documents:

- [**Commands**](docs/commands.md) — Full command list and permissions.
- [**Configuration**](docs/configuration.md) — Guide on how to configure the plugin.
```

---

### 7. Configuration

Short overview of where config files are generated and what they control. One sentence per file.

```markdown
---

### ⚙️ Configuration

Settings are located in `mods/<PluginName>/`:

| File | Purpose |
|---|---|
| `config.json` | General plugin settings. |
| `JoinMsg.json` | Welcome messages and MOTD configuration. |
```

---

### 8. Credits

List contributors. Only include if there are actual contributions to credit.

```markdown
---

### 🤝 Credits

- **Slife** (SlifeExE) — Dawntale Team
- **Badnick1000** — Bread with Meat design
```

---

### 9. Author

Always the final section.

```markdown
---

### 👤 Author

Maintained by: **SlifeExE**

This project is the property of Dawntale.
Copyright © 2026 SlifeExE. All rights reserved.
```

---

---

## Part 2: Documentation Structure (docs/)

Every project should have a `docs/` folder. Quickstart guides, tutorials, and detailed how-tos belong here — **not** in the README.

### Standard Doc Files

| File | Required | Content |
|---|---|---|
| `commands.md` | If plugin has commands | All commands, usage, arguments, permissions |
| `configuration.md` | If plugin has config files | All config options with defaults and explanations |
| `events.md` | If plugin fires custom events | Custom events, payloads, usage examples |
| `ui.md` | If plugin has custom UI | UI pages, HUD elements, layout references |
| `permissions.md` | If plugin has complex permission trees | All permission nodes, role examples, wildcard patterns |
| `api.md` | If plugin exposes API for other plugins | Public methods, integration guide, dependency setup |
| `economy.md` | If plugin has economy features | Currency, wallets, transactions, shop integration |
| `chat.md` | If plugin modifies chat | Chat formatting, prefixes, channels, placeholders |
| `items.md` | If plugin adds custom items | Item definitions, properties, recipes |
| `entities.md` | If plugin adds custom entities | Entity types, spawning, behaviors, stats |
| `npc.md` | If plugin has NPC system | NPC definitions, dialogues, interactions |
| `sounds.md` | If plugin uses custom audio | Sound events, playback, volume settings |
| `effects.md` | If plugin has entity effects | Effect types, durations, stacking behavior |
| `crafting.md` | If plugin adds recipes | Recipe definitions, ingredients, outputs |
| `sync.md` | If plugin syncs data | Sync domains, polling, conflict resolution |
| `colors.md` | If plugin has color system | Color codes, formatting, examples |
| `quickstart.md` | Optional | Getting started guide for new developers |

Only create doc files that are relevant to the project.

**Note:** `CHANGELOG.md` lives in the **project root** alongside `README.md` and `LICENSE`, not in `docs/`. It tracks version history, breaking changes, and migration notes.

---

### Template: CHANGELOG.md

```markdown
# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/).

---

## [Unreleased]

### Added
- New feature or functionality that was added.

### Changed
- Changes to existing functionality.

### Fixed
- Bug fixes.

---

## [1.1.0] - 2026-02-15

### Added
- Role management commands (`/role assign`, `/role remove`, `/role list`).
- Chat prefix system with color support.

### Changed
- Permission schema migrated to `dawntale.<plugin>.<command>` pattern.

### Fixed
- Database connection leak on player disconnect.

### Deprecated
- `Player.getPlayerRef()` usage replaced with `Players.getPlayerRef()`.

---

## [1.0.0] - 2026-01-20

### Added
- Initial release.
- Ban system with temporary and permanent bans.
- Join message system with placeholder support.
- Player info command with database lookup.
```

**Structure rules for CHANGELOG.md:**

- Newest version is always at the top, oldest at the bottom.
- `[Unreleased]` section at the very top collects changes that are not yet in a tagged release.
- Each version uses the format `## [X.Y.Z] - YYYY-MM-DD`.
- Follow [Semantic Versioning](https://semver.org/): MAJOR.MINOR.PATCH.
- Use exactly these change categories (omit empty ones):

| Category | When to use |
|---|---|
| **Added** | New features or functionality |
| **Changed** | Changes to existing behavior |
| **Fixed** | Bug fixes |
| **Deprecated** | Features marked for future removal |
| **Removed** | Features that were removed |
| **Security** | Security-related fixes |

- Each entry is one concise sentence describing the change.
- Reference related permission nodes, commands, or config keys where helpful.
- Never delete old entries — the changelog is append-only.

**Workflow:**

- **Do NOT** update the changelog on every commit or push.
- **Do** update the changelog when creating a new release (new version number).
- During development, optionally collect notable changes in the `[Unreleased]` section as they happen.
- At release time:
  1. Move all `[Unreleased]` entries under a new version heading `## [X.Y.Z] - YYYY-MM-DD`.
  2. Add a fresh empty `[Unreleased]` section at the top.
  3. Commit: `chore: release vX.Y.Z`.
  4. Tag: `vX.Y.Z`.
- If `[Unreleased]` was not maintained during development, review the Git history before release and summarize the relevant changes.

---

### Template: commands.md

```markdown
# Commands

## Overview

| Command | Description | Permission |
|---|---|---|
| `/role assign <player> <role>` | Assign a role to a player. | `dawntale.utils.role.assign` |
| `/role remove <player> <role>` | Remove a role from a player. | `dawntale.utils.role.remove` |
| `/role list` | List all available roles. | `dawntale.utils.role.list` |

---

## Detailed Command Reference

### /role assign

Assigns a role to a player.

**Usage:** `/role assign <player> <role>`

**Arguments:**

| Argument | Type | Required | Description |
|---|---|---|---|
| `player` | Player | Yes | The target player. |
| `role` | String | Yes | The role name to assign. |

**Permission:** `dawntale.utils.role.assign`

**Examples:**
- `/role assign Steve Admin`
- `/role assign Notch Builder`
```

**Structure rules for commands.md:**

- Always start with an **Overview table** listing all commands at a glance.
- Then a **Detailed Command Reference** section with one subsection per command.
- Each command subsection must include: Usage, Arguments table, Permission, and Examples.
- Group related commands under a shared parent heading (e.g., all `/role` subcommands together).

---

### Template: configuration.md

```markdown
# Configuration

## File Locations

All configuration files are generated in `mods/<PluginName>/` on first startup.

| File | Purpose |
|---|---|
| `config.json` | General plugin settings. |
| `JoinMsg.json` | Welcome messages and MOTD. |

---

## config.json

| Key | Type | Default | Description |
|---|---|---|---|
| `debug` | boolean | `false` | Enable debug logging. |
| `language` | string | `"en"` | Default server language. |
| `maxPlayers` | int | `50` | Maximum player count. |

**Example:**

```json
{
  "debug": false,
  "language": "en",
  "maxPlayers": 50
}
```

---

## JoinMsg.json

| Key | Type | Default | Description |
|---|---|---|---|
| `enabled` | boolean | `true` | Enable join messages. |
| `message` | string | `"Welcome, {player}!"` | The welcome message template. |

**Placeholders:**

| Placeholder | Value |
|---|---|
| `{player}` | The player's username. |
| `{online}` | Current online player count. |
```

**Structure rules for configuration.md:**

- Start with a **File Locations** overview table.
- Then one section per config file.
- Each section must include: a table of all keys with Type, Default, and Description.
- Include a JSON example showing the default config.
- Document all placeholders if the config supports them.

---

### Template: events.md

```markdown
# Events

## Overview

| Event | Cancellable | Description |
|---|---|---|
| `PlayerBanEvent` | Yes | Fired when a player is banned. |
| `RoleChangeEvent` | No | Fired when a player's role changes. |

---

## PlayerBanEvent

Fired when a player is banned from the server.

| Field | Type | Description |
|---|---|---|
| `target` | `PlayerRef` | The banned player. |
| `reason` | `String` | Ban reason. |
| `duration` | `Duration` | Ban duration (null if permanent). |
| `issuer` | `PlayerRef` | The player who issued the ban. |

**Cancellable:** Yes — cancelling prevents the ban from being applied.
```

**Structure rules for events.md:**

- Start with an **Overview table** of all events.
- Then one section per event.
- Each section must include: description, fields table, and cancellable behavior.

---

### Template: database.md

```markdown
# Database

## Tables

| Table | Purpose |
|---|---|
| `players` | Player profiles and metadata. |
| `bans` | Active and expired bans. |
| `roles` | Role definitions and hierarchy. |

---

## players

| Column | Type | Nullable | Description |
|---|---|---|---|
| `uuid` | `UUID` | No | Player UUID (primary key). |
| `username` | `VARCHAR(32)` | No | Last known username. |
| `first_join` | `TIMESTAMP` | No | First join date. |
| `last_seen` | `TIMESTAMP` | No | Last seen date. |
```

**Structure rules for database.md:**

- Start with a **Tables** overview.
- Then one section per table with full column definitions.
- Note primary keys, foreign keys, and indexes.

---

### Template: ui.md

```markdown
# UI Pages

## Overview

| Page | File | Description |
|---|---|---|
| Custom Inventory | `CustomInventory.ui` | Player inventory replacement. |
| Shop Page | `ShopPage.ui` | In-game item shop. |

All UI files are located in the Dawntale Asset Pack:
`Dawntale_Assets/Common/UI/Custom/DawntaleUI/`

---

## Custom Inventory

**File:** `CustomInventory.ui`

**Events:**

| Event ID | Trigger | Description |
|---|---|---|
| `slot_click` | Player clicks a slot | Returns slot index and item data. |
| `close` | Page is closed | Cleanup event. |
```

**Structure rules for ui.md:**

- Start with an **Overview table** of all pages.
- Reference the asset pack location.
- Then one section per UI page with file reference, events, and behavior.

---

---

## Part 3: General Rules

- READMEs are kept **concise** — no tutorials, no quickstart, no code examples. That goes in `docs/`.
- All text in **English**.
- README section order must be followed exactly as defined in Part 1.
- Omit README sections that do not apply (e.g., no Credits if solo project), but never reorder.
- Use the emoji headers as shown (⚖️, ✨, 📦, 📁, 📚, ⚙️, 🤝, 👤).
- Doc files follow their respective templates from Part 2.
- API documentation belongs in the DawntaleAPI repo, not in individual plugins.
- Only create doc files that are relevant to the project.
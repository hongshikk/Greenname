# **GreenName – Green Chat Name Plugin**

---

## Introduction

**GreenName** is a lightweight Paper plugin that allows admins to give players a **green chat name** with an **expiration date**. Players can toggle the effect on/off using `/greenname on/off`. All data is stored by **player name**, making it perfect for **offline-mode servers**. The plugin comes with **12 built-in languages** and allows admins to customize the chat format.

---

[![github](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/github_64h.png)](https://github.com/hongshikk/Greenname)

---

## Features

- ✅ Set a green chat name for players (color customizable, default green)
- ✅ Configurable **expiration time** (in days), auto-expires after expiry
- ✅ Players can toggle the effect on/off using `/greenname on/off`
- ✅ Data stored by **player name (lowercase)** – ideal for offline-mode servers
- ✅ **12 languages** supported (Chinese Simplified/Traditional, English, Russian, German, French, Spanish, Portuguese, Japanese, Korean, Indonesian, Arabic)
- ✅ Admins can customize **chat format** (supports color code `&`)
- ✅ All messages can be freely edited via language files
- ✅ Lightweight, no extra dependencies
- ✅ Compatible with Paper 1.13 ~ 1.21+ (Java 17+)

---

## Commands & Permissions

| Command | Description | Permission | Default |
|---------|-------------|------------|---------|
| `/setgreen <player> <days>` | Set a player's green name duration (days) | `greennames.admin` | OP |
| `/greenname on` | Enable your own green name (Only players with an active greenname) | `greennames.use` | true (all) |
| `/greenname off` | Disable your own green name (Only players with an active greenname) | `greennames.use` | true (all) |

---

## Configuration

`config.yml`

```yml

    # language name in its own script
    language: cn

    # Chat format, '&' is color code, must contain two %s
    # Example: "&a<%s>&d %s"  → green <playername> light purple message
    chat-format: "&a<%s>&d %s"

```

## Language Files

Language files are stored in `plugins/Greenname/language/` with naming `messages_<lang>.yml`. Supports color codes `&0-9 &a-f &k-o &r`. Admins can freely edit all messages.

## Supported Languages

| Language Code | Language Name |
|---------------|---------------|
| `zh` | 简体中文 |
| `zh_tw` | 繁體中文 |
| `en` | English |
| `ru` | Русский |
| `de` | Deutsch |
| `fr` | Français |
| `es` | Español |
| `pt` | Português |
| `ja` | 日本語 |
| `ko` | 한국어 |
| `id` | Bahasa Indonesia |
| `ar` | العربية |

---

## Data Storage

All player data is saved in `plugins/Greenname/data.yml` with the following structure:

```yml

    expiry:
      player1: 1735689600000   # expiration timestamp (milliseconds)
      player2: 1735776000000
    toggle:
      player1: true
      player2: false

```

## Installation

1. Download `Greenname.jar`
2. Place it in your server's `plugins/` folder
3. Restart the server (or use `/reload`)
4. Edit `config.yml` and language files (optional)
5. Use `/setgreen <player> <days>` to give a player a green name

---

## Compatibility

- **Server:** 1.13~1.21+ (All plugin loader support)
- **Java:** 17 or higher (recommended 21)
- **Dependencies:** None

---

## License

This project is licensed under the **MIT License**. You are free to use, modify, and distribute it.

---

## Support

If you have any issues or suggestions, please submit an Issue on GitHub or contact the author.

---

**Thanks for using GreenName!**
